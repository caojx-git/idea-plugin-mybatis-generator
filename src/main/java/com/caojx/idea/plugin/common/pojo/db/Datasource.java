package com.caojx.idea.plugin.common.pojo.db;

import java.util.List;

/**
 * 数据源
 */
public class Datasource {

    /**
     * 数据库列表
     */
    List<Database> databases;

    public List<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Database> databases) {
        this.databases = databases;
    }
}
