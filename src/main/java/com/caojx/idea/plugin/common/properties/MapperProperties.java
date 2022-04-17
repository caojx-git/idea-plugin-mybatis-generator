package com.caojx.idea.plugin.common.properties;

import java.io.Serializable;

/**
 * Mapper配置属性
 *
 * @author caojx
 * @date 2022/4/10 12:15 PM
 */
public class MapperProperties implements Serializable {

    /**
     * 是否生成mapper
     */
    private boolean selectedGenerateCheckBox = true;

    /**
     * mapper路径
     */
    private String path;

    /**
     * mapper包名
     */
    private String packageName;

    /**
     * mapper命名格式
     */
    private String namePattern;

    /**
     * mapper父类
     */
    private String superMapperClass;

    /**
     * mapper生成方法控制
     */
    private boolean selectedEnableInsertCheckBox;
    private boolean selectedEnableSelectByPrimaryKeyCheckBox;
    private boolean selectedEnableSelectByExampleCheckBox;
    private boolean selectedEnableUpdateByPrimaryKeyCheckBox;
    private boolean selectedEnableUpdateByExampleCheckBox;
    private boolean selectedEnableDeleteByPrimaryKeyCheckBox;
    private boolean selectedEnableDeleteByExampleCheckBox;
    private boolean selectedEnableCountByExampleCheckBox;

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

    public String getSuperMapperClass() {
        return superMapperClass;
    }

    public void setSuperMapperClass(String superMapperClass) {
        this.superMapperClass = superMapperClass;
    }

    public boolean isSelectedEnableInsertCheckBox() {
        return selectedEnableInsertCheckBox;
    }

    public void setSelectedEnableInsertCheckBox(boolean selectedEnableInsertCheckBox) {
        this.selectedEnableInsertCheckBox = selectedEnableInsertCheckBox;
    }

    public boolean isSelectedEnableSelectByPrimaryKeyCheckBox() {
        return selectedEnableSelectByPrimaryKeyCheckBox;
    }

    public void setSelectedEnableSelectByPrimaryKeyCheckBox(boolean selectedEnableSelectByPrimaryKeyCheckBox) {
        this.selectedEnableSelectByPrimaryKeyCheckBox = selectedEnableSelectByPrimaryKeyCheckBox;
    }

    public boolean isSelectedEnableSelectByExampleCheckBox() {
        return selectedEnableSelectByExampleCheckBox;
    }

    public void setSelectedEnableSelectByExampleCheckBox(boolean selectedEnableSelectByExampleCheckBox) {
        this.selectedEnableSelectByExampleCheckBox = selectedEnableSelectByExampleCheckBox;
    }

    public boolean isSelectedEnableUpdateByPrimaryKeyCheckBox() {
        return selectedEnableUpdateByPrimaryKeyCheckBox;
    }

    public void setSelectedEnableUpdateByPrimaryKeyCheckBox(boolean selectedEnableUpdateByPrimaryKeyCheckBox) {
        this.selectedEnableUpdateByPrimaryKeyCheckBox = selectedEnableUpdateByPrimaryKeyCheckBox;
    }

    public boolean isSelectedEnableUpdateByExampleCheckBox() {
        return selectedEnableUpdateByExampleCheckBox;
    }

    public void setSelectedEnableUpdateByExampleCheckBox(boolean selectedEnableUpdateByExampleCheckBox) {
        this.selectedEnableUpdateByExampleCheckBox = selectedEnableUpdateByExampleCheckBox;
    }

    public boolean isSelectedEnableDeleteByPrimaryKeyCheckBox() {
        return selectedEnableDeleteByPrimaryKeyCheckBox;
    }

    public void setSelectedEnableDeleteByPrimaryKeyCheckBox(boolean selectedEnableDeleteByPrimaryKeyCheckBox) {
        this.selectedEnableDeleteByPrimaryKeyCheckBox = selectedEnableDeleteByPrimaryKeyCheckBox;
    }

    public boolean isSelectedEnableDeleteByExampleCheckBox() {
        return selectedEnableDeleteByExampleCheckBox;
    }

    public void setSelectedEnableDeleteByExampleCheckBox(boolean selectedEnableDeleteByExampleCheckBox) {
        this.selectedEnableDeleteByExampleCheckBox = selectedEnableDeleteByExampleCheckBox;
    }

    public boolean isSelectedEnableCountByExampleCheckBox() {
        return selectedEnableCountByExampleCheckBox;
    }

    public void setSelectedEnableCountByExampleCheckBox(boolean selectedEnableCountByExampleCheckBox) {
        this.selectedEnableCountByExampleCheckBox = selectedEnableCountByExampleCheckBox;
    }
}
