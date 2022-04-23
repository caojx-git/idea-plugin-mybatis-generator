<#if entityExamplePackage?default("")?trim?length gt 1>
package ${entityExamplePackage};

</#if>
import java.util.ArrayList;
import java.util.List;
<#if table.haveJdbcDateField || table.haveJdbcTimeField>
import java.util.Iterator;
</#if>
<#list entityImportPackages as import>
import ${import};
</#list>

/**
* 注释${table.comment!} Example
*
* @author ${author!}
* @date ${.now?string("yyyy-MM-dd HH:mm")}
*/
public class ${entityExampleName} {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ${entityExampleName}() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }
        <#if table.haveJdbcDateField>

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }
        </#if>
        <#if table.haveJdbcTimeField>

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
        }
        </#if>
<#list table.notBlobFields as field>

        public Criteria and${field.name?cap_first}IsNull() {
            addCriterion("${field.columnName} is null");
            return (Criteria) this;
        }

        public Criteria and${field.name?cap_first}IsNotNull() {
            addCriterion("${field.columnName} is not null");
            return (Criteria) this;
        }

        public Criteria and${field.name?cap_first}EqualTo(${field.typeSimpleName} value) {
        <#if field.jdbcDateFlag>
            addCriterionForJDBCDate("${field.columnName} =", value, "${field.name}");
        <#elseif field.jdbcTimeFlag>
            addCriterionForJDBCTime("${field.columnName} =", value, "${field.name}");
        <#else >
            addCriterion("${field.columnName} =", value, "${field.name}");
        </#if>
            return (Criteria) this;
        }

        public Criteria and${field.name?cap_first}NotEqualTo(${field.typeSimpleName} value) {
        <#if field.jdbcDateFlag>
            addCriterionForJDBCDate("${field.columnName} <>", value, "${field.name}");
        <#elseif field.jdbcTimeFlag>
            addCriterionForJDBCTime("${field.columnName} <>", value, "${field.name}");
        <#else >
            addCriterion("${field.columnName} <>", value, "${field.name}");
        </#if>
            return (Criteria) this;
        }

        public Criteria and${field.name?cap_first}GreaterThan(${field.typeSimpleName} value) {
        <#if field.jdbcDateFlag>
            addCriterionForJDBCDate("${field.columnName} >", value, "${field.name}");
        <#elseif field.jdbcTimeFlag>
            addCriterionForJDBCTime("${field.columnName} >", value, "${field.name}");
        <#else >
            addCriterion("${field.columnName} >", value, "${field.name}");
        </#if>
            return (Criteria) this;
        }

        public Criteria and${field.name?cap_first}GreaterThanOrEqualTo(${field.typeSimpleName} value) {
        <#if field.jdbcDateFlag>
            addCriterionForJDBCDate("${field.columnName} >=", value, "${field.name}");
        <#elseif field.jdbcTimeFlag>
            addCriterionForJDBCTime("${field.columnName} >=", value, "${field.name}");
        <#else >
            addCriterion("${field.columnName} >=", value, "${field.name}");
        </#if>
            return (Criteria) this;
        }

        public Criteria and${field.name?cap_first}LessThan(${field.typeSimpleName} value) {
        <#if field.jdbcDateFlag>
            addCriterionForJDBCDate("${field.columnName} <", value, "${field.name}");
        <#elseif field.jdbcTimeFlag>
            addCriterionForJDBCTime("${field.columnName} <", value, "${field.name}");
        <#else >
            addCriterion("${field.columnName} <", value, "${field.name}");
        </#if>
            return (Criteria) this;
        }

        public Criteria and${field.name?cap_first}LessThanOrEqualTo(${field.typeSimpleName} value) {
        <#if field.jdbcDateFlag>
            addCriterionForJDBCDate("${field.columnName} <=", value, "${field.name}");
        <#elseif field.jdbcTimeFlag>
            addCriterionForJDBCTime("${field.columnName} <=", value, "${field.name}");
        <#else >
            addCriterion("${field.columnName} <=", value, "${field.name}");
        </#if>
            return (Criteria) this;
        }
        <#if field.typeSimpleName == "String">

        public Criteria and${field.name?cap_first}Like(${field.typeSimpleName} value) {
            addCriterion("${field.columnName} like", value, "${field.name}");
            return (Criteria) this;
        }

        public Criteria and${field.name?cap_first}NotLike(${field.typeSimpleName} value) {
            addCriterion("${field.columnName} not like", value, "${field.name}");
            return (Criteria) this;
        }
        </#if>

        public Criteria and${field.name?cap_first}In(List<${field.typeSimpleName}> values) {
        <#if field.jdbcDateFlag>
            addCriterionForJDBCDate("${field.columnName} in", values, "${field.name}");
        <#elseif field.jdbcTimeFlag>
            addCriterionForJDBCTime("${field.columnName} in", values, "${field.name}");
        <#else >
            addCriterion("${field.columnName} in", values, "${field.name}");
        </#if>
            return (Criteria) this;
        }

        public Criteria and${field.name?cap_first}NotIn(List<${field.typeSimpleName}> values) {
        <#if field.jdbcDateFlag>
            addCriterionForJDBCDate("${field.columnName} not in", values, "${field.name}");
        <#elseif field.jdbcTimeFlag>
            addCriterionForJDBCTime("${field.columnName} not in", values, "${field.name}");
        <#else >
            addCriterion("${field.columnName} not in", values, "${field.name}");
        </#if>
            return (Criteria) this;
        }

        public Criteria and${field.name?cap_first}Between(${field.typeSimpleName} value1, ${field.typeSimpleName} value2) {
        <#if field.jdbcDateFlag>
            addCriterionForJDBCDate("${field.columnName} between", value1, value2, "${field.name}");
        <#elseif field.jdbcTimeFlag>
            addCriterionForJDBCTime("${field.columnName} between", value1, value2, "${field.name}");
        <#else >
            addCriterion("${field.columnName} between", value1, value2, "${field.name}");
        </#if>
            return (Criteria) this;
        }

        public Criteria and${field.name?cap_first}NotBetween(${field.typeSimpleName} value1, ${field.typeSimpleName} value2) {
        <#if field.jdbcDateFlag>
            addCriterionForJDBCDate("${field.columnName} not between", value1, value2, "${field.name}");
        <#elseif field.jdbcTimeFlag>
            addCriterionForJDBCTime("${field.columnName} not between", value1, value2, "${field.name}");
        <#else >
            addCriterion("${field.columnName} not between", value1, value2, "${field.name}");
        </#if>
            return (Criteria) this;
        }
</#list>

    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

}
