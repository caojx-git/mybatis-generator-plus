package caojx.learn.mybatisgeneratorplus.generator.mybatis;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础service实现类
 *
 * @author caojx created on 2021/3/12 5:35 下午
 */
public class ServiceImpl<M extends TkBaseMapper<T>, T> implements IService<T> {

    @Autowired
    protected M mapper;

    @Override
    public T selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T entity) {
        return mapper.insert(entity);
    }

    @Override
    public int updateByPrimaryKeySelective(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int updateByPrimaryKey(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int insertSelective(T entity) {
        return mapper.insertSelective(entity);
    }
}
