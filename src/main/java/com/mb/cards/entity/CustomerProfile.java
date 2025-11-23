package com.mb.cards.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name="customer_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fi_code", nullable=false)
    private String fiCode;

    @Column(name="customer_id", nullable=false, unique=true)
    private String custID;

    @Column(name="name", nullable=false)
    private String custName;

    @Column(name="nric", nullable=false, unique=true)
    private String custNRIC;

    @Column(name="email")
    private String custEml;

    @Column(name="contact_no")
    private String custContactNum;

    @Column(name="crt_tms", updatable=false)
    private String creationTimestamp;

    @Column(name="upd_tms")
    private String updateTimestamp;

    @PrePersist
    public void onCreate(){
        creationTimestamp = getCurrentTimestamp();
        updateTimestamp = this.creationTimestamp;
    }

    @PreUpdate
    public void onUpdate(){
        updateTimestamp = getCurrentTimestamp();
    }

    private String getCurrentTimestamp(){
        return DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
    }

    //One-To-Many
    @OneToMany(mappedBy = "customerProfile", cascade = CascadeType.ALL, orphanRemoval = true) //mappedBy must match the exact name in Card.java
    @JsonManagedReference
    private List<Card> card;
}
