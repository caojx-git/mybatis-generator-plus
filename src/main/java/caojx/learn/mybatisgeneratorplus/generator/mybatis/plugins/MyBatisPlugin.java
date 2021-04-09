package caojx.learn.mybatisgeneratorplus.generator.mybatis.plugins;

import caojx.learn.mybatisgeneratorplus.common.properties.GeneratorCodeProperties;
import caojx.learn.mybatisgeneratorplus.common.utils.BeanUtil;
import caojx.learn.mybatisgeneratorplus.generator.mybatis.engin.SimpleFreemarkerTemplateEngine;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Map;

/**
 * MyBatis 插件
 *
 * @author caojx created on 2021/4/2 2:25 下午
 */
public class MyBatisPlugin extends PluginAdapter {

    private GeneratorCommonMethod generatorCommonMethod;

    private GeneratorCodeProperties generatorCodeProperties;

    private SimpleFreemarkerTemplateEngine freemarkerTemplateEngine;

    public MyBatisPlugin() {
        this.generatorCommonMethod = new GeneratorCommonMethod();
        this.generatorCodeProperties = BeanUtil.getBean(GeneratorCodeProperties.class);
        this.freemarkerTemplateEngine = new SimpleFreemarkerTemplateEngine();
    }

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        // 实体是否有必要重命名
        generatorCommonMethod.renameEntityIfNecessary(introspectedTable);
        // Mapper接口是否有必要重命名
        generatorCommonMethod.renameMapperIfNecessary(introspectedTable);
        // xml是否有必要重命名
        generatorCommonMethod.renameXmlIfNecessary(introspectedTable);
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 实体添加类注释
        generatorCommonMethod.addModelClassComment(topLevelClass, introspectedTable);
        // 实体是否有必要添加lombok注解
        generatorCommonMethod.addLombokAnnotationIfNecessary(topLevelClass);
        // 模板参数
        Map<String, Object> objectMap = freemarkerTemplateEngine.getObjectMap(introspectedTable);
        // 生成service
        generatorCommonMethod.serviceGenerated(objectMap);
        // 生成 serviceImpl
        generatorCommonMethod.serviceImplGenerated(objectMap);
        // 生成 Controller
        generatorCommonMethod.controllerGenerated(objectMap);
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 实体添加属性注释
        generatorCommonMethod.addFieldComment(field, introspectedColumn);
        return true;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 添加Getter方法注释
        generatorCommonMethod.addGetterComment(method, introspectedColumn);
        return !generatorCodeProperties.isEntityLombokModel();
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 添加Setter方法注释
        generatorCommonMethod.addSetterComment(method, introspectedColumn);
        return !generatorCodeProperties.isEntityLombokModel();
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // Mapper接口添加注释生成
        generatorCommonMethod.addMapperClassComment(interfaze, introspectedTable);
        // Mapper接口是否有必要添加参数化类型
        generatorCommonMethod.addMapperTypeArgumentIfNecessary(interfaze, introspectedTable);
        return true;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        // 添加方法注释
        generatorCommonMethod.addMapperMethodComment(method, "根据主键删除数据");
        return true;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        // 添加方法注释
        generatorCommonMethod.addMapperMethodComment(method, "插入数据库记录（不建议使用）");
        return true;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        // 添加方法注释
        generatorCommonMethod.addMapperMethodComment(method, "插入数据库记录（建议使用）");
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        // 添加方法注释
        generatorCommonMethod.addMapperMethodComment(method, "根据主键id查询");
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        // 添加方法注释
        generatorCommonMethod.addMapperMethodComment(method, "修改数据(推荐使用)");
        return true;
    }
}