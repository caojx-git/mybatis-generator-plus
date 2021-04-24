[toc]

# mybatis-generator-plus

**特点：**

- 1.生成代码入口：MybatisGeneratorPlusApplication
- 2.支持MyBatis、TkMyBatis、MyBatisPlus 三中风格代码生成
- 3.实体支持Lombok风格
- 4.MyBatis、TkMyBatis、MyBatisPlus 支持生成Entity、Mapper、Xml、Service、ServiceImpl、Controller
- 5.Entity、Mapper、Xml、Service、ServiceImpl、Controller可以使用自定义命名格式、自定义生成目录

# 1.配置 application.properties 示例

```properties
#非web模式
spring.main.web-application-type=none
#数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

# 相关配置属性请参考 caojx.learn.mybatisgeneratorplus.common.properties.GeneratorCodeProperties
generator.code.type=MyBatis
generator.code.author=caojx
generator.code.outputDir=/Users/caojx/Desktop/mybatis-generator-plus/src/main/java
generator.code.likeTable=%

# 实体
generator.code.entityPackageName=com.generator.test.entity
generator.code.entityLombokModel=true
#generator.code.entityNameFormat=%sEntity

# mapper
generator.code.mapperPackageName=com.generator.test.mapper
#generator.code.superMapperClass=caojx.learn.mybatisgeneratorplus.generator.mybatis.TkBaseMapper
#generator.code.mapperNameFormat=%sDao

# mapper.xml
generator.code.mapperXmlPackageName=com.generator.test.mapper.xml
#generator.code.xmlNameFormat=%sDao

# service
generator.code.servicePackageName=com.generator.test.service
#generator.code.superServiceClass=caojx.learn.mybatisgeneratorplus.generator.mybatis.IService
#generator.code.serviceNameFormat=%sService

# serviceImpl
generator.code.serviceImplPackageName=com.generator.test.service.impl
#generator.code.superServiceImplClass=caojx.learn.mybatisgeneratorplus.generator.mybatis.ServiceImpl
#generator.code.serviceImplNameFormat=%sServiceImpl

# controller
generator.code.controllerPackageName=com.generator.test.controller
#generator.code.controllerNameFormat=%sController

# 日志配置
logging.config=classpath:logback-spring.xml
```

# 2.配置说明

## 2.1 生成代码注释作者参数

```properties
generator.code.author=caojx
```

## 2.2 生成不同风格的代码

生成普通MyBatis风格代码

```properties
generator.code.type=MyBatis
```

生成普通TkMyBatis风格代码

```properties
generator.code.type=TkMyBatis
```

生成普通MyBatisPlu风格代码

```properties
generator.code.type=MyBatisPlus
```

## 2.3 代码生成绝对路径配置

```properties
generator.code.outputDir=/Users/caojx/Desktop/mybatis-generator-plus/src/main/java
```

## 2.4 需要生成代码的数据库表

支持使用%对表进行模糊匹配

```properties
generator.code.likeTable=%user
```

## 2.5 生成实体规则配置

```properties
# 实体包名，不配置默认为com.generator.test.entity
generator.code.entityPackageName=com.generator.test.entity
# 默认使用lombok风格实体，如果想生成getter、setter的实体，可以设置为false
generator.code.entityLombokModel=true
# 实体名格式，不配置默认为 %s
generator.code.entityNameFormat=%sEntity
```

## 2.6 mapper生成规则配置

```properties
# Mapper包名，不配置默认为com.generator.test.mapper
generator.code.mapperPackageName=com.generator.test.mapper
# Mapper父类接口，可以不配置，MyBatis、TkMyBatis 不配置则无父接口；MyBatisPlus 不配置则使用默认的父接口
generator.code.superMapperClass=caojx.learn.mybatisgeneratorplus.generator.mybatis.TkBaseMapper
# Mapper命名格式，不配置默认为 %sMapper
generator.code.mapperNameFormat=%sDao
```

## 2.7 mapper.xml生成规则配置

```properties
# Mapper.xml包名，不配置默认为com.generator.test.mapper.xml
generator.code.mapperXmlPackageName=com.generator.test.mapper.xml
# Mapper.xml命名格式，不配置默认为 %sMapper
generator.code.xmlNameFormat=%sDao
```

## 2.8 service生成规则配置

```properties
# service包名，不配置默认为com.generator.test.service
generator.code.servicePackageName=com.generator.test.service
# service继承父接口，可以不配置，MyBatis、TkMyBatis 不配置则不继承父接口；MyBatisPlus 不配置则继承默认父接口
generator.code.superServiceClass=caojx.learn.mybatisgeneratorplus.generator.mybatis.IService
# service命名格式，不配置默认为 %sService
generator.code.serviceNameFormat=%sService
```

## 2.9 serviceImpl生成规则配置

```properties
# ServiceImpl包名，不配置默认为com.generator.test.service.impl
generator.code.serviceImplPackageName=com.generator.test.service.impl
# serviceImpl继承父类，可以不配置，MyBatis、TkMyBatis 不配置则不继承父类；MyBatisPlus 不配置则继承默认父类
generator.code.superServiceImplClass=caojx.learn.mybatisgeneratorplus.generator.mybatis.ServiceImpl
# ServiceImpl命名格式，不配置默认为 %sServiceImpl
generator.code.serviceImplNameFormat=%sServiceImpl
```

## 2.10 controller生成规则配置

```properties
# controller包名，不配置默认为com.generator.test.mapper.xml
generator.code.controllerPackageName=com.generator.test.controller
# controller命名格式，不配置默认为 %sController
generator.code.controllerNameFormat=%sController
```