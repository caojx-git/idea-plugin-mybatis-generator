<#if controllerPackage?default("")?trim?length gt 1>
package ${controllerPackage};

</#if>
<#if isSelectedSwaggerCheckBox>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${table.comment!} 前端控制器
 *
 * @author ${author!}
 * @date ${.now?string("yyyy-MM-dd HH:mm")}
 */
<#if isSelectedSwaggerCheckBox>
@Api(tags = "${table.comment!}前端控制器")
</#if>
@RestController
@RequestMapping("/${controllerMappingHyphen}")
public class ${controllerName} {

}
