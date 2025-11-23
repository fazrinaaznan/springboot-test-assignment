package com.mb.cards.service;

import com.mb.cards.entity.Card;
import com.mb.cards.entity.CustomerProfile;
import com.mb.cards.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    @Transactional(readOnly = true)
    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Card> getAllCards(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Card> getCardsByCustomerProfile(CustomerProfile customer, Pageable pageable) {
        return cardRepository.findByCustomerProfile(customer, pageable);
    }

    @Transactional
    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    @Transactional
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
