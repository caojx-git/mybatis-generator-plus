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
     * Entity包名
     */
    String ENTITY_PACKAGE_NAME = "entity";

    /**
     * Service包名
     */
    String SERVICE_PACKAGE_NAME = "service";

    /**
     * Service Impl包名
     */
    String SERVICE_IMPL_PACKAGE_NAME = "service.impl";

    /**
     * Mapper包名
     */
    String MAPPER_PACKAGE_NAME = "mapper";

    /**
     * Controller包名
     */
    String CONTROLLER_PACKAGE_NAME = "controller";

    /**
     * service 公共接口名称
     */
    String SUPER_SERVICE_NAME = "IService";

    /**
     * service 公共实现类名称
     */
    String SUPER_SERVICE_IMPL_NAME = "ServiceImpl";

    /**
     * service 公共接口
     */
    String SUPER_SERVICE_CLASS = "caojx.learn.mybatisgeneratorplus.generator.mybatis.IService";

    /**
     * service 公共实现类
     */
    String SUPER_SERVICE_IMPL_CLASS = "caojx.learn.mybatisgeneratorplus.generator.mybatis.ServiceImpl";
}