package com.caojx.idea.plugin.infrastructure.utils;

import com.caojx.idea.plugin.common.utils.MySQLDBHelper;
import com.caojx.idea.plugin.common.pojo.Database;
import com.caojx.idea.plugin.common.pojo.TableInfo;
import org.junit.Test;

import java.util.List;

public class MySQLDBHelperTest {

    @Test
    public void testGetTableInfo(){
        Database database = new Database();
        database.setDatabaseType("mysql");
        database.setHost("127.0.0.1");
        database.setPort(3306);
        database.setDatabaseName("yeecode");
        database.setUserName("root");
        database.setPassword("root");

        MySQLDBHelper mySQLDBHelper = new MySQLDBHelper(database);

        TableInfo tableInfo = mySQLDBHelper.getTableInfo("task");
        System.out.println(tableInfo);

        List<String> allTableName = mySQLDBHelper.getAllTableName(database.getDatabaseName());
        System.out.println(allTableName);

        String s = mySQLDBHelper.testDatabase();
        System.out.println(s);
    }

}
