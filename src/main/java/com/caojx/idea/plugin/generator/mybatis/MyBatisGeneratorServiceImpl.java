package com.caojx.idea.plugin.generator.mybatis;

import com.caojx.idea.plugin.common.constants.Constant;
import com.caojx.idea.plugin.common.properties.GeneratorConfigContext;
import com.caojx.idea.plugin.common.properties.BaseGeneratorProperties;
import com.caojx.idea.plugin.common.pojo.model.Field;
import com.caojx.idea.plugin.common.pojo.model.Mapper;
import com.caojx.idea.plugin.common.pojo.model.Entity;
import com.caojx.idea.plugin.generator.AbstractGeneratorService;
import com.caojx.idea.plugin.common.pojo.db.Column;
import com.caojx.idea.plugin.common.pojo.db.Table;
import com.google.common.base.CaseFormat;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis代码生成
 *
 * @author caojx
 * @date 2022/4/4 12:57 PM
 */
public class MyBatisGeneratorServiceImpl extends AbstractGeneratorService {

    @Override
    public void doGenerator(Project project, GeneratorConfigContext generatorConfigContext) {
        for (Table table : generatorConfigContext.getTables()) {
            List<Column> columns = table.getColumns();
            List<Field> fields = new ArrayList<>();

            for (Column column : columns) {
                Field field = new Field(column.getName(), column.getComment(), column.getType(), column.isId());
                fields.add(field);
            }

            BaseGeneratorProperties myBatisGeneratorConfig = generatorConfigContext.getBaseGeneratorProperties();

            String baseEntityName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName());

            // 生成Entity
            String entityName = String.format(Constant.ENTITY_NAME_DEFAULT_FORMAT, baseEntityName);
            if (StringUtils.isNotBlank(myBatisGeneratorConfig.getEntityNamePattern())) {
                entityName = String.format(myBatisGeneratorConfig.getEntityNamePattern(), baseEntityName);
            }
            Entity entity = new Entity(table.getName(), table.getComment(), myBatisGeneratorConfig.getEntityPackage() + Constant.POINT + entityName, fields);
            writeFile(project, myBatisGeneratorConfig.getEntityPath(), entityName + Constant.JAVA_SUFFIX, "/templates/mybatis/entity.ftl", entity);

            // 生成entityExample
            String entityExampleName = String.format(Constant.ENTITY_EXAMPLE_NAME_DEFAULT_FORMAT, baseEntityName);
            writeFile(project, myBatisGeneratorConfig.getEntityPath(), entityExampleName + Constant.JAVA_SUFFIX, "/templates/mybatis/entityExample.ftl", entity);

            // 生成Mapper
            String mapperName = String.format(Constant.MAPPER_NAME_DEFAULT_FORMAT, baseEntityName);
            if (StringUtils.isNotBlank(myBatisGeneratorConfig.getMapperNamePattern())) {
                mapperName = String.format(myBatisGeneratorConfig.getMapperNamePattern(), baseEntityName);
            }
            Mapper mapper = new Mapper(table.getComment(), myBatisGeneratorConfig.getMapperPackage() + Constant.POINT + mapperName, entity);
            writeFile(project, myBatisGeneratorConfig.getMapperPath(), mapperName + Constant.JAVA_SUFFIX, "/templates/mybatis/mapper.ftl", mapper);

            // 生成Mapper
            String mapperXmlName = String.format(Constant.MAPPER_NAME_DEFAULT_FORMAT, baseEntityName);
            if (StringUtils.isNotBlank(myBatisGeneratorConfig.getMapperXmlNamePattern())) {
                mapperXmlName = String.format(myBatisGeneratorConfig.getMapperXmlNamePattern(), baseEntityName);
            }
            writeFile(project, myBatisGeneratorConfig.getMapperXmlPath(), mapperXmlName + Constant.XML_SUFFIX, "/templates/mybatis/mapperXml.ftl", mapper);
        }
    }
}

