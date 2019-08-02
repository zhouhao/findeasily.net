package net.findeasily.website.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_ext")
@Data
public class UserExt implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(insertable = false, name = "user_id", nullable = false)
    private String userId;

    @Column(name = "description")
    private String description;


}