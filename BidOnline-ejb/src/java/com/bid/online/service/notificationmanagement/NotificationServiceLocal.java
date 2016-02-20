/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.service.notificationmanagement;

import com.bid.online.utils.Message;
import javax.ejb.Local;

/**
 *
 * @author prabuddha
 */
@Local
public interface NotificationServiceLocal {
     public void sendNotification(Message msg);
}
