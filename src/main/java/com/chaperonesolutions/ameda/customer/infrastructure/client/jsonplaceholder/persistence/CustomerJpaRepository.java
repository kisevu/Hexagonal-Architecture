package com.chaperonesolutions.ameda.customer.infrastructure.client.jsonplaceholder.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findByExternalId(Integer externalId);

    boolean existsByEmail(String email);

}
