package com.chaperonesolutions.ameda.customer.domain.port;

import com.chaperonesolutions.ameda.customer.domain.Customer;
import com.chaperonesolutions.ameda.shared.pagging.PageResult;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Customer save(Customer customer);

    Optional<Customer> findById(UUID id);

    Optional<Customer> findByExternalId(int externalId);

    boolean existsByEmail(String email);

    PageResult<Customer> findAll(int page, int size);

}
