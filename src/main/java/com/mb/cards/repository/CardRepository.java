package com.mb.cards.repository;

import com.mb.cards.entity.Card;
import com.mb.cards.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Page<Card> findAll(Pageable pageable);

    Page<Card> findByCustomerProfile(CustomerProfile customerProfile, Pageable pageable);
}