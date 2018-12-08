package net.findeasily.website.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 *
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-08-08
 */
public class Listing implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    private String propertyId;
    private String ownerId;
    private Integer rentPrice;
    private Integer securityDeposit;
    private Date availableDate;
    private Integer bedroomCount;
    private Integer bathroomCount;
    private Integer duration;
    private String contactType;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private Boolean showEmail;
    private Boolean showPhone;
    private String description;
    private String type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Integer getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(Integer securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public Date getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    public Integer getBedroomCount() {
        return bedroomCount;
    }

    public void setBedroomCount(Integer bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public Integer getBathroomCount() {
        return bathroomCount;
    }

    public void setBathroomCount(Integer bathroomCount) {
        this.bathroomCount = bathroomCount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Boolean getShowEmail() {
        return showEmail;
    }

    public void setShowEmail(Boolean showEmail) {
        this.showEmail = showEmail;
    }

    public Boolean getShowPhone() {
        return showPhone;
    }

    public void setShowPhone(Boolean showPhone) {
        this.showPhone = showPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Listing{" +
                ", id=" + id +
                ", propertyId=" + propertyId +
                ", ownerId=" + ownerId +
                ", rentPrice=" + rentPrice +
                ", securityDeposit=" + securityDeposit +
                ", availableDate=" + availableDate +
                ", bedroomCount=" + bedroomCount +
                ", bathroomCount=" + bathroomCount +
                ", duration=" + duration +
                ", contactType=" + contactType +
                ", contactName=" + contactName +
                ", contactEmail=" + contactEmail +
                ", contactPhone=" + contactPhone +
                ", showEmail=" + showEmail +
                ", showPhone=" + showPhone +
                ", description=" + description +
                ", type=" + type +
                "}";
    }
}
