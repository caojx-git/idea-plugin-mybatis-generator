package com.caojx.idea.plugin.common.properties;

/**
 * mybatis生成方法控制
 */
public class GeneratorMethodProperties {

    /**
     * 生成insert方法
     */
    private boolean enableInsert;

    /**
     * 生成delete方法
     */
    private boolean enableDeleteByPrimaryKey;

    /**
     * 生成update方法
     */
    private boolean enableUpdateByPrimaryKey;

    /**
     * 生成主键查询方法
     */
    private boolean enableSelectByPrimaryKey;

    /**
     * 生成 enableSelectByExample
     */
    private boolean enableSelectByExample;

    /**
     * 生成 enableDeleteByExample
     */
    private boolean enableDeleteByExample;

    /**
     * 生成 enableCountByExample
     */
    private boolean enableCountByExample;

    /**
     * 生成 enableUpdateByExample
     */
    private boolean enableUpdateByExample;

    /**
     * 生成 selectByExampleQueryId
     */
    private boolean selectByExampleQueryId;

    public boolean isEnableInsert() {
        return enableInsert;
    }

    public void setEnableInsert(boolean enableInsert) {
        this.enableInsert = enableInsert;
    }

    public boolean isEnableDeleteByPrimaryKey() {
        return enableDeleteByPrimaryKey;
    }

    public void setEnableDeleteByPrimaryKey(boolean enableDeleteByPrimaryKey) {
        this.enableDeleteByPrimaryKey = enableDeleteByPrimaryKey;
    }

    public boolean isEnableUpdateByPrimaryKey() {
        return enableUpdateByPrimaryKey;
    }

    public void setEnableUpdateByPrimaryKey(boolean enableUpdateByPrimaryKey) {
        this.enableUpdateByPrimaryKey = enableUpdateByPrimaryKey;
    }

    public boolean isEnableSelectByPrimaryKey() {
        return enableSelectByPrimaryKey;
    }

    public void setEnableSelectByPrimaryKey(boolean enableSelectByPrimaryKey) {
        this.enableSelectByPrimaryKey = enableSelectByPrimaryKey;
    }

    public boolean isEnableSelectByExample() {
        return enableSelectByExample;
    }

    public void setEnableSelectByExample(boolean enableSelectByExample) {
        this.enableSelectByExample = enableSelectByExample;
    }

    public boolean isEnableDeleteByExample() {
        return enableDeleteByExample;
    }

    public void setEnableDeleteByExample(boolean enableDeleteByExample) {
        this.enableDeleteByExample = enableDeleteByExample;
    }

    public boolean isEnableCountByExample() {
        return enableCountByExample;
    }

    public void setEnableCountByExample(boolean enableCountByExample) {
        this.enableCountByExample = enableCountByExample;
    }

    public boolean isEnableUpdateByExample() {
        return enableUpdateByExample;
    }

    public void setEnableUpdateByExample(boolean enableUpdateByExample) {
        this.enableUpdateByExample = enableUpdateByExample;
    }

    public boolean isSelectByExampleQueryId() {
        return selectByExampleQueryId;
    }

    public void setSelectByExampleQueryId(boolean selectByExampleQueryId) {
        this.selectByExampleQueryId = selectByExampleQueryId;
    }
}
