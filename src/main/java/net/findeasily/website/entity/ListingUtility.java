package net.findeasily.website.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "listing_utility")
@Entity
@Data
public class ListingUtility implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(insertable = false, name = "listing_id", nullable = false)
    private String listingId;

    @Column(name = "AC", nullable = false)
    private Boolean AC = Boolean.FALSE;

    @Column(name = "electricity", nullable = false)
    private Boolean electricity = Boolean.FALSE;

    @Column(name = "water", nullable = false)
    private Boolean water = Boolean.FALSE;

    @Column(name = "hot_water", nullable = false)
    private Boolean hotWater = Boolean.FALSE;

    @Column(name = "heater")
    private Boolean heater = Boolean.FALSE;

    @Column(name = "internet")
    private Boolean internet = Boolean.FALSE;

    @Column(name = "parking", nullable = false)
    private Boolean parking = Boolean.FALSE;


}