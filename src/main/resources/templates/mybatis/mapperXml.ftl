<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${fullClazzName}">

  <resultMap id="BaseResultMap" type="${entity.fullClazzName}">
    <#list entity.fields as field>
     <#if field.id>
    <id column="${field.columnName}" jdbcType="${field.jdbcType}" property="${field.name}"/>
     <#elseif !field.BLOB>
    <result column="${field.columnName}" jdbcType="${field.jdbcType}" property="${field.name}"/>
     </#if>
    </#list>
  </resultMap>

  <#if entity.containBLOBField>
  <resultMap extends="BaseResultMap" id="ResultMapWithBLOBs" type="${entity.fullClazzName}">
    <#list entity.fields as field>
      <#if field.BLOB>
    <result column="${field.columnName}" jdbcType="${field.jdbcType}" property="${field.name}"/>
      </#if>
    </#list>
  </resultMap>
  </#if>

  <sql id="Example_Where_Clause">
    <where>
      <foreach collection="oredCriteria" item="criteria" separator="or">
        <if test="criteria.valid">
          <trim prefix="(" prefixOverrides="and" suffix=")">
            <foreach collection="criteria.criteria" item="criterion">
              <choose>
                <when test="criterion.noValue">
                  and <#noparse>${criterion</#noparse>.condition}
                </when>
                <when test="criterion.singleValue">
                  and <#noparse>${criterion</#noparse>.condition} <#noparse>#{</#noparse> criterion.value}
                </when>
                <when test="criterion.betweenValue">
                  and <#noparse>${criterion</#noparse>.condition} <#noparse>#{</#noparse> criterion.value} and <#noparse>#{</#noparse> criterion.secondValue}
                </when>
                <when test="criterion.listValue">
                  and <#noparse>${criterion</#noparse>.condition}
                  <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">
                    <#noparse>#{</#noparse> listItem}
                  </foreach>
                </when>
              </choose>
            </foreach>
          </trim>
        </if>
      </foreach>
    </where>
  </sql>

  <sql id="Update_By_Example_Where_Clause">
    <where>
      <foreach collection="example.oredCriteria" item="criteria" separator="or">
        <if test="criteria.valid">
          <trim prefix="(" prefixOverrides="and" suffix=")">
            <foreach collection="criteria.criteria" item="criterion">
              <choose>
                <when test="criterion.noValue">
                  and <#noparse>${criterion</#noparse>.condition}
                </when>
                <when test="criterion.singleValue">
                  and <#noparse>${criterion</#noparse>.condition} <#noparse>#{</#noparse> criterion.value}
                </when>
                <when test="criterion.betweenValue">
                  and <#noparse>${criterion</#noparse>.condition} <#noparse>#{</#noparse> criterion.value} and <#noparse>#{</#noparse> criterion.secondValue}
                </when>
                <when test="criterion.listValue">
                  and <#noparse>${criterion</#noparse>.condition}
                  <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">
                    <#noparse>#{</#noparse> listItem}
                  </foreach>
                </when>
              </choose>
            </foreach>
          </trim>
        </if>
      </foreach>
    </where>
  </sql>

  <sql id="Base_Column_List">
    <#list entity.fields as field><#if !field.BLOB>${field.columnName}<#if field_has_next>, </#if><#assign newLine=(field_index+1)%6 == 0>${newLine?string("\n\t","")}</#if></#list>
  </sql>

  <#if entity.containBLOBField>
  <sql id="Blob_Column_List">
    <#list entity.BLOBFields as field><#if field.BLOB>${field.columnName}<#if field_has_next>, </#if><#assign newLine=(field_index+1)%6 == 0>${newLine?string("\n\t","")}</#if></#list>
  </sql>
  </#if>

  <#if entity.containBLOBField>
  <select id="selectByExampleWithBLOBs" parameterType="${entity.fullClazzName}Example" resultMap="ResultMapWithBLOBs">
    select
    <if test="distinct">
      distinct
    </if>
    'true' as QUERYID,
    <include refid="Base_Column_List" />
    ,
    <include refid="Blob_Column_List" />
    from ${entity.tableName}
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
    <if test="orderByClause != null">
      order by <#noparse>${orderByClause</#noparse>}
    </if>
  </select>
  </#if>

  <select id="selectByExample" parameterType="${entity.fullClazzName}Example" resultMap="BaseResultMap">
    select
    <if test="distinct">
      distinct
    </if>
    'true' as QUERYID,
    <include refid="Base_Column_List" />
    from ${entity.tableName}
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
    <if test="orderByClause != null">
      order by <#noparse>${orderByClause</#noparse>}
    </if>
  </select>

  <#if !entity.containBLOBField>
  <select id="selectByPrimaryKey" parameterType="${entity.idType}" resultMap="BaseResultMap">
    select
    <include refid="Base_Column_List" />
    from ${entity.tableName}
    <#list entity.fields as field>
    <#if field.id>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}
    </#if>
    </#list>
  </select>
  </#if>

  <#if entity.containBLOBField>
  <select id="selectByPrimaryKey" parameterType="${entity.idType}" resultMap="ResultMapWithBLOBs">
    select
    <include refid="Base_Column_List" />
    ,
    <include refid="Blob_Column_List" />
    from ${entity.tableName}
    <#list entity.fields as field>
      <#if field.id>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}
      </#if>
    </#list>
  </select>
  </#if>

  <delete id="deleteByPrimaryKey" parameterType="${entity.idType}">
    delete from ${entity.tableName}
    <#list entity.fields as field>
    <#if field.id>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}
    </#if>
    </#list>
  </delete>

  <delete id="deleteByExample" parameterType="${entity.fullClazzName}Example">
    delete from ${entity.tableName}
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
  </delete>

  <insert id="insert" parameterType="${entity.fullClazzName}">
    <#list entity.fields as field>
      <#if field.id>
    <selectKey keyProperty="${field.columnName}" order="AFTER" resultType="${field.type.name}">
      SELECT LAST_INSERT_ID()
    </selectKey>
      </#if>
    </#list>
    insert into ${entity.tableName}(<#list entity.fields as field>${field.columnName}<#if field_has_next>, </#if><#assign newLine=(field_index+1)%4 == 0>${newLine?string("\n\t","")}</#list>)
    values
    (<#list entity.fields as field><#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>, </#if><#assign newLine=(field_index+1)%4 == 0>${newLine?string("\n\t","")}</#list>)
  </insert>

  <insert id="insertSelective" parameterType="${entity.fullClazzName}">
    <#list entity.fields as field>
     <#if field.id>
    <selectKey keyProperty="${field.columnName}" order="AFTER" resultType="${field.type.name}">
      SELECT LAST_INSERT_ID()
    </selectKey>
     </#if>
    </#list>
    insert into ${entity.tableName}
    <trim prefix="(" suffix=")" suffixOverrides=",">
      <#list entity.fields as field>
      <if test="${field.name} != null">
        ${field.columnName}<#if field_has_next>,</#if>
      </if>
     </#list>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
     <#list entity.fields as field>
      <if test="${field.name} != null">
        <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>,</#if>
      </if>
      </#list>
    </trim>
  </insert>

  <select id="countByExample" parameterType="${entity.fullClazzName}Example" resultType="java.lang.Long">
    select count(*) from ${entity.tableName}
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
  </select>

  <update id="updateByExampleSelective" parameterType="map">
    update ${entity.tableName}
    <set>
      <#list entity.fields as field>
      <if test="record.${field.name} != null">
       ${field.columnName} = <#noparse>#{</#noparse>record.${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>,</#if>
      </if>
      </#list>
    </set>
    <if test="_parameter != null">
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>

  <#if entity.containBLOBField>
  <update id="updateByExampleWithBLOBs" parameterType="map">
    update ${entity.tableName}
    set
    <#list entity.fields as field>
      <#if !field.id>
     ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>,</#if>
      </#if>
    </#list>
    <if test="_parameter != null">
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
  </#if>

  <update id="updateByExample" parameterType="map">
    update ${entity.tableName}
    set
    <#list entity.fields as field>${field.columnName} = <#noparse>#{</#noparse>record.${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>,</#if><#assign newLine=(field_index)%1 == 0>${newLine?string("\n\t","")}</#list>
    <if test="_parameter != null">
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>

  <update id="updateByPrimaryKeySelective" parameterType="${entity.fullClazzName}">
    update ${entity.tableName}
    <set>
     <#list entity.fields as field>
      <if test="${field.name} != null">
        userId = <#noparse>#{</#noparse>${field.name},jdbcType=INTEGER}<#if field_has_next>,</#if>
      </if>
      </#list>
    </set>
    <#list entity.fields as field>
    <#if field.id>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}
    </#if>
    </#list>
  </update>

  <#if entity.containBLOBField>
  <update id="updateByPrimaryKeyWithBLOBs" parameterType="com.generator.test.entity.Film">
    update ${entity.tableName}
    set
    <#list entity.fields as field>
      <#if !field.id>
        ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>,</#if>
      </#if>
    </#list>
    <#list entity.fields as field>
      <#if field.id>
     where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}
      </#if>
    </#list>
  </update>
  </#if>

  <update id="updateByPrimaryKey" parameterType="${entity.fullClazzName}">
    update ${entity.tableName}
    set
     <#list entity.fields as field>
     <#if !field.id && !field.BLOB>
       ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>,</#if>
      </#if>
      </#list>
   <#list entity.fields as field>
      <#if field.id>
      where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}
      </#if>
   </#list>
  </update>
</mapper>