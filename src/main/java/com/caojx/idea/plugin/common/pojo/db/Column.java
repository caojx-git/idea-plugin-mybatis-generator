package com.caojx.idea.plugin.common.pojo.db;

public class Column {

    /**
     * 列名
     */
    private String name;

    /**
     * 列注释
     */
    private String comment;

    /**
     * 列类型
     */
    private int type;


    /**
     * 是否为主键
     */
    private boolean id;

    public Column(String name, String comment, int type) {
        this.name = name;
        this.comment = comment;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }
}
