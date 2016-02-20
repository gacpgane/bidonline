/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.dao.usermanagement;

import com.bid.online.dao.BaseDAO;
import com.bid.online.persistence.user.User;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author prabuddha
 */
@Stateless
@LocalBean
public class UserDAO extends BaseDAO<User>{

    @PersistenceContext(unitName="bidonlinePU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UserDAO(){
        super(User.class);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public User findUserByEmail(String email) {
        Query q = getEntityManager().createQuery("SELECT u FROM User u where u.email = :email");
        q.setParameter("email", email);
        List<User> list = q.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
