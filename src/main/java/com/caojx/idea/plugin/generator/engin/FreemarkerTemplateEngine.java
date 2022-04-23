package com.caojx.idea.plugin.generator.engin;

import com.caojx.idea.plugin.common.constants.Constant;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Objects;

/**
 * freemarker模板引擎
 *
 * @author caojx
 * @date 2022/4/16 10:59 AM
 */
public class FreemarkerTemplateEngine {

    private final Configuration configuration;

    public FreemarkerTemplateEngine() {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(Constant.UTF8);
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, Constant.SLASH);
    }

    /**
     * 生成文件
     *
     * @param objectMap    模板参数
     * @param templatePath 模板路径
     * @param outputFile   生成文件
     */
    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
        // 上级目录不存在，创建目录
        File file = new File(outputFile);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // 生成文件
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            Template template = configuration.getTemplate(templatePath);
            template.process(objectMap, new OutputStreamWriter(fileOutputStream, Constant.UTF8));
        }
    }
}
