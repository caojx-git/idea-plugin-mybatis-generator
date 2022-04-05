package com.caojx.idea.plugin.common.pojo.model;

import com.google.common.base.CaseFormat;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Entity数据模型
 */
public class Entity extends Base {

    /**
     * 表名
     */
    private final String tableName;

    /**
     * 属性列表
     */
    private final List<Field> fields;

    /**
     * 主键类型
     */
    private Class<?> idType;

    /**
     * 是否包含BLOB属性
     */
    private final boolean containBLOBField;

    /**
     * BLOB属性列表
     */
    private final List<Field> BLOBFields;

    /**
     * 构造器
     *
     * @param tableName     表名
     * @param comment       注释
     * @param fullClazzName 全类名
     * @param fields        属性列表
     */
    public Entity(String tableName, String comment, String fullClazzName, List<Field> fields) {
        super(comment, fullClazzName);
        this.tableName = tableName;
        this.fields = fields;

        Field idField = Optional.ofNullable(fields).orElse(new ArrayList<>()).stream().filter(Field::isId).findAny().orElse(null);
        this.idType = Long.class;
        if (Objects.nonNull(idField)) {
            this.idType = idField.getType();
        }

        this.BLOBFields = Optional.ofNullable(fields).orElse(new ArrayList<>()).stream().filter(Field::isBLOB).collect(Collectors.toList());
        containBLOBField = this.BLOBFields.size() > 0;
    }

    public String getTableName() {
        return tableName;
    }

    public List<Field> getFields() {
        return fields;
    }

    public String getVarName() {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, getSimpleName());
    }

    public boolean isContainBLOBField() {
        return containBLOBField;
    }

    public List<Field> getBLOBFields() {
        return BLOBFields;
    }

    /**
     * 返回主键类型
     *
     * @return 主键类型
     */
    public Class<?> getIdType() {
        return idType;
    }

    @Override
    public Set<String> getImports() {
        Set<String> imports = new HashSet<>();
        List<Field> fields = getFields();
        for (Field field : fields) {
            if (field.isImport()) {
                imports.add(field.getFullClazzName());
            }
        }
        return imports;
    }

}
