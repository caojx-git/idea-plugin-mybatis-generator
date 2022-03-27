package com.caojx.idea.plugin.infrastructure.utils;

import com.caojx.idea.plugin.infrastructure.po.Column;
import com.caojx.idea.plugin.infrastructure.po.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * MySQL数据库工具类
 */
public class MySQLDBHelper {

    /**
     * host
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库名
     */
    private String databaseName;

    /**
     * 数据库连接属性
     */
    private Properties properties;

    /**
     * 构造器
     *
     * @param host         ip
     * @param port         端口
     * @param username     用户名
     * @param password     密码
     * @param databaseName 数据库名
     */
    public MySQLDBHelper(String host, Integer port, String username, String password, String databaseName) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        properties = new Properties();
        properties.put("user", this.username);
        properties.put("password", this.password);
        properties.setProperty("remarks", "true");
        properties.put("useInformationSchema", "true");
    }


    /**
     * 获取连接对象
     *
     * @return 连接对象
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false", properties);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取指定数据库连接对象
     *
     * @return 连接对象
     */
    private Connection getConnection(String database) {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + database + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false", properties);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 关闭连接
     *
     * @param conn 连接对象
     */
    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }
    }


    /**
     * 获取数据库中所有的表名
     *
     * @param databaseName 数据库名
     * @return 表名列表
     */
    public List<String> getAllTableName(String databaseName) {
        return getTableName(databaseName, "%");
    }

    /**
     * 获取数据库表明
     *
     * @param databaseName     数据库名
     * @param tableNamePattern 表明表达式
     * @return 表名列表
     */
    public List<String> getTableName(String databaseName, String tableNamePattern) {
        this.databaseName = databaseName;
        Connection connection = this.getConnection(databaseName);
        DatabaseMetaData metaData = null;
        try {
            metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, tableNamePattern, new String[]{"TABLE"});
            List<String> ls = new ArrayList<>();
            while (resultSet.next()) {
                String s = resultSet.getString("TABLE_NAME");
                ls.add(s);
            }
            return ls;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * 获取表信息
     *
     * @param tableName 表名
     * @return 表信息
     */
    public Table getTable(String tableName) {
        Connection conn = getConnection(this.databaseName);
        try {
            DatabaseMetaData metaData = conn.getMetaData();

            ResultSet rs = metaData.getTables(null, "", tableName, new String[]{"TABLE"});
            Table table = null;
            if (rs.next()) {
                table = new Table(tableName, getAllColumn(tableName, conn), rs.getString("REMARKS"));
            }
            return table;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

    public List<Column> getAllColumn(String tableName, Connection connection) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
            String primaryKey = null;
            while (primaryKeys.next()) {
                primaryKey = primaryKeys.getString("COLUMN_NAME");
            }

            ResultSet rs = metaData.getColumns(null, null, tableName, null);
            List<Column> columns = new ArrayList<>();
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                Column column = new Column(rs.getString("REMARKS"), columnName, rs.getInt("DATA_TYPE"));
                if (columnName.endsWith(primaryKey)) {
                    column.setId(true);
                }
                columns.add(column);
            }
            return columns;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 测试数据库连接
     *
     * @return 连接结果
     */
    public String testDatabase() {
        Connection conn = getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT VERSION() AS MYSQL_VERSION");
            ResultSet resultSet = null;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("MYSQL_VERSION");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return "Err";
    }

}
