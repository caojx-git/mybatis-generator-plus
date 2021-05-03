package caojx.learn.mybatisgeneratorplus.generator.mybatisplus.config;


import caojx.learn.mybatisgeneratorplus.common.properties.GeneratorCodeProperties;
import caojx.learn.mybatisgeneratorplus.generator.mybatisplus.converts.SimpleMysqlTypeConvert;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bean配置
 * 配置参考 https://github.com/baomidou/generator
 *
 * @author caojx created on 2021/4/1 3:30 下午
 */
@Configuration
public class MyBatisPlusGeneratorConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private GeneratorCodeProperties generatorCodeProperties;

    /**
     * 数据源配置
     *
     * @return
     */
    @Bean
    public DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // jdbc路径
        dataSourceConfig.setUrl(dataSourceProperties.getUrl());
        // 数据库驱动
        dataSourceConfig.setDriverName(dataSourceProperties.getDriverClassName());
        // 用户名
        dataSourceConfig.setUsername(dataSourceProperties.getUsername());
        // 密码
        dataSourceConfig.setPassword(dataSourceProperties.getPassword());

        // 字段类型转换
        if (DbType.MYSQL.getDb().equals(dataSourceConfig.getDbType().getDb())) {
            dataSourceConfig.setTypeConvert(new SimpleMysqlTypeConvert());
        }
        return dataSourceConfig;
    }

    /**
     * 全局配置
     *
     * @return
     */
    @Bean
    public GlobalConfig getGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        // 指定输出目录
        globalConfig.setOutputDir(generatorCodeProperties.getOutputDir());
        // 作者名
        globalConfig.setAuthor(generatorCodeProperties.getAuthor());
        // 时间策略
        globalConfig.setDateType(DateType.ONLY_DATE);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        if (StringUtils.isNotBlank(generatorCodeProperties.getEntityNameFormat())) {
            globalConfig.setEntityName(generatorCodeProperties.getEntityNameFormat());
        }
        if (StringUtils.isNotBlank(generatorCodeProperties.getMapperNameFormat())) {
            globalConfig.setMapperName(generatorCodeProperties.getMapperNameFormat());
        }
        if (StringUtils.isNotBlank(generatorCodeProperties.getXmlNameFormat())) {
            globalConfig.setXmlName(generatorCodeProperties.getXmlNameFormat());
        }
        if (StringUtils.isNotBlank(generatorCodeProperties.getServiceNameFormat())) {
            globalConfig.setServiceName(generatorCodeProperties.getServiceNameFormat());
        }
        if (StringUtils.isNotBlank(generatorCodeProperties.getServiceImplNameFormat())) {
            globalConfig.setServiceImplName(generatorCodeProperties.getServiceImplNameFormat());
        }
        if (StringUtils.isNotBlank(generatorCodeProperties.getControllerNameFormat())) {
            globalConfig.setControllerName(generatorCodeProperties.getControllerNameFormat());
        }

        // 启用BaseColumnList
        globalConfig.setBaseColumnList(true);
        // 启用BaseResultMap生成
        globalConfig.setBaseResultMap(true);

        // 是否打开生成目录
        globalConfig.setOpen(false);
        // 是否生成swagger2注解
        globalConfig.setSwagger2(generatorCodeProperties.isSwagger2());

        return globalConfig;
    }

    /**
     * 包配置
     *
     * @return
     */
    @Bean
    public PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        // 父包名
        packageConfig.setParent("");
        // 实体包名
        packageConfig.setEntity(generatorCodeProperties.getEntityPackageName());
        // 实体Mapper包名
        packageConfig.setMapper(generatorCodeProperties.getMapperPackageName());
        // xml路径名
        packageConfig.setXml(generatorCodeProperties.getMapperXmlPackageName());
        // service包名
        packageConfig.setService(generatorCodeProperties.getServicePackageName());
        // serviceImpl包名
        packageConfig.setServiceImpl(generatorCodeProperties.getServiceImplPackageName());
        // controller 包名
        packageConfig.setController(generatorCodeProperties.getControllerPackageName());
        return packageConfig;
    }

    /**
     * 策略配置
     *
     * @return
     */
    @Bean
    public StrategyConfig getStrategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // 是否开启lombok模型
        strategyConfig.setEntityLombokModel(generatorCodeProperties.isEntityLombokModel());
        // 开启生成实体时生成字段注解
        strategyConfig.setEntityTableFieldAnnotationEnable(false);
        // 开启生成@RestController控制器
        strategyConfig.setRestControllerStyle(true);
        // 开启驼峰转连字符
        strategyConfig.setControllerMappingHyphenStyle(true);

        // 模糊表匹配
        if (StringUtils.isNotBlank(generatorCodeProperties.getLikeTable())) {
            strategyConfig.setLikeTable(new LikeTable(generatorCodeProperties.getLikeTable()));
        }

        // Mapper公共父类
        if (StringUtils.isNotBlank(generatorCodeProperties.getSuperMapperClass())) {
            strategyConfig.setSuperMapperClass(generatorCodeProperties.getSuperMapperClass());
        }

        // service接口父类
        if (StringUtils.isNotBlank(generatorCodeProperties.getSuperServiceClass())) {
            strategyConfig.setSuperServiceClass(generatorCodeProperties.getSuperServiceClass());
        }

        // service实现类父类
        if (StringUtils.isNotBlank(generatorCodeProperties.getSuperServiceImplClass())) {
            strategyConfig.setSuperServiceImplClass(generatorCodeProperties.getSuperServiceImplClass());
        }

        return strategyConfig;
    }

}