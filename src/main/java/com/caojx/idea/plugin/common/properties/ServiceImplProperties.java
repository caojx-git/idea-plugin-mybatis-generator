package com.caojx.idea.plugin.common.properties;

/**
 * ServiceImpl配置属性
 *
 * @author caojx
 * @date 2022/4/10 12:16 PM
 */
public class ServiceImplProperties {

    /**
     * 是否生成serviceImpl
     */
    private boolean serviceImplGenerateValue;

    /**
     * serviceImpl路径
     */
    private String serviceImplPath;

    /**
     * serviceImpl包名
     */
    private String serviceImplPackage;

    /**
     * serviceImpl命名格式
     */
    private String serviceImplNamePattern;

    /**
     * serviceImpl父类
     */
    private String superServiceImplClass;

    public boolean getServiceImplGenerateValue() {
        return serviceImplGenerateValue;
    }

    public void setServiceImplGenerateValue(boolean serviceImplGenerateValue) {
        this.serviceImplGenerateValue = serviceImplGenerateValue;
    }

    public String getServiceImplPath() {
        return serviceImplPath;
    }

    public void setServiceImplPath(String serviceImplPath) {
        this.serviceImplPath = serviceImplPath;
    }

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public String getServiceImplNamePattern() {
        return serviceImplNamePattern;
    }

    public void setServiceImplNamePattern(String serviceImplNamePattern) {
        this.serviceImplNamePattern = serviceImplNamePattern;
    }

    public boolean isServiceImplGenerateValue() {
        return serviceImplGenerateValue;
    }

    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    public void setSuperServiceImplClass(String superServiceImplClass) {
        this.superServiceImplClass = superServiceImplClass;
    }
}
