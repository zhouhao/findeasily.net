package net.findeasily.website.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "listing_photo")
@Entity
public class ListingPhoto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "listing_id", nullable = false)
    private String listingId;

    @Column(name = "upload_date", nullable = false)
    private Timestamp uploadDate;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "description")
    private String description;


}