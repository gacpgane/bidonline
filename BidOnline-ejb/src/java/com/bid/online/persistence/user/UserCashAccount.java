/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.persistence.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author prabuddha
 */
@Entity
@Table(name="USER_CASH_ACCOUNT")
public class UserCashAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    
    @Column(name="ACC_NUMBER")
    private String accNo;
    
    @Column(name = "BALANCE")
    private BigDecimal balance;
    
    @Column(name="BLOCK_AMOUNT")
    private BigDecimal blockAmount;
    
    @Column(name = "CREATE_TIMESTAMP")
    @Temporal(TemporalType.DATE)
    private Date createTimestamp;
    
    @Column(name="MODIFIED_TIMESTAMP")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedTimestamp;
    
    
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
        if (!(object instanceof UserCashAccount)) {
            return false;
        }
        UserCashAccount other = (UserCashAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bid.online.persistence.user.CashAccount[ id=" + id + " ]";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public void setLastModifiedTimestamp(Date lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public BigDecimal getBlockAmount() {
        return blockAmount;
    }

    public void setBlockAmount(BigDecimal blockAmount) {
        this.blockAmount = blockAmount;
    }
    
    
    
}
