package com.caojx.idea.plugin.infrastructure.po;

import java.util.List;

public class Table {

    /**
     * 表名
     */
    private String name;

    /**
     * 列
     */
    List<Column> columns;

    /**
     * 表注释
     */
    private String comment;

    public Table(String name, List<Column> columns, String comment) {
        this.name = name;
        this.columns = columns;
        this.comment = comment;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
