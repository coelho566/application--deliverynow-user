package com.deliverynow.user.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sessionId;
    private String name;
    private String phone;
    private String email;
    private String document;
    private String documentType;
    private String password;
    private Boolean allowNotification;
    @Embedded
    private AddressEntity address;

}