package com.caojx.idea.plugin.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库类型枚举
 *
 * @author caojx
 * @date 2022/4/10 4:00 PM
 */
public enum DataBaseTypeEnum {

    MySQL,

//    Oracle,

    ;

    /**
     * 获取数据库类型
     *
     * @return 数据库类型列表
     */
    public static List<String> getDatabaseTypes() {
        List<String> list = new ArrayList<>();
        for (DataBaseTypeEnum dataBaseTypeEnum : DataBaseTypeEnum.values()) {
            list.add(dataBaseTypeEnum.name());
        }
        return list;
    }
}
