package caojx.learn.mybatisgeneratorplus.common.utils;

import org.springframework.context.ApplicationContext;

/**
 * Bean工具类
 *
 * @author caojx created on 2021/4/2 1:59 下午
 */
public class BeanUtil {

    /**
     * 将管理上下文的applicationContext设置成静态变量，供全局调用
     */
    public static ApplicationContext applicationContext;

    /**
     * 定义一个获取已经实例化bean的方法
     *
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> c) {
        return applicationContext.getBean(c);
    }
}