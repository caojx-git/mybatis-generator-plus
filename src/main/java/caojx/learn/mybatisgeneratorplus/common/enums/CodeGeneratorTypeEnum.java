package caojx.learn.mybatisgeneratorplus.common.enums;

import lombok.Getter;

/**
 * 生成代码类型枚举
 *
 * @author caojx created on 2021/4/2 7:21 下午
 */
@Getter
public enum CodeGeneratorTypeEnum {

    /**
     * 生成MyBatis风格类型代码
     */
    MYBATIS("MyBatis"),

    /**
     * 生成TkMyBatis风格类型代码
     */
    TK_MYBATIS("TkMyBatis"),

    /**
     * 生成MyBatisPlus风格类型代码
     */
    MYBATIS_PLUS("MyBatisPlus");

    private String code;

    CodeGeneratorTypeEnum(String code) {
        this.code = code;
    }
}