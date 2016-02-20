/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.service.notificationmanagement;

import com.bid.online.notification.utils.SendMail;
import javax.ejb.Stateless;
import com.bid.online.utils.Message;
 
/**
 *
 * @author prabuddha
 */
@Stateless
public class NotificationService implements NotificationServiceLocal {

    
    @Override
    public void sendNotification(Message msg){
        SendMail sendMail=new SendMail();
        
        sendMail.send(msg);
    }
}
