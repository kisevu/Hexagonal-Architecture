package com.chaperonesolutions.ameda.customer.application;

import com.chaperonesolutions.ameda.customer.domain.Customer;

public record ImportResult(Customer customer, boolean created) {
}
