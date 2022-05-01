package com.caojx.idea.plugin.common.utils;

import com.caojx.idea.plugin.common.pojo.DatabaseWithPwd;
import com.caojx.idea.plugin.common.pojo.TableField;
import com.caojx.idea.plugin.common.pojo.TableInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * MySQL数据库工具类
 *
 * @author caojx
 * @date 2022/4/10 12:20 PM
 */
public class MySQLDBHelper {

    /**
     * 数据库
     */
    private final DatabaseWithPwd databaseWithPwd;

    /**
     * 数据库连接属性
     */
    private final Properties properties;

    /**
     * 构造器
     *
     * @param databaseWithPwd 数据库
     */
    public MySQLDBHelper(DatabaseWithPwd databaseWithPwd) {
        this.databaseWithPwd = databaseWithPwd;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        properties = new Properties();
        properties.put("user", this.databaseWithPwd.getUserName());
        properties.put("password", this.databaseWithPwd.getPassword());
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
            return DriverManager.getConnection("jdbc:mysql://" + this.databaseWithPwd.getHost() + ":" + this.databaseWithPwd.getPort() + "/?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false", properties);
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
            return DriverManager.getConnection("jdbc:mysql://" + this.databaseWithPwd.getHost() + ":" + this.databaseWithPwd.getPort() + "/" + database + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false", properties);
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
        Connection connection = this.getConnection(databaseName);
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, tableNamePattern, new String[]{"TABLE"});
            List<String> tableNames = new ArrayList<>();
            while (resultSet.next()) {
                if (resultSet.getString(4) != null && resultSet.getString(4).equalsIgnoreCase("TABLE")) {
                    String tableName = resultSet.getString(3).toLowerCase();
                    tableNames.add(tableName);
                }
            }
            return tableNames;
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
    public TableInfo getTableInfo(String tableName) {
        Connection conn = getConnection(this.databaseWithPwd.getDatabaseName());
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getTables(null, "", tableName, new String[]{"TABLE"});
            if (rs.next()) {
                // 表注释
                String remarks = rs.getString("REMARKS");
                // 列列表
                List<TableField> fields = getAllTableField(tableName, conn);
                // 返回表信息
                return new TableInfo(tableName, remarks, fields);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return null;
    }

    /**
     * 获取所有的列名
     *
     * @param tableName  表名
     * @param connection 连接
     * @return 列列表
     */
    public List<TableField> getAllTableField(String tableName, Connection connection) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();

            // 主键
            String primaryKey = null;
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
            while (primaryKeys.next()) {
                primaryKey = primaryKeys.getString("COLUMN_NAME");
            }

            // 获取表中的所有列名
            ResultSet rs = metaData.getColumns(null, "%", tableName, "%");
            List<TableField> fields = new ArrayList<>();
            while (rs.next()) {
                // 列名
                String columnName = rs.getString("COLUMN_NAME");
                // 字段注释
                String remarks = rs.getString("REMARKS");
                // 字段类型
                int dataType = rs.getInt("DATA_TYPE");

                // 是否为主键
                boolean primaryKeyFlag = Objects.nonNull(primaryKey) && columnName.equals(primaryKey);

                // 构建表属性
                TableField tableField = new TableField(columnName, remarks, dataType, primaryKeyFlag);
                fields.add(tableField);
            }
            return fields;
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
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getString("MYSQL_VERSION");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return "Err";
    }

}
