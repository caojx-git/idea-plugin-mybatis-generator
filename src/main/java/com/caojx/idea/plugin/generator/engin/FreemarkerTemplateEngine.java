package com.caojx.idea.plugin.generator.engin;

import com.caojx.idea.plugin.common.constants.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

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
    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) {
        try {
            Template template = configuration.getTemplate(templatePath);
            try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                template.process(objectMap, new OutputStreamWriter(fileOutputStream, Constant.UTF8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
