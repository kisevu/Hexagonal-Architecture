package com.chaperonesolutions.ameda.account.domain.exception;


import com.chaperonesolutions.ameda.shared.error.ErrorCode;
import com.chaperonesolutions.ameda.shared.error.exception.BusinessRuleException;

import java.io.Serial;
import java.util.UUID;

/** 422: the account is closed; no money can move. */
public final class AccountNotActiveException extends BusinessRuleException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AccountNotActiveException(UUID accountId) {
        super(ErrorCode.ACCOUNT_NOT_ACTIVE,
                "Account " + accountId + " is not active",
                accountId);
    }
}
