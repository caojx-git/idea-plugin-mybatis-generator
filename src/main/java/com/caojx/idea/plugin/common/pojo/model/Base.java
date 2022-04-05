package com.caojx.idea.plugin.common.pojo.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * 基础类
 */
public abstract class Base {

    /**
     * 注释
     */
    private final String comment;

    /**
     * 全类名
     */
    private final String fullClazzName;

    /**
     * 构造器
     *
     * @param comment       注释
     * @param fullClazzName 全类名
     */
    public Base(String comment, String fullClazzName) {
        this.comment = comment;
        this.fullClazzName = fullClazzName;
    }

    public String getComment() {
        return comment;
    }

    public String getFullClazzName() {
        return fullClazzName;
    }

    /**
     * 获取包名
     *
     * @return 包名
     */
    public String getPackage() {
        return StringUtils.substringBeforeLast(fullClazzName, ".");
    }

    /**
     * 获取简单类名
     *
     * @return 获取简单类名
     */
    public String getSimpleName() {
        return StringUtils.substringAfterLast(fullClazzName, ".");
    }

    /**
     * 获取导包列表
     *
     * @return 导包列表
     */
    public abstract Set<String> getImports();
}
