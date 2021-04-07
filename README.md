[toc]

# mybatis-generator-plus

**特点：**

- 1.生成代码入口：MybatisGeneratorPlusApplication
- 2.支持MyBatis、TkMyBatis、MyBatisPlus 三中风格代码生成
- 3.MyBatis、TkMyBatis、MyBatisPlus 支持生成Entity、Mapper、Xml、Service、ServiceImpl、Controller
- 4.Entity、Mapper、Xml、Service、ServiceImpl、Controller可以使用自定义命名风格

# 1.配置 application.properties 示例

```properties
#数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root
# 相关配置属性请参考 caojx.learn.mybatisgeneratorplus.common.properties.GeneratorCodeProperties
generator.code.author=caojx
generator.code.type=MyBatis
generator.code.outputDir=/Users/caojx/Desktop/mybatis-generator-plus/src/main/java
generator.code.parent=com.generator.test
generator.code.likeTable=%user
generator.code.entityLombokModel=true
generator.code.entityName=%sEntity
#generator.code.mapperName=%sDao
#generator.code.xmlName=%sDao
#generator.code.serviceName=%sService
#generator.code.serviceImplName=%sServiceImpl
#generator.code.controllerName=%sController
generator.code.superMapperClass=caojx.learn.mybatisgeneratorplus.generator.mybatis.TkBaseMapper
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

## 2.4 父包名配置

```properties
generator.code.parent=com.generator.test
```

## 2.4 对应需要生成代码的数据库表

支持使用%对表进行模糊匹配

```properties
generator.code.likeTable=%user
```

## 2.5 实体使用lombok风格

默认生成 lombok 风格实体，如果想生成getter、setter的实体，可以设置为false

```properties
generator.code.entityLombokModel=true
```

## 2.6 自定义实体、mapper、xml命名风格

%s代表原始实体名字，不配置使用默认风格

```properties
generator.code.entityName=%sEntity
generator.code.mapperName=%sDao
generator.code.xmlName=%sDao
#generator.code.serviceName=%sService
#generator.code.serviceImplName=%sServiceImpl
#generator.code.controllerName=%sController
```

## 2.7 配置Mapper基类

TkMyBatis 必须配置一个Mapper基类  
MyBatis 可以不配置Mapper基类  
MyBatisPlus 可以不配置Mapper基类，不配置时使用默认的Mapper基类

```properties
generator.code.superMapperClass=caojx.learn.mybatisgeneratorplus.generator.mybatis.TkBaseMapper
```