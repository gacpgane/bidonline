/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.dao.bidmanagement;

import com.bid.online.dao.BaseDAO;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bid.online.persistence.bid.Item;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author prabuddha
 */
@Stateless
@LocalBean
public class ItemDAO extends BaseDAO<Item> {

    @PersistenceContext(unitName = "bidonlinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ItemDAO(){
        super(Item.class);
    }
    
    public List<Item> getItemByCategory(Long code) {
        Query query = em.createQuery("select i from Item i where i.category.id = :code");
        query.setParameter("code", code);
        List<Item> items = query.getResultList();
        return items;
    }
}
