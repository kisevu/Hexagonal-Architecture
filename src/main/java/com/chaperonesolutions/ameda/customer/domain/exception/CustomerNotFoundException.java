package com.chaperonesolutions.ameda.customer.domain.exception;

import com.chaperonesolutions.ameda.shared.error.ErrorCode;
import com.chaperonesolutions.ameda.shared.error.exception.NotFoundException;

import java.io.Serial;
import java.util.UUID;

public class CustomerNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(UUID id) {
        super(ErrorCode.CUSTOMER_NOT_FOUND, "Customer " + id + " not found", String.valueOf(id));
    }

}
