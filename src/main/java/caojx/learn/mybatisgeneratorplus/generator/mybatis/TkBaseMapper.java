package caojx.learn.mybatisgeneratorplus.generator.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通过mapper 注意，该接口不能被Mybatis扫描到，否则会出错
 *
 * @author caojx created on 2021/4/2 10:52 下午
 */
public interface TkBaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}