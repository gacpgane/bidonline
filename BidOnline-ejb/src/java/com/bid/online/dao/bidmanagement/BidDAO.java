/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.dao.bidmanagement;

import com.bid.online.dao.BaseDAO;
import com.bid.online.persistence.bid.Bid;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author prabuddha
 */
@Stateless
@LocalBean
public class BidDAO extends BaseDAO<Bid> {

    @PersistenceContext(unitName = "bidonlinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public BidDAO(){
        super(Bid.class);
    }
    
    public Bid getHighestBid(Long itemId){
         
        Query query=em.createQuery("select b from Bid b where b.item.id = :itemId order by b.value desc");
        query.setParameter("itemId", itemId);
        List<Bid> bidList=query.getResultList();
        if(bidList==null||bidList.isEmpty()){
            return null;
        }else{
            return bidList.get(0);
        }
         
    }
     
}
