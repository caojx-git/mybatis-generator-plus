package caojx.learn.mybatisgeneratorplus.common.utils;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

/**
 * 工具类
 *
 * @author caojx created on 2021/4/4 5:20 下午
 */
public class CommonUtil {

    /**
     * 连接父子包名
     *
     * @param parent     父包名
     * @param subPackage 子包名
     * @return 连接后的包名
     */
    public static String joinPackage(String parent, String subPackage) {
        return StringUtils.isBlank(parent) ? subPackage : (parent + "." + subPackage);
    }

    /**
     * 获取简单类名
     *
     * @param fullClassName 类全路径
     * @return 简单类名
     */
    public static String getSimpleClassName(String fullClassName) {
        return StringUtils.substring(fullClassName, StringUtils.lastIndexOf(fullClassName, ".") + 1);
    }

    /**
     * 转化文件名
     *
     * @param format 格式化
     * @param name   名称
     * @return
     */
    public static String convertFileName(String format, String name) {
        return String.format(format, name);
    }

    /**
     * 下划线命名转首字母驼峰
     *
     * @param name 名称
     * @return
     */
    public static String underlineToCamel(String name) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name);
    }

    /**
     * 驼峰转下连字符
     *
     * @param name 名称
     * @return
     */
    public static String camelToHyphen(String name) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, name);
    }
}