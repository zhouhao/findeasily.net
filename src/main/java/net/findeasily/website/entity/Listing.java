package net.findeasily.website.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import net.findeasily.website.domain.enums.ListingStatus;

@Data
@Entity
@Table(name = "listing")
public class Listing {
    @Id
    @Column(name = "id", insertable = false, nullable = false)
    private String id;

    @Column(name = "property_id", nullable = false)
    private String propertyId;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(name = "rent_price")
    private Integer rentPrice;

    @Column(name = "security_deposit")
    private Integer securityDeposit;

    @Column(name = "available_date", nullable = false)
    private Timestamp availableDate;

    @Column(name = "bedroom_count")
    private Integer bedroomCount = 1;

    @Column(name = "bathroom_count")
    private Integer bathroomCount = 1;

    @Column(name = "duration")
    private Integer duration = 0;

    @Column(name = "contact_type")
    private String contactType;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "show_email", nullable = false)
    private Boolean showEmail = Boolean.FALSE;

    @Column(name = "show_phone", nullable = false)
    private Boolean showPhone = Boolean.FALSE;

    @Column(name = "description")
    private String description;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "address1", nullable = false)
    private String address1 = "";

    @Column(name = "address2", nullable = false)
    private String address2 = "";

    @Column(name = "city", nullable = false)
    private String city = "";

    @Column(name = "state")
    private String state = "";

    @Column(name = "country", nullable = false)
    private String country = "";

    @Column(name = "zip", nullable = false)
    private String zip = "";

    @Column(name = "latitude")
    private Float latitude = 0F;

    @Column(name = "longitude")
    private Float longitude = 0F;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ListingStatus status = ListingStatus.DRAFT;

}