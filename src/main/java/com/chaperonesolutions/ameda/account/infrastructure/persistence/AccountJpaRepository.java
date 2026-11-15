package com.chaperonesolutions.ameda.account.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {

    List<AccountEntity> findByCustomerIdOrderByCreatedAtAsc(UUID customerId);

}
