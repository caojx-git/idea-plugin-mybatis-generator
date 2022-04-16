package com.caojx.idea.plugin.common.properties;

/**
 * 实体配置属性
 *
 * @author caojx
 * @date 2022/4/10 12:15 PM
 */
public class EntityProperties {

    /**
     * 是否生成实体
     */
    private boolean entityGenerateValue;

    /**
     * 实体路径
     */
    private String entityPath;

    /**
     * 实体包名
     */
    private String entityPackage;

    /**
     * 实体命名格式
     */
    private String entityNamePattern;

    /**
     * entityExample 命名格式
     */
    private String entityExampleNamePattern;

    /**
     * 实体lombok @Data注解
     */
    private boolean dataCheckBoxValue;

    /**
     * 实体lombok @Builder注解
     */
    private boolean builderCheckBoxValue;

    /**
     * 实体lombok @NoArgsConstructor注解
     */
    private boolean noArgsConstructorCheckBoxValue;

    /**
     * 实体lombok @AllArgsConstructor注解
     */
    private boolean allArgsConstructorCheckBoxValue;

    public boolean getEntityGenerateValue() {
        return entityGenerateValue;
    }

    public void setEntityGenerateValue(boolean entityGenerateValue) {
        this.entityGenerateValue = entityGenerateValue;
    }

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

    public String getEntityExampleNamePattern() {
        return entityExampleNamePattern;
    }

    public void setEntityExampleNamePattern(String entityExampleNamePattern) {
        this.entityExampleNamePattern = entityExampleNamePattern;
    }

    public boolean getDataCheckBoxValue() {
        return dataCheckBoxValue;
    }

    public void setDataCheckBoxValue(boolean dataCheckBoxValue) {
        this.dataCheckBoxValue = dataCheckBoxValue;
    }

    public boolean getBuilderCheckBoxValue() {
        return builderCheckBoxValue;
    }

    public void setBuilderCheckBoxValue(boolean builderCheckBoxValue) {
        this.builderCheckBoxValue = builderCheckBoxValue;
    }

    public boolean getNoArgsConstructorCheckBoxValue() {
        return noArgsConstructorCheckBoxValue;
    }

    public void setNoArgsConstructorCheckBoxValue(boolean noArgsConstructorCheckBoxValue) {
        this.noArgsConstructorCheckBoxValue = noArgsConstructorCheckBoxValue;
    }

    public boolean getAllArgsConstructorCheckBoxValue() {
        return allArgsConstructorCheckBoxValue;
    }

    public void setAllArgsConstructorCheckBoxValue(boolean allArgsConstructorCheckBoxValue) {
        this.allArgsConstructorCheckBoxValue = allArgsConstructorCheckBoxValue;
    }
}
