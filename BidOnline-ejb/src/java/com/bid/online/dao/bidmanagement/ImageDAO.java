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
import com.bid.online.persistence.bid.Image;

/**
 *
 * @author prabuddha
 */
@Stateless
@LocalBean
public class ImageDAO extends BaseDAO<Image> {

    @PersistenceContext(unitName = "bidonlinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ImageDAO(){
        super(Image.class);
    }
}
