package caojx.learn.mybatisgeneratorplus;

import caojx.learn.mybatisgeneratorplus.common.properties.GeneratorCodeProperties;
import caojx.learn.mybatisgeneratorplus.common.utils.BeanUtil;
import caojx.learn.mybatisgeneratorplus.generator.CodeGenerator;
import caojx.learn.mybatisgeneratorplus.generator.CodeGeneratorProcessorHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * MyBatis-plus代码生成工具
 *
 * @author caojx created on 2021/4/1 5:01 下午
 */
@SpringBootApplication
@EnableConfigurationProperties(value = {DataSourceProperties.class, GeneratorCodeProperties.class})
public class MybatisGeneratorPlusApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(MybatisGeneratorPlusApplication.class, args);
        if (configurableApplicationContext.isRunning()) {
            BeanUtil.applicationContext = configurableApplicationContext;

            // 读取配置bean
            GeneratorCodeProperties generatorCodeProperties = BeanUtil.getBean(GeneratorCodeProperties.class);

            // 查询代码生成器类型
            CodeGeneratorProcessorHolder generatorProcessorHolder = BeanUtil.getBean(CodeGeneratorProcessorHolder.class);
            CodeGenerator codeGenerator = generatorProcessorHolder.findCodeGenerator(generatorCodeProperties.getType());

            // 代码生成
            codeGenerator.generate();
        }
    }

}
