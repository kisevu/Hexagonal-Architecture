package com.chaperonesolutions.ameda.customer.infrastructure.client.jsonplaceholder;


import java.io.IOException;

import com.chaperonesolutions.ameda.shared.error.exception.DownstreamRejectedException;
import com.chaperonesolutions.ameda.shared.error.exception.DownstreamUnavailableException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class JsonPlaceholderErrorHandler implements RestClient.ResponseSpec.ErrorHandler {

    @Override
    public void handle(HttpRequest request, ClientHttpResponse response) throws IOException {
        HttpStatusCode status = response.getStatusCode();
        if (status.value() == 404) {
            throw new JsonPlaceholderNotFoundException();
        }
        if (status.value() == 429 || status.is5xxServerError()) {
            throw new DownstreamUnavailableException(JsonPlaceHolderApi.GROUP, status.value(), null);
        }
        throw new DownstreamRejectedException(JsonPlaceHolderApi.GROUP, status.value());
    }


}
