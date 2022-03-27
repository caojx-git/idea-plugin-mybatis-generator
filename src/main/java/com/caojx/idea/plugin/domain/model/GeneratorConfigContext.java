package com.caojx.idea.plugin.domain.model;

/**
 * 生成代码上下文
 */
public class GeneratorConfigContext {

    /**
     * mybatis生成配置
     */
    private MyBatisGeneratorConfig myBatisGeneratorConfig = new MyBatisGeneratorConfig();

    /**
     * lombok生成配置
     */
    private LombokGeneratorConfig lombokGeneratorConfig = new LombokGeneratorConfig();

    /**
     * swagger生成配置
     */
    private SwaggerGeneratorConfig swaggerGeneratorConfig = new SwaggerGeneratorConfig();


    public MyBatisGeneratorConfig getMyBatisGeneratorConfig() {
        return myBatisGeneratorConfig;
    }

    public void setMyBatisGeneratorConfig(MyBatisGeneratorConfig myBatisGeneratorConfig) {
        this.myBatisGeneratorConfig = myBatisGeneratorConfig;
    }

    public LombokGeneratorConfig getLombokGeneratorConfig() {
        return lombokGeneratorConfig;
    }

    public void setLombokGeneratorConfig(LombokGeneratorConfig lombokGeneratorConfig) {
        this.lombokGeneratorConfig = lombokGeneratorConfig;
    }

    public SwaggerGeneratorConfig getSwaggerGeneratorConfig() {
        return swaggerGeneratorConfig;
    }

    public void setSwaggerGeneratorConfig(SwaggerGeneratorConfig swaggerGeneratorConfig) {
        this.swaggerGeneratorConfig = swaggerGeneratorConfig;
    }
}
