package com.deliverynow.user.adapters.gateway;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AddressRestGatewayTest {

    @Inject
    AddressRestGateway target;

    @Test
    void mustGetAddressTest() {

        var addressDto = target.getAddress("81265-228");

        Assertions.assertNotNull(addressDto);
        Assertions.assertEquals("PR", addressDto.getState());
    }
}