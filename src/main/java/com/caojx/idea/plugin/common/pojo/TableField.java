package com.caojx.idea.plugin.common.pojo;

import com.caojx.idea.plugin.common.utils.TypeHandler;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 表属性模型
 *
 * @author caojx
 * @date 2022/4/10 4:00 PM
 */
public class TableField implements Serializable {

    /**
     * 字段列名
     */
    private String columnName;

    /**
     * 注释
     */
    private String comment;

    /**
     * 属性名
     */
    private String name;

    /**
     * 类型
     */
    private Class<?> type;

    /**
     * jdbcType
     */
    private String jdbcTypeName;

    /**
     * 是否主键
     */
    private boolean primaryKeyFlag;

    /**
     * 是否为JDBCDateColumn
     */
    private boolean jdbcDateFlag;

    /**
     * 是否为JDBCTimeColumn
     */
    private boolean jdbcTimeFlag;

    /**
     * 是否blob类型
     */
    private boolean blobFlag;

    /**
     * 构造器
     */
    public TableField() {
    }

    /**
     * 构造器
     *
     * @param columnName     字段列名
     * @param comment        注释
     * @param sqlType        类型
     * @param primaryKeyFlag 是否主键
     */
    public TableField(String columnName, String comment, int sqlType, boolean primaryKeyFlag) {
        this.columnName = columnName;
        this.comment = comment;
        this.name = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
        this.type = TypeHandler.convertJavaType(sqlType);
        this.jdbcTypeName = TypeHandler.convertJdbcType(sqlType);
        this.primaryKeyFlag = primaryKeyFlag;
        this.jdbcDateFlag = TypeHandler.isJDBCDateColumn(sqlType);
        this.jdbcTimeFlag = TypeHandler.isJDBCTimeColumn(sqlType);
        this.blobFlag = TypeHandler.isBLOBColumn(sqlType);
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getJdbcTypeName() {
        return jdbcTypeName;
    }

    public void setJdbcTypeName(String jdbcTypeName) {
        this.jdbcTypeName = jdbcTypeName;
    }

    public boolean isPrimaryKeyFlag() {
        return primaryKeyFlag;
    }

    public void setPrimaryKeyFlag(boolean primaryKeyFlag) {
        this.primaryKeyFlag = primaryKeyFlag;
    }

    public boolean isJdbcDateFlag() {
        return jdbcDateFlag;
    }

    public void setJdbcDateFlag(boolean jdbcDateFlag) {
        this.jdbcDateFlag = jdbcDateFlag;
    }

    public boolean isJdbcTimeFlag() {
        return jdbcTimeFlag;
    }

    public void setJdbcTimeFlag(boolean jdbcTimeFlag) {
        this.jdbcTimeFlag = jdbcTimeFlag;
    }

    public boolean isBlobFlag() {
        return blobFlag;
    }

    public void setBlobFlag(boolean blobFlag) {
        this.blobFlag = blobFlag;
    }

    public String getTypeSimpleName() {
        return type.getSimpleName();
    }

    public String getFullClassName() {
        return type.getName();
    }

    public boolean isImport() {
        String fullClassName = getFullClassName();
        return !type.isPrimitive() && !"java.lang".equals(StringUtils.substringBeforeLast(fullClassName, ".")) && !"byte[]".equals(type.getSimpleName());
    }
}
