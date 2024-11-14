package com.deliverynow.user.infrastructure.repository.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    private String postalCode;
    private String street;
    private String state;
    private String city;
    private String numberAddress;
}
