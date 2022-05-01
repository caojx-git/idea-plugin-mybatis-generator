package com.caojx.idea.plugin.common.utils;

import com.caojx.idea.plugin.common.pojo.DatabaseWithOutPwd;
import com.caojx.idea.plugin.common.pojo.DatabaseWithPwd;

/**
 * 数据库转换工具类
 *
 * @author caojx
 * @date 2022/5/1 10:08 AM
 */
public class DatabaseConvert {

    public static DatabaseWithOutPwd convertDatabase(DatabaseWithPwd databaseWithPwd) {
        DatabaseWithOutPwd database = new DatabaseWithOutPwd();
        database.setDatabaseType(databaseWithPwd.getDatabaseType());
        database.setHost(databaseWithPwd.getHost());
        database.setPort(databaseWithPwd.getPort());
        database.setDatabaseName(databaseWithPwd.getDatabaseName());
        database.setUserName(databaseWithPwd.getUserName());
        database.setIdentifierName(databaseWithPwd.getIdentifierName());
        return database;
    }

    public static DatabaseWithPwd convertDatabaseWithPwd(DatabaseWithOutPwd databaseWithOutPwd, String password) {
        DatabaseWithPwd databaseWithPwd = new DatabaseWithPwd();
        databaseWithPwd.setPassword(password);
        databaseWithPwd.setDatabaseType(databaseWithOutPwd.getDatabaseType());
        databaseWithPwd.setHost(databaseWithOutPwd.getHost());
        databaseWithPwd.setPort(databaseWithOutPwd.getPort());
        databaseWithPwd.setDatabaseName(databaseWithOutPwd.getDatabaseName());
        databaseWithPwd.setUserName(databaseWithOutPwd.getUserName());
        databaseWithPwd.setIdentifierName(databaseWithOutPwd.getIdentifierName());
        return databaseWithPwd;
    }
}
