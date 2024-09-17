package com.caojx.idea.plugin.ui;

import com.caojx.idea.plugin.common.enums.DataBaseTypeEnum;
import com.caojx.idea.plugin.common.pojo.DatabaseWithOutPwd;
import com.caojx.idea.plugin.common.pojo.DatabaseWithPwd;
import com.caojx.idea.plugin.common.utils.DatabaseConvert;
import com.caojx.idea.plugin.common.utils.MyMessages;
import com.caojx.idea.plugin.common.utils.MySQLDBHelper;
import com.caojx.idea.plugin.persistent.PersistentExtConfig;
import com.caojx.idea.plugin.persistent.PersistentStateService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 编辑数据库
 *
 * @author caojx
 * @date 2022/4/10 10:00 AM
 */
public class EditDatabaseSettingUI extends DialogWrapper {
    private JPanel mainPanel;
    private JComboBox<String> databaseTypeComboBox;
    private JTextField hostTf;
    private JTextField portTf;
    private JTextField databaseNameTf;
    private JTextField userNameTf;
    private JPasswordField passwordTf;
    private JButton saveBtn;
    private JButton testBtn;
    private JTextField urlTf;

    private final Project project;

    /**
     * 数据源UI配置类
     */
    private final DataSourcesSettingUI dataSourcesSettingUI;

    /**
     * 编辑的数据库
     */
    private final DatabaseWithOutPwd editDatabase;

    /**
     * 数据库列表
     */
    private List<DatabaseWithOutPwd> databases;


    private final PersistentStateService persistentStateService;


    public EditDatabaseSettingUI(@NotNull Project project, DatabaseWithOutPwd editDatabase, @NotNull DataSourcesSettingUI dataSourcesSettingUI) {
        super(true);
        init();

        this.project = project;
        this.editDatabase = editDatabase;
        this.dataSourcesSettingUI = dataSourcesSettingUI;
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

        // 设置数据库类型下拉框
        DataBaseTypeEnum.getDatabaseTypes().forEach(databaseType -> databaseTypeComboBox.addItem(databaseType));

        // 初始化数据
        if (Objects.nonNull(editDatabase)) {
            databaseTypeComboBox.setSelectedItem(StringUtils.trim(editDatabase.getDatabaseType()));
            hostTf.setText(StringUtils.trim(editDatabase.getHost()));
            portTf.setText(String.valueOf(editDatabase.getPort()));
            databaseNameTf.setText(StringUtils.trim(editDatabase.getDatabaseName()));
            userNameTf.setText(StringUtils.trim(editDatabase.getUserName()));
//            passwordTf.setText(editDatabase.getPassword());

            String password = persistentStateService.getPassword(editDatabase.getIdentifierName());
            passwordTf.setText(password);

            if (StringUtils.isNotBlank(editDatabase.getUrl())) {
                urlTf.setText(editDatabase.getUrl());
            } else {
                urlTf.setText(buildURL(hostTf.getText(), portTf.getText(), databaseNameTf.getText(), ""));
            }
        }
    }

    /**
     * 构建数据库连接url
     *
     * @param host         host
     * @param port         port
     * @param dataBaseName 数据库名称
     * @return 数据库连接url
     */
    private String buildURL(String host, String port, String dataBaseName, String propertiesStr) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dataBaseName;
        if (StringUtils.isNotBlank(propertiesStr)) {
            return url + "?" + propertiesStr;
        }
        return url;
    }

    /**
     * 例如："jdbc:mysql://localhost:3306/xxxx?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false"
     * 转为数据库配置信息
     *
     * @param url      url
     * @param userName 用户名
     * @return 数据库配置信息
     */
    private DatabaseWithOutPwd convert2DatabaseWithOutPwd(String url, String userName) {
        if (StringUtils.isBlank(url)) {
//            throw new RuntimeException("url为空");
            return null;
        }

        String[] split = url.split("//");
        if (split.length < 2) {
//            throw new RuntimeException("url格式错误");
            return null;
        }
        String newUrl = split[1];

        String[] split1 = newUrl.split(":");
        String host = split1[0];
        Integer port = null;
        String databaseName = "";
        if (split1.length > 1) {
            String[] split2 = split1[1].split("/");
            try {
                port = Integer.valueOf(split2[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (split2.length > 1) {
                databaseName = split2[1].split("\\?")[0];
            }
        }

        // 属性
        DatabaseWithOutPwd databaseWithOutPwd = new DatabaseWithOutPwd();
        databaseWithOutPwd.setDatabaseType(DataBaseTypeEnum.MySQL.name());
        databaseWithOutPwd.setHost(host);
        databaseWithOutPwd.setPort(port);
        databaseWithOutPwd.setDatabaseName(databaseName);
        databaseWithOutPwd.setUrl(url);
        databaseWithOutPwd.setUserName(userName);
        return databaseWithOutPwd;
    }

    /**
     * 提取属性信息字符串
     *
     * @param url jdbc url
     * @return 属性信息
     */
    private String extractPropertiesStr(String url) {
        if (StringUtils.isBlank(url)) {
            return "";
        }

        // 属性
        String[] propertiesSplit = url.split("\\?");
        if (propertiesSplit.length > 1) {
            return propertiesSplit[1];
        }
        return "";
    }

    /**
     * 创建事件监听器
     *
     * @param project 项目
     */
    private void initActionListener(Project project) {
        hostTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String propertiesStr = extractPropertiesStr(urlTf.getText());
                urlTf.setText(buildURL(hostTf.getText(), portTf.getText(), databaseNameTf.getText(), propertiesStr));
            }
        });

        portTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String propertiesStr = extractPropertiesStr(urlTf.getText());
                urlTf.setText(buildURL(hostTf.getText(), portTf.getText(), databaseNameTf.getText(), propertiesStr));
            }
        });

        databaseNameTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String propertiesStr = extractPropertiesStr(urlTf.getText());
                urlTf.setText(buildURL(hostTf.getText(), portTf.getText(), databaseNameTf.getText(), propertiesStr));
            }
        });

        urlTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                DatabaseWithOutPwd databaseWithOutPwd = convert2DatabaseWithOutPwd(urlTf.getText(), userNameTf.getText());
                if (Objects.isNull(databaseWithOutPwd)) {
                    databaseTypeComboBox.setSelectedItem(DataBaseTypeEnum.MySQL.name());
                    hostTf.setText("");
                    portTf.setText("");
                    databaseNameTf.setText("");
                    return;
                }
                databaseTypeComboBox.setSelectedItem(StringUtils.isNotBlank(databaseWithOutPwd.getDatabaseType()) ? StringUtils.trim(databaseWithOutPwd.getDatabaseType()) : DataBaseTypeEnum.MySQL.name());
                hostTf.setText(StringUtils.isNotBlank(databaseWithOutPwd.getHost()) ? StringUtils.trim(databaseWithOutPwd.getHost()) : "");
                portTf.setText(Objects.nonNull(databaseWithOutPwd.getPort()) ? String.valueOf(databaseWithOutPwd.getPort()) : "");
                databaseNameTf.setText(StringUtils.isNotBlank(databaseWithOutPwd.getDatabaseName()) ? StringUtils.trim(databaseWithOutPwd.getDatabaseName()) : "");
            }
        });


        // 测试连接
        testBtn.addActionListener(e -> {
            DatabaseWithPwd formDatabase = getFormDatabase();
            if (Objects.isNull(formDatabase) || !testConnectionDB(formDatabase)) {
                MyMessages.showWarningDialog(project, "数据库连接错误，请检查配置.", "Warning");
            } else {
                MyMessages.showInfoMessage(project, "Connection successful!", "Info");
            }
        });

        // 保存
        saveBtn.addActionListener(e -> {
            DatabaseWithPwd formDatabase = getFormDatabase();

            // 连接数据库测试
            if (!testConnectionDB(formDatabase)) {
                MyMessages.showWarningDialog(project, "数据库连接错误，请检查配置.", "Warning");
                return;
            }

            // 移除相同的数据库
            databases.removeIf(next -> next.getIdentifierName().equals(formDatabase.getIdentifierName()));

            // 存储密码
            persistentStateService.setPassword(formDatabase.getIdentifierName(), formDatabase.getPassword());

            // 添加到列表
            DatabaseWithOutPwd database = DatabaseConvert.convertDatabase(formDatabase);
            databases.add(database);

            // 保存数据库配置
            PersistentExtConfig.saveDatabases(databases);

            // 刷新列表
            dataSourcesSettingUI.refreshDatabaseTable(databases);

            // 隐藏
            EditDatabaseSettingUI.this.dispose();
        });
    }

    /**
     * 获取表单数据库配置信息
     *
     * @return 数据库
     */
    private DatabaseWithPwd getFormDatabase() {
        DatabaseWithOutPwd databaseWithOutPwd = convert2DatabaseWithOutPwd(urlTf.getText(), StringUtils.trim(userNameTf.getText()));
        if (Objects.isNull(databaseWithOutPwd)) {
            return null;
        }
        DatabaseWithPwd database = new DatabaseWithPwd();
        database.setDatabaseType(StringUtils.isNotBlank(databaseWithOutPwd.getDatabaseType()) ? databaseWithOutPwd.getDatabaseType() : DataBaseTypeEnum.MySQL.name());
        database.setHost(databaseWithOutPwd.getHost());
        database.setPort(databaseWithOutPwd.getPort());
        database.setDatabaseName(databaseWithOutPwd.getDatabaseName());
        database.setUserName(databaseWithOutPwd.getUserName());
        database.setUrl(databaseWithOutPwd.getUrl());
        database.setPassword(passwordTf.getText());
        return database;
    }

    /**
     * 测试连接数据库
     *
     * @param databaseWithPwd 数据库
     */
    private boolean testConnectionDB(DatabaseWithPwd databaseWithPwd) {
        try {
            MySQLDBHelper dbHelper = new MySQLDBHelper(databaseWithPwd, new HashMap<>(4));
            dbHelper.testDatabase();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
