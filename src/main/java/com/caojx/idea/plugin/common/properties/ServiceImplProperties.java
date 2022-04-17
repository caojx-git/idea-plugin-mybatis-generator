package com.caojx.idea.plugin.common.properties;

import java.io.Serializable;

/**
 * ServiceImpl配置属性
 *
 * @author caojx
 * @date 2022/4/10 12:16 PM
 */
public class ServiceImplProperties implements Serializable {

    /**
     * 是否生成serviceImpl
     */
    private boolean selectedGenerateCheckBox;

    /**
     * serviceImpl路径
     */
    private String path;

    /**
     * serviceImpl包名
     */
    private String packageName;

    /**
     * serviceImpl命名格式
     */
    private String namePattern;

    /**
     * serviceImpl父类
     */
    private String superServiceImplClass;

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

    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    public void setSuperServiceImplClass(String superServiceImplClass) {
        this.superServiceImplClass = superServiceImplClass;
    }
}
