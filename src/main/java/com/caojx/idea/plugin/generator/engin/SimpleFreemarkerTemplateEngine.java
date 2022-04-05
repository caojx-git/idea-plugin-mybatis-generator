package com.caojx.idea.plugin.generator.engin;

import com.caojx.idea.plugin.common.constants.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;

/**
 * FreemarkerTemplateEngine
 *
 * @author caojx
 * @date 2022/4/4 5:28 PM
 */
public class SimpleFreemarkerTemplateEngine {

    private final Configuration configuration;

    public SimpleFreemarkerTemplateEngine() {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(Constant.UTF8);
        configuration.setClassForTemplateLoading(SimpleFreemarkerTemplateEngine.class, Constant.SLASH);
    }

    /**
     * 获取模板
     *
     * @param templatePath 模板路径
     * @return 模板
     * @throws IOException
     */
    public Template getTemplate(String templatePath) throws IOException {
        return configuration.getTemplate(templatePath);
    }

}
