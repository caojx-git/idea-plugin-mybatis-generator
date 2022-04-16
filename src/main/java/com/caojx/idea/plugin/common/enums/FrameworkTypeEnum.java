package com.caojx.idea.plugin.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成代码类型枚举
 *
 * @author caojx
 * @date 2022/4/10 1:58 PM
 */
public enum FrameworkTypeEnum {

    /**
     * 生成MyBatis风格类型代码
     */
    MYBATIS("MyBatis"),

    /**
     * 生成TkMyBatis风格类型代码
     */
    TK_MYBATIS("TkMyBatis"),

    /**
     * 生成MyBatisPlus风格类型代码
     */
    MYBATIS_PLUS("MyBatisPlus");

    /**
     * 框架类型
     */
    private final String frameworkName;

    FrameworkTypeEnum(String frameworkName) {
        this.frameworkName = frameworkName;
    }

    public String getFrameworkName() {
        return frameworkName;
    }

    /**
     * 获取框架类型列表
     *
     * @return 框架类型列表
     */
    public static List<String> getFrameworkNames() {
        List<String> list = new ArrayList<>();
        for (FrameworkTypeEnum frameworkTypeEnum : FrameworkTypeEnum.values()) {
            list.add(frameworkTypeEnum.getFrameworkName());
        }
        return list;
    }
}
