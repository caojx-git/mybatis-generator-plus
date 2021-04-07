package caojx.learn.mybatisgeneratorplus.generator.mybatisplus.converts;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * MYSQL 数据库字段映射
 *
 * @author caojx created on 2021/4/1 5:01 下午
 */
public class SimpleMysqlTypeConvert extends MySqlTypeConvert {

    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        String t = fieldType.toLowerCase();
        if (t.contains("clob")) {
            return DbColumnType.STRING;
        } else if (t.contains("tinyint")) {
            return DbColumnType.INTEGER;
        }
        return super.processTypeConvert(globalConfig, fieldType);
    }
}