/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.service.usermanagement;

import javax.ejb.Local;
import com.bid.online.persistence.user.User;
import java.util.List;

/**
 *
 * @author prabuddha
 */
@Local
public interface UserServiceLocal {

    public User addUser(User u);

    public User deleteUser(User u);

    public User updateUser(User u);

    public User findUser(Long id);

    public List<User> getUsers();

    public User findUserByEmail(String email);

}
