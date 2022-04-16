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
    private boolean mapperGenerateValue;

    /**
     * mapper路径
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
     * mapper父类
     */
    private String superMapperClass;

    /**
     * mapper生成方法
     */
    private boolean selectByExampleWithBLOBsCheckBoxValue;
    private boolean selectByExampleCheckBoxValue;
    private boolean selectByPrimaryKeyCheckBoxValue;
    private boolean insertCheckBoxValue;
    private boolean insertSelectiveCheckBoxValue;
    private boolean countByExampleCheckBoxValue;
    private boolean updateByExampleCheckBoxValue;
    private boolean updateByExampleSelectiveCheckBoxValue;
    private boolean updateByPrimaryKeyCheckBoxValue;
    private boolean updateByPrimaryKeySelectiveCheckBoxValue;
    private boolean updateByExampleWithBLOBsCheckBoxValue;
    private boolean updateByPrimaryKeyWithBLOBsCheckBoxValue;
    private boolean deleteByExampleCheckBoxValue;
    private boolean deleteByPrimaryKeyCheckBoxValue;

    public boolean getMapperGenerateValue() {
        return mapperGenerateValue;
    }

    public void setMapperGenerateValue(boolean mapperGenerateValue) {
        this.mapperGenerateValue = mapperGenerateValue;
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

    public String getSuperMapperClass() {
        return superMapperClass;
    }

    public void setSuperMapperClass(String superMapperClass) {
        this.superMapperClass = superMapperClass;
    }

    public boolean getSelectByExampleWithBLOBsCheckBoxValue() {
        return selectByExampleWithBLOBsCheckBoxValue;
    }

    public void setSelectByExampleWithBLOBsCheckBoxValue(boolean selectByExampleWithBLOBsCheckBoxValue) {
        this.selectByExampleWithBLOBsCheckBoxValue = selectByExampleWithBLOBsCheckBoxValue;
    }

    public boolean getSelectByExampleCheckBoxValue() {
        return selectByExampleCheckBoxValue;
    }

    public void setSelectByExampleCheckBoxValue(boolean selectByExampleCheckBoxValue) {
        this.selectByExampleCheckBoxValue = selectByExampleCheckBoxValue;
    }

    public boolean getSelectByPrimaryKeyCheckBoxValue() {
        return selectByPrimaryKeyCheckBoxValue;
    }

    public void setSelectByPrimaryKeyCheckBoxValue(boolean selectByPrimaryKeyCheckBoxValue) {
        this.selectByPrimaryKeyCheckBoxValue = selectByPrimaryKeyCheckBoxValue;
    }

    public boolean getInsertCheckBoxValue() {
        return insertCheckBoxValue;
    }

    public void setInsertCheckBoxValue(boolean insertCheckBoxValue) {
        this.insertCheckBoxValue = insertCheckBoxValue;
    }

    public boolean getInsertSelectiveCheckBoxValue() {
        return insertSelectiveCheckBoxValue;
    }

    public void setInsertSelectiveCheckBoxValue(boolean insertSelectiveCheckBoxValue) {
        this.insertSelectiveCheckBoxValue = insertSelectiveCheckBoxValue;
    }

    public boolean getCountByExampleCheckBoxValue() {
        return countByExampleCheckBoxValue;
    }

    public void setCountByExampleCheckBoxValue(boolean countByExampleCheckBoxValue) {
        this.countByExampleCheckBoxValue = countByExampleCheckBoxValue;
    }

    public boolean getUpdateByExampleCheckBoxValue() {
        return updateByExampleCheckBoxValue;
    }

    public void setUpdateByExampleCheckBoxValue(boolean updateByExampleCheckBoxValue) {
        this.updateByExampleCheckBoxValue = updateByExampleCheckBoxValue;
    }

    public boolean getUpdateByExampleSelectiveCheckBoxValue() {
        return updateByExampleSelectiveCheckBoxValue;
    }

    public void setUpdateByExampleSelectiveCheckBoxValue(boolean updateByExampleSelectiveCheckBoxValue) {
        this.updateByExampleSelectiveCheckBoxValue = updateByExampleSelectiveCheckBoxValue;
    }

    public boolean getUpdateByPrimaryKeyCheckBoxValue() {
        return updateByPrimaryKeyCheckBoxValue;
    }

    public void setUpdateByPrimaryKeyCheckBoxValue(boolean updateByPrimaryKeyCheckBoxValue) {
        this.updateByPrimaryKeyCheckBoxValue = updateByPrimaryKeyCheckBoxValue;
    }

    public boolean getUpdateByPrimaryKeySelectiveCheckBoxValue() {
        return updateByPrimaryKeySelectiveCheckBoxValue;
    }

    public void setUpdateByPrimaryKeySelectiveCheckBoxValue(boolean updateByPrimaryKeySelectiveCheckBoxValue) {
        this.updateByPrimaryKeySelectiveCheckBoxValue = updateByPrimaryKeySelectiveCheckBoxValue;
    }

    public boolean getUpdateByExampleWithBLOBsCheckBoxValue() {
        return updateByExampleWithBLOBsCheckBoxValue;
    }

    public void setUpdateByExampleWithBLOBsCheckBoxValue(boolean updateByExampleWithBLOBsCheckBoxValue) {
        this.updateByExampleWithBLOBsCheckBoxValue = updateByExampleWithBLOBsCheckBoxValue;
    }

    public boolean getUpdateByPrimaryKeyWithBLOBsCheckBoxValue() {
        return updateByPrimaryKeyWithBLOBsCheckBoxValue;
    }

    public void setUpdateByPrimaryKeyWithBLOBsCheckBoxValue(boolean updateByPrimaryKeyWithBLOBsCheckBoxValue) {
        this.updateByPrimaryKeyWithBLOBsCheckBoxValue = updateByPrimaryKeyWithBLOBsCheckBoxValue;
    }

    public boolean getDeleteByExampleCheckBoxValue() {
        return deleteByExampleCheckBoxValue;
    }

    public void setDeleteByExampleCheckBoxValue(boolean deleteByExampleCheckBoxValue) {
        this.deleteByExampleCheckBoxValue = deleteByExampleCheckBoxValue;
    }

    public boolean getDeleteByPrimaryKeyCheckBoxValue() {
        return deleteByPrimaryKeyCheckBoxValue;
    }

    public void setDeleteByPrimaryKeyCheckBoxValue(boolean deleteByPrimaryKeyCheckBoxValue) {
        this.deleteByPrimaryKeyCheckBoxValue = deleteByPrimaryKeyCheckBoxValue;
    }
}
