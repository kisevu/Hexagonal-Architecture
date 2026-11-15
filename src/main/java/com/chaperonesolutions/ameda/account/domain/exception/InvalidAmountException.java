package com.chaperonesolutions.ameda.account.domain.exception;

import com.chaperonesolutions.ameda.shared.error.ErrorCode;
import com.chaperonesolutions.ameda.shared.error.exception.BusinessRuleException;

import java.io.Serial;
import java.math.BigDecimal;

/** 422: amount not positive, more than two decimals, or the balance would exceed the maximum. */
public final class InvalidAmountException extends BusinessRuleException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidAmountException(BigDecimal amount, String currency) {
        super(ErrorCode.INVALID_AMOUNT,
                "Invalid amount " + amount + " " + currency,
                amount, currency);
    }
}

