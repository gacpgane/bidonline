/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.persistence.bid;

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
@Table(name="TRANSACTION_LOG")
public class TransactionLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "BID_ID")
    private Bid bid;
    
    @Column(name="SELLER_COMMI")
    private BigDecimal sellerCommision;
    
    @Column(name = "BUYER_COMMI")
    private BigDecimal buyerCommision;
    
    @Column(name="CREATE_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTimestamp;
    
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
        if (!(object instanceof TransactionLog)) {
            return false;
        }
        TransactionLog other = (TransactionLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bid.online.persistence.bid.Transaction[ id=" + id + " ]";
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public BigDecimal getSellerCommision() {
        return sellerCommision;
    }

    public void setSellerCommision(BigDecimal sellerCommision) {
        this.sellerCommision = sellerCommision;
    }

    public BigDecimal getBuyerCommision() {
        return buyerCommision;
    }

    public void setBuyerCommision(BigDecimal buyerCommision) {
        this.buyerCommision = buyerCommision;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
    
    
}
