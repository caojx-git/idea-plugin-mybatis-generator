package ${package};

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
<#list imports as import>
import ${import};
</#list>

/**
* 注释${comment!""} Mapper
*
* @author ${author!""}
* @date ${.now?string("yyyy-MM-dd HH:mm")}
*/
@Mapper
public interface ${simpleName} {

<#if entity.containBLOBField>
    /**
     * 查询记录包含BLOB信息
     *
     * @param example 查询条件
     * @return 列表
     */
    List<${entity.simpleName}> selectByExampleWithBLOBs(${entity.simpleName}Example example);
</#if>

    /**
     * 列表查询
     *
     * @param example 条件
     * @return 列表
     */
    List<${entity.simpleName}> selectByExample(${entity.simpleName}Example example);

<#list entity.fields as field>
    <#if field.id>
    /**
     * 根据主键id查询
     *
     * @param ${field.name}
     * @return 记录信息
     */
    ${entity.simpleName} selectByPrimaryKey(${field.typeSimpleName} ${field.name});

    /**
     * 根据主键删除数据
     *
     * @param ${field.name}
     * @return 数量
     */
    int deleteByPrimaryKey(${field.typeSimpleName} ${field.name});
    </#if>
</#list>

    /**
     * 删除数据
     *
     * @param example 条件
     * @return 删除数量
     */
    int deleteByExample(${entity.simpleName}Example example);

    /**
     * 插入数据库记录（不建议使用）
     *
     * @param record
     */
    int insert(${entity.simpleName} record);

    /**
     * 插入数据库记录（建议使用）
     *
     * @param record 插入数据
     * @return 插入数量
     */
    int insertSelective(${entity.simpleName} record);

    /**
     * 计数
     *
     * @param example 条件
     * @return 数量
     */
    long countByExample(${entity.simpleName}Example example);

    /**
     * 修改数据
     *
     * @param record  更新值
     * @param example 条件
     * @return 更新数量
     */
    int updateByExampleSelective(@Param("record") ${entity.simpleName} record, @Param("example") ${entity.simpleName}Example example);

<#if entity.containBLOBField>
    /**
     * 更新记录包含BLOB
     *
     * @param record  更新值
     * @param example 条件
     * @return 更新数量
     */
    int updateByExampleWithBLOBs(@Param("record") ${entity.simpleName} record, @Param("example") ${entity.simpleName}Example example);
</#if>

    /**
     * 修改数据
     *
     * @param record  更新值
     * @param example 条件
     * @return 更新数量
     */
    int updateByExample(@Param("record") ${entity.simpleName} record, @Param("example") ${entity.simpleName}Example example);

    /**
     * 修改数据(推荐使用)
     *
     * @param record 更新值
     * @return 更新数量
     */
    int updateByPrimaryKeySelective(${entity.simpleName} record);

<#if entity.containBLOBField>
    /**
     * 根据主键更新数据
     *
     * @param record 更新值
     * @return 更新数量
     */
    int updateByPrimaryKeyWithBLOBs(${entity.simpleName} record);
</#if>

    /**
     * 根据主键更新数据
     *
     * @param record 更新值
     * @return 更新数量
     */
    int updateByPrimaryKey(${entity.simpleName} record);
}