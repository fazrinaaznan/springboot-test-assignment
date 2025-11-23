package com.mb.cards.controller;

import com.mb.cards.entity.CustomerProfile;
import com.mb.cards.service.CustomerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService customerProfileService;

    @GetMapping
    public ResponseEntity<Page<CustomerProfile>> getCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<CustomerProfile> customers = customerProfileService.getAllCustomersProfile(page, size);
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<CustomerProfile> createCustomer(@RequestBody CustomerProfile customer) {
        CustomerProfile saved = customerProfileService.createCustomer(customer);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{custID}")
    public ResponseEntity<CustomerProfile> getCustomer(@PathVariable String custID) {
        Optional<CustomerProfile> customer = customerProfileService.getCustomerProfileByCustID(custID);
        return customer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
