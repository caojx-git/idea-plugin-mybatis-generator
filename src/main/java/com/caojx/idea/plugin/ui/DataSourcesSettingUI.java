package com.caojx.idea.plugin.ui;

import com.caojx.idea.plugin.common.pojo.DatabaseWithOutPwd;
import com.caojx.idea.plugin.common.utils.MyMessages;
import com.caojx.idea.plugin.persistent.PersistentExtConfig;
import com.caojx.idea.plugin.persistent.PersistentStateService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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

    private final Project project;

    /**
     * 代码生成配置UI
     */
    private final GeneratorSettingUI generatorSettingUI;

    /**
     * 数据库列表
     */
    private List<DatabaseWithOutPwd> databases;

    /**
     * 表头
     */
    private static final String[] TABLE_COLUMN_NAME = {"database", "host", "port", "type"};

    /**
     * 表数据模型
     */
    public static DefaultTableModel TABLE_MODEL = new DefaultTableModel(null, TABLE_COLUMN_NAME);

    /**
     * 选中的行
     */
    private int selectedRow = -1;

    private final PersistentStateService persistentStateService;

    public DataSourcesSettingUI(@NotNull Project project, @NotNull GeneratorSettingUI generatorSettingUI) {
        super(true);
        init();

        this.project = project;
        this.generatorSettingUI = generatorSettingUI;
        this.persistentStateService = PersistentStateService.getInstance(project);

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
//        PersistentState persistentState = persistentStateService.getState();
//        CommonProperties commonProperties = persistentState.getGeneratorProperties().getCommonProperties();
//        databases = commonProperties.getDatabases();
        databases = PersistentExtConfig.loadDatabase();

        // 初始化表数据
        dataSourcesTable.setModel(TABLE_MODEL);
        refreshDatabaseTable(databases);
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
                selectedRow = dataSourcesTable.getSelectedRow();
            }
        });

        // 删除数据库
        deleteBtn.addActionListener(e -> {
            if (selectedRow == -1) {
                MyMessages.showWarningDialog(project, "请选择需要删除的数据库", "Warning");
                return;
            }

            // 清除密码
            DatabaseWithOutPwd deleteDatabase = databases.get(selectedRow);
            persistentStateService.clearPassword(deleteDatabase.getIdentifierName());

            // 从数组列表中移除
            databases.remove(selectedRow);
            selectedRow = -1;

            // 更新数据库配置
            PersistentExtConfig.saveDatabases(databases);

            // 刷新
            refreshDatabaseTable(databases);
            generatorSettingUI.refreshDatabaseComBox(databases);
        });

        // 编辑数据库
        editBtn.addActionListener(e -> {
            if (selectedRow == -1) {
                MyMessages.showWarningDialog(project, "请选择需要编辑的数据库", "Warning");
                return;
            }

            DatabaseWithOutPwd database = databases.get(selectedRow);
            EditDatabaseSettingUI editDatabaseSettingUI = new EditDatabaseSettingUI(project, database, this);
            editDatabaseSettingUI.show();
            selectedRow = -1;
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
    public void refreshDatabaseTable(List<DatabaseWithOutPwd> databases) {
        // 刷新数据库表
        TABLE_MODEL.setDataVector(null, TABLE_COLUMN_NAME);
        databases.forEach(database -> {
            Object[] row = new Object[4];
            row[0] = database.getDatabaseName();
            row[1] = database.getHost();
            row[2] = database.getPort();
            row[3] = database.getDatabaseType();
            TABLE_MODEL.addRow(row);
        });

        // 刷新数据库选择下拉框
        generatorSettingUI.refreshDatabaseComBox(databases);
    }
}
