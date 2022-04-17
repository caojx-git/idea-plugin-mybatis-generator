package com.caojx.idea.plugin.common.properties;

/**
 * Mapper配置属性
 *
 * @author caojx
 * @date 2022/4/10 12:15 PM
 */
public class MapperProperties {

    /**
     * 是否生成mapper
     */
    private boolean selectedGenerateCheckBox;

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
     * mapper生成方法
     */
    private boolean selectedSelectByExampleWithBLOBsCheckBox;
    private boolean selectedSelectByExampleCheckBox;
    private boolean selectedSelectByPrimaryKeyCheckBox;
    private boolean selectedInsertCheckBox;
    private boolean selectedInsertSelectiveCheckBox;
    private boolean selectedCountByExampleCheckBox;
    private boolean selectedUpdateByExampleCheckBox;
    private boolean selectedUpdateByExampleSelectiveCheckBox;
    private boolean selectedUpdateByPrimaryKeyCheckBox;
    private boolean selectedUpdateByPrimaryKeySelectiveCheckBox;
    private boolean selectedUpdateByExampleWithBLOBsCheckBox;
    private boolean selectedUpdateByPrimaryKeyWithBLOBsCheckBox;
    private boolean selectedDeleteByExampleCheckBox;
    private boolean selectedDeleteByPrimaryKeyCheckBox;

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

    public boolean isSelectedSelectByExampleWithBLOBsCheckBox() {
        return selectedSelectByExampleWithBLOBsCheckBox;
    }

    public void setSelectedSelectByExampleWithBLOBsCheckBox(boolean selectedSelectByExampleWithBLOBsCheckBox) {
        this.selectedSelectByExampleWithBLOBsCheckBox = selectedSelectByExampleWithBLOBsCheckBox;
    }

    public boolean isSelectedSelectByExampleCheckBox() {
        return selectedSelectByExampleCheckBox;
    }

    public void setSelectedSelectByExampleCheckBox(boolean selectedSelectByExampleCheckBox) {
        this.selectedSelectByExampleCheckBox = selectedSelectByExampleCheckBox;
    }

    public boolean isSelectedSelectByPrimaryKeyCheckBox() {
        return selectedSelectByPrimaryKeyCheckBox;
    }

    public void setSelectedSelectByPrimaryKeyCheckBox(boolean selectedSelectByPrimaryKeyCheckBox) {
        this.selectedSelectByPrimaryKeyCheckBox = selectedSelectByPrimaryKeyCheckBox;
    }

    public boolean isSelectedInsertCheckBox() {
        return selectedInsertCheckBox;
    }

    public void setSelectedInsertCheckBox(boolean selectedInsertCheckBox) {
        this.selectedInsertCheckBox = selectedInsertCheckBox;
    }

    public boolean isSelectedInsertSelectiveCheckBox() {
        return selectedInsertSelectiveCheckBox;
    }

    public void setSelectedInsertSelectiveCheckBox(boolean selectedInsertSelectiveCheckBox) {
        this.selectedInsertSelectiveCheckBox = selectedInsertSelectiveCheckBox;
    }

    public boolean isSelectedCountByExampleCheckBox() {
        return selectedCountByExampleCheckBox;
    }

    public void setSelectedCountByExampleCheckBox(boolean selectedCountByExampleCheckBox) {
        this.selectedCountByExampleCheckBox = selectedCountByExampleCheckBox;
    }

    public boolean isSelectedUpdateByExampleCheckBox() {
        return selectedUpdateByExampleCheckBox;
    }

    public void setSelectedUpdateByExampleCheckBox(boolean selectedUpdateByExampleCheckBox) {
        this.selectedUpdateByExampleCheckBox = selectedUpdateByExampleCheckBox;
    }

    public boolean isSelectedUpdateByExampleSelectiveCheckBox() {
        return selectedUpdateByExampleSelectiveCheckBox;
    }

    public void setSelectedUpdateByExampleSelectiveCheckBox(boolean selectedUpdateByExampleSelectiveCheckBox) {
        this.selectedUpdateByExampleSelectiveCheckBox = selectedUpdateByExampleSelectiveCheckBox;
    }

    public boolean isSelectedUpdateByPrimaryKeyCheckBox() {
        return selectedUpdateByPrimaryKeyCheckBox;
    }

    public void setSelectedUpdateByPrimaryKeyCheckBox(boolean selectedUpdateByPrimaryKeyCheckBox) {
        this.selectedUpdateByPrimaryKeyCheckBox = selectedUpdateByPrimaryKeyCheckBox;
    }

    public boolean isSelectedUpdateByPrimaryKeySelectiveCheckBox() {
        return selectedUpdateByPrimaryKeySelectiveCheckBox;
    }

    public void setSelectedUpdateByPrimaryKeySelectiveCheckBox(boolean selectedUpdateByPrimaryKeySelectiveCheckBox) {
        this.selectedUpdateByPrimaryKeySelectiveCheckBox = selectedUpdateByPrimaryKeySelectiveCheckBox;
    }

    public boolean isSelectedUpdateByExampleWithBLOBsCheckBox() {
        return selectedUpdateByExampleWithBLOBsCheckBox;
    }

    public void setSelectedUpdateByExampleWithBLOBsCheckBox(boolean selectedUpdateByExampleWithBLOBsCheckBox) {
        this.selectedUpdateByExampleWithBLOBsCheckBox = selectedUpdateByExampleWithBLOBsCheckBox;
    }

    public boolean isSelectedUpdateByPrimaryKeyWithBLOBsCheckBox() {
        return selectedUpdateByPrimaryKeyWithBLOBsCheckBox;
    }

    public void setSelectedUpdateByPrimaryKeyWithBLOBsCheckBox(boolean selectedUpdateByPrimaryKeyWithBLOBsCheckBox) {
        this.selectedUpdateByPrimaryKeyWithBLOBsCheckBox = selectedUpdateByPrimaryKeyWithBLOBsCheckBox;
    }

    public boolean isSelectedDeleteByExampleCheckBox() {
        return selectedDeleteByExampleCheckBox;
    }

    public void setSelectedDeleteByExampleCheckBox(boolean selectedDeleteByExampleCheckBox) {
        this.selectedDeleteByExampleCheckBox = selectedDeleteByExampleCheckBox;
    }

    public boolean isSelectedDeleteByPrimaryKeyCheckBox() {
        return selectedDeleteByPrimaryKeyCheckBox;
    }

    public void setSelectedDeleteByPrimaryKeyCheckBox(boolean selectedDeleteByPrimaryKeyCheckBox) {
        this.selectedDeleteByPrimaryKeyCheckBox = selectedDeleteByPrimaryKeyCheckBox;
    }
}
