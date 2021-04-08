package caojx.learn.mybatisgeneratorplus.generator.mybatis;

import caojx.learn.mybatisgeneratorplus.generator.CodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * TKMyBatis代码生成实现
 *
 * @author caojx created on 2021/4/2 10:13 下午
 */
@Slf4j
@Component
public class TkMyBatisCodeGenerator implements CodeGenerator {

    @Override
    public void generate() {
        try {
            log.info("--------------------start TkMyBatisCodeGenerator-------------------");
            List<String> warnings = new ArrayList();
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(Thread.currentThread().getContextClassLoader().getResourceAsStream("generator/generatorConfig-tkmybatis.xml"));
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            log.info("--------------------end TkMyBatisCodeGenerator-------------------");
        } catch (Exception e) {
            log.error("TkMyBatisCodeGenerator error", e);
        }
    }
}