package caojx.learn.mybatisgeneratorplus.generator.mybatis.plugins;

import caojx.learn.mybatisgeneratorplus.common.properties.GeneratorCodeProperties;
import caojx.learn.mybatisgeneratorplus.common.utils.BeanUtil;
import caojx.learn.mybatisgeneratorplus.generator.mybatis.constants.Constant;
import caojx.learn.mybatisgeneratorplus.generator.mybatis.engin.SimpleFreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TkMapper 插件
 *
 * @author caojx created on 2021/4/3 8:35 下午
 */
public class TkMybatisPlugin extends tk.mybatis.mapper.generator.MapperPlugin {

    private Pattern mapperPattern = Pattern.compile("Mapper$");

    private Pattern xmlPattern = Pattern.compile("Mapper.xml");

    private GeneratorCodeProperties generatorCodeProperties;

    private SimpleFreemarkerTemplateEngine freemarkerTemplateEngine;

    public TkMybatisPlugin() {
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
        renameEntityIfNecessary(introspectedTable);
        // Mapper接口是否有必要重命名
        renameMapperIfNecessary(introspectedTable);
        // xml是否有必要重命名
        renameXmlIfNecessary(introspectedTable);
    }

    /**
     * 是否生成Mapper文件
     *
     * @param interfaze
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // Mapper接口添加注释生成
        addMapperClassComment(interfaze, introspectedTable);
        // Mapper接口是否有必要添加参数化类型
        addMapperTypeArgumentIfNecessary(interfaze, introspectedTable);
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
        addModelClassComment(topLevelClass, introspectedTable);
        // 实体是否有必要添加lombok注解
        addLombokAnnotationIfNecessary(topLevelClass);
        // 生成实体
        super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
        // 模板参数
        Map<String, Object> objectMap = freemarkerTemplateEngine.getObjectMap(introspectedTable);
        // 生成service
        generateServiceFile(objectMap);
        // 生成 serviceImpl
        generateServiceImplFile(objectMap);
        // 生成 Controller
        generateControllerFile(objectMap);
        return true;
    }

    /**
     * 生成controller文件
     */
    private void generateControllerFile(Map<String, Object> objectMap) {
        try {
            StringBuilder pathSb = new StringBuilder();
            pathSb.append(generatorCodeProperties.getOutputDir());
            pathSb.append(File.separator);
            pathSb.append(generatorCodeProperties.getParent().replaceAll("\\.", File.separator));
            pathSb.append(File.separator);
            pathSb.append("controller");

            File catalog = new File(pathSb.toString());
            if (!catalog.exists()) {
                catalog.mkdirs();
            }

            StringBuilder controllerFileFileSb = new StringBuilder();
            controllerFileFileSb.append(pathSb);
            controllerFileFileSb.append(File.separator);

            Map<String, Object> tableInfo = (Map<String, Object>) objectMap.get("table");
            String controllerName = (String) tableInfo.get("controllerName");
            controllerFileFileSb.append(controllerName);
            controllerFileFileSb.append(Constant.JAVA_SUFFIX);
            String controllerFile = controllerFileFileSb.toString();
            freemarkerTemplateEngine.writer(objectMap, Constant.CONTROLLER_TEMPLATE_PATH, controllerFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成service文件
     */
    private void generateServiceFile(Map<String, Object> objectMap) {
        try {
            StringBuilder pathSb = new StringBuilder();
            pathSb.append(generatorCodeProperties.getOutputDir());
            pathSb.append(File.separator);
            pathSb.append(generatorCodeProperties.getParent().replaceAll("\\.", File.separator));
            pathSb.append(File.separator);
            pathSb.append("service");

            File catalog = new File(pathSb.toString());
            if (!catalog.exists()) {
                catalog.mkdirs();
            }

            StringBuilder serviceFileSb = new StringBuilder();
            serviceFileSb.append(pathSb);
            serviceFileSb.append(File.separator);

            Map<String, Object> tableInfo = (Map<String, Object>) objectMap.get("table");
            String serviceName = (String) tableInfo.get("serviceName");
            serviceFileSb.append(serviceName);
            serviceFileSb.append(Constant.JAVA_SUFFIX);
            String serviceFile = serviceFileSb.toString();
            freemarkerTemplateEngine.writer(objectMap, Constant.SERVICE_TEMPLATE_PATH, serviceFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成serviceImpl文件
     */
    private void generateServiceImplFile(Map<String, Object> objectMap) {
        try {
            StringBuilder pathSb = new StringBuilder();
            pathSb.append(generatorCodeProperties.getOutputDir());
            pathSb.append(File.separator);
            pathSb.append(generatorCodeProperties.getParent().replaceAll("\\.", File.separator));
            pathSb.append(File.separator);
            pathSb.append("service");
            pathSb.append(File.separator);
            pathSb.append("impl");

            File catalog = new File(pathSb.toString());
            if (!catalog.exists()) {
                catalog.mkdirs();
            }

            StringBuilder serviceImplFileSb = new StringBuilder();
            serviceImplFileSb.append(pathSb);
            serviceImplFileSb.append(File.separator);

            Map<String, Object> tableInfo = (Map<String, Object>) objectMap.get("table");
            String serviceImplName = (String) tableInfo.get("serviceImplName");
            serviceImplFileSb.append(serviceImplName + ".java");
            String serviceImplFile = serviceImplFileSb.toString();

            freemarkerTemplateEngine.writer(objectMap, Constant.SERVICE_IMPL_TEMPLATE_PATH, serviceImplFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 实体是否有必要重命名
     *
     * @param introspectedTable
     */
    private void renameEntityIfNecessary(IntrospectedTable introspectedTable) {
        if (StringUtils.isNotBlank(this.generatorCodeProperties.getEntityName())) {
            String oldType = introspectedTable.getBaseRecordType();

            // 拼接新的后缀名
            oldType = String.format(this.generatorCodeProperties.getEntityName(), oldType);

            introspectedTable.setBaseRecordType(oldType);
        }
    }

    /**
     * Mapper接口是否有必要重命名
     *
     * @param introspectedTable
     */
    private void renameMapperIfNecessary(IntrospectedTable introspectedTable) {
        if (StringUtils.isNotBlank(this.generatorCodeProperties.getMapperName())) {
            String oldType = introspectedTable.getMyBatis3JavaMapperType();

            // 清理原有的后缀名
            Matcher matcher = mapperPattern.matcher(oldType);
            oldType = matcher.replaceAll("");

            // 拼接新的后缀名
            oldType = String.format(this.generatorCodeProperties.getMapperName(), oldType);

            introspectedTable.setMyBatis3JavaMapperType(oldType);
        }
    }

    /**
     * xml是否有必要重命名
     *
     * @param introspectedTable
     */
    private void renameXmlIfNecessary(IntrospectedTable introspectedTable) {
        if (StringUtils.isNotBlank(this.generatorCodeProperties.getXmlName())) {
            String oldType = introspectedTable.getMyBatis3XmlMapperFileName();

            // 清理原有的后缀名
            Matcher matcher = xmlPattern.matcher(oldType);
            oldType = matcher.replaceAll("");

            // 拼接新的后缀名
            oldType = String.format(this.generatorCodeProperties.getXmlName() + Constant.XML_SUFFIX, oldType);

            introspectedTable.setMyBatis3XmlMapperFileName(oldType);
        }
    }

    /**
     * 实体添加类注释
     *
     * @param topLevelClass
     * @param introspectedTable
     */
    private void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* " + introspectedTable.getRemarks() + " 实体");
        topLevelClass.addJavaDocLine("* ");
        topLevelClass.addJavaDocLine("* @author " + generatorCodeProperties.getAuthor());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String time = new SimpleDateFormat("h:mm a").format(new Date());
        topLevelClass.addJavaDocLine("* @date " + date + " " + time);
        topLevelClass.addJavaDocLine("*/");
    }

    /**
     * 实体是否有必要添加lombok注解
     *
     * @param topLevelClass
     */
    private void addLombokAnnotationIfNecessary(TopLevelClass topLevelClass) {
        if (generatorCodeProperties.isEntityLombokModel()) {
            topLevelClass.addImportedType("lombok.Data");
            topLevelClass.addAnnotation("@Data");

            topLevelClass.addImportedType("lombok.EqualsAndHashCode");
            topLevelClass.addAnnotation("@EqualsAndHashCode(callSuper = false)");
        }
    }

    /**
     * Mapper接口添加注释生成
     *
     * @param interfaze
     * @param introspectedTable
     */
    private void addMapperClassComment(Interface interfaze, IntrospectedTable introspectedTable) {
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine("* " + introspectedTable.getRemarks() + " Mapper 接口");
        interfaze.addJavaDocLine("* ");
        interfaze.addJavaDocLine("* @author " + generatorCodeProperties.getAuthor());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String time = new SimpleDateFormat("h:mm a").format(new Date());
        interfaze.addJavaDocLine("* @date " + date + " " + time);
        interfaze.addJavaDocLine("*/");
    }

    /**
     * Mapper接口是否有必要添加参数化类型
     *
     * @param interfaze
     * @param introspectedTable
     */
    private void addMapperTypeArgumentIfNecessary(Interface interfaze, IntrospectedTable introspectedTable) {
        String rootInterface = generatorCodeProperties.getSuperMapperClass();
        if (StringUtils.isNotBlank(rootInterface)) {
            FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
            interfaze.addImportedType(new FullyQualifiedJavaType(rootInterface));
            interfaze.addSuperInterface(new FullyQualifiedJavaType(rootInterface + "<" + entityType.getShortName() + ">"));
            interfaze.addImportedType(entityType);
        }
    }
}