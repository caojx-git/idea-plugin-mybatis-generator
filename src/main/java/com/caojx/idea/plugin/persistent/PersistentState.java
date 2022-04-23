package com.caojx.idea.plugin.persistent;

import com.caojx.idea.plugin.common.properties.GeneratorProperties;

import java.io.Serializable;

/**
 * 持久化数据
 */
public class PersistentState implements Serializable {

    /**
     * 代码生成配置
     */
    private GeneratorProperties generatorProperties = new GeneratorProperties();

    public GeneratorProperties getGeneratorProperties() {
        return generatorProperties;
    }

    public void setGeneratorProperties(GeneratorProperties generatorProperties) {
        this.generatorProperties = generatorProperties;
    }
}
