package com.caojx.idea.plugin.domain.model;

/**
 * MyBatis生成代码配置类
 */
public class MyBatisGeneratorConfig extends MyBatisGeneratorMethodConfig {

    /**
     * model生成路径
     */
    private String modelPath;

    /**
     * model包名
     */
    private String modelPackage;

    /**
     * model命名格式
     */
    private String modelNamePattern;

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
     * modelXml生成路径
     */
    private String mapperXmlPath;

    /**
     * modelXml命名格式
     */
    private String mapperXmlNamePattern;

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getModelNamePattern() {
        return modelNamePattern;
    }

    public void setModelNamePattern(String modelNamePattern) {
        this.modelNamePattern = modelNamePattern;
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
}
