package com.congdinh.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.congdinh.entities.Category;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDAO {
    private SessionFactory sessionFactory;

    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Category", Category.class).getResultList();
    }

    @Override
    public List<Category> search(String keyword, int page, int size) {
        Session session = sessionFactory.getCurrentSession();
        Query<Category> query = session.createQuery("FROM Category WHERE name LIKE :keyword", Category.class);
        query.setParameter("keyword", "%" + keyword + "%");
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public Category getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Category.class, id);
    }

    @Override
    public void create(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(category);
    }

    @Override
    public void update(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(category);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.find(Category.class, id);

        if (category != null) {
            session.remove(category);
        }
    }

    @Override
    public void delete(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(category);
    }
}
