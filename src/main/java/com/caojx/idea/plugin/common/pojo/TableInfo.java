package com.caojx.idea.plugin.common.pojo;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 表模型
 *
 * @author caojx
 * @date 2022/4/10 4:00 PM
 */
public class TableInfo implements Serializable {

    /**
     * 表名
     */
    private String name;

    /**
     * 表注释
     */
    private String comment;

    /**
     * 属性列表
     */
    private List<TableField> fields;

    /**
     * 是否有主键
     */
    private boolean havePrimaryKey;

    /**
     * 主键id名称
     */
    private String primaryKeyName;

    /**
     * 主键类型
     */
    private Class<?> primaryKeyType;

    /**
     * 是否有Date属性
     */
    private boolean haveDateField;

    /**
     * 是否有blob属性
     */
    private boolean haveBlobField;

    /**
     * blob属性列表
     */
    private List<TableField> blobFields;

    /**
     * 构造器
     */
    public TableInfo() {
    }

    /**
     * 构造器
     *
     * @param name    表名
     * @param comment 表注释
     * @param fields  表属性列表
     */
    public TableInfo(String name, String comment, List<TableField> fields) {
        this.name = name;
        this.comment = comment;
        this.fields = fields;

        // 主键类型
        TableField primaryKeyField = Optional.ofNullable(fields).orElse(new ArrayList<>()).stream().filter(TableField::isPrimaryKeyFlag).findAny().orElse(null);
        if (Objects.nonNull(primaryKeyField)) {
            this.havePrimaryKey = true;
            this.primaryKeyName = primaryKeyField.getName();
            this.primaryKeyType = primaryKeyField.getType();
        }

        // 是否含有date属性
        this.haveDateField = Optional.ofNullable(fields).orElse(new ArrayList<>()).stream().allMatch(TableField::isDataFlag);

        // 是否含有blob属性
        this.blobFields = Optional.ofNullable(fields).orElse(new ArrayList<>()).stream().filter(TableField::isBlobFlag).collect(Collectors.toList());
        this.haveBlobField = !this.blobFields.isEmpty();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<TableField> getFields() {
        return fields;
    }

    public void setFields(List<TableField> fields) {
        this.fields = fields;
    }

    public boolean isHavePrimaryKey() {
        return havePrimaryKey;
    }

    public void setHavePrimaryKey(boolean havePrimaryKey) {
        this.havePrimaryKey = havePrimaryKey;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public Class<?> getPrimaryKeyType() {
        return primaryKeyType;
    }

    public void setPrimaryKeyType(Class<?> primaryKeyType) {
        this.primaryKeyType = primaryKeyType;
    }

    public boolean isHaveDateField() {
        return haveDateField;
    }

    public void setHaveDateField(boolean haveDateField) {
        this.haveDateField = haveDateField;
    }

    public boolean isHaveBlobField() {
        return haveBlobField;
    }

    public void setHaveBlobField(boolean haveBlobField) {
        this.haveBlobField = haveBlobField;
    }

    public List<TableField> getBlobFields() {
        return blobFields;
    }

    public void setBlobFields(List<TableField> blobFields) {
        this.blobFields = blobFields;
    }

    /**
     * 获取实体导包
     *
     * @return 实体导包列表
     */
    public Set<String> getImportPackages() {
        Set<String> imports = new HashSet<>();
        for (TableField field : this.fields) {
            if (field.isImport()) {
                imports.add(field.getFullClassName());
            }
        }
        return imports;
    }
}
