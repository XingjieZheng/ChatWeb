package framework;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xj
 * on 2016/8/8.
 */
public interface IDao<T> {

    void save(T entity);

    void update(T entity);

    void delete(Serializable id);

    T findById(Serializable id);

    List<T> findByHQL(String hql, Object... params);

    List<T> findByHQL(String hql);
}
