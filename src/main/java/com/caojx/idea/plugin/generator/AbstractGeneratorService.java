package com.caojx.idea.plugin.generator;

import com.caojx.idea.plugin.common.constants.Constant;
import com.caojx.idea.plugin.common.pojo.TableInfo;
import com.caojx.idea.plugin.common.properties.*;
import com.caojx.idea.plugin.common.utils.ClassUtils;
import com.caojx.idea.plugin.common.utils.MyMessages;
import com.caojx.idea.plugin.generator.engin.FreemarkerTemplateEngine;
import com.google.common.base.CaseFormat;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
            if (entityProperties.isSelectedGenerateCheckBox()) {
                String entityFile = entityProperties.getPath() + File.separator + objectMap.get("entityName") + Constant.JAVA_SUFFIX;
                generatorFile(project, objectMap, objectMap.get("entityTemplatePath").toString(), entityFile);
            }

            // entityExample
            if (entityProperties.isSelectedGenerateEntityExampleCheckBox()) {
                String entityExampleFile = entityProperties.getPath() + File.separator + objectMap.get("entityExampleName") + Constant.JAVA_SUFFIX;
                generatorFile(project, objectMap, objectMap.get("entityExampleTemplatePath").toString(), entityExampleFile);
            }

            // mapper
            MapperProperties mapperProperties = generatorProperties.getMapperProperties();
            if (mapperProperties.isSelectedGenerateCheckBox()) {
                String mapperFile = mapperProperties.getPath() + File.separator + objectMap.get("mapperName") + Constant.JAVA_SUFFIX;
                generatorFile(project, objectMap, objectMap.get("mapperTemplatePath").toString(), mapperFile);
            }

            // mapperXml
            MapperXmlProperties mapperXmlProperties = generatorProperties.getMapperXmlProperties();
            if (mapperXmlProperties.isSelectedGenerateCheckBox()) {
                String mapperXmlFile = mapperXmlProperties.getPath() + File.separator + objectMap.get("mapperXmlName") + Constant.XML_SUFFIX;
                generatorFile(project, objectMap, objectMap.get("mapperXmlTemplatePath").toString(), mapperXmlFile);
            }

            // service
            ServiceProperties serviceProperties = generatorProperties.getServiceProperties();
            if (serviceProperties.isSelectedGenerateCheckBox()) {
                String serviceFile = serviceProperties.getPath() + File.separator + objectMap.get("serviceName") + Constant.JAVA_SUFFIX;
                generatorFile(project, objectMap, objectMap.get("serviceTemplatePath").toString(), serviceFile);
            }

            // serviceImpl
            ServiceImplProperties serviceImplProperties = generatorProperties.getServiceImplProperties();
            if (serviceImplProperties.isSelectedGenerateCheckBox()) {
                String serviceImplFile = serviceImplProperties.getPath() + File.separator + objectMap.get("serviceImplName") + Constant.JAVA_SUFFIX;
                generatorFile(project, objectMap, objectMap.get("serviceImplTemplatePath").toString(), serviceImplFile);
            }

            // controller
            ControllerProperties controllerProperties = generatorProperties.getControllerProperties();
            if (controllerProperties.isSelectedGenerateCheckBox()) {
                String controllerFile = controllerProperties.getPath() + File.separator + objectMap.get("controllerName") + Constant.JAVA_SUFFIX;
                generatorFile(project, objectMap, objectMap.get("controllerTemplatePath").toString(), controllerFile);
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

        // 公共配置
        CommonProperties commonProperties = generatorProperties.getCommonProperties();
        objectMap.put("author", commonProperties.getAuthor());
        objectMap.put("frameworkType", commonProperties.getFrameworkTypeComboBoxValue());

        // entity
        EntityProperties entityProperties = generatorProperties.getEntityProperties();
        String entityName = String.format(entityProperties.getNamePattern(), baseEntityName);
        objectMap.put("entityTemplatePath", Constant.ENTITY_TEMPLATE_PATH);
        objectMap.put("entityPackage", entityProperties.getPackageName());
        objectMap.put("entityName", entityName);
        objectMap.put("entityFullClassName", ClassUtils.getFullClassName(entityProperties.getPackageName(), entityName));
        objectMap.put("entityImportPackages", tableInfo.getImportPackages());
        objectMap.put("isSelectedSerializableCheckBox", entityProperties.isSelectedSerializableCheckBox());
        objectMap.put("isSelectedDataCheckBox", entityProperties.isSelectedDataCheckBox());
        objectMap.put("isSelectedBuilderCheckBox", entityProperties.isSelectedBuilderCheckBox());
        objectMap.put("isSelectedNoArgsConstructorCheckBox", entityProperties.isSelectedNoArgsConstructorCheckBox());
        objectMap.put("isSelectedAllArgsConstructorCheckBox", entityProperties.isSelectedAllArgsConstructorCheckBox());
        objectMap.put("isGenerateGetterSetter", isGenerateGetterSetter(entityProperties));
        objectMap.put("isSelectedEntitySwaggerCheckBox", entityProperties.isSelectedSwaggerCheckBox());

        // entityExample
        String entityExampleName = String.format(entityProperties.getExampleNamePattern(), baseEntityName);
        objectMap.put("entityExampleTemplatePath", Constant.ENTITY_EXAMPLE_TEMPLATE_PATH);
        objectMap.put("entityExamplePackage", entityProperties.getPackageName());
        objectMap.put("entityExampleName", entityExampleName);
        objectMap.put("entityExampleFullClassName", ClassUtils.getFullClassName(entityProperties.getPackageName(), entityExampleName));

        // mapper
        MapperProperties mapperProperties = generatorProperties.getMapperProperties();
        String mapperName = String.format(mapperProperties.getNamePattern(), baseEntityName);
        objectMap.put("mapperTemplatePath", Constant.MAPPER_TEMPLATE_PATH);
        objectMap.put("mapperPackage", mapperProperties.getPackageName());
        objectMap.put("mapperName", mapperName);
        objectMap.put("mapperFullClassName", ClassUtils.getFullClassName(mapperProperties.getPackageName(), mapperName));
        objectMap.put("superMapperClass", mapperProperties.getSuperMapperClass());
        objectMap.put("superMapperClassName", ClassUtils.getClassNameByFullClassName(mapperProperties.getSuperMapperClass()));
        objectMap.put("superMapperClassPackage", ClassUtils.getPackageNameByFullClassName(mapperProperties.getSuperMapperClass()));
        objectMap.put("isSelectedEnableInsertCheckBox", mapperProperties.isSelectedEnableInsertCheckBox());
        objectMap.put("isSelectedEnableSelectByPrimaryKeyCheckBox", mapperProperties.isSelectedEnableSelectByPrimaryKeyCheckBox());
        objectMap.put("isSelectedEnableSelectByExampleCheckBox", mapperProperties.isSelectedEnableSelectByExampleCheckBox());
        objectMap.put("isSelectedEnableUpdateByPrimaryKeyCheckBox", mapperProperties.isSelectedEnableUpdateByPrimaryKeyCheckBox());
        objectMap.put("isSelectedEnableUpdateByExampleCheckBox", mapperProperties.isSelectedEnableUpdateByExampleCheckBox());
        objectMap.put("isSelectedEnableDeleteByPrimaryKeyCheckBox", mapperProperties.isSelectedEnableDeleteByPrimaryKeyCheckBox());
        objectMap.put("isSelectedEnableDeleteByExampleCheckBox", mapperProperties.isSelectedEnableDeleteByExampleCheckBox());
        objectMap.put("isSelectedEnableCountByExampleCheckBox", mapperProperties.isSelectedEnableCountByExampleCheckBox());

        // mapperXml
        MapperXmlProperties mapperXmlProperties = generatorProperties.getMapperXmlProperties();
        String mapperXmlName = String.format(mapperXmlProperties.getNamePattern(), baseEntityName);
        objectMap.put("mapperXmlTemplatePath", Constant.MAPPER_XML_TEMPLATE_PATH);
        objectMap.put("mapperXmlName", mapperXmlName);

        // service
        ServiceProperties serviceProperties = generatorProperties.getServiceProperties();
        String serviceName = String.format(serviceProperties.getNamePattern(), baseEntityName);
        objectMap.put("serviceTemplatePath", Constant.SERVICE_TEMPLATE_PATH);
        objectMap.put("servicePackage", serviceProperties.getPackageName());
        objectMap.put("serviceName", serviceName);
        objectMap.put("serviceFullClassName", ClassUtils.getFullClassName(serviceProperties.getPackageName(), serviceName));
        objectMap.put("superServiceClass", serviceProperties.getSuperServiceClass());
        objectMap.put("superServiceClassName", ClassUtils.getClassNameByFullClassName(serviceProperties.getSuperServiceClass()));
        objectMap.put("superServiceClassPackage", ClassUtils.getPackageNameByFullClassName(serviceProperties.getSuperServiceClass()));

        // serviceImpl
        ServiceImplProperties serviceImplProperties = generatorProperties.getServiceImplProperties();
        String serviceImplName = String.format(serviceImplProperties.getNamePattern(), baseEntityName);
        objectMap.put("serviceImplTemplatePath", Constant.SERVICE_IMPL_TEMPLATE_PATH);
        objectMap.put("serviceImplPackage", serviceImplProperties.getPackageName());
        objectMap.put("serviceImplName", serviceImplName);
        objectMap.put("serviceImplFullClassName", ClassUtils.getFullClassName(serviceImplProperties.getPackageName(), serviceImplName));
        objectMap.put("superServiceImplClass", serviceImplProperties.getSuperServiceImplClass());
        objectMap.put("superServiceImplClassName", ClassUtils.getClassNameByFullClassName(serviceImplProperties.getSuperServiceImplClass()));
        objectMap.put("superServiceImplClassPackage", ClassUtils.getPackageNameByFullClassName(serviceImplProperties.getSuperServiceImplClass()));

        // controller
        ControllerProperties controllerProperties = generatorProperties.getControllerProperties();
        String controllerName = String.format(controllerProperties.getNamePattern(), baseEntityName);
        objectMap.put("controllerTemplatePath", Constant.CONTROLLER_TEMPLATE_PATH);
        objectMap.put("controllerPackage", controllerProperties.getPackageName());
        objectMap.put("controllerName", controllerName);
        objectMap.put("controllerFullClassName", ClassUtils.getFullClassName(controllerProperties.getPackageName(), controllerName));
        objectMap.put("isSelectedSwaggerCheckBox", controllerProperties.isSelectedSwaggerCheckBox());
        objectMap.put("controllerMappingHyphen", CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, tableInfo.getName()));
        return objectMap;
    }

    /**
     * 生成文件
     *
     * @param project      项目
     * @param objectMap    模板参数
     * @param templatePath 模板路径
     * @param outputFile   生成文件
     */
    public void generatorFile(Project project, Map<String, Object> objectMap, String templatePath, String outputFile) {
        try {
            // 生成文件
            freemarkerTemplateEngine.writer(objectMap, templatePath, outputFile);

            // 刷新文件
            VirtualFile virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(new File(outputFile));
            if (Objects.nonNull(virtualFile)) {
                virtualFile.refresh(false, true);
            }
        } catch (Exception e) {
            MyMessages.showErrorNotify(project, outputFile + "文件生成失败" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 是否生成GetterSetter
     *
     * @param entityProperties entity配置
     * @return true 生成、false 不生成
     */
    private boolean isGenerateGetterSetter(EntityProperties entityProperties) {
        return !entityProperties.isSelectedDataCheckBox();
    }
}
