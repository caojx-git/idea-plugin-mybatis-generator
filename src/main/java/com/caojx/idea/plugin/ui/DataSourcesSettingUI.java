package com.caojx.idea.plugin.ui;

import com.caojx.idea.plugin.infrastructure.persistent.PersistentState;
import com.caojx.idea.plugin.infrastructure.persistent.PersistentStateService;
import com.caojx.idea.plugin.infrastructure.po.Database;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.NlsContexts;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * 数据源UI配置类
 */
public class DataSourcesSettingUI extends DialogWrapper {
    private JPanel mainPanel;
    private JTable dataSourcesTable;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton editBtn;

    private Project project;
    private GeneratorSettingUI generatorSettingUI;
    private PersistentState persistentState;

    private String[] title = {"database", "host", "port", "type"};
    private DefaultTableModel defaultTableModel;


    private String selectDatabaseName;

    public DataSourcesSettingUI(Project project, GeneratorSettingUI generatorSettingUI) {
        super(true);
        init();

        this.project = project;
        this.generatorSettingUI = generatorSettingUI;

        this.persistentState = PersistentStateService.getInstance(project).getState();

        // 初始化数据库列表
        initDataSourcesTable(persistentState.getDatabases());

        // 监听表格选中的行
        dataSourcesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object selectObj = dataSourcesTable.getValueAt(dataSourcesTable.getSelectedRow(), 0);
                if (Objects.nonNull(selectObj)) {
                    selectDatabaseName = selectObj.toString();
                }
            }
        });

        // 删除数据库
        deleteBtn.addActionListener(e -> {
            if (StringUtils.isBlank(selectDatabaseName)) {
                Messages.showWarningDialog(project, "请选择需要删除的数据库", "Warning");
                return;
            }
            removeDatabaseByName(persistentState.getDatabases(), selectDatabaseName);
            selectDatabaseName = "";

            // 刷新
            refreshDatabaseTable(persistentState.getDatabases());
            generatorSettingUI.refreshDatabaseComBox(persistentState.getDatabases());

        });

        // 编辑数据库
        editBtn.addActionListener(e -> {
            if (StringUtils.isBlank(selectDatabaseName)) {
                Messages.showWarningDialog(project, "请选择需要编辑的数据库", "Warning");
                return;
            }

            Database database = getDatabaseByName(persistentState.getDatabases(), selectDatabaseName);
            EditDatabaseSettingUI editDatabaseSettingUI = new EditDatabaseSettingUI(this.project, database, this);
            editDatabaseSettingUI.show();

            selectDatabaseName = "";
        });

        // 添加数据库
        addBtn.addActionListener(e -> {
            EditDatabaseSettingUI editDatabaseSettingUI = new EditDatabaseSettingUI(this.project, null, this);
            editDatabaseSettingUI.show();
        });
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return mainPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        return null;
    }

    /**
     * 初始化数据库表
     *
     * @param databases 数据库列表
     */
    private void initDataSourcesTable(List<Database> databases) {
        Object[][] data = new Object[databases.size()][4];
        for (int i = 0; i < databases.size(); i++) {
            data[i][0] = databases.get(i).getDatabaseName();
            data[i][1] = databases.get(i).getHost();
            data[i][2] = databases.get(i).getPort();
            data[i][3] = databases.get(i).getDatabaseType();
        }
        this.defaultTableModel = new DefaultTableModel(data, title);
        dataSourcesTable.setModel(defaultTableModel);
    }

    /**
     * 刷新数据库表
     *
     * @param databases 数据库列表
     */
    public void refreshDatabaseTable(List<Database> databases) {
        // 刷新数据库表
        this.defaultTableModel.setDataVector(null, title);
        databases.forEach(database -> {
            Object[] row = new Object[4];
            row[0] = database.getDatabaseName();
            row[1] = database.getHost();
            row[2] = database.getPort();
            row[3] = database.getDatabaseType();
            defaultTableModel.addRow(row);
        });

        // 刷新数据库选择下拉框
        this.generatorSettingUI.refreshDatabaseComBox(databases);
    }

    /**
     * 根据数据库名获取数据库配置信息
     *
     * @param databases    数据库列表
     * @param databaseName 数据名
     * @return 数据库配置信息
     */
    private Database getDatabaseByName(List<Database> databases, String databaseName) {
        return databases.stream()
                .filter(database -> database.getDatabaseName().equals(databaseName))
                .findFirst()
                .orElse(null);
    }

    /**
     * 根据数据库名删除数据库配置信息
     *
     * @param databases    数据库列表
     * @param databaseName 需要删除的数据名
     */
    private void removeDatabaseByName(List<Database> databases, String databaseName) {
        Iterator<Database> iterator = databases.iterator();
        while (iterator.hasNext()) {
            Database database = iterator.next();
            if (database.getDatabaseName().equals(databaseName)) {
                iterator.remove();
            }
        }
    }


}
