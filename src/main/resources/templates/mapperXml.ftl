<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapperFullClassName}">

  <resultMap id="BaseResultMap" type="${entityFullClassName}">
    <#list table.notBlobFields as field>
     <#if field.primaryKeyFlag>
    <id column="${field.columnName}" jdbcType="${field.jdbcTypeName}" property="${field.name}"/>
     <#else>
    <result column="${field.columnName}" jdbcType="${field.jdbcTypeName}" property="${field.name}"/>
     </#if>
    </#list>
  </resultMap>
  <#if table.haveBlobField>
  <resultMap extends="BaseResultMap" id="ResultMapWithBLOBs" type="${entityFullClassName}">
    <#list table.blobFields as field>
    <result column="${field.columnName}" jdbcType="${field.jdbcTypeName}" property="${field.name}"/>
    </#list>
  </resultMap>
  </#if>
  <#if isSelectedEnableSelectByExampleCheckBox || isSelectedEnableDeleteByExampleCheckBox || isSelectedEnableCountByExampleCheckBox || isSelectedEnableUpdateByExampleCheckBox>
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
  </#if>
  <sql id="Base_Column_List">
    <#list table.notBlobFields as field>${field.columnName}<#if field_has_next>, <#assign newLine=(field_index+1)%7 == 0>${newLine?string("\n\t","")}</#if></#list>
  </sql>
  <#if table.haveBlobField>
  <sql id="Blob_Column_List">
    <#list table.blobFields as field>${field.columnName}<#if field_has_next>, <#assign newLine=(field_index+1)%7 == 0>${newLine?string("\n\t","")}</#if></#list>
  </sql>
  </#if>
  <#if isSelectedEnableSelectByExampleCheckBox && table.haveBlobField>

  <select id="selectByExampleWithBLOBs" parameterType="${entityExampleFullClassName}" resultMap="ResultMapWithBLOBs">
    select
    <if test="distinct">
      distinct
    </if>
    <include refid="Base_Column_List" />
    ,
    <include refid="Blob_Column_List" />
    from ${table.name}
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
    <if test="orderByClause != null">
      order by <#noparse>${orderByClause</#noparse>}
    </if>
  </select>
  </#if>
  <#if isSelectedEnableSelectByExampleCheckBox>

  <select id="selectByExample" parameterType="${entityExampleFullClassName}" resultMap="BaseResultMap">
    select
    <if test="distinct">
      distinct
    </if>
    <include refid="Base_Column_List" />
    from ${table.name}
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
    <if test="orderByClause != null">
      order by <#noparse>${orderByClause</#noparse>}
    </if>
  </select>
</#if>
  <#if isSelectedEnableSelectByPrimaryKeyCheckBox && table.havePrimaryKey && !table.haveBlobField>

  <select id="selectByPrimaryKey" parameterType="${table.primaryKeyType.name}" resultMap="BaseResultMap">
    select
    <include refid="Base_Column_List" />
    from ${table.name}
    <#list table.fields as field>
    <#if field.primaryKeyFlag>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcTypeName}}
    </#if>
    </#list>
  </select>
  </#if>
  <#if isSelectedEnableSelectByPrimaryKeyCheckBox && table.havePrimaryKey && table.haveBlobField>

  <select id="selectByPrimaryKey" parameterType="${table.primaryKeyType.name}" resultMap="ResultMapWithBLOBs">
    select
    <include refid="Base_Column_List" />
    ,
    <include refid="Blob_Column_List" />
    from ${table.name}
    <#list table.fields as field>
      <#if field.primaryKeyFlag>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcTypeName}}
      </#if>
    </#list>
  </select>
  </#if>
  <#if isSelectedEnableDeleteByPrimaryKeyCheckBox && table.havePrimaryKey>

  <delete id="deleteByPrimaryKey" parameterType="${table.primaryKeyType.name}">
    delete from ${table.name}
    <#list table.fields as field>
    <#if field.primaryKeyFlag>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcTypeName}}
    </#if>
    </#list>
  </delete>
  </#if>
  <#if isSelectedEnableDeleteByExampleCheckBox>

  <delete id="deleteByExample" parameterType="${entityExampleFullClassName}">
    delete from ${table.name}
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
  </delete>
</#if>
  <#if isSelectedEnableInsertCheckBox>

  <insert id="insert" parameterType="${entityFullClassName}">
    <#list table.fields as field>
      <#if field.primaryKeyFlag>
    <selectKey keyProperty="${field.name}" order="AFTER" resultType="${field.type.name}">
      SELECT LAST_INSERT_ID()
    </selectKey>
      </#if>
    </#list>
    insert into ${table.name}(<#list table.fields as field><#if !field.primaryKeyFlag>${field.columnName}<#if field_has_next>, <#assign newLine=(field_index+1)%4 == 0>${newLine?string("\n\t ","")}</#if></#if></#list>)
    values (<#list table.fields as field><#if !field.primaryKeyFlag><#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcTypeName}}<#if field_has_next>, <#assign newLine=(field_index+1)%4 == 0>${newLine?string("\n\t ","")}</#if></#if></#list>)
  </insert>
</#if>
  <#if isSelectedEnableInsertCheckBox>

  <insert id="insertSelective" parameterType="${entityFullClassName}">
    <#list table.fields as field>
     <#if field.primaryKeyFlag>
    <selectKey keyProperty="${field.name}" order="AFTER" resultType="${field.type.name}">
      SELECT LAST_INSERT_ID()
    </selectKey>
     </#if>
    </#list>
    insert into ${table.name}
    <trim prefix="(" suffix=")" suffixOverrides=",">
      <#list table.fields as field>
      <#if !field.primaryKeyFlag>
      <if test="${field.name} != null">
        ${field.columnName},
      </if>
      </#if>
      </#list>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
     <#list table.fields as field>
       <#if !field.primaryKeyFlag>
      <if test="${field.name} != null">
        <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcTypeName}},
      </if>
      </#if>
      </#list>
    </trim>
  </insert>
</#if>
  <#if isSelectedEnableCountByExampleCheckBox>

  <select id="countByExample" parameterType="${entityExampleFullClassName}" resultType="java.lang.Long">
    select count(*) from ${table.name}
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
  </select>
</#if>
  <#if isSelectedEnableUpdateByExampleCheckBox>

  <update id="updateByExampleSelective" parameterType="map">
    update ${table.name}
    <set>
      <#list table.fields as field>
      <if test="record.${field.name} != null">
       ${field.columnName} = <#noparse>#{</#noparse>record.${field.name},jdbcType=${field.jdbcTypeName}},
      </if>
      </#list>
    </set>
    <if test="_parameter != null">
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
</#if>
  <#if isSelectedEnableUpdateByExampleCheckBox && table.haveBlobField>

  <update id="updateByExampleWithBLOBs" parameterType="map">
    update ${table.name}
    set<#list table.fields as field> ${field.columnName} = <#noparse>#{</#noparse>record.${field.name},jdbcType=${field.jdbcTypeName}}<#if field_has_next>,<#assign newLine=true>${newLine?string("\n\t ","")}</#if></#list>
    <if test="_parameter != null">
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
  </#if>
  <#if isSelectedEnableUpdateByExampleCheckBox>

  <update id="updateByExample" parameterType="map">
    update ${table.name}
    set<#list table.notBlobFields as field> ${field.columnName} = <#noparse>#{</#noparse>record.${field.name},jdbcType=${field.jdbcTypeName}}<#if field_has_next>,<#assign newLine=true>${newLine?string("\n\t ","")}</#if></#list>
    <if test="_parameter != null">
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
</#if>
  <#if isSelectedEnableUpdateByPrimaryKeyCheckBox && table.havePrimaryKey>

  <update id="updateByPrimaryKeySelective" parameterType="${entityFullClassName}">
    update ${table.name}
    <set>
     <#list table.fields as field>
       <#if !field.primaryKeyFlag>
      <if test="${field.name} != null">
        ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcTypeName}},
      </if>
       </#if>
      </#list>
    </set>
    <#list table.fields as field>
    <#if field.primaryKeyFlag>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcTypeName}}
    </#if>
    </#list>
  </update>
</#if>
  <#if isSelectedEnableUpdateByPrimaryKeyCheckBox && table.havePrimaryKey && table.haveBlobField>

  <update id="updateByPrimaryKeyWithBLOBs" parameterType="${entityFullClassName}">
    update ${table.name}
    set<#list table.fields as field><#if !field.primaryKeyFlag> ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcTypeName}}<#if field_has_next>,<#assign newLine=true>${newLine?string("\n\t ","")}</#if></#if></#list>
    <#list table.fields as field>
      <#if field.primaryKeyFlag>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcTypeName}}
      </#if>
    </#list>
  </update>
  </#if>
  <#if isSelectedEnableUpdateByPrimaryKeyCheckBox && table.havePrimaryKey>

  <update id="updateByPrimaryKey" parameterType="${entityFullClassName}">
    update ${table.name}
    set<#list table.notBlobFields as field><#if !field.primaryKeyFlag> ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcTypeName}}<#if field_has_next>,<#assign newLine=true>${newLine?string("\n\t ","")}</#if></#if></#list>
   <#list table.fields as field>
      <#if field.primaryKeyFlag>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcTypeName}}
      </#if>
   </#list>
  </update>
  </#if>
</mapper>
