package com.caojx.idea.plugin.common.properties;

import com.caojx.idea.plugin.common.constants.Constant;

import java.io.Serializable;

/**
 * 实体配置属性
 *
 * @author caojx
 * @date 2022/4/10 12:15 PM
 */
public class EntityProperties implements Serializable {

    /**
     * 是否生成实体
     */
    private boolean selectedGenerateCheckBox = true;

    /**
     * 实体路径
     */
    private String path;

    /**
     * 实体包名
     */
    private String packageName;

    /**
     * 实体命名格式
     */
    private String namePattern;

    /**
     * entityExample 命名格式
     */
    private String exampleNamePattern;

    /**
     * 实体lombok @Data注解
     */
    private boolean selectedDataCheckBox;

    /**
     * 实体lombok @Builder注解
     */
    private boolean selectedBuilderCheckBox;

    /**
     * 实体lombok @NoArgsConstructor注解
     */
    private boolean selectedNoArgsConstructorCheckBox;

    /**
     * 实体lombok @AllArgsConstructor注解
     */
    private boolean selectedAllArgsConstructorCheckBox;

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

    public String getExampleNamePattern() {
        return exampleNamePattern;
    }

    public void setExampleNamePattern(String exampleNamePattern) {
        this.exampleNamePattern = exampleNamePattern;
    }

    public boolean isSelectedDataCheckBox() {
        return selectedDataCheckBox;
    }

    public void setSelectedDataCheckBox(boolean selectedDataCheckBox) {
        this.selectedDataCheckBox = selectedDataCheckBox;
    }

    public boolean isSelectedBuilderCheckBox() {
        return selectedBuilderCheckBox;
    }

    public void setSelectedBuilderCheckBox(boolean selectedBuilderCheckBox) {
        this.selectedBuilderCheckBox = selectedBuilderCheckBox;
    }

    public boolean isSelectedNoArgsConstructorCheckBox() {
        return selectedNoArgsConstructorCheckBox;
    }

    public void setSelectedNoArgsConstructorCheckBox(boolean selectedNoArgsConstructorCheckBox) {
        this.selectedNoArgsConstructorCheckBox = selectedNoArgsConstructorCheckBox;
    }

    public boolean isSelectedAllArgsConstructorCheckBox() {
        return selectedAllArgsConstructorCheckBox;
    }

    public void setSelectedAllArgsConstructorCheckBox(boolean selectedAllArgsConstructorCheckBox) {
        this.selectedAllArgsConstructorCheckBox = selectedAllArgsConstructorCheckBox;
    }
}
