/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.presentation.model.bidmanagement;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author prabuddha
 */
public class CategoryView implements Serializable{
    
    private String name;
    private List<CategoryView> subCategories;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryView> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<CategoryView> subCategories) {
        this.subCategories = subCategories;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
}
