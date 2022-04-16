package com.caojx.idea.plugin.generator;

import com.caojx.idea.plugin.common.context.GeneratorContext;
import com.intellij.openapi.project.Project;

/**
 * 代码生成接口
 *
 * @author caojx
 * @date 2022/4/4 12:57 PM
 */
public interface IGeneratorService {

    /**
     * 生成代码
     *
     * @param project          项目
     * @param generatorContext 上下文
     */
    void doGenerator(Project project, GeneratorContext generatorContext);
}
