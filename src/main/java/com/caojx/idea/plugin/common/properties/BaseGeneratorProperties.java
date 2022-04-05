package com.caojx.idea.plugin.common.properties;

import org.apache.commons.lang3.StringUtils;

/**
 * MyBatis生成代码配置类
 */
public class BaseGeneratorProperties extends GeneratorMethodProperties {

    /**
     * entity生成路径
     */
    private String entityPath;

    /**
     * entity包名
     */
    private String entityPackage;

    /**
     * entity命名格式
     */
    private String entityNamePattern;

    /**
     * entity名称
     */
    private String entityName;

    /**
     * mapper生成路径
     */
    private String mapperPath;

    /**
     * mapper包名
     */
    private String mapperPackage;

    /**
     * mapper命名格式
     */
    private String mapperNamePattern;

    /**
     * mapper名称
     */
    private String mapperName;

    /**
     * mapperXml生成路径
     */
    private String mapperXmlPath;

    /**
     * mapperXml命名格式
     */
    private String mapperXmlNamePattern;

    /**
     * mapperXml名称
     */
    private String mapperXmlName;

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getEntityNamePattern() {
        return entityNamePattern;
    }

    public void setEntityNamePattern(String entityNamePattern) {
        this.entityNamePattern = entityNamePattern;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getMapperPath() {
        return mapperPath;
    }

    public void setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getMapperNamePattern() {
        return mapperNamePattern;
    }

    public void setMapperNamePattern(String mapperNamePattern) {
        this.mapperNamePattern = mapperNamePattern;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getMapperXmlPath() {
        return mapperXmlPath;
    }

    public void setMapperXmlPath(String mapperXmlPath) {
        this.mapperXmlPath = mapperXmlPath;
    }

    public String getMapperXmlNamePattern() {
        return mapperXmlNamePattern;
    }

    public void setMapperXmlNamePattern(String mapperXmlNamePattern) {
        this.mapperXmlNamePattern = mapperXmlNamePattern;
    }

    public String getMapperXmlName() {
        return mapperXmlName;
    }

    public void setMapperXmlName(String mapperXmlName) {
        this.mapperXmlName = mapperXmlName;
    }
}
