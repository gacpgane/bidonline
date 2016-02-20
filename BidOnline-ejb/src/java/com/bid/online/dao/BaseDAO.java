/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.dao;

import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author prabuddha
 */
public abstract class BaseDAO<T> {

    private Class<T> entityClass;

    protected abstract EntityManager getEntityManager();

    public BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T persist(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        return entity;
    }

    public T update(T entity) {
        getEntityManager().merge(entity);
        return entity;
    }

    public T remove(T entity) {
        getEntityManager().remove(entity);
        return entity;
    }

    public List<T> findAll() {
        Query q = getEntityManager().createQuery("SELECT e FROM " + entityClass.getName() + " e");
        List<T> list = (List<T>) q.getResultList();
        return list;
    }

    public T find(Long id) {
        T e = getEntityManager().find(entityClass, id);
        return e;
    }
}
