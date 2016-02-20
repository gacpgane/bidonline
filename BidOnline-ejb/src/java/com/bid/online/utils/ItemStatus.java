/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.utils;

/**
 *
 * @author prabuddha
 */
public enum ItemStatus {
    
    COMPLETED("COMPLETED"), EXPIRED("EXPIRED"), ACTIVE("ACTIVE");

    private String code;

    ItemStatus(String code) {
        this.code = code;
    }

    @Override
    public String toString(){
        return this.code;
    }
}
