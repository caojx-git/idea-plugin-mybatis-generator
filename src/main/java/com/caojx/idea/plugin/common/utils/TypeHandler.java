package com.caojx.idea.plugin.common.utils;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类型处理
 *
 * @author caojx
 * @date 2022/4/10 12:20 PM
 */
public class TypeHandler {

    private static final ConcurrentHashMap<JDBCType, Class<?>> JDBC_JAVA_TYPE_MAPPING = new ConcurrentHashMap<>();

    static {
        // jdbc与java类型映射
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.VARCHAR, String.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.LONGVARCHAR, String.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.CHAR, String.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.INTEGER, Integer.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.BIGINT, Long.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.SMALLINT, Integer.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.TINYINT, Integer.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.FLOAT, Float.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.DOUBLE, Double.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.DECIMAL, BigDecimal.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.BOOLEAN, Boolean.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.DATE, Date.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.TIME, Date.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.TIMESTAMP, Date.class);
        JDBC_JAVA_TYPE_MAPPING.put(JDBCType.BIT, boolean.class);
    }

    /**
     * sqlType 转为javaType
     *
     * @param sqlType
     * @return javaType
     */
    public static Class<?> convertJavaType(int sqlType) {
        return JDBC_JAVA_TYPE_MAPPING.getOrDefault(JDBCType.valueOf(sqlType), Object.class);
    }

    /**
     * javaType 转jdbcType
     *
     * @param sqlType
     * @return jdbcType
     */
    public static String convertJdbcType(int sqlType) {
        return JDBCType.valueOf(sqlType).name();
    }

    /**
     * 是否Date类型
     *
     * @param sqlType
     * @return true 是、false 不是
     */
    public static boolean isDate(int sqlType) {
        List<JDBCType> dates = Arrays.asList(JDBCType.DATE, JDBCType.TIME, JDBCType.TIMESTAMP, JDBCType.TIME_WITH_TIMEZONE, JDBCType.TIMESTAMP_WITH_TIMEZONE);
        JDBCType jdbcType = JDBCType.valueOf(sqlType);
        return dates.contains(jdbcType);
    }

    /**
     * 是否BLOB类型
     *
     * @param sqlType
     * @return true 是、false 不是
     */
    public static boolean isBLOB(int sqlType) {
        List<JDBCType> blobs = Arrays.asList(JDBCType.LONGVARCHAR, JDBCType.LONGNVARCHAR, JDBCType.LONGVARBINARY, JDBCType.BLOB, JDBCType.CLOB, JDBCType.NCLOB);
        JDBCType jdbcType = JDBCType.valueOf(sqlType);
        return blobs.contains(jdbcType);
    }

}
