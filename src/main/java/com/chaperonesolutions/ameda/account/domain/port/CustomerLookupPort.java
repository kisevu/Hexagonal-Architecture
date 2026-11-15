package com.chaperonesolutions.ameda.account.domain.port;

import java.util.UUID;

public interface CustomerLookupPort {

    void requireExists(UUID customerId);

}
