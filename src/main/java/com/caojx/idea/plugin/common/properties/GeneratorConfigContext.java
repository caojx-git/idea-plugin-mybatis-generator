package com.caojx.idea.plugin.common.properties;

import com.caojx.idea.plugin.common.pojo.db.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成代码上下文
 */
public class GeneratorConfigContext {

    /**
     * 生成的表
     */
    private List<Table> tables = new ArrayList<>();

    /**
     * mybatis生成配置
     */
    private BaseGeneratorProperties baseGeneratorProperties = new BaseGeneratorProperties();

    /**
     * lombok生成配置
     */
    private LombokProperties lombokProperties = new LombokProperties();

    /**
     * swagger生成配置
     */
    private SwaggerProperties swaggerProperties = new SwaggerProperties();


    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public BaseGeneratorProperties getBaseGeneratorProperties() {
        return baseGeneratorProperties;
    }

    public void setBaseGeneratorProperties(BaseGeneratorProperties baseGeneratorProperties) {
        this.baseGeneratorProperties = baseGeneratorProperties;
    }

    public LombokProperties getLombokProperties() {
        return lombokProperties;
    }

    public void setLombokProperties(LombokProperties lombokProperties) {
        this.lombokProperties = lombokProperties;
    }

    public SwaggerProperties getSwaggerProperties() {
        return swaggerProperties;
    }

    public void setSwaggerProperties(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }
}
