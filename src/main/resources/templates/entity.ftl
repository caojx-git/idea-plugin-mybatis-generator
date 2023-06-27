<#if entityPackage?default("")?trim?length gt 1>
package ${entityPackage};

</#if>
<#if isSelectedSerializableCheckBox>
import java.io.Serializable;
</#if>
<#list entityImportPackages as import>
import ${import};
</#list>
<#if isSelectedDataCheckBox>
import lombok.Data;
</#if>
<#if isSelectedBuilderCheckBox>
import lombok.Builder;
</#if>
<#if isSelectedNoArgsConstructorCheckBox>
import lombok.NoArgsConstructor;
</#if>
<#if isSelectedAllArgsConstructorCheckBox>
import lombok.AllArgsConstructor;
</#if>
<#if table.havePrimaryKey && frameworkType =="TkMyBatis">
import javax.persistence.*;
</#if>
<#if frameworkType == "MyBatisPlus">
import com.baomidou.mybatisplus.annotation.TableName;
</#if>
<#if table.havePrimaryKey && frameworkType =="MyBatisPlus">
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
</#if>
<#if isSelectedEntitySwaggerCheckBox>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>

/**
 * ${table.comment!}
 *
 * @author ${author!}
 * @date ${.now?string("yyyy-MM-dd HH:mm")}
 */
<#if isSelectedDataCheckBox>
@Data
</#if>
<#if isSelectedBuilderCheckBox>
@Builder
</#if>
<#if isSelectedNoArgsConstructorCheckBox>
@NoArgsConstructor
</#if>
<#if isSelectedAllArgsConstructorCheckBox>
@AllArgsConstructor
</#if>
<#if isSelectedEntitySwaggerCheckBox>
@ApiModel("${table.comment!}")
</#if>
<#if frameworkType == "MyBatisPlus">
@TableName("${table.name}")
</#if>
<#if isSelectedSerializableCheckBox>
public class ${entityName} implements Serializable {

    private static final long serialVersionUID = 1L;
<#else>
public class ${entityName} {
</#if>
<#list table.fields as field>

    <#if field.comment?default("")?trim?length gt 1 && !isSelectedEntitySwaggerCheckBox>
    /**
     * ${field.comment}
     */
   </#if>
   <#if isSelectedEntitySwaggerCheckBox>
    @ApiModelProperty("${field.comment}")
   </#if>
   <#if field.primaryKeyFlag && frameworkType == "TkMyBatis">
    @Id
   </#if>
    <#if field.primaryKeyFlag && frameworkType == "MyBatisPlus">
    @TableId(value = "${field.columnName}", type = IdType.AUTO)
    </#if>
    private ${field.typeSimpleName} ${field.name};
</#list>
<#if isGenerateGetterSetter>
<#list table.fields as field>

    /**
     * 获取${field.comment}值
     *
     * @return ${field.comment}
     */
    public ${field.typeSimpleName} get${field.name?cap_first}() {
        return ${field.name};
    }

    /**
     * 设置${field.comment}值
     *
     * @param ${field.name} ${field.comment}
     */
    public void set${field.name?cap_first}(${field.typeSimpleName} ${field.name}) {
        this.${field.name} = ${field.name};
    }
</#list>
</#if>
}
