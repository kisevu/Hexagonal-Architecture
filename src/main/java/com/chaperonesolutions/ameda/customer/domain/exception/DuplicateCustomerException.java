package com.chaperonesolutions.ameda.customer.domain.exception;

import com.chaperonesolutions.ameda.shared.error.ErrorCode;
import com.chaperonesolutions.ameda.shared.error.exception.ConflictException;

import java.io.Serial;

public class DuplicateCustomerException extends ConflictException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DuplicateCustomerException() {
        super(ErrorCode.DUPLICATE_CUSTOMER, "A customer with the same email or directory id already exists");
    }
}
