package com.caojx.idea.plugin.generator;

import com.caojx.idea.plugin.common.constants.Constant;
import com.caojx.idea.plugin.common.context.GeneratorContext;
import com.caojx.idea.plugin.common.pojo.model.TableInfo;
import com.caojx.idea.plugin.common.properties.*;
import com.caojx.idea.plugin.generator.engin.FreemarkerTemplateEngine;
import com.google.common.base.CaseFormat;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成文件抽象类
 *
 * @author caojx
 * @date 2022/4/4
 */
public abstract class AbstractGeneratorService implements IGeneratorService {

    private final FreemarkerTemplateEngine freemarkerTemplateEngine = new FreemarkerTemplateEngine();

    @Override
    public void doGenerator(Project project, GeneratorContext generatorContext) {
        for (TableInfo table : generatorContext.getTables()) {

            // 获取模板数据
            Map<String, Object> objectMap = getObjectMap(generatorContext, table);

            // 生成配置信息
            GeneratorProperties generatorProperties = generatorContext.getGeneratorProperties();

            // entity
            EntityProperties entityProperties = generatorProperties.getEntityProperties();
            if (entityProperties.getEntityGenerateValue()) {
                String entityFile = entityProperties.getEntityPath() + File.separator + objectMap.get("entityName") + Constant.JAVA_SUFFIX;
                freemarkerTemplateEngine.writer(objectMap, objectMap.get("entityTemplatePath").toString(), entityFile);
            }

            // entityExample
            MapperProperties mapperProperties = generatorProperties.getMapperProperties();
            if (isGenerateEntityExample(mapperProperties)) {
                String entityExampleFile = entityProperties.getEntityPath() + File.separator + objectMap.get("entityExampleName") + Constant.JAVA_SUFFIX;
                freemarkerTemplateEngine.writer(objectMap, objectMap.get("entityExampleTemplatePath").toString(), entityExampleFile);
            }

            // mapper
            if (mapperProperties.getMapperGenerateValue()) {
                String mapperFile = entityProperties.getEntityPath() + File.separator + objectMap.get("mapperName") + Constant.JAVA_SUFFIX;
                freemarkerTemplateEngine.writer(objectMap, objectMap.get("mapperTemplatePath").toString(), mapperFile);
            }

            // mapperXml
            MapperXmlProperties mapperXmlProperties = generatorProperties.getMapperXmlProperties();
            if (mapperXmlProperties.getMapperXmlGenerateValue()) {
                String mapperXmlFile = entityProperties.getEntityPath() + File.separator + objectMap.get("mapperXmlName") + Constant.XML_SUFFIX;
                freemarkerTemplateEngine.writer(objectMap, objectMap.get("mapperXmlTemplatePath").toString(), mapperXmlFile);
            }

            // service
            ServiceProperties serviceProperties = generatorProperties.getServiceProperties();
            if (serviceProperties.getServiceGenerateValue()) {
                String serviceFile = entityProperties.getEntityPath() + File.separator + objectMap.get("serviceName") + Constant.JAVA_SUFFIX;
                freemarkerTemplateEngine.writer(objectMap, objectMap.get("serviceTemplatePath").toString(), serviceFile);
            }

            // serviceImpl
            ServiceImplProperties serviceImplProperties = generatorProperties.getServiceImplProperties();
            if (serviceImplProperties.getServiceImplGenerateValue()) {
                String serviceImplFile = entityProperties.getEntityPath() + File.separator + objectMap.get("serviceImplName") + Constant.JAVA_SUFFIX;
                freemarkerTemplateEngine.writer(objectMap, objectMap.get("serviceImplTemplatePath").toString(), serviceImplFile);
            }

            // controller
            ControllerProperties controllerProperties = generatorProperties.getControllerProperties();
            if (controllerProperties.getControllerGenerateValue()) {
                String controllerFile = entityProperties.getEntityPath() + File.separator + objectMap.get("controllerName") + Constant.JAVA_SUFFIX;
                freemarkerTemplateEngine.writer(objectMap, objectMap.get("controllerTemplatePath").toString(), controllerFile);
            }
        }
    }

    /**
     * 模板数据
     *
     * @param generatorContext 上线文信息
     * @param tableInfo        表信息
     * @return 模板数据
     */
    public Map<String, Object> getObjectMap(GeneratorContext generatorContext, TableInfo tableInfo) {
        Map<String, Object> objectMap = new HashMap<>();

        GeneratorProperties generatorProperties = generatorContext.getGeneratorProperties();

        // 表名转基础驼峰
        String baseEntityName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableInfo.getName());

        // 表信息
        objectMap.put("table", tableInfo);

        // entity
        EntityProperties entityProperties = generatorProperties.getEntityProperties();
        String entityName = String.format(entityProperties.getEntityNamePattern(), baseEntityName);
        objectMap.put("entityTemplatePath", Constant.ENTITY_TEMPLATE_PATH);
        objectMap.put("entityPackage", entityProperties.getEntityPackage());
        objectMap.put("entityName", entityName);
        objectMap.put("entityFullClazzName", fullClazzName(entityProperties.getEntityPackage(), entityName));
        objectMap.put("entityImportPackages", tableInfo.getImportPackages());
        objectMap.put("dataCheckBoxValue", entityProperties.getDataCheckBoxValue());
        objectMap.put("builderCheckBoxValue", entityProperties.getBuilderCheckBoxValue());
        objectMap.put("noArgsConstructorCheckBoxValue", entityProperties.getNoArgsConstructorCheckBoxValue());
        objectMap.put("allArgsConstructorCheckBoxValue", entityProperties.getAllArgsConstructorCheckBoxValue());
        objectMap.put("generateGetterSetter", isGenerateGetterSetter(entityProperties));


        // entityExample
        String entityExampleName = String.format(entityProperties.getEntityExampleNamePattern(), baseEntityName);
        objectMap.put("generateEntityExample", isGenerateEntityExample(generatorProperties.getMapperProperties()));
        objectMap.put("entityExampleTemplatePath", Constant.ENTITY_EXAMPLE_TEMPLATE_PATH);
        objectMap.put("entityExamplePackage", entityProperties.getEntityPackage());
        objectMap.put("entityExampleName", entityExampleName);
        objectMap.put("entityExampleFullClazzName", fullClazzName(entityProperties.getEntityPackage(), entityExampleName));

        // mapper
        MapperProperties mapperProperties = generatorProperties.getMapperProperties();
        String mapperName = String.format(mapperProperties.getMapperNamePattern(), baseEntityName);
        objectMap.put("mapperTemplatePath", Constant.MAPPER_TEMPLATE_PATH);
        objectMap.put("mapperPackage", mapperProperties.getMapperPackage());
        objectMap.put("mapperName", mapperName);
        objectMap.put("mapperFullClazzName", fullClazzName(mapperProperties.getMapperPackage(), mapperName));
        objectMap.put("superMapperClass", mapperProperties.getSuperMapperClass());
        objectMap.put("superMapperClassName", StringUtils.substringAfterLast(mapperProperties.getSuperMapperClass(), "."));
        objectMap.put("superMapperClassPackage", StringUtils.substringBeforeLast(mapperProperties.getSuperMapperClass(), "."));
        objectMap.put("selectByExampleWithBLOBsCheckBoxValue", mapperProperties.getSelectByExampleWithBLOBsCheckBoxValue());
        objectMap.put("selectByExampleCheckBoxValue", mapperProperties.getSelectByExampleCheckBoxValue());
        objectMap.put("selectByPrimaryKeyCheckBoxValue", mapperProperties.getSelectByPrimaryKeyCheckBoxValue());
        objectMap.put("insertCheckBoxValue", mapperProperties.getInsertCheckBoxValue());
        objectMap.put("insertSelectiveCheckBoxValue", mapperProperties.getInsertSelectiveCheckBoxValue());
        objectMap.put("countByExampleCheckBoxValue", mapperProperties.getCountByExampleCheckBoxValue());
        objectMap.put("updateByExampleCheckBoxValue", mapperProperties.getUpdateByExampleCheckBoxValue());
        objectMap.put("updateByExampleSelectiveCheckBoxValue", mapperProperties.getUpdateByExampleSelectiveCheckBoxValue());
        objectMap.put("updateByPrimaryKeyCheckBoxValue", mapperProperties.getUpdateByPrimaryKeyCheckBoxValue());
        objectMap.put("updateByPrimaryKeySelectiveCheckBoxValue", mapperProperties.getUpdateByPrimaryKeySelectiveCheckBoxValue());
        objectMap.put("updateByExampleWithBLOBsCheckBoxValue", mapperProperties.getUpdateByExampleWithBLOBsCheckBoxValue());
        objectMap.put("updateByPrimaryKeyWithBLOBsCheckBoxValue", mapperProperties.getUpdateByPrimaryKeyWithBLOBsCheckBoxValue());
        objectMap.put("deleteByExampleCheckBoxValue", mapperProperties.getDeleteByExampleCheckBoxValue());
        objectMap.put("deleteByPrimaryKeyCheckBoxValue", mapperProperties.getDeleteByPrimaryKeyCheckBoxValue());

        // mapperXml
        MapperXmlProperties mapperXmlProperties = generatorProperties.getMapperXmlProperties();
        String mapperXmlName = String.format(mapperXmlProperties.getMapperXmlNamePattern(), baseEntityName);
        objectMap.put("mapperXmlTemplatePath", Constant.MAPPER_XML_TEMPLATE_PATH);
        objectMap.put("mapperXmlName", mapperXmlName);

        // service
        ServiceProperties serviceProperties = generatorProperties.getServiceProperties();
        String serviceName = String.format(serviceProperties.getServiceNamePattern(), baseEntityName);
        objectMap.put("serviceTemplatePath", Constant.SERVICE_TEMPLATE_PATH);
        objectMap.put("servicePackage", serviceProperties.getServicePackage());
        objectMap.put("serviceName", serviceName);
        objectMap.put("serviceFullClazzName", fullClazzName(serviceProperties.getServicePackage(), serviceName));
        objectMap.put("superServiceClass", serviceProperties.getSuperServiceClass());
        objectMap.put("superServiceClassName", StringUtils.substringAfterLast(serviceProperties.getSuperServiceClass(), "."));
        objectMap.put("superServiceClassPackage", StringUtils.substringBeforeLast(serviceProperties.getSuperServiceClass(), "."));

        // serviceImpl
        ServiceImplProperties serviceImplProperties = generatorProperties.getServiceImplProperties();
        String serviceImplName = String.format(serviceImplProperties.getServiceImplNamePattern(), baseEntityName);
        objectMap.put("serviceImplTemplatePath", Constant.SERVICE_IMPL_TEMPLATE_PATH);
        objectMap.put("serviceImplPackage", serviceImplProperties.getServiceImplPackage());
        objectMap.put("serviceImplName", serviceImplName);
        objectMap.put("serviceImplFullClazzName", fullClazzName(serviceImplProperties.getServiceImplPackage(), serviceImplName));
        objectMap.put("superServiceImplClass", serviceImplProperties.getSuperServiceImplClass());
        objectMap.put("superServiceImplClassName", StringUtils.substringAfterLast(serviceImplProperties.getSuperServiceImplClass(), "."));
        objectMap.put("superServiceImplClassPackage", StringUtils.substringBeforeLast(serviceImplProperties.getSuperServiceImplClass(), "."));

        // controller
        ControllerProperties controllerProperties = generatorProperties.getControllerProperties();
        String controllerName = String.format(controllerProperties.getControllerNamePattern(), baseEntityName);
        objectMap.put("controllerTemplatePath", Constant.CONTROLLER_TEMPLATE_PATH);
        objectMap.put("controllerPackage", controllerProperties.getControllerPackage());
        objectMap.put("controllerName", controllerName);
        objectMap.put("controllerFullClazzName", fullClazzName(controllerProperties.getControllerPackage(), controllerName));
        objectMap.put("controllerSwaggerCheckBoxValue", controllerProperties.getControllerSwaggerCheckBoxValue());
        objectMap.put("controllerMappingHyphen", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, tableInfo.getName()));
        return objectMap;
    }

    /**
     * 全类名
     *
     * @param packageName 包名
     * @param clazzName   类名
     * @return 全类名
     */
    private String fullClazzName(String packageName, String clazzName) {
        return packageName + Constant.POINT + clazzName;
    }

    /**
     * 是否生成GetterSetter
     *
     * @param entityProperties entity配置
     * @return true 生成、false 不生成
     */
    private boolean isGenerateGetterSetter(EntityProperties entityProperties) {
        return !entityProperties.getDataCheckBoxValue();
    }

    /**
     * 是否生成 EntityExample
     *
     * @param mapperProperties mapper 配置信息
     * @return true 生成、false 不生成
     */
    private boolean isGenerateEntityExample(MapperProperties mapperProperties) {
        return mapperProperties.getSelectByExampleWithBLOBsCheckBoxValue()
                || mapperProperties.getSelectByExampleCheckBoxValue()
                || mapperProperties.getCountByExampleCheckBoxValue()
                || mapperProperties.getUpdateByExampleCheckBoxValue()
                || mapperProperties.getUpdateByExampleSelectiveCheckBoxValue()
                || mapperProperties.getUpdateByExampleWithBLOBsCheckBoxValue()
                || mapperProperties.getDeleteByExampleCheckBoxValue();
    }
}
