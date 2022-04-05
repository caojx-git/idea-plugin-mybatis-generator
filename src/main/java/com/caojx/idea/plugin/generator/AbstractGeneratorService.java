package com.caojx.idea.plugin.generator;

import com.caojx.idea.plugin.common.constants.Constant;
import com.caojx.idea.plugin.generator.engin.SimpleFreemarkerTemplateEngine;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import freemarker.template.Template;

import java.io.File;
import java.io.StringWriter;

/**
 * 生成文件抽象路
 *
 * @author caojx
 * @date 2022/4/4
 */
public abstract class AbstractGeneratorService implements IGeneratorService {

    /**
     * FreemarkerTemplateEngine
     */
    private final SimpleFreemarkerTemplateEngine freemarkerTemplateEngine = new SimpleFreemarkerTemplateEngine();

    /**
     * 写文件
     *
     * @param project      项目
     * @param dirPath      目录路径
     * @param fileName     文件名
     * @param templatePath 模板路径
     * @param dataModel    模板数据
     */
    public void writeFile(Project project, String dirPath, String fileName, String templatePath, Object dataModel) {
        try {
            VirtualFile virtualFile = createDir(dirPath).createChildData(project, fileName);
            StringWriter stringWriter = new StringWriter();
            Template template = freemarkerTemplateEngine.getTemplate(templatePath);
            template.process(dataModel, stringWriter);
            virtualFile.setBinaryContent(stringWriter.toString().getBytes(Constant.UTF8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建目录
     *
     * @param dirPath 目录
     * @return 文件
     */
    private static VirtualFile createDir(String dirPath) {
        String path = FileUtil.toSystemIndependentName(StringUtil.replace(dirPath, Constant.POINT, Constant.SLASH));
        new File(path).mkdir();
        return LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
    }

}
