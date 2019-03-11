package com.joesamyn.MyLife.dao;

import com.joesamyn.MyLife.model.Category;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Category category) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(category);
            tx.commit();
        } catch (HibernateException e){
            if(tx == null){ tx.rollback();}
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Category category) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(category);
            tx.commit();
        } catch (HibernateException e){
            if(tx == null){ tx.rollback(); }
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();
        List<Category> categories;
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Category> cq = builder.createQuery(Category.class);
        cq.from(Category.class);
        categories = session.createQuery(cq).getResultList();
        session.close();
        return categories;
    }

    @Override
    public Category findById(Long id) {
        Session session = sessionFactory.openSession();
        Category category = session.get(Category.class, id);
        session.close();
        return category;
    }
}
