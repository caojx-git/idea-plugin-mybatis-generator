<#if serviceImplPackage?default("")?trim?length gt 1>
package ${serviceImplPackage};

</#if>
<#if servicePackage?default("")?trim?length gt 1>
import ${serviceFullClassName};
</#if>
<#if superServiceImplClass?? && superServiceImplClass !="">
<#if entityPackage?default("")?trim?length gt 1>
import ${entityFullClassName};
</#if>
<#if mapperPackage?default("")?trim?length gt 1>
import ${mapperFullClassName};
</#if>
import ${superServiceImplClass};
</#if>
import org.springframework.stereotype.Service;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author!}
 * @since ${.now?string("yyyy-MM-dd HH:mm")}
 */
@Service
<#if superServiceImplClass?? && superServiceImplClass !="">
public class ${serviceImplName} extends ${superServiceImplClassName}<${mapperName}, ${entityName}> implements ${serviceName} {
<#else>
public class ${serviceImplName} implements ${serviceName} {
</#if>

}
