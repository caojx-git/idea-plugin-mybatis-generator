package com.caojx.idea.plugin.common.pojo.model;

import com.caojx.idea.plugin.common.utils.TypeHandler;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

/**
 * 属性
 */
public class Field {

    /**
     * 属性名
     */
    private final String name;

    /**
     * 字段列名
     */
    private final String columnName;

    /**
     * 注释
     */
    private final String comment;

    /**
     * 类型
     */
    private final Class<?> type;

    /**
     * jdbctype
     */
    private final String jdbcType;

    /**
     * 是否主键
     */
    private final boolean id;

    /**
     * 是否blob类型
     */
    private final boolean BLOB;

    /**
     * 构造器
     *
     * @param columnName 字段列名
     * @param comment    注释
     * @param sqlType    类型
     * @param id         是否主键
     */
    public Field(String columnName, String comment, int sqlType, boolean id) {
        this.comment = comment;
        this.columnName = columnName;
        this.name = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
        this.type = TypeHandler.convertJavaType(sqlType);
        this.jdbcType = TypeHandler.convertJdbcType(sqlType);
        this.id = id;
        this.BLOB = TypeHandler.isBLOB(sqlType);
    }

    public String getName() {
        return name;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getComment() {
        return comment;
    }

    public Class<?> getType() {
        return type;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public boolean isId() {
        return id;
    }

    public boolean isBLOB() {
        return BLOB;
    }

    public String getTypeSimpleName() {
        return type.getSimpleName();
    }

    public String getFullClazzName() {
        return type.getName();
    }

    public boolean isImport() {
        String fullClazzName = getFullClazzName();
        return !type.isPrimitive() && !"java.lang".equals(StringUtils.substringBeforeLast(fullClazzName, "."));
    }
}
