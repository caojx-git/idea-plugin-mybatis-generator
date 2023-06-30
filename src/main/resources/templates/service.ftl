<#if servicePackage?default("")?trim?length gt 1>
package ${servicePackage};

</#if>
<#if superServiceClass?? && superServiceClass !="">
<#if entityPackage?default("")?trim?length gt 1>
import ${entityFullClassName};
</#if>
import ${superServiceClass};
</#if>

/**
 * ${table.comment!} 服务类接口
 *
 * @author ${author!}
 * @since ${.now?string("yyyy-MM-dd HH:mm")}
 */
<#if superServiceClass?? && superServiceClass !="" >
public interface ${serviceName} extends ${superServiceClassName}<${entityName}> {
<#else>
public interface ${serviceName} {
</#if>

}
