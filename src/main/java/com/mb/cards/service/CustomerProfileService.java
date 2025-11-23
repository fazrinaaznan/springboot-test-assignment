package com.mb.cards.service;

import com.mb.cards.entity.CustomerProfile;
import com.mb.cards.repository.CustomerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerProfileService {
    @Autowired
    private CustomerProfileRepository customerProfileRepository;

    @Transactional
    public CustomerProfile createCustomer(CustomerProfile customerProfile) {
        return customerProfileRepository.save(customerProfile);
    }

    @Transactional(readOnly = true)
    public Optional<CustomerProfile> getCustomerProfileById(Long id) {
        return customerProfileRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<CustomerProfile> getCustomerProfileByCustID(String custID) {
        return customerProfileRepository.findByCustID(custID);
    }

    public Page<CustomerProfile> getAllCustomersProfile() {
        return getAllCustomersProfile(0, 100); //default
    }

    @Transactional(readOnly = true)
    public Page<CustomerProfile> getAllCustomersProfile(int page, int size) {
        return customerProfileRepository.findAll(PageRequest.of(page, size));
    }

    @Transactional
    public CustomerProfile updateCustomerProfile(CustomerProfile customerProfile) {
        return customerProfileRepository.save(customerProfile);
    }

    @Transactional
    public void deleteCustomerProfile(Long id) {
        customerProfileRepository.deleteById(id);
    }
}
