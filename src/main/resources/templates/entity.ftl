package ${entityPackage};

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
<#list entityImportPackages as import>
import ${import};
</#list>
<#if dataCheckBoxValue>
import lombok.Data;
</#if>
<#if builderCheckBoxValue>
import lombok.Builder;
</#if>
<#if noArgsConstructorCheckBoxValue>
import lombok.NoArgsConstructor;
</#if>
<#if allArgsConstructorCheckBoxValue>
import lombok.AllArgsConstructor;
</#if>

/**
 * 注释${table.comment!}
 *
 * @author ${author!}
 * @date ${.now?string("yyyy-MM-dd HH:mm")}
 */
<#if dataCheckBoxValue>
@Data
</#if>
<#if builderCheckBoxValue>
@Builder
</#if>
<#if noArgsConstructorCheckBoxValue>
@NoArgsConstructor
</#if>
<#if allArgsConstructorCheckBoxValue>
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

<#if generateGetterSetter>
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
