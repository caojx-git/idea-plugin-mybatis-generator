package ${entityPackage};

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
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

/**
 * 注释${table.comment!}
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
public class ${entityName} implements Serializable {

    private static final long serialVersionUID = 1L;
<#list table.fields as field>

    <#if field.comment?default("")?trim?length gt 1>
    /**
     * ${field.comment}
     */
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
