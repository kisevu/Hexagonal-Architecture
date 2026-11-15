package com.chaperonesolutions.ameda.customer.infrastructure.client.jsonplaceholder;

import com.chaperonesolutions.ameda.shared.error.exception.DownstreamRejectedException;

import java.io.Serial;

public class JsonPlaceholderNotFoundException extends DownstreamRejectedException {
    @Serial
    private static final long serialVersionUID = 1L;

    JsonPlaceholderNotFoundException() {
        super(JsonPlaceHolderApi.GROUP, 404);
    }
}
