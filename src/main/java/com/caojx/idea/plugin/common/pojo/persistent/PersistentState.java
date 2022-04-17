package com.caojx.idea.plugin.common.pojo.persistent;

import com.caojx.idea.plugin.common.context.GeneratorContext;

import java.io.Serializable;

/**
 * 持久化数据
 */
public class PersistentState implements Serializable {

    /**
     * 生成代码配置上线文
     */
    private GeneratorContext generatorContext = new GeneratorContext();

    public GeneratorContext getGeneratorContext() {
        return generatorContext;
    }

    public void setGeneratorContext(GeneratorContext generatorContext) {
        this.generatorContext = generatorContext;
    }
}
