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
     * 插件名称
     */
    String PLUGIN_NAME = "MyBatisCodeGenerator";

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

    // 文件类型
    String FILE_TYPE_ENTITY = "entity";
    String FILE_TYPE_ENTITY_EXAMPLE = "entityExample";
    String FILE_TYPE_MAPPER = "mapper";
    String FILE_TYPE_MAPPER_XML = "mapperXml";
    String FILE_TYPE_SERVICE = "service";
    String FILE_TYPE_FILE_TYPE_SERVICE_IMPL = "serviceImpl";
    String FILE_TYPE_CONTROLLER = "controller";

    /**
     * 相对包名
     */
    String RELATIVE_PACKAGE = "entity";

    /**
     * 默认路径
     */
    String DEFAULT_BASE_PATH = "src/main/java";

    /**
     * 默认资源路径
     */
    String DEFAULT_BASE_RESOURCES_PATH = "src/main/resources";

    /**
     * 实体默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String DEFAULT_ENTITY_NAME_FORMAT = "%s";

    /**
     * 实体默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String DEFAULT_ENTITY_EXAMPLE_NAME_FORMAT = "%sExample";

    /**
     * mapper默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String DEFAULT_MAPPER_NAME_FORMAT = "%sMapper";

    /**
     * mapper.xml默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String DEFAULT_MAPPER_XML_NAME_FORMAT = "%sMapper";

    /**
     * service默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String DEFAULT_SERVICE_NAME_FORMAT = "I%sService";

    /**
     * serviceImpl默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String DEFAULT_SERVICE_IMPL_NAME_FORMAT = "%sServiceImpl";

    /**
     * controller默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String DEFAULT_CONTROLLER_NAME_FORMAT = "%sController";

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

    /**
     * tk mybatis 默认 superMapperClass
     */
    String DEFAULT_TK_MYBATIS_SUPER_MAPPER_CLASS = "tk.mybatis.mapper.common.Mapper";

    /**
     * mybatis plus 默认 superMapperClass
     */
    String DEFAULT_MYBATIS_PLUS_SUPER_MAPPER_CLASS = "com.baomidou.mybatisplus.core.mapper.BaseMapper";

    /**
     * mybatis plus 默认 superServiceClass
     */
    String DEFAULT_MYBATIS_PLUS_SUPER_SERVICE_CLASS = "com.baomidou.mybatisplus.extension.service.IService";

    /**
     * mybatis plus 默认 superServiceImplClass
     */
    String DEFAULT_MYBATIS_PLUS_SUPER_SERVICE_IMPL_CLASS = "com.baomidou.mybatisplus.extension.service.impl.ServiceImpl";

}
