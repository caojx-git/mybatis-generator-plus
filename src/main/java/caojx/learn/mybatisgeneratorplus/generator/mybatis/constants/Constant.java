package caojx.learn.mybatisgeneratorplus.generator.mybatis.constants;

/**
 * 常量类
 *
 * @author caojx created on 2021/4/6 10:56 上午
 */
public interface Constant {

    /**
     * java文件后缀
     */
    String JAVA_SUFFIX = ".java";

    /**
     * xml文件后缀
     */
    String XML_SUFFIX = ".xml";

    /**
     * 斜线
     */
    String SLASH = "/";

    /**
     * service模板名称
     */
    String SERVICE_TEMPLATE_PATH = "/templates/mybatis/myService.java.ftl";

    /**
     * serviceImpl模板名称
     */
    String SERVICE_IMPL_TEMPLATE_PATH = "/templates/mybatis/myServiceImpl.java.ftl";

    /**
     * controller模板名称
     */
    String CONTROLLER_TEMPLATE_PATH = "/templates/mybatis/myController.java.ftl";

    /**
     * 实体 默认包名
     */
    String ENTITY_PACKAGE_DEFAULT_NAME = "com.generator.test.entity";

    /**
     * mapper 默认包名
     */
    String MAPPER_PACKAGE_DEFAULT_NAME = "com.generator.test.mapper";

    /**
     * mapper.xml 默认包名
     */
    String MAPPER_XML_PACKAGE_DEFAULT_NAME = "com.generator.test.mapper.xml";

    /**
     * service 默认包名
     */
    String SERVICE_PACKAGE_DEFAULT_NAME = "com.generator.test.service";

    /**
     * serviceImpl 默认包名
     */
    String SERVICE_IMPL_PACKAGE_DEFAULT_NAME = "com.generator.test.service.impl";

    /**
     * controller 默认包名
     */
    String CONTROLLER_PACKAGE_DEFAULT_NAME = "com.generator.test.controller";

    /**
     * 实体默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String ENTITY_NAME_DEFAULT_FORMAT = "%s";

    /**
     * mapper默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String MAPPER_NAME_DEFAULT_FORMAT = "%sMapper";

    /**
     * mapper.xml默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String XML_NAME_DEFAULT_FORMAT = "%sMapper";

    /**
     * service默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String SERVICE_NAME_DEFAULT_FORMAT = "I%sService";

    /**
     * serviceImpl默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String SERVICE_IMPL_NAME_DEFAULT_FORMAT = "%sServiceImpl";

    /**
     * controller默认文件名格式，注意 %s 会自动填充表原始实体名称
     */
    String CONTROLLER_NAME_DEFAULT_FORMAT = "%sController";
}