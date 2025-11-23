package com.mb.cards.repository;

import com.mb.cards.entity.Card;
import com.mb.cards.entity.CustomerProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
    Page<CustomerProfile> findAll(Pageable pageable);

    boolean existsByCustNRIC(String custNRIC);

    Optional<CustomerProfile> findByCustID(String custID);
}