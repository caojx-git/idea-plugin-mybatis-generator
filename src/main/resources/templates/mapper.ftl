package ${mapperPackage};

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import ${entityFullClazzName};
import ${entityExampleFullClazzName};

/**
* 注释${table.comment!} Mapper
*
* @author ${author!}
* @date ${.now?string("yyyy-MM-dd HH:mm")}
*/
@Mapper
public interface ${mapperName} {
<#if selectByExampleWithBLOBsCheckBoxValue && table.haveBlobField>

    /**
     * 查询记录包含BLOB信息
     *
     * @param example 查询条件
     * @return 列表
     */
    List<${entityName}> selectByExampleWithBLOBs(${entityExampleName} example);
</#if>
<#if selectByExampleCheckBoxValue>

    /**
     * 列表查询
     *
     * @param example 条件
     * @return 列表
     */
    List<${entityName}> selectByExample(${entityExampleName} example);
</#if>
<#if selectByPrimaryKeyCheckBoxValue>

    /**
     * 根据主键id查询
     *
     * @param ${table.primaryKeyName}
     * @return 记录信息
     */
    ${entityName} selectByPrimaryKey(${table.primaryKeyType.simpleName} ${table.primaryKeyName});
</#if>
<#if deleteByPrimaryKeyCheckBoxValue>

    /**
     * 根据主键删除数据
     *
     * @param ${table.primaryKeyName}
     * @return 数量
     */
    int deleteByPrimaryKey(${table.primaryKeyType.simpleName} ${table.primaryKeyName});
</#if>
<#if deleteByExampleCheckBoxValue>

    /**
     * 删除数据
     *
     * @param example 条件
     * @return 删除数量
     */
    int deleteByExample(${entityExampleName} example);
</#if>
<#if insertCheckBoxValue>

    /**
     * 插入数据库记录（不建议使用）
     *
     * @param record
     */
    int insert(${entityName} record);
</#if>
<#if insertSelectiveCheckBoxValue>

    /**
     * 插入数据库记录（建议使用）
     *
     * @param record 插入数据
     * @return 插入数量
     */
    int insertSelective(${entityName} record);
</#if>
<#if countByExampleCheckBoxValue>

    /**
     * 计数
     *
     * @param example 条件
     * @return 数量
     */
    long countByExample(${entityExampleName} example);
</#if>
<#if updateByExampleSelectiveCheckBoxValue>

    /**
     * 修改数据
     *
     * @param record  更新值
     * @param example 条件
     * @return 更新数量
     */
    int updateByExampleSelective(@Param("record") ${entityName} record, @Param("example") ${entityExampleName} example);
</#if>
<#if updateByExampleWithBLOBsCheckBoxValue && table.haveBlobField>

    /**
     * 更新记录包含BLOB
     *
     * @param record  更新值
     * @param example 条件
     * @return 更新数量
     */
    int updateByExampleWithBLOBs(@Param("record") ${entityName} record, @Param("example") ${entityExampleName} example);
</#if>
<#if updateByExampleCheckBoxValue>

    /**
     * 修改数据
     *
     * @param record  更新值
     * @param example 条件
     * @return 更新数量
     */
    int updateByExample(@Param("record") ${entityName} record, @Param("example") ${entityExampleName} example);
</#if>
<#if updateByPrimaryKeySelectiveCheckBoxValue>

    /**
     * 修改数据(推荐使用)
     *
     * @param record 更新值
     * @return 更新数量
     */
    int updateByPrimaryKeySelective(${entityName} record);
</#if>
<#if updateByPrimaryKeyWithBLOBsCheckBoxValue && table.haveBlobField>

    /**
     * 根据主键更新数据
     *
     * @param record 更新值
     * @return 更新数量
     */
    int updateByPrimaryKeyWithBLOBs(${entityName} record);
</#if>
<#if updateByPrimaryKeyCheckBoxValue>

    /**
     * 根据主键更新数据
     *
     * @param record 更新值
     * @return 更新数量
     */
    int updateByPrimaryKey(${entityName} record);
</#if>
}
