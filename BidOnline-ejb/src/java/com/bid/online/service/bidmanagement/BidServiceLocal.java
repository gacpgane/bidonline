/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.service.bidmanagement;

import com.bid.online.persistence.bid.*;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author prabuddha
 */
@Local
public interface BidServiceLocal {
    public List<Category> getCategoryList();
    
    public Item getItemById(Long id);
    
    public Item createItem(Item item);
    
    public Image createImage(Image image);
    
    public Image getImageById(Long id);
    
    public SubCategory getSubCategoryById(Long id);
    
    public List<Item> getItemList();
    
    public List<Item> getItemByCategory(Long code);
    
    public Bid createBid(Bid bid);
    
    public Bid getHighestBid(Long itemId);
}
