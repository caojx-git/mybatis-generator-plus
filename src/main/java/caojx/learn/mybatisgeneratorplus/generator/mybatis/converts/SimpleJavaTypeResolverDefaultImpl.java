package caojx.learn.mybatisgeneratorplus.generator.mybatis.converts;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * MYSQL 数据库字段映射
 *
 * @author caojx created on 2021/4/2 9:33 上午
 */
public class SimpleJavaTypeResolverDefaultImpl extends JavaTypeResolverDefaultImpl {

    public SimpleJavaTypeResolverDefaultImpl() {
        super();
        //把数据库的 TINYINT 映射成 Integer
        super.typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
        super.typeMap.put(Types.SMALLINT, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
    }
}