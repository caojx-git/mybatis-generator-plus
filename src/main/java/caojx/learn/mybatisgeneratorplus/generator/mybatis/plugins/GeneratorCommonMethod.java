package caojx.learn.mybatisgeneratorplus.generator.mybatis.plugins;

import caojx.learn.mybatisgeneratorplus.common.properties.GeneratorCodeProperties;
import caojx.learn.mybatisgeneratorplus.common.utils.BeanUtil;
import caojx.learn.mybatisgeneratorplus.generator.mybatis.constants.Constant;
import caojx.learn.mybatisgeneratorplus.generator.mybatis.engin.SimpleFreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 插件公共方法
 *
 * @author caojx created on 2021/4/8 9:42 上午
 */
@Slf4j
public class GeneratorCommonMethod {

    private Pattern mapperPattern = Pattern.compile("Mapper$");

    private Pattern xmlPattern = Pattern.compile("Mapper.xml");

    private GeneratorCodeProperties generatorCodeProperties;

    private SimpleFreemarkerTemplateEngine freemarkerTemplateEngine;

    public GeneratorCommonMethod() {
        this.generatorCodeProperties = BeanUtil.getBean(GeneratorCodeProperties.class);
        this.freemarkerTemplateEngine = new SimpleFreemarkerTemplateEngine();
    }

    /**
     * 生成controller文件
     *
     * @param objectMap 模板参数
     */
    public void controllerGenerated(Map<String, Object> objectMap) {
        try {
            String controllerFile = getFileAbsolutePath(objectMap, Constant.CONTROLLER_PACKAGE_NAME, "controllerName");
            freemarkerTemplateEngine.writer(objectMap, Constant.CONTROLLER_TEMPLATE_PATH, controllerFile);
        } catch (Exception e) {
            log.error("controllerGenerated error", e);
        }
    }

    /**
     * 生成service文件
     *
     * @param objectMap 模板参数
     */
    public void serviceGenerated(Map<String, Object> objectMap) {
        try {
            String serviceFile = getFileAbsolutePath(objectMap, Constant.SERVICE_PACKAGE_NAME, "serviceName");
            freemarkerTemplateEngine.writer(objectMap, Constant.SERVICE_TEMPLATE_PATH, serviceFile);
        } catch (Exception e) {
            log.error("serviceGenerated error", e);
        }

    }

    /**
     * 生成serviceImpl文件
     *
     * @param objectMap 模板参数
     */
    public void serviceImplGenerated(Map<String, Object> objectMap) {
        try {
            String serviceImplFile = getFileAbsolutePath(objectMap, Constant.SERVICE_IMPL_PACKAGE_NAME, "serviceImplName");
            freemarkerTemplateEngine.writer(objectMap, Constant.SERVICE_IMPL_TEMPLATE_PATH, serviceImplFile);
        } catch (Exception e) {
            log.error("serviceImplGenerated error", e);
        }
    }

    /**
     * 获取文件绝对路径
     *
     * @param objectMap      模板参数
     * @param subPackageName 子包名称
     * @param fileNameParam  文件参数名
     * @return 文件绝对路径
     */
    private String getFileAbsolutePath(Map<String, Object> objectMap, String subPackageName, String fileNameParam) {
        // 文件生成路径
        StringBuilder directoryPath = new StringBuilder();
        directoryPath.append(generatorCodeProperties.getOutputDir());
        directoryPath.append(File.separator);
        directoryPath.append(generatorCodeProperties.getParent().replaceAll("\\.", File.separator));
        directoryPath.append(File.separator);
        directoryPath.append(subPackageName.replaceAll("\\.", File.separator));
        directoryPath.append(File.separator);

        // 目录不存在则创建目录
        File directory = new File(directoryPath.toString());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 文件名
        StringBuilder fileName = new StringBuilder();
        Map<String, Object> tableInfo = (Map<String, Object>) objectMap.get("table");
        String controllerName = (String) tableInfo.get(fileNameParam);
        fileName.append(controllerName);
        fileName.append(Constant.JAVA_SUFFIX);

        // 文件绝对路径
        return directoryPath.append(fileName).toString();
    }

    /**
     * 实体是否有必要重命名
     *
     * @param introspectedTable
     */
    public void renameEntityIfNecessary(IntrospectedTable introspectedTable) {
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
    public void renameMapperIfNecessary(IntrospectedTable introspectedTable) {
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
    public void renameXmlIfNecessary(IntrospectedTable introspectedTable) {
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
     * 实体是否有必要添加lombok注解
     *
     * @param topLevelClass
     */
    public void addLombokAnnotationIfNecessary(TopLevelClass topLevelClass) {
        if (generatorCodeProperties.isEntityLombokModel()) {
            topLevelClass.addImportedType("lombok.Data");
            topLevelClass.addAnnotation("@Data");

            topLevelClass.addImportedType("lombok.EqualsAndHashCode");
            topLevelClass.addAnnotation("@EqualsAndHashCode(callSuper = false)");
        }
    }

    /**
     * 实体添加类注释
     *
     * @param topLevelClass
     * @param introspectedTable
     */
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks() + " 实体");
        topLevelClass.addJavaDocLine(" * ");
        topLevelClass.addJavaDocLine(" * @author " + generatorCodeProperties.getAuthor());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String time = new SimpleDateFormat("h:mm a").format(new Date());
        topLevelClass.addJavaDocLine(" * @date " + date + " " + time);
        topLevelClass.addJavaDocLine(" */");
    }

    /**
     * 添加getter方法注释
     *
     * @param method
     * @param introspectedColumn
     */
    public void addGetterComment(Method method, IntrospectedColumn introspectedColumn) {
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            sb.append(" * 获取");
            sb.append(introspectedColumn.getRemarks());
            method.addJavaDocLine(sb.toString());
            method.addJavaDocLine(" *");
        }
        sb.setLength(0);
        sb.append(" * @return ");
        sb.append(introspectedColumn.getActualColumnName());
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            sb.append(" - ");
            sb.append(introspectedColumn.getRemarks());
        }
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
    }

    /**
     * 添加setter方法注释
     *
     * @param method
     * @param introspectedColumn
     */
    public void addSetterComment(Method method, IntrospectedColumn introspectedColumn) {
        StringBuilder sb = new StringBuilder();
        method.addJavaDocLine("/**");
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            sb.append(" * 设置");
            sb.append(introspectedColumn.getRemarks());
            method.addJavaDocLine(sb.toString());
            method.addJavaDocLine(" *");
        }
        Parameter parm = method.getParameters().get(0);
        sb.setLength(0);
        sb.append(" * @param ");
        sb.append(parm.getName());
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            sb.append(" ");
            sb.append(introspectedColumn.getRemarks());
        }
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
    }


    /**
     * 实体添加属性注释
     *
     * @param field
     * @param introspectedColumn
     */
    public void addFieldComment(Field field, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        if (StringUtils.isNotBlank(remarks)) {
            StringBuilder sb = new StringBuilder();
            field.addJavaDocLine("/**");
            sb.append(" * ");
            sb.append(remarks);
            field.addJavaDocLine(sb.toString());
            field.addJavaDocLine(" */");
        }
    }

    /**
     * Mapper接口添加注释生成
     *
     * @param interfaze
     * @param introspectedTable
     */
    public void addMapperClassComment(Interface interfaze, IntrospectedTable introspectedTable) {
        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine(" * " + introspectedTable.getRemarks() + " Mapper 接口");
        interfaze.addJavaDocLine(" * ");
        interfaze.addJavaDocLine(" * @author " + generatorCodeProperties.getAuthor());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String time = new SimpleDateFormat("h:mm a").format(new Date());
        interfaze.addJavaDocLine(" * @date " + date + " " + time);
        interfaze.addJavaDocLine(" */");
    }

    /**
     * Mapper接口是否有必要添加参数化类型
     *
     * @param interfaze
     * @param introspectedTable
     */
    public void addMapperTypeArgumentIfNecessary(Interface interfaze, IntrospectedTable introspectedTable) {
        String rootInterface = generatorCodeProperties.getSuperMapperClass();
        if (StringUtils.isNotBlank(rootInterface)) {
            FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
            interfaze.addImportedType(new FullyQualifiedJavaType(rootInterface));
            interfaze.addSuperInterface(new FullyQualifiedJavaType(rootInterface + "<" + entityType.getShortName() + ">"));
            interfaze.addImportedType(entityType);
        }
    }

    /**
     * Mapper方法添加注释
     *
     * @param method
     * @param remarks
     */
    public void addMapperMethodComment(Method method, String remarks) {
        Parameter parameter = method.getParameters().get(0);
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * " + remarks);
        method.addJavaDocLine(" * ");
        method.addJavaDocLine(" * @param " + parameter.getName());
        method.addJavaDocLine(" */");
    }
}