package framework;

import util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by xj
 * on 2016/8/8.
 */
public class BaseDao<T> implements IDao<T> {

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class<T>) params[0];
    }

    private String getGenericClassName() {
        return entityClass == null ? null : entityClass.getName();
    }


    @Override
    public void save(T entity) {
        if (entity == null) {
            return;
        }

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getSession();
            if (session == null) {
                return;
            }
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public void update(T entity) {
        if (entity == null) {
            return;
        }

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getSession();
            if (session == null) {
                return;
            }
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public void delete(Serializable id) {
        if (id == null) {
            return;
        }

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getSession();
            if (session == null) {
                return;
            }
            transaction = session.beginTransaction();
            session.delete(findById(id));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.clear();
                session.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(Serializable id) {
        Session session = null;
        T result = null;

        try {
            session = HibernateUtils.getSession();
            if (session == null) {
                return null;
            }
            result = (T) session.get(getGenericClassName(), id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.clear();
                session.close();
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByHQL(String hql, Object... params) {
        if (hql == null) {
            return null;
        }
        Session session = null;
        List<T> result = null;
        try {
            session = HibernateUtils.getSession();
            if (session == null) {
                return null;
            }
            Query query = session.createQuery(hql);
            for (int i = 0; params != null && i < params.length; i++) {
                query.setParameter(i, params);
            }
            result = (List<T>) query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.clear();
                session.close();
            }
        }
        return result;
    }

    @Override
    public List<T> findByHQL(String hql) {
        if (hql == null) {
            return null;
        }
        Session session = null;
        List<T> result = null;
        try {
            session = HibernateUtils.getSession();
            if (session == null) {
                return null;
            }
            Query query = session.createQuery(hql);
            result = (List<T>) query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.clear();
                session.close();
            }
        }
        return result;
    }
}
