package net.findeasily.website.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "contact_log")
@Entity
@Data
public class ContactLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "email", nullable = false)
    private String email = "";

    @Column(name = "message")
    private String message;

    @Column(name = "replied_content")
    private String repliedContent;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "replied_time")
    private Timestamp repliedTime;
}