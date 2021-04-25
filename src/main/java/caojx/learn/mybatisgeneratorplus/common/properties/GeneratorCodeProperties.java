package caojx.learn.mybatisgeneratorplus.common.properties;

import caojx.learn.mybatisgeneratorplus.common.constants.Constant;
import caojx.learn.mybatisgeneratorplus.common.enums.CodeGeneratorTypeEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 代码生成配置
 *
 * @author caojx created on 2021/4/1 2:59 下午
 */
@Data
@ConfigurationProperties(prefix = "generator.code")
public class GeneratorCodeProperties {

    /**
     * 代码生成类型，参考 {@link CodeGeneratorTypeEnum}
     * mybatis、tkMybatis、mybatisPlus
     */
    private String type;

    /**
     * 作者
     */
    private String author;

    /**
     * 代码生成目录
     */
    private String outputDir;

    /**
     * 实体包名
     */
    private String entityPackageName = Constant.ENTITY_PACKAGE_DEFAULT_NAME;

    /**
     * mapper包名
     */
    private String mapperPackageName = Constant.MAPPER_PACKAGE_DEFAULT_NAME;

    /**
     * mapper.xml包名
     */
    private String mapperXmlPackageName = Constant.MAPPER_XML_PACKAGE_DEFAULT_NAME;

    /**
     * service包名
     */
    private String servicePackageName = Constant.SERVICE_PACKAGE_DEFAULT_NAME;

    /**
     * serviceImpl包名
     */
    private String serviceImplPackageName = Constant.SERVICE_IMPL_PACKAGE_DEFAULT_NAME;

    /**
     * controller包名
     */
    private String controllerPackageName = Constant.CONTROLLER_PACKAGE_DEFAULT_NAME;

    /**
     * Mapper公共父类
     */
    private String superMapperClass;

    /**
     * service接口父类
     */
    private String superServiceClass;

    /**
     * service实现类父类
     */
    private String superServiceImplClass;

    /**
     * 模糊表匹配，不配置生成全部
     */
    private String likeTable;

    /**
     * 实体使用lombok注解
     */
    private boolean entityLombokModel = Boolean.TRUE;

    /**
     * 实体文件名格式，注意 %s 会自动填充表原始实体名称
     */
    private String entityNameFormat = Constant.ENTITY_NAME_DEFAULT_FORMAT;

    /**
     * mapper文件名格式，注意 %s 会自动填充表原始实体名称
     */
    private String mapperNameFormat = Constant.MAPPER_NAME_DEFAULT_FORMAT;

    /**
     * mapper.xml文件名格式，注意 %s 会自动填充表原始实体名称
     */
    private String xmlNameFormat = Constant.XML_NAME_DEFAULT_FORMAT;

    /**
     * service文件名格式，注意 %s 会自动填充表原始实体名称
     */
    private String serviceNameFormat = Constant.SERVICE_NAME_DEFAULT_FORMAT;

    /**
     * serviceImpl文件名格式，注意 %s 会自动填充表原始实体名称
     */
    private String serviceImplNameFormat = Constant.SERVICE_IMPL_NAME_DEFAULT_FORMAT;

    /**
     * controller文件名格式，注意 %s 会自动填充表原始实体名称
     */
    private String controllerNameFormat = Constant.CONTROLLER_NAME_DEFAULT_FORMAT;
}