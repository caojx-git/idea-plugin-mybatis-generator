package com.caojx.idea.plugin.common.pojo.model;

import java.util.List;

/**
 * 数据源
 *
 * @author caojx
 * @date 2022/4/10 4:00 PM
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
