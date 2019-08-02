package net.findeasily.website.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "listing")
public class Listing implements Serializable {
    private static final long serialVersionUID = 1L;

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


}