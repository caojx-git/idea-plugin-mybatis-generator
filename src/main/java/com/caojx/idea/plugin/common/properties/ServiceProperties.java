package com.caojx.idea.plugin.common.properties;

/**
 * Service配置属性
 *
 * @author caojx
 * @date 2022/4/10 12:16 PM
 */
public class ServiceProperties {

    /**
     * 是否生成service
     */
    private boolean serviceGenerateValue;

    /**
     * service路径
     */
    private String servicePath;

    /**
     * service包名
     */
    private String servicePackage;

    /**
     * service命名格式
     */
    private String serviceNamePattern;

    /**
     * service父类
     */
    private String superServiceClass;

    public boolean getServiceGenerateValue() {
        return serviceGenerateValue;
    }

    public void setServiceGenerateValue(boolean serviceGenerateValue) {
        this.serviceGenerateValue = serviceGenerateValue;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceNamePattern() {
        return serviceNamePattern;
    }

    public void setServiceNamePattern(String serviceNamePattern) {
        this.serviceNamePattern = serviceNamePattern;
    }

    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public void setSuperServiceClass(String superServiceClass) {
        this.superServiceClass = superServiceClass;
    }
}
