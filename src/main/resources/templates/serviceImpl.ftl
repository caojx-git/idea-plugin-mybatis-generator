package ${serviceImplPackage};

import ${serviceFullClazzName};
<#if superServiceImplClass?? && superServiceImplClass !="">
import ${entityFullClazzName};
import ${mapperFullClazzName};
import ${superServiceImplClass};
</#if>
import org.springframework.stereotype.Service;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author!}
 * @date ${.now?string("yyyy-MM-dd HH:mm")}
 */
@Service
<#if superServiceImplClass?? && superServiceImplClass !="">
public class ${serviceImplName} extends ${superServiceImplClassName}<${mapperName}, ${entityName}> implements ${serviceName} {
<#else>
public class ${serviceImplName} implements ${serviceName} {
</#if>

}
