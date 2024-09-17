package com.caojx.idea.plugin.common.properties;

import com.caojx.idea.plugin.common.pojo.DatabaseWithOutPwd;

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
     * 模块名称
     */
    private String moduleName;

    /**
     * 模块路径
     */
    private String modulePath;

    /**
     * 基础包
     */
    private String basePackage;

    /**
     * 基础路径
     */
    private String basePath;

    /**
     * entity 相对包
     */
    private String entityRelativePackage;

    /**
     * 数据库列表
     * 1.3.7版本之后，数据库配置将会保存到 user.home/.myBatisCodeGenerator/ext-config.json 文件中
     */
    private List<DatabaseWithOutPwd> databases = new ArrayList<>();

    /**
     * 选择的数据库
     */
    private String databaseComboBoxValue;

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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModulePath() {
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getEntityRelativePackage() {
        return entityRelativePackage;
    }

    public void setEntityRelativePackage(String entityRelativePackage) {
        this.entityRelativePackage = entityRelativePackage;
    }

    public List<DatabaseWithOutPwd> getDatabases() {
        return databases;
    }

    public void setDatabases(List<DatabaseWithOutPwd> databases) {
        this.databases = databases;
    }

    public String getDatabaseComboBoxValue() {
        return databaseComboBoxValue;
    }

    public void setDatabaseComboBoxValue(String databaseComboBoxValue) {
        this.databaseComboBoxValue = databaseComboBoxValue;
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
