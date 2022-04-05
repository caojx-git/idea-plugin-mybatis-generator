package ${package};

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
<#list imports as import>
import ${import};
</#list>

/**
 * 注释${comment!""}
 *
 * @author ${author!""}
 * @date ${.now?string("yyyy-MM-dd HH:mm")}
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ${simpleName} implements Serializable {

 private static final long serialVersionUID = 1L;

<#list fields as field>

    <#if field.comment?default("")?trim?length gt 1>
    /**
     * ${field.comment}
     */
   </#if>
    private ${field.typeSimpleName} ${field.name};

</#list>


<#list fields as field>
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

}