package com.caojx.idea.plugin.common.pojo;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据库
 *
 * @author caojx
 * @date 2022/4/10 4:00 PM
 */
public class Database {

    /**
     * 数据库类型
     */
    private String databaseType;

    /**
     * 主机
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 数据库
     */
    private String databaseName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 显示的数据库名称
     */
    private String showDatabaseName;

    public Database() {
    }

    public Database(String databaseType, String host, Integer port, String databaseName, String userName, String password) {
        this.databaseType = databaseType;
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
        this.showDatabaseName = databaseName + "@" + host + ":" + port + ":" + databaseType;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShowDatabaseName() {
        if (StringUtils.isBlank(showDatabaseName)) {
            showDatabaseName = databaseName + "@" + host + ":" + port + ":" + databaseType;
        }
        return showDatabaseName;
    }

    public void setShowDatabaseName(String showDatabaseName) {
        this.showDatabaseName = showDatabaseName;
    }
}
