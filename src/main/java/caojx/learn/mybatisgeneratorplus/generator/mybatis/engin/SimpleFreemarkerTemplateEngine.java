package caojx.learn.mybatisgeneratorplus.generator.mybatis.engin;

import caojx.learn.mybatisgeneratorplus.common.properties.GeneratorCodeProperties;
import caojx.learn.mybatisgeneratorplus.common.utils.BeanUtil;
import caojx.learn.mybatisgeneratorplus.common.utils.CommonUtil;
import caojx.learn.mybatisgeneratorplus.generator.mybatis.constants.Constant;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.mybatis.generator.api.IntrospectedTable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Freemarker模板引擎
 *
 * @author caojx created on 2021/4/4 10:14 上午
 */
public class SimpleFreemarkerTemplateEngine {

    private Configuration configuration;

    private Map<String, Object> objectMap;

    private static final GeneratorCodeProperties GENERATOR_CODE_PROPERTIES = BeanUtil.getBean(GeneratorCodeProperties.class);

    public SimpleFreemarkerTemplateEngine() {
        objectMap = new HashMap<>();
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(ConstVal.UTF8);
        configuration.setClassForTemplateLoading(SimpleFreemarkerTemplateEngine.class, Constant.SLASH);
    }

    /**
     * 生成文件
     *
     * @param objectMap    模板参数
     * @param templatePath 模板路径
     * @param outputFile   生成文件
     * @throws IOException
     * @throws TemplateException
     */
    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws IOException, TemplateException {
        Template template = configuration.getTemplate(templatePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            template.process(objectMap, new OutputStreamWriter(fileOutputStream, ConstVal.UTF8));
        }
    }

    /**
     * 获取模板参数信息
     *
     * @param introspectedTable
     * @return
     */
    public Map<String, Object> getObjectMap(IntrospectedTable introspectedTable) {
        objectMap.put("table", getTableInfo(introspectedTable));
        objectMap.put("package", getPackageInfo());

        objectMap.put("author", GENERATOR_CODE_PROPERTIES.getAuthor());
        objectMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        objectMap.put("time", new SimpleDateFormat("h:mm a").format(new Date()));

        String entityName = CommonUtil.getSimpleClassName(introspectedTable.getBaseRecordType());
        objectMap.put("entity", entityName);

        objectMap.put("superMapperClassPackage", GENERATOR_CODE_PROPERTIES.getSuperMapperClass());
        objectMap.put("superMapperClass", GENERATOR_CODE_PROPERTIES.getSuperMapperClass());
        objectMap.put("superServiceClassPackage", Constant.SUPER_SERVICE_CLASS);
        objectMap.put("superServiceClass", Constant.SUPER_SERVICE_NAME);

        objectMap.put("superServiceImplClassPackage", Constant.SUPER_SERVICE_IMPL_CLASS);
        objectMap.put("superServiceImplClass", Constant.SUPER_SERVICE_IMPL_NAME);
        objectMap.put("controllerMappingHyphen", CommonUtil.camelToHyphen(entityName));
        return objectMap;
    }

    /**
     * 获取包信息
     *
     * @return
     */
    private Map<String, Object> getPackageInfo() {
        Map<String, Object> packageInfo = CollectionUtils.newHashMapWithExpectedSize(7);
        packageInfo.put("ModuleName", GENERATOR_CODE_PROPERTIES.getParent());
        packageInfo.put("Entity", CommonUtil.joinPackage(GENERATOR_CODE_PROPERTIES.getParent(), Constant.ENTITY_PACKAGE_NAME));
        packageInfo.put("Mapper", CommonUtil.joinPackage(GENERATOR_CODE_PROPERTIES.getParent(), Constant.MAPPER_PACKAGE_NAME));
        packageInfo.put("Service", CommonUtil.joinPackage(GENERATOR_CODE_PROPERTIES.getParent(), Constant.SERVICE_PACKAGE_NAME));
        packageInfo.put("ServiceImpl", CommonUtil.joinPackage(GENERATOR_CODE_PROPERTIES.getParent(), Constant.SERVICE_IMPL_PACKAGE_NAME));
        packageInfo.put("Controller", CommonUtil.joinPackage(GENERATOR_CODE_PROPERTIES.getParent(), Constant.CONTROLLER_PACKAGE_NAME));
        return packageInfo;
    }

    /**
     * 获取table信息
     *
     * @param introspectedTable
     * @return
     */
    private Map<String, Object> getTableInfo(IntrospectedTable introspectedTable) {
        Map<String, Object> packageInfo = CollectionUtils.newHashMapWithExpectedSize(7);
        packageInfo.put("comment", introspectedTable.getRemarks());

        String originalEntityName = CommonUtil.underlineToCamel(introspectedTable.getFullyQualifiedTable().getIntrospectedTableName());

        packageInfo.put("mapperName", CommonUtil.convertFileName(GENERATOR_CODE_PROPERTIES.getMapperName(), originalEntityName));
        packageInfo.put("serviceName", CommonUtil.convertFileName(GENERATOR_CODE_PROPERTIES.getServiceName(), originalEntityName));
        packageInfo.put("serviceImplName", CommonUtil.convertFileName(GENERATOR_CODE_PROPERTIES.getServiceImplName(), originalEntityName));
        packageInfo.put("controllerName", CommonUtil.convertFileName(GENERATOR_CODE_PROPERTIES.getControllerName(), originalEntityName));
        return packageInfo;
    }
}