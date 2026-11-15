package com.chaperonesolutions.ameda.account.infrastructure.customer;


import com.chaperonesolutions.ameda.account.domain.port.CustomerLookupPort;
import com.chaperonesolutions.ameda.customer.application.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Driven adapter between modules: the account module asks the customer module through its
 * public application API ({@link CustomerService}), never through its repository or entity.
 * If customers ever move to their own service, only this class changes (to an HTTP client).
 */
@Component
@RequiredArgsConstructor
class CustomerModuleAdapter implements CustomerLookupPort {

    private final CustomerService customerService;

    @Override
    public void requireExists(UUID customerId) {
        // Throws the customer module's not-found exception (404) when the customer is unknown
        customerService.get(customerId);
    }
}
