package ${package.Service};

<#if superServiceClassPackage??>
import ${package.Entity}.${entity};
import ${superServiceClassPackage};
</#if>

/**
 * ${table.comment!} 服务类接口
 *
 * @author ${author}
 * @date ${date} ${time}
 */
<#if superServiceClass??>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
<#else>
public interface ${table.serviceName} {
</#if>

}
