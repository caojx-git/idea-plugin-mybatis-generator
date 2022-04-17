package com.caojx.idea.plugin.common.context;

import com.caojx.idea.plugin.common.pojo.model.TableInfo;
import com.caojx.idea.plugin.common.properties.GeneratorProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成代码上下文
 *
 * @author caojx
 * @date 2022/4/10 4:00 PM
 */
public class GeneratorContext implements Serializable {

    /**
     * 代码生成配置
     */
    private GeneratorProperties generatorProperties = new GeneratorProperties();

    /**
     * 生成的表
     */
    private List<TableInfo> tables = new ArrayList<>();

    public GeneratorProperties getGeneratorProperties() {
        return generatorProperties;
    }

    public void setGeneratorProperties(GeneratorProperties generatorProperties) {
        this.generatorProperties = generatorProperties;
    }

    public List<TableInfo> getTables() {
        return tables;
    }

    public void setTables(List<TableInfo> tables) {
        this.tables = tables;
    }
}
