package com.chaperonesolutions.ameda.customer.infrastructure.client.jsonplaceholder;


import com.chaperonesolutions.ameda.customer.domain.CustomerPost;
import com.chaperonesolutions.ameda.customer.domain.ExternalUser;
import com.chaperonesolutions.ameda.customer.domain.exception.ExternalUserNotFoundException;
import com.chaperonesolutions.ameda.customer.domain.port.CustomerDirectoryPort;
import com.chaperonesolutions.ameda.shared.error.exception.DownstreamUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

/**
 * Driven adapter for the customer directory (JSONPlaceholder).
 * <p>
 * Order of the annotations at runtime (outermost first): cache, retry, circuit breaker.
 * A cache hit returns at once and never reaches the directory, retry or breaker, so cached
 * users and posts are still served while the circuit is open. Exceptions are never cached:
 * a user missing today is found as soon as it exists in the directory. See ADR-0005.
 */
@Component
@RequiredArgsConstructor
class JsonPlaceholderAdapter implements CustomerDirectoryPort {

    private final JsonPlaceHolderApi api;
    private final JsonPlaceholderMapper mapper;

    /** Cached in {@code demo:v1:directory-users::<externalId>}; one directory call per id at a time. */
    @Override
    @Cacheable(cacheNames = DirectoryCaches.USERS, sync = true)
    @Retry(name = JsonPlaceHolderApi.GROUP)
    @CircuitBreaker(name = JsonPlaceHolderApi.GROUP)
    public ExternalUser findUser(int externalId) {
        try {
            return mapper.toDomain(api.getUser(externalId));
        } catch (JsonPlaceholderNotFoundException e) {
            throw new ExternalUserNotFoundException(externalId);
        } catch (ResourceAccessException e) {
            throw new DownstreamUnavailableException(JsonPlaceHolderApi.GROUP, null, e);
        }
    }

    /** Cached in {@code demo:v1:directory-posts::<externalId>}; an empty list is cached too. */
    @Override
    @Cacheable(cacheNames = DirectoryCaches.POSTS, sync = true)
    @Retry(name = JsonPlaceHolderApi.GROUP)
    @CircuitBreaker(name = JsonPlaceHolderApi.GROUP)
    public List<CustomerPost> findPosts(int externalId) {
        try {
            return mapper.toDomain(api.getPostsByUser(externalId));
        } catch (ResourceAccessException e) {
            throw new DownstreamUnavailableException(JsonPlaceHolderApi.GROUP, null, e);
        }
    }
}

