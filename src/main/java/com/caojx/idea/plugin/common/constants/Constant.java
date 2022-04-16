package com.caojx.idea.plugin.common.constants;

import java.nio.charset.StandardCharsets;

/**
 * 常量类
 *
 * @author caojx
 * @date 2022/4/10 4:00 PM
 */
public interface Constant {

    /**
     * java文件后缀
     */
    String JAVA_SUFFIX = ".java";

    /**
     * xml文件后缀
     */
    String XML_SUFFIX = ".xml";

    /**
     * 点
     */
    String POINT = ".";

    /**
     * 斜线
     */
    String SLASH = "/";

    /**
     * UTF-8
     */
    String UTF8 = StandardCharsets.UTF_8.name();

    /**
     * 实体默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String ENTITY_NAME_DEFAULT_FORMAT = "%sEntity";

    /**
     * 实体默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String ENTITY_EXAMPLE_NAME_DEFAULT_FORMAT = "%sExample";

    /**
     * mapper默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String MAPPER_NAME_DEFAULT_FORMAT = "%sMapper";

    /**
     * mapper.xml默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String MAPPER_XML_NAME_DEFAULT_FORMAT = "%sMapper";

    /**
     * service默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String SERVICE_NAME_DEFAULT_FORMAT = "I%sService";

    /**
     * serviceImpl默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String SERVICE_IMPL_NAME_DEFAULT_FORMAT = "%sServiceImpl";

    /**
     * controller默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String CONTROLLER_NAME_DEFAULT_FORMAT = "%sController";

    /**
     * entity模板路径
     */
    String ENTITY_TEMPLATE_PATH = "/templates/entity.ftl";

    /**
     * entityExample模板路径
     */
    String ENTITY_EXAMPLE_TEMPLATE_PATH = "/templates/entityExample.ftl";

    /**
     * mapper模板路径
     */
    String MAPPER_TEMPLATE_PATH = "/templates/mapper.ftl";

    /**
     * mapperXml实体模板路径
     */
    String MAPPER_XML_TEMPLATE_PATH = "/templates/mapperXml.ftl";

    /**
     * service模板路径
     */
    String SERVICE_TEMPLATE_PATH = "/templates/service.ftl";

    /**
     * serviceImpl 模板路径
     */
    String SERVICE_IMPL_TEMPLATE_PATH = "/templates/serviceImpl.ftl";

    /**
     * controller模板路径
     */
    String CONTROLLER_TEMPLATE_PATH = "/templates/controller.ftl";
}