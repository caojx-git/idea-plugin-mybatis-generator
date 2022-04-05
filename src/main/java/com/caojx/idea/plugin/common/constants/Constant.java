package com.caojx.idea.plugin.common.constants;

import java.nio.charset.StandardCharsets;

/**
 * 常量类
 *
 * @author caojx created on 2021/4/6 10:56 上午
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
    String ENTITY_NAME_DEFAULT_FORMAT = "%s";

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
    String XML_NAME_DEFAULT_FORMAT = "%sMapper";

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
}