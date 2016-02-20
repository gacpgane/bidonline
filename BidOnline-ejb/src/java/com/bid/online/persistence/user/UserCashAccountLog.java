/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.persistence.user;

import com.bid.online.utils.TransactionType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author prabuddha
 */
@Entity
@Table(name="USER_CASH_ACCOUNT_LOG")
public class UserCashAccountLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="AMOUNT")
    private BigDecimal amount;
    
    @Column(name="TYPE")
    private TransactionType type;
    
    @Column(name="CREATE_TIMESTAMP")
    @Temporal(TemporalType.DATE)
    private Date createTimeStamp;
    
    @ManyToOne 
    @JoinColumn(name = "USER_CASH_ACC_ID")
    private UserCashAccount userCashAccount;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCashAccountLog)) {
            return false;
        }
        UserCashAccountLog other = (UserCashAccountLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bid.online.persistence.user.UserCashAccountLog[ id=" + id + " ]";
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(Date createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public UserCashAccount getUserCashAccount() {
        return userCashAccount;
    }

    public void setUserCashAccount(UserCashAccount userCashAccount) {
        this.userCashAccount = userCashAccount;
    }
    
    
}
