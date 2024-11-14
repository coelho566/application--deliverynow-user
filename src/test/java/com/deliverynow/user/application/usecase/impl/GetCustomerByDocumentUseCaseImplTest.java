package com.deliverynow.user.application.usecase.impl;

import com.deliverynow.user.application.exception.CustomerException;
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

import java.util.Optional;

@QuarkusTest
class GetCustomerByDocumentUseCaseImplTest {

    @InjectMocks
    GetCustomerByDocumentUseCaseImpl target;
    @Mock
    CustomerGateway customerGateway;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustReturnUserPerDocument() {
        //GIVEN
        var customer = CustomerFactory.getCustomerEntity();

        //WHEN
        Mockito.when(customerGateway.getCustomerByDocument(Mockito.anyString())).thenReturn(Optional.of(customer));

        //THEN
        var userByDocument = target.getUserByDocument("1236");

        Assertions.assertEquals("Mario", userByDocument.getName());
    }

    @Test
    void shouldReturnErrorWhenSearchingUserByDocument() {

        //WHEN
        Mockito.when(customerGateway.getCustomerByDocument(Mockito.anyString())).thenReturn(Optional.empty());

        //THEN
        var customerException = Assertions.assertThrows(CustomerException.class, () -> target.getUserByDocument("112"));

        Assertions.assertEquals("User not found with the document provided.", customerException.getMessage());
    }
}