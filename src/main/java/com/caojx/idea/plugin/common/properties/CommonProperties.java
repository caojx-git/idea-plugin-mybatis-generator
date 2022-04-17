package com.caojx.idea.plugin.common.properties;

import com.caojx.idea.plugin.common.pojo.model.Database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共配置属性
 *
 * @author caojx
 * @date 2022/4/10 12:20 PM
 */
public class CommonProperties implements Serializable {


    /**
     * 作者
     */
    private String author;

    /**
     * 项目路径
     */
    private String projectPath;

    /**
     * 数据库列表
     */
    private List<Database> databases = new ArrayList<>();

    /**
     * 选择的数据库
     */
    private String databaseComboBoxValue;

    /**
     * 表格式
     */
    private String tableNameRegex;

    /**
     * 框架类型列表
     */
    private List<String> frameworkTypeComboBoxValues;

    /**
     * 选择的框架类型
     */
    private String frameworkTypeComboBoxValue;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public List<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Database> databases) {
        this.databases = databases;
    }

    public String getDatabaseComboBoxValue() {
        return databaseComboBoxValue;
    }

    public void setDatabaseComboBoxValue(String databaseComboBoxValue) {
        this.databaseComboBoxValue = databaseComboBoxValue;
    }

    public String getTableNameRegex() {
        return tableNameRegex;
    }

    public void setTableNameRegex(String tableNameRegex) {
        this.tableNameRegex = tableNameRegex;
    }

    public List<String> getFrameworkTypeComboBoxValues() {
        return frameworkTypeComboBoxValues;
    }

    public void setFrameworkTypeComboBoxValues(List<String> frameworkTypeComboBoxValues) {
        this.frameworkTypeComboBoxValues = frameworkTypeComboBoxValues;
    }

    public String getFrameworkTypeComboBoxValue() {
        return frameworkTypeComboBoxValue;
    }

    public void setFrameworkTypeComboBoxValue(String frameworkTypeComboBoxValue) {
        this.frameworkTypeComboBoxValue = frameworkTypeComboBoxValue;
    }
}
