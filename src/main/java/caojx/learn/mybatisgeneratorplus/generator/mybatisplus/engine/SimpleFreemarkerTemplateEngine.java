package caojx.learn.mybatisgeneratorplus.generator.mybatisplus.engine;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Freemarker 模板引擎
 *
 * @author caojx created on 2021/4/1 9:09 下午
 */
public class SimpleFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    /**
     * 增加模板参数
     *
     * @param tableInfo
     * @return
     */
    @Override
    public Map<String, Object> getObjectMap(TableInfo tableInfo) {
        Map<String, Object> objectMap = super.getObjectMap(tableInfo);
        objectMap.put("controllerMappingHyphen", StringUtils.camelToHyphen(StringUtils.underlineToCamel(tableInfo.getName())));
        objectMap.put("time", new SimpleDateFormat("h:mm a").format(new Date()));
        return objectMap;
    }
}