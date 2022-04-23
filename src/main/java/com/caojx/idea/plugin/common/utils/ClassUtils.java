package com.caojx.idea.plugin.common.utils;

import com.caojx.idea.plugin.common.constants.Constant;
import org.apache.commons.lang3.StringUtils;

/**
 * 类处理工具类
 *
 * @author caojx
 * @date 2022/4/23 11:26 AM
 */
public class ClassUtils {

    /**
     * 获取全类名
     *
     * @param packageName 包名
     * @param className   类名
     * @return 全类名
     */
    public static String getFullClassName(String packageName, String className) {
        if(StringUtils.isBlank(packageName)){
            return className;
        }
        return packageName + Constant.POINT + className;
    }

    /**
     * 根据全类名获取类名
     *
     * @param fullClassName 全类名
     * @return 类名
     */
    public static String getClassNameByFullClassName(String fullClassName) {
        String className = StringUtils.substringAfterLast(fullClassName, ".");
        return StringUtils.isNotBlank(className) ? className : fullClassName;
    }

    /**
     * 根据全类名获取包名
     *
     * @param fullClassName 全类名
     * @return 包名
     */
    public static String getPackageNameByFullClassName(String fullClassName) {
        return StringUtils.substringBeforeLast(fullClassName, ".");
    }
}
