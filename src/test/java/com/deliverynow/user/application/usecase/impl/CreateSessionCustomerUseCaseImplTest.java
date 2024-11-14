package com.deliverynow.user.application.usecase.impl;

import com.deliverynow.user.adapters.controller.request.CustomerRequest;
import com.deliverynow.user.domain.gateway.CustomerGateway;
import com.deliverynow.user.mock.CustomerFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@QuarkusTest
class CreateSessionCustomerUseCaseImplTest {

    @InjectMocks
    CreateSessionCustomerUseCaseImpl target;

    @Mock
    CustomerGateway customerGateway;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustCreateSessionTest() {

        var session = target.createSession(null);

        Assertions.assertNotNull(session);
        Mockito.verify(customerGateway, Mockito.times(0)).updateCustomer(anyString(), anyString());
    }


    @Test
    void mustUpdateSessionTest() {

        var customerRequest = CustomerFactory.getCustomerRequest("08197588974");

        var session = target.createSession(customerRequest.document());

        Assertions.assertNotNull(session);
        Mockito.verify(customerGateway, Mockito.times(1)).updateCustomer(anyString(), anyString());
    }
}