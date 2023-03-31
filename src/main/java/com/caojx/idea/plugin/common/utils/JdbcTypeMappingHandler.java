package com.caojx.idea.plugin.common.utils;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类型处理
 *
 * @author caojx
 * @date 2022/4/10 12:20 PM
 */
public class JdbcTypeMappingHandler {

    private static final ConcurrentHashMap<JDBCType, Class<?>> JDBC_TYPE_JAVA_TYPE_MAPPING = new ConcurrentHashMap<>();

    public JdbcTypeMappingHandler() {
    }

    public JdbcTypeMappingHandler(Map<JDBCType, Class<?>> customerJdbcTypeMappingMap) {
        initJdbcTypeJavaTypeMapping(customerJdbcTypeMappingMap);
    }

    private void initJdbcTypeJavaTypeMapping(Map<JDBCType, Class<?>> customerJdbcTypeMappingMap) {
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

        // 覆盖默认的配置
        if (customerJdbcTypeMappingMap != null && customerJdbcTypeMappingMap.size() != 0) {
            JDBC_TYPE_JAVA_TYPE_MAPPING.putAll(customerJdbcTypeMappingMap);
        }
    }

    /**
     * sqlType 转为javaType
     *
     * @param sqlType
     * @return javaType
     */
    public Class<?> convertJavaType(int sqlType) {
        JDBCType jdbcType = JDBCType.valueOf(sqlType);
        if (jdbcType == null) {
            return Object.class;
        }
        return JDBC_TYPE_JAVA_TYPE_MAPPING.getOrDefault(jdbcType, Object.class);
    }

    /**
     * javaType 转jdbcType
     *
     * @param sqlType
     * @return jdbcType
     */
    public String convertJdbcType(int sqlType) {
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
    public boolean isJDBCDateColumn(int sqlType) {
        return Date.class.equals(convertJavaType(sqlType)) && Types.DATE == sqlType;
    }

    /**
     * 是否isJDBCTimeColumn
     * 参考：org.mybatis.generator.api.IntrospectedColumn#isJDBCTimeColumn
     *
     * @param sqlType
     * @return true 是、false 不是
     */
    public boolean isJDBCTimeColumn(int sqlType) {
        return Date.class.equals(convertJavaType(sqlType)) && Types.TIME == sqlType;
    }

    /**
     * 是否BLOB类型
     * 参考：org.mybatis.generator.api.IntrospectedColumn#isBLOBColumn
     *
     * @param sqlType
     * @return true 是、false 不是
     */
    public boolean isBLOBColumn(int sqlType) {
        return Types.BINARY == sqlType || Types.BLOB == sqlType
                || Types.CLOB == sqlType || Types.LONGNVARCHAR == sqlType
                || Types.LONGVARBINARY == sqlType || Types.LONGVARCHAR == sqlType
                || Types.NCLOB == sqlType || Types.VARBINARY == sqlType || Types.OTHER == sqlType;
    }
}
