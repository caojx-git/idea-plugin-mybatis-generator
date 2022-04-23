<#if servicePackage?default("")?trim?length gt 1>
package ${servicePackage};

</#if>
<#if superServiceClass?? && superServiceClass !="">
import ${entityFullClassName};
import ${superServiceClassPackage};
</#if>

/**
 * ${table.comment!} 服务类接口
 *
 * @author ${author!}
 * @date ${.now?string("yyyy-MM-dd HH:mm")}
 */
<#if superServiceClass?? && superServiceClass !="" >
public interface ${serviceName} extends ${superServiceClassName}<${entityName}> {
<#else>
public interface ${serviceName} {
</#if>

}
