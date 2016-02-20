/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bid.online.persistence.bid;

import com.bid.online.persistence.user.User;
import com.bid.online.utils.ItemStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author prabuddha
 */
@Entity
@Table(name="BID_ITEM")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="DESCRIPTION")
    private String description;
    
    @ManyToOne
    private User owner;
    
    @OneToOne
    @JoinColumn(name = "SUB_CATEGORY_ID")
    private SubCategory category;
    
    @Column(name="INITIAL_BID_VALUE")
    private BigDecimal initialBidValue;
    
    @Column(name="ACTUAL_VALUE")
    private BigDecimal actualValue;
    
    @Column(name="CREATE_TIMESTAMP")
    @Temporal(TemporalType.DATE)
    private Date createTimestamp;
    
    @Column(name="MODIFIED_TIMESTAMP")
    @Temporal(TemporalType.DATE)
    private Date modifiedTimestamp;
    
    @Column(name="BID_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar bidStartDate;
    
    @Column(name="BID_END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar bidEndDate;
    
    @OneToOne
    @JoinColumn(name = "IMAGE_ID")
    private Image image;
    
    @Column(name="STATUS")
    private ItemStatus status;

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
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bid.online.persistence.bid.Item[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public SubCategory getCategory() {
        return category;
    }

    public void setCategory(SubCategory category) {
        this.category = category;
    }

    public BigDecimal getInitialBidValue() {
        return initialBidValue;
    }

    public void setInitialBidValue(BigDecimal initialBidValue) {
        this.initialBidValue = initialBidValue;
    }

    public BigDecimal getActualValue() {
        return actualValue;
    }

    public void setActualValue(BigDecimal actualValue) {
        this.actualValue = actualValue;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Calendar getBidStartDate() {
        return bidStartDate;
    }

    public void setBidStartDate(Calendar bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    public Calendar getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(Calendar bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public Date getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    public void setModifiedTimestamp(Date modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }
    
    
}
