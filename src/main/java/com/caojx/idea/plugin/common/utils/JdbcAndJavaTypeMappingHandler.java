package com.caojx.idea.plugin.common.utils;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类型处理
 *
 * @author caojx
 * @date 2022/4/10 12:20 PM
 */
public class JdbcAndJavaTypeMappingHandler {

//    private static final ConcurrentHashMap<Integer, Class<?>> JDBC_JAVA_TYPE_MAPPING = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<JDBCType, Class<?>> JDBC_TYPE_JAVA_TYPE_MAPPING = new ConcurrentHashMap<>();

    static {
        // 参考：org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.ARRAY, Object.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.BIGINT, Long.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.BINARY, byte[].class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.BIT, Boolean.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.BLOB, byte[].class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.BOOLEAN, Boolean.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.CHAR, String.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.CLOB, String.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.DATALINK, Object.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.DATE, Date.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.DECIMAL, BigDecimal.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.DISTINCT, Object.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.DOUBLE, Double.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.FLOAT, Double.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.INTEGER, Integer.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.JAVA_OBJECT, Object.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.LONGNVARCHAR, String.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.LONGVARBINARY, byte[].class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.LONGVARCHAR, String.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.NCHAR, String.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.NCLOB, String.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.NVARCHAR, String.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.NULL, Object.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.NUMERIC, BigDecimal.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.REAL, Float.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.REF, Object.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.SMALLINT, Integer.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.STRUCT, Object.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.TIME, Date.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.TIMESTAMP, Date.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.TINYINT, Integer.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.VARBINARY, byte[].class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.VARCHAR, String.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.OTHER, byte[].class);
        // JDK 1.8 types
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.TIME_WITH_TIMEZONE, OffsetTime.class);
        JDBC_TYPE_JAVA_TYPE_MAPPING.put(JDBCType.TIMESTAMP_WITH_TIMEZONE, OffsetDateTime.class);

        // jdbc与java类型映射
//        JDBC_JAVA_TYPE_MAPPING.put(Types.ARRAY, Object.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.BIGINT, Long.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.BINARY, byte[].class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.BIT, Boolean.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.BLOB, byte[].class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.BOOLEAN, Boolean.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.CHAR, String.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.CLOB, String.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.DATALINK, Object.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.DATE, Date.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.DECIMAL, BigDecimal.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.DISTINCT, Object.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.DOUBLE, Double.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.FLOAT, Double.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.INTEGER, Integer.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.JAVA_OBJECT, Object.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.LONGNVARCHAR, String.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.LONGVARBINARY, byte[].class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.LONGVARCHAR, String.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.NCHAR, String.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.NCLOB, String.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.NVARCHAR, String.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.NULL, Object.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.NUMERIC, BigDecimal.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.REAL, Float.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.REF, Object.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.SMALLINT, Integer.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.STRUCT, Object.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.TIME, Date.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.TIMESTAMP, Date.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.TINYINT, Integer.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.VARBINARY, byte[].class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.VARCHAR, String.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.OTHER, byte[].class);
//        // JDK 1.8 types
//        JDBC_JAVA_TYPE_MAPPING.put(Types.TIME_WITH_TIMEZONE, OffsetTime.class);
//        JDBC_JAVA_TYPE_MAPPING.put(Types.TIMESTAMP_WITH_TIMEZONE, OffsetDateTime.class);
    }

    /**
     * sqlType 转为javaType
     *
     * @param sqlType
     * @return javaType
     */
    public static Class<?> convertJavaType(int sqlType) {
        JDBCType jdbcType = JDBCType.valueOf(sqlType);
        if (jdbcType == null) {
            return Object.class;
        }
//        return JDBC_JAVA_TYPE_MAPPING.getOrDefault(sqlType, Object.class);
        return JDBC_TYPE_JAVA_TYPE_MAPPING.getOrDefault(jdbcType, Object.class);
    }

    /**
     * javaType 转jdbcType
     *
     * @param sqlType
     * @return jdbcType
     */
    public static String convertJdbcType(int sqlType) {
        String name = JDBCType.valueOf(sqlType).name();
        if (JDBCType.OTHER.name().equals(name)) {
            return JDBCType.BINARY.name();
        }
        return name;
    }

    /**
     * 是否为isJDBCDateColumn
     * 参考：org.mybatis.generator.api.IntrospectedColumn#isJDBCDateColumn
     *
     * @param sqlType
     * @return true 是、false 不是
     */
    public static boolean isJDBCDateColumn(int sqlType) {
        return Date.class.equals(convertJavaType(sqlType)) && Types.DATE == sqlType;
    }

    /**
     * 是否isJDBCTimeColumn
     * 参考：org.mybatis.generator.api.IntrospectedColumn#isJDBCTimeColumn
     *
     * @param sqlType
     * @return true 是、false 不是
     */
    public static boolean isJDBCTimeColumn(int sqlType) {
        return Date.class.equals(convertJavaType(sqlType)) && Types.TIME == sqlType;
    }

    /**
     * 是否BLOB类型
     * 参考：org.mybatis.generator.api.IntrospectedColumn#isBLOBColumn
     *
     * @param sqlType
     * @return true 是、false 不是
     */
    public static boolean isBLOBColumn(int sqlType) {
        return Types.BINARY == sqlType || Types.BLOB == sqlType
                || Types.CLOB == sqlType || Types.LONGNVARCHAR == sqlType
                || Types.LONGVARBINARY == sqlType || Types.LONGVARCHAR == sqlType
                || Types.NCLOB == sqlType || Types.VARBINARY == sqlType || Types.OTHER == sqlType;
    }
}
