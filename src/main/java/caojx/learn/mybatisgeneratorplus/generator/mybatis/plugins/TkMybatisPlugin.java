package caojx.learn.mybatisgeneratorplus.generator.mybatis.plugins;

import caojx.learn.mybatisgeneratorplus.common.properties.GeneratorCodeProperties;
import caojx.learn.mybatisgeneratorplus.common.utils.BeanUtil;
import caojx.learn.mybatisgeneratorplus.generator.mybatis.engin.SimpleFreemarkerTemplateEngine;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Map;

/**
 * TkMapper 插件
 *
 * @author caojx created on 2021/4/3 8:35 下午
 */
public class TkMybatisPlugin extends tk.mybatis.mapper.generator.MapperPlugin {

    private GeneratorCommonMethod generatorCommonMethod;

    private GeneratorCodeProperties generatorCodeProperties;

    private SimpleFreemarkerTemplateEngine freemarkerTemplateEngine;

    public TkMybatisPlugin() {
        this.generatorCommonMethod = new GeneratorCommonMethod();
        this.freemarkerTemplateEngine = new SimpleFreemarkerTemplateEngine();
        this.generatorCodeProperties = BeanUtil.getBean(GeneratorCodeProperties.class);
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
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // Mapper接口添加注释生成
        generatorCommonMethod.addMapperClassComment(interfaze, introspectedTable);
        // Mapper接口是否有必要添加参数化类型
        generatorCommonMethod.addMapperTypeArgumentIfNecessary(interfaze, introspectedTable);
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return !generatorCodeProperties.isEntityLombokModel();
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return !generatorCodeProperties.isEntityLombokModel();
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 实体添加类注释
        generatorCommonMethod.addModelClassComment(topLevelClass, introspectedTable);
        // 实体是否有必要添加lombok注解
        generatorCommonMethod.addLombokAnnotationIfNecessary(topLevelClass);
        // 生成实体
        super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
        // 模板参数
        Map<String, Object> objectMap = freemarkerTemplateEngine.getObjectMap(introspectedTable);
        // 生成service
        generatorCommonMethod.generateServiceFile(objectMap);
        // 生成 serviceImpl
        generatorCommonMethod.generateServiceImplFile(objectMap);
        // 生成 Controller
        generatorCommonMethod.generateControllerFile(objectMap);
        return true;
    }
}