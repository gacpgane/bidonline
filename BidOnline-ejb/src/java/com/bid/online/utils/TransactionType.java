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
public enum TransactionType {

    TOPUP("TOPUP"), SELL("SELL"), BUY("BUY"), WITHDRAW("WITHDR"),COMMISION("COMMI");
    private final String code;

    TransactionType(String code) {
        this.code = code;
    }
    
    @Override
    public String toString(){
        return code;
    }
}
