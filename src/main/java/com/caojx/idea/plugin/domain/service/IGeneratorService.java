package com.caojx.idea.plugin.domain.service;

import com.caojx.idea.plugin.domain.model.GeneratorConfigContext;
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
