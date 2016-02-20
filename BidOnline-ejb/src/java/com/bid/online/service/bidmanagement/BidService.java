/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.service.bidmanagement;

import com.bid.online.dao.bidmanagement.*;
import com.bid.online.persistence.bid.Bid;
 
import com.bid.online.persistence.bid.Category;
import com.bid.online.persistence.bid.Image;
import com.bid.online.persistence.bid.Item;
import com.bid.online.persistence.bid.SubCategory;
import com.bid.online.service.notificationmanagement.NotificationServiceLocal;
import com.bid.online.utils.Message;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author prabuddha
 */
@Stateless
public class BidService implements BidServiceLocal {

    @EJB
    private CategoryDAO categoryDao;
    
    @EJB
    private SubCategoryDAO subCategoryDao;
    
    @EJB 
    private ItemDAO itemDao;
    
    @EJB
    private ImageDAO imageDao;
    
    @EJB
    private BidDAO bidDao;
    
    @EJB
    private NotificationServiceLocal notificationService;
    
    @Override
    public List<Category> getCategoryList() {
         return categoryDao.findAll();
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Item createItem(Item item) {
        return itemDao.persist(item);
    }

    @Override
    public Image createImage(Image image) {
        return imageDao.persist(image);
    }

    @Override
    public Image getImageById(Long id) {
        return imageDao.find(id);
    }

    @Override
    public SubCategory getSubCategoryById(Long id) {
        return subCategoryDao.find(id);
    }

    @Override
    public List<Item> getItemList() {
       return itemDao.findAll();
    }

    @Override
    public List<Item> getItemByCategory(Long code) {
         return itemDao.getItemByCategory(code);
    }

    @Override
    public Bid createBid(Bid bid) {
          bid= bidDao.persist(bid);
          
          Message msg=new Message();
          msg.setSubject("Bid Online");
          msg.setBody("New Bid Value for the item is:"+bid.getValue().toString());
          msg.setTo(bid.getItem().getOwner().getEmail());
          notificationService.sendNotification(msg);
          return bid;
    }

    @Override
    public Item getItemById(Long id) {
         return itemDao.find(id);
    }

    @Override
    public Bid getHighestBid(Long itemId) {
        return bidDao.getHighestBid(itemId);
    }
}
