package com.caojx.idea.plugin.ui;

import com.caojx.idea.plugin.common.pojo.Database;
import com.caojx.idea.plugin.common.properties.CommonProperties;
import com.caojx.idea.plugin.common.utils.MyMessages;
import com.caojx.idea.plugin.persistent.PersistentState;
import com.caojx.idea.plugin.persistent.PersistentStateService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
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
 *
 * @author caojx
 * @date 2022/4/10 10:00 AM
 */
public class DataSourcesSettingUI extends DialogWrapper {
    private JPanel mainPanel;
    private JTable dataSourcesTable;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton editBtn;

    /**
     * 代码生成配置UI
     */
    private final GeneratorSettingUI generatorSettingUI;

    /**
     * 数据库列表
     */
    private List<Database> databases;

    /**
     * 选择的数据库名
     */
    private String selectDatabaseName;

    /**
     * 表头
     */
    private final String[] title = {"database", "host", "port", "type"};

    /**
     * 表数据模型
     */
    private DefaultTableModel defaultTableModel;

    public DataSourcesSettingUI(@NotNull Project project, @NotNull GeneratorSettingUI generatorSettingUI) {
        super(true);
        init();

        this.generatorSettingUI = generatorSettingUI;

        // 初始化界面数据
        renderUIData(project);

        // 创建事件监听器
        initActionListener(project);
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
     * 渲染UI数据
     *
     * @param project 项目
     */
    private void renderUIData(Project project) {
        // 数据库列表
        PersistentState persistentState = PersistentStateService.getInstance(project).getState();
        CommonProperties commonProperties = persistentState.getGeneratorProperties().getCommonProperties();
        databases = commonProperties.getDatabases();

        // 初始化表
        Object[][] data = new Object[databases.size()][4];
        for (int i = 0; i < databases.size(); i++) {
            data[i][0] = databases.get(i).getDatabaseName();
            data[i][1] = databases.get(i).getHost();
            data[i][2] = databases.get(i).getPort();
            data[i][3] = databases.get(i).getDatabaseType();
        }
        defaultTableModel = new DefaultTableModel(data, title);
        dataSourcesTable.setModel(defaultTableModel);
    }

    /**
     * 创建事件监听器
     *
     * @param project 项目
     */
    private void initActionListener(Project project) {
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
                MyMessages.showWarningDialog(project, "请选择需要删除的数据库", "Warning");
                return;
            }
            removeDatabaseByName(databases, selectDatabaseName);
            selectDatabaseName = "";

            // 刷新
            generatorSettingUI.refreshDatabaseComBox(databases);

        });

        // 编辑数据库
        editBtn.addActionListener(e -> {
            if (StringUtils.isBlank(selectDatabaseName)) {
                MyMessages.showWarningDialog(project, "请选择需要编辑的数据库", "Warning");
                return;
            }

            Database database = getDatabaseByName(databases, selectDatabaseName);
            EditDatabaseSettingUI editDatabaseSettingUI = new EditDatabaseSettingUI(project, database, this);
            editDatabaseSettingUI.show();

            selectDatabaseName = "";
        });

        // 添加数据库
        addBtn.addActionListener(e -> {
            EditDatabaseSettingUI editDatabaseSettingUI = new EditDatabaseSettingUI(project, null, this);
            editDatabaseSettingUI.show();
        });
    }

    /**
     * 刷新数据库表
     *
     * @param databases 数据库列表
     */
    public void refreshDatabaseTable(List<Database> databases) {
        // 刷新数据库表
        defaultTableModel.setDataVector(null, title);
        databases.forEach(database -> {
            Object[] row = new Object[4];
            row[0] = database.getDatabaseName();
            row[1] = database.getHost();
            row[2] = database.getPort();
            row[3] = database.getDatabaseType();
            defaultTableModel.addRow(row);
        });

        // 刷新数据库选择下拉框
        generatorSettingUI.refreshDatabaseComBox(databases);
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
