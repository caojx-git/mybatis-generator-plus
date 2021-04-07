package caojx.learn.mybatisgeneratorplus.generator.mybatis;

/**
 * 通用Service
 *
 * @author caojx created on 2021/3/12 5:23 下午
 */
public interface IService<T> {

    /**
     * 根据主键查询数据
     *
     * @param id 主键id
     * @return
     */
    T selectByPrimaryKey(Long id);

    /**
     * 插入数据库记录
     *
     * @param entity 数据
     * @return
     */
    int insert(T entity);

    /**
     * 插入数据库记录
     *
     * @param entity 数据
     * @return
     */
    int insertSelective(T entity);

    /**
     * 根据主键更新数据
     *
     * @param entity 数据
     * @return
     */
    int updateByPrimaryKeySelective(T entity);

    /**
     * 根据主键更新数据
     *
     * @param entity 数据
     * @return
     */
    int updateByPrimaryKey(T entity);

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     * @return
     */
    int deleteByPrimaryKey(Long id);
}
