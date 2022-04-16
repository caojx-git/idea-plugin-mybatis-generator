package com.caojx.idea.plugin.common.properties;

/**
 * Controller配置属性
 *
 * @author caojx
 * @date 2022/4/10 12:16 PM
 */
public class ControllerProperties {

    /**
     * 是否生成controller
     */
    private boolean controllerGenerateValue;

    /**
     * controller路径
     */
    private String controllerPath;

    /**
     * controller包名
     */
    private String controllerPackage;

    /**
     * controller 命名格式
     */
    private String controllerNamePattern;

    /**
     * controller swagger注解
     */
    private boolean controllerSwaggerCheckBoxValue;

    public boolean getControllerGenerateValue() {
        return controllerGenerateValue;
    }

    public void setControllerGenerateValue(boolean controllerGenerateValue) {
        this.controllerGenerateValue = controllerGenerateValue;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public void setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getControllerNamePattern() {
        return controllerNamePattern;
    }

    public void setControllerNamePattern(String controllerNamePattern) {
        this.controllerNamePattern = controllerNamePattern;
    }

    public boolean getControllerSwaggerCheckBoxValue() {
        return controllerSwaggerCheckBoxValue;
    }

    public void setControllerSwaggerCheckBoxValue(boolean controllerSwaggerCheckBoxValue) {
        this.controllerSwaggerCheckBoxValue = controllerSwaggerCheckBoxValue;
    }
}
