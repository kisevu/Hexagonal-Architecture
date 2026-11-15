package com.chaperonesolutions.ameda.customer.infrastructure.client.jsonplaceholder.persistence;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
class CustomerEntity {

    @Id
    private UUID id;

    @Version
    private Long version;

    @Column(name = "external_id", nullable = false , length = 100)
    private Integer externalId;

    @Column(name = "full_name", nullable = false , length = 100)
    private String fullName;

    @Column(name = "email", nullable = false , length = 155)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "city")
    private String city;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}

