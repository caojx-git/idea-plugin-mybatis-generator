package com.caojx.idea.plugin.ui;

import com.caojx.idea.plugin.infrastructure.persistent.PersistentState;
import com.caojx.idea.plugin.infrastructure.persistent.PersistentStateService;
import com.caojx.idea.plugin.infrastructure.po.Database;
import com.caojx.idea.plugin.infrastructure.utils.MySQLDBHelper;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Iterator;
import java.util.Objects;

public class EditDatabaseSettingUI extends DialogWrapper {
    private JPanel mainPanel;
    private JComboBox databaseType;
    private JTextField host;
    private JTextField port;
    private JTextField databaseName;
    private JTextField userName;
    private JPasswordField password;
    private JButton saveBtn;
    private JButton testBtn;

    private Project project;
    private Database database;

    private DataSourcesSettingUI dataSourcesSettingUI;

    public EditDatabaseSettingUI(Project project, Database database, DataSourcesSettingUI dataSourcesSettingUI) {
        super(true);
        init();

        this.project = project;
        this.database = database;
        this.dataSourcesSettingUI = dataSourcesSettingUI;

        this.databaseType.addItem("MySQL");
        this.databaseType.addItem("Oracle");

        // 初始化数据
        if (Objects.nonNull(database)) {
            databaseType.setSelectedItem(database.getDatabaseName());
            host.setText(database.getHost());
            port.setText(String.valueOf(database.getPort()));
            databaseName.setText(database.getDatabaseName());
            userName.setText(database.getUserName());
            password.setText(database.getPassword());
        }

        // 测试连接
        testBtn.addActionListener(e -> {
            if (!testConnectionDB()) {
                Messages.showWarningDialog(this.project, "数据库连接错误,请检查配置.", "Warning");
            } else {
                Messages.showInfoMessage(this.project, "Connection successful!", "Info");
            }
        });

        saveBtn.addActionListener(e -> {
            if (Objects.isNull(this.database)) {
                this.database = new Database();
            }

            // 连接数据库测试
            if (!testConnectionDB()) {
                Messages.showWarningDialog(this.project, "数据库连接错误,请检查配置.", "Warning");
                return;
            }

            // 保存db
            this.database.setDatabaseType(databaseType.getSelectedItem().toString());
            this.database.setHost(host.getText());
            this.database.setPort(Integer.valueOf(port.getText()));
            this.database.setDatabaseName(databaseName.getText());
            this.database.setUserName(userName.getText());
            this.database.setPassword(password.getText());

            // 持久化
            PersistentState persistentState = PersistentStateService.getInstance(this.project).getState();
            Iterator<Database> iterator = persistentState.getDatabases().iterator();
            while (iterator.hasNext()) {
                Database next = iterator.next();
                if (next.getDatabaseName().equals(this.database.getDatabaseName())) {
                    iterator.remove();
                }
            }
            persistentState.getDatabases().add(this.database);

            // 刷新列表
            dataSourcesSettingUI.refreshDatabaseTable(persistentState.getDatabases());

            // 隐藏
            EditDatabaseSettingUI.this.dispose();
        });
    }

    /**
     * 测试连接数据库
     */
    private boolean testConnectionDB() {
        try {
            MySQLDBHelper dbHelper = new MySQLDBHelper(host.getText(), Integer.valueOf(port.getText()), userName.getText(), password.getText(), databaseName.getText());
            dbHelper.testDatabase();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected @Nullable
    JComponent createCenterPanel() {
        return mainPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        return null;
    }
}
