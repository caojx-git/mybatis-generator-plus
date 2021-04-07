package caojx.learn.mybatisgeneratorplus.generator;

import caojx.learn.mybatisgeneratorplus.common.enums.CodeGeneratorTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 代码生成辅助类
 *
 * @author caojx created on 2021/4/2 10:21 下午
 */
@Component
public class CodeGeneratorProcessorHolder {

    @Autowired
    private Map<String, CodeGenerator> codeGeneratorMap;

    /**
     * 根据代码生成枚举类型查找代码生成器
     *
     * @param generatorType
     * @return
     */
    public CodeGenerator findCodeGenerator(CodeGeneratorTypeEnum generatorType) {
        return findCodeGenerator(generatorType.getCode());
    }

    public CodeGenerator findCodeGenerator(String type) {
        type = Character.toLowerCase(type.charAt(0)) + type.substring(1);
        String name = type + CodeGenerator.class.getSimpleName();
        CodeGenerator codeGenerator = codeGeneratorMap.get(name);
        if (codeGenerator == null) {
            throw new CodeGeneratorException("代码生成器" + name + "不存在");
        }
        return codeGenerator;
    }
}