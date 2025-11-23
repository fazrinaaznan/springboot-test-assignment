package com.mb.cards.controller;

import com.mb.cards.entity.Card;
import com.mb.cards.entity.CustomerProfile;
import com.mb.cards.service.CardService;
import com.mb.cards.service.CustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CustomerProfileService customerProfileService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Card> createCard(@PathVariable String customerId, @RequestBody Card card) throws Exception {
        Optional<CustomerProfile> customerProfileOpt = customerProfileService.getCustomerProfileByCustID(customerId);
        if (customerProfileOpt.isEmpty()) throw new Exception("Customer id " + customerId + " does not exist!");

        card.setCustomerProfile(customerProfileOpt.get());
        Card saved = cardService.createCard(card);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCardsByCustomer(@PathVariable String customerId,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        Optional<CustomerProfile> customerProfileOpt = customerProfileService.getCustomerProfileByCustID(customerId);
        // If customer not found, just return empty page
        if (customerProfileOpt.isEmpty()) {
            Page<Card> emptyPage = Page.empty(PageRequest.of(page, size));
            return ResponseEntity.ok(emptyPage);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Card> card = cardService.getCardsByCustomerProfile(customerProfileOpt.get(), pageable);
        return ResponseEntity.ok(card);
    }

    @GetMapping
    public ResponseEntity<?> getCards(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(cardService.getAllCards(pageable));
    }

    @GetMapping("/{cardId}/credit-score")
    public ResponseEntity<?> getCreditScore(@PathVariable Long cardId) {
        Optional<Card> cardOpt = cardService.getCardById(cardId);
        if (cardOpt.isEmpty()) return ResponseEntity.notFound().build();

        // Call mock external API
        String url = "https://jsonplaceholder.typicode.com/users/1"; // mock API
        Object response = restTemplate.getForObject(url, Object.class);

        return ResponseEntity.ok(
                new CreditScoreResponse(cardId, 750, "ExternalAPI", response)
        );
    }

    // Inner DTO class for credit score response
    record CreditScoreResponse(Long cardId, int creditScore, String source, Object externalData) {}
}
