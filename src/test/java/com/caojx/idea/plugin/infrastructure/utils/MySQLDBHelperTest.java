package com.caojx.idea.plugin.infrastructure.utils;

import com.caojx.idea.plugin.common.pojo.DatabaseWithPwd;
import com.caojx.idea.plugin.common.pojo.TableInfo;
import com.caojx.idea.plugin.common.utils.MySQLDBHelper;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class MySQLDBHelperTest {

    @Test
    public void testGetTableInfo(){
        DatabaseWithPwd database = new DatabaseWithPwd();
        database.setDatabaseType("mysql");
        database.setHost("127.0.0.1");
        database.setPort(3306);
        database.setDatabaseName("sakila");
        database.setUserName("root");
        database.setPassword("root");

        MySQLDBHelper mySQLDBHelper = new MySQLDBHelper(database, new HashMap<>(4));

        TableInfo tableInfo = mySQLDBHelper.getTableInfo("task");
        System.out.println(tableInfo);

        List<String> allTableName = mySQLDBHelper.getAllTableName();
        System.out.println(allTableName);

        String s = mySQLDBHelper.testDatabase();
        System.out.println(s);
    }

}
