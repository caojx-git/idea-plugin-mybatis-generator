package com.caojx.idea.plugin.ui;

import com.caojx.idea.plugin.common.pojo.persistent.PersistentState;
import com.caojx.idea.plugin.generator.PersistentStateService;
import com.caojx.idea.plugin.common.pojo.db.Database;
import com.caojx.idea.plugin.common.utils.MySQLDBHelper;
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

    public EditDatabaseSettingUI(Project project, Database database, DataSourcesSettingUI dataSourcesSettingUI) {
        super(true);
        init();

        this.project = project;
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
            Database formDatabase = getFormDatabase();

            // 连接数据库测试
            if (!testConnectionDB()) {
                Messages.showWarningDialog(this.project, "数据库连接错误,请检查配置.", "Warning");
                return;
            }

            // 持久化
            PersistentState persistentState = PersistentStateService.getInstance(this.project).getState();
            Iterator<Database> iterator = persistentState.getDatabases().iterator();
            while (iterator.hasNext()) {
                Database next = iterator.next();
                if (next.getDatabaseName().equals(formDatabase.getDatabaseName())) {
                    iterator.remove();
                }
            }
            persistentState.getDatabases().add(formDatabase);

            // 刷新列表
            dataSourcesSettingUI.refreshDatabaseTable(persistentState.getDatabases());

            // 隐藏
            EditDatabaseSettingUI.this.dispose();
        });
    }

    /**
     * 获取表单数据库配置信息
     *
     * @return 数据库
     */
    private Database getFormDatabase() {
        Database database = new Database();
        database.setDatabaseType(databaseType.getSelectedItem().toString());
        database.setHost(host.getText());
        database.setPort(Integer.valueOf(port.getText()));
        database.setDatabaseName(databaseName.getText());
        database.setUserName(userName.getText());
        database.setPassword(password.getText());
        return database;
    }

    /**
     * 测试连接数据库
     */
    private boolean testConnectionDB() {
        try {
            Database formDatabase = getFormDatabase();
            MySQLDBHelper dbHelper = new MySQLDBHelper(formDatabase);
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
