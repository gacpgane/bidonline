/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.service.usermanagement;

import com.bid.online.dao.usermanagement.UserDAO;
import com.bid.online.persistence.user.User;
import com.bid.online.persistence.user.UserCashAccount;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author prabuddha
 */
@Stateless
public class UserService implements UserServiceLocal {

    @EJB
    private UserDAO userDao;

    @Override
    public User addUser(User user) {
        UserCashAccount cashAccount=new UserCashAccount();
        synchronized(UserService.class){
            long token=System.currentTimeMillis();
            cashAccount.setAccNo(String.valueOf(token));
        }
        cashAccount.setBalance(BigDecimal.ZERO);
        cashAccount.setBlockAmount(BigDecimal.ZERO);
        cashAccount.setCreateTimestamp(new Date());
        cashAccount.setUser(user);
        user.setCreateTimestamp(new Date());
        user.setAccount(cashAccount);
        return userDao.persist(user);
    }

    @Override
    public User deleteUser(User u) {
        return userDao.remove(u);
    }

    @Override
    public User updateUser(User u) {
        return userDao.update(u);
    }

    @Override
    public User findUser(Long id) {
        return userDao.find(id);
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUserByEmail(String email) {
       return userDao.findUserByEmail(email);
    }
}
