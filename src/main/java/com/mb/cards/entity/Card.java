package com.mb.cards.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fi_code", nullable=false)
    private String fiCode;

    @Column(name="card_number", nullable=false, unique=true)
    private String cardNum;

    @Column(name="card_type", nullable = false)
    private String cardType;

    @Column(name="status", nullable=false)
    private String cardStat;

    @Column(name="exp_date", nullable=false)
    private String cardExpDate;

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
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id")
    @JsonBackReference
    private CustomerProfile customerProfile;
}
