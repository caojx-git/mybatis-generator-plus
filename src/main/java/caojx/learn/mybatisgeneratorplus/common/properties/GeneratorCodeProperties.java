package caojx.learn.mybatisgeneratorplus.common.properties;

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
     * 父包名
     */
    private String parent;

    /**
     * Mapper公共父类
     */
    private String superMapperClass;

    /**
     * 模糊表匹配，不配置生成全部
     */
    private String likeTable;

    /**
     * 实体使用lombok注解
     */
    private boolean entityLombokModel = true;

    /**
     * 自定义文件命名，注意 %s 会自动填充表原始实体名称
     */
    private String entityName = "%s";
    private String mapperName = "%sMapper";
    private String xmlName = "%sMapper";
    private String serviceName = "I%sService";
    private String serviceImplName = "%sServiceImpl";
    private String controllerName = "%sController";
}