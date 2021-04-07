package caojx.learn.mybatisgeneratorplus.generator.mybatisplus;

import caojx.learn.mybatisgeneratorplus.common.utils.BeanUtil;
import caojx.learn.mybatisgeneratorplus.generator.CodeGenerator;
import caojx.learn.mybatisgeneratorplus.generator.mybatisplus.config.MyBatisPlusGeneratorConfig;
import caojx.learn.mybatisgeneratorplus.generator.mybatisplus.engine.SimpleFreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import org.springframework.stereotype.Component;

/**
 * MyBatisPlus代码生成实现
 *
 * @author caojx created on 2021/4/2 10:13 下午
 */
@Component
public class MyBatisPlusCodeGenerator implements CodeGenerator {

    @Override
    public void generate() {
        System.out.println("--------------------start MyBatisPlusCodeGenerator-------------------");

        MyBatisPlusGeneratorConfig generatorConfig = BeanUtil.getBean(MyBatisPlusGeneratorConfig.class);
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 配置数据源
        mpg.setDataSource(generatorConfig.getDataSourceConfig());
        // 全局配置
        mpg.setGlobalConfig(generatorConfig.getGlobalConfig());
        // 包配置
        mpg.setPackageInfo(generatorConfig.getPackageConfig());
        // 策略配置
        mpg.setStrategy(generatorConfig.getStrategyConfig());
        // 模板引擎
        mpg.setTemplateEngine(new SimpleFreemarkerTemplateEngine());

        mpg.execute();
        System.out.println("--------------------start MyBatisPlusCodeGenerator-------------------");
    }
}