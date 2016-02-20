/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.dao.bidmanagement;

import com.bid.online.dao.BaseDAO;
import com.bid.online.persistence.bid.SubCategory;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author prabuddha
 */
@Stateless
@LocalBean
public class SubCategoryDAO extends BaseDAO<SubCategory> {

    @PersistenceContext(unitName = "bidonlinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public SubCategoryDAO(){
        super(SubCategory.class);
    }
}
