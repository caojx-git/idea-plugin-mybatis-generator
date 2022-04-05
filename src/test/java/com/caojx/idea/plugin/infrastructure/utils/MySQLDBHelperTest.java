package com.caojx.idea.plugin.infrastructure.utils;

import com.caojx.idea.plugin.common.utils.MySQLDBHelper;
import com.caojx.idea.plugin.common.pojo.db.Database;
import com.caojx.idea.plugin.common.pojo.db.Table;

import java.util.List;

public class MySQLDBHelperTest {


    public static void main(String[] args) {
        Database database = new Database();
        database.setDatabaseType("mysql");
        database.setHost("127.0.0.1");
        database.setPort(3306);
        database.setDatabaseName("yeecode");
        database.setUserName("root");
        database.setPassword("root");

        MySQLDBHelper mySQLDBHelper = new MySQLDBHelper(database);

        Table yeecode = mySQLDBHelper.getTable("task");
        System.out.println(yeecode);

        List<String> allTableName = mySQLDBHelper.getAllTableName(database.getDatabaseName());
        System.out.println(allTableName);

        String s = mySQLDBHelper.testDatabase();
        System.out.println(s);

    }
}
