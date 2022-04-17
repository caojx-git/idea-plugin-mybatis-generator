package com.caojx.idea.plugin.common.properties;

import java.io.Serializable;

/**
 * Service配置属性
 *
 * @author caojx
 * @date 2022/4/10 12:16 PM
 */
public class ServiceProperties implements Serializable {

    /**
     * 是否生成service
     */
    private boolean selectedGenerateCheckBox;

    /**
     * service路径
     */
    private String path;

    /**
     * service包名
     */
    private String packageName;

    /**
     * service命名格式
     */
    private String namePattern;

    /**
     * service父类
     */
    private String superServiceClass;

    public boolean isSelectedGenerateCheckBox() {
        return selectedGenerateCheckBox;
    }

    public void setSelectedGenerateCheckBox(boolean selectedGenerateCheckBox) {
        this.selectedGenerateCheckBox = selectedGenerateCheckBox;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getNamePattern() {
        return namePattern;
    }

    public void setNamePattern(String namePattern) {
        this.namePattern = namePattern;
    }

    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public void setSuperServiceClass(String superServiceClass) {
        this.superServiceClass = superServiceClass;
    }
}
