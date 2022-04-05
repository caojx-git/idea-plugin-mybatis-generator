package com.caojx.idea.plugin.generator;

import com.caojx.idea.plugin.common.properties.GeneratorConfigContext;
import com.intellij.openapi.project.Project;

public interface IGeneratorService {

    /**
     * 生成代码
     *
     * @param project                项目
     * @param generatorConfigContext 上下文
     */
    void doGenerator(Project project, GeneratorConfigContext generatorConfigContext);
}
