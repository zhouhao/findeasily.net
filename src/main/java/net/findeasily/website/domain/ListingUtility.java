package net.findeasily.website.domain;

import java.io.Serializable;

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
public class ListingUtility implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "listing_id", type = IdType.INPUT)
    private String listingId;
    private Boolean ac;
    private Boolean electricity;
    private Boolean water;
    private Boolean hotWater;
    private Boolean heater;
    private Boolean internet;
    private Boolean parking;


    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public Boolean getAc() {
        return ac;
    }

    public void setAc(Boolean ac) {
        this.ac = ac;
    }

    public Boolean getElectricity() {
        return electricity;
    }

    public void setElectricity(Boolean electricity) {
        this.electricity = electricity;
    }

    public Boolean getWater() {
        return water;
    }

    public void setWater(Boolean water) {
        this.water = water;
    }

    public Boolean getHotWater() {
        return hotWater;
    }

    public void setHotWater(Boolean hotWater) {
        this.hotWater = hotWater;
    }

    public Boolean getHeater() {
        return heater;
    }

    public void setHeater(Boolean heater) {
        this.heater = heater;
    }

    public Boolean getInternet() {
        return internet;
    }

    public void setInternet(Boolean internet) {
        this.internet = internet;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    @Override
    public String toString() {
        return "ListingUtility{" +
                ", listingId=" + listingId +
                ", ac=" + ac +
                ", electricity=" + electricity +
                ", water=" + water +
                ", hotWater=" + hotWater +
                ", heater=" + heater +
                ", internet=" + internet +
                ", parking=" + parking +
                "}";
    }
}
