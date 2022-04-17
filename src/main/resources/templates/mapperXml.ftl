<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapperFullClassName}">

  <resultMap id="BaseResultMap" type="${entityFullClassName}">
    <#list table.fields as field>
     <#if field.primaryKeyFlag>
    <id column="${field.columnName}" jdbcType="${field.jdbcType}" property="${field.name}"/>
     <#elseif !field.blobFlag>
    <result column="${field.columnName}" jdbcType="${field.jdbcType}" property="${field.name}"/>
     </#if>
    </#list>
  </resultMap>

  <#if table.haveBlobField>
  <resultMap extends="BaseResultMap" id="ResultMapWithBLOBs" type="${entityFullClassName}">
    <#list table.fields as field>
      <#if field.blobFlag>
    <result column="${field.columnName}" jdbcType="${field.jdbcType}" property="${field.name}"/>
      </#if>
    </#list>
  </resultMap>
  </#if>
  <#if generateEntityExample>
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
    <#list table.fields as field><#if !field.blobFlag>${field.columnName}<#if field_has_next>, </#if><#assign newLine=(field_index+1)%6 == 0>${newLine?string("\n\t","")}</#if></#list>
  </sql>

  <#if table.haveBlobField>
  <sql id="Blob_Column_List">
    <#list table.blobFields as field><#if field.blobFlag>${field.columnName}<#if field_has_next>, </#if><#assign newLine=(field_index+1)%6 == 0>${newLine?string("\n\t","")}</#if></#list>
  </sql>
  </#if>

  <#if isSelectByExampleWithBLOBsCheckBox && table.haveBlobField>
  <select id="selectByExampleWithBLOBs" parameterType="${entityExampleName}" resultMap="ResultMapWithBLOBs">
    select
    <if test="distinct">
      distinct
    </if>
    'true' as QUERYID,
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
  <#if isSelectByExampleCheckBox>
  <select id="selectByExample" parameterType="${entityExampleName}" resultMap="BaseResultMap">
    select
    <if test="distinct">
      distinct
    </if>
    'true' as QUERYID,
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
  <#if isSelectByPrimaryKeyCheckBox && !table.haveBlobField>
  <select id="selectByPrimaryKey" parameterType="${table.primaryKeyType}" resultMap="BaseResultMap">
    select
    <include refid="Base_Column_List" />
    from ${table.name}
    <#list table.fields as field>
    <#if field.primaryKeyFlag>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}
    </#if>
    </#list>
  </select>
  </#if>
  <#if isSelectByPrimaryKeyCheckBox && table.haveBlobField>
  <select id="selectByPrimaryKey" parameterType="${table.primaryKeyType}" resultMap="ResultMapWithBLOBs">
    select
    <include refid="Base_Column_List" />
    ,
    <include refid="Blob_Column_List" />
    from ${table.name}
    <#list table.fields as field>
      <#if field.primaryKeyFlag>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}
      </#if>
    </#list>
  </select>
  </#if>
 <#if isDeleteByPrimaryKeyCheckBox>
  <delete id="deleteByPrimaryKey" parameterType="${table.primaryKeyType}">
    delete from ${table.name}
    <#list table.fields as field>
    <#if field.primaryKeyFlag>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}
    </#if>
    </#list>
  </delete>
</#if>
  <#if isDeleteByExampleCheckBox>
  <delete id="deleteByExample" parameterType="${entityExampleName}">
    delete from ${table.name}
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
  </delete>
</#if>
  <#if isInsertCheckBox>
  <insert id="insert" parameterType="${entityFullClassName}">
    <#list table.fields as field>
      <#if field.primaryKeyFlag>
    <selectKey keyProperty="${field.columnName}" order="AFTER" resultType="${field.type.name}">
      SELECT LAST_INSERT_ID()
    </selectKey>
      </#if>
    </#list>
    insert into ${table.name}(<#list table.fields as field>${field.columnName}<#if field_has_next>, </#if><#assign newLine=(field_index+1)%4 == 0>${newLine?string("\n\t","")}</#list>)
    values
    (<#list table.fields as field><#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>, </#if><#assign newLine=(field_index+1)%4 == 0>${newLine?string("\n\t","")}</#list>)
  </insert>
</#if>
  <#if isInsertSelectiveCheckBox>
  <insert id="insertSelective" parameterType="${entityFullClassName}">
    <#list table.fields as field>
     <#if field.primaryKeyFlag>
    <selectKey keyProperty="${field.columnName}" order="AFTER" resultType="${field.type.name}">
      SELECT LAST_INSERT_ID()
    </selectKey>
     </#if>
    </#list>
    insert into ${table.name}
    <trim prefix="(" suffix=")" suffixOverrides=",">
      <#list table.fields as field>
      <if test="${field.name} != null">
        ${field.columnName}<#if field_has_next>,</#if>
      </if>
     </#list>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
     <#list table.fields as field>
      <if test="${field.name} != null">
        <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>,</#if>
      </if>
      </#list>
    </trim>
  </insert>
</#if>
  <#if isCountByExampleCheckBox>
  <select id="countByExample" parameterType="${entityExampleName}" resultType="java.lang.Long">
    select count(*) from ${table.name}
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
  </select>
</#if>
  <#if isUpdateByExampleSelectiveCheckBox>
  <update id="updateByExampleSelective" parameterType="map">
    update ${table.name}
    <set>
      <#list table.fields as field>
      <if test="record.${field.name} != null">
       ${field.columnName} = <#noparse>#{</#noparse>record.${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>,</#if>
      </if>
      </#list>
    </set>
    <if test="_parameter != null">
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
</#if>
  <#if isUpdateByExampleWithBLOBsCheckBox && table.haveBlobField>
  <update id="updateByExampleWithBLOBs" parameterType="map">
    update ${table.name}
    set
    <#list table.fields as field>
      <#if !field.primaryKeyFlag>
     ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>,</#if>
      </#if>
    </#list>
    <if test="_parameter != null">
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
  </#if>
  <#if isUpdateByExampleCheckBox>
  <update id="updateByExample" parameterType="map">
    update ${table.name}
    set
    <#list table.fields as field>${field.columnName} = <#noparse>#{</#noparse>record.${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>,</#if><#assign newLine=(field_index)%1 == 0>${newLine?string("\n\t","")}</#list>
    <if test="_parameter != null">
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
</#if>
  <#if isUpdateByPrimaryKeySelectiveCheckBox>
  <update id="updateByPrimaryKeySelective" parameterType="${entityFullClassName}">
    update ${table.name}
    <set>
     <#list table.fields as field>
      <if test="${field.name} != null">
        userId = <#noparse>#{</#noparse>${field.name},jdbcType=INTEGER}<#if field_has_next>,</#if>
      </if>
      </#list>
    </set>
    <#list table.fields as field>
    <#if field.primaryKeyFlag>
    where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}
    </#if>
    </#list>
  </update>
</#if>
  <#if isUpdateByPrimaryKeyWithBLOBsCheckBox && table.haveBlobField>
  <update id="updateByPrimaryKeyWithBLOBs" parameterType="${entityFullClassName}">
    update ${table.name}
    set
    <#list table.fields as field>
      <#if !field.primaryKeyFlag>
        ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>,</#if>
      </#if>
    </#list>
    <#list table.fields as field>
      <#if field.primaryKeyFlag>
     where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}
      </#if>
    </#list>
  </update>
  </#if>
  <#if isUpdateByPrimaryKeyCheckBox>
  <update id="updateByPrimaryKey" parameterType="${entityFullClassName}">
    update ${table.name}
    set
     <#list table.fields as field>
     <#if !field.primaryKeyFlag && !field.blobFlag>
       ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}<#if field_has_next>,</#if>
      </#if>
      </#list>
   <#list table.fields as field>
      <#if field.primaryKeyFlag>
      where ${field.columnName} = <#noparse>#{</#noparse>${field.name},jdbcType=${field.jdbcType}}
      </#if>
   </#list>
  </update>
  </#if>
</mapper>
