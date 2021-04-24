package ${package.ServiceImpl};

import ${package.Service}.${table.serviceName};
<#if superServiceImplClassPackage??>
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${superServiceImplClassPackage};
</#if>
import org.springframework.stereotype.Service;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @date ${date} ${time}
 */
@Service
<#if superServiceImplClass??>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {
</#if>

}
