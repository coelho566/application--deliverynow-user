package com.deliverynow.user.application.usecase.impl;

import com.deliverynow.user.application.exception.CustomerException;
import com.deliverynow.user.application.presenter.CustomerPresenter;
import com.deliverynow.user.domain.entity.Customer;
import com.deliverynow.user.domain.gateway.AddressGateway;
import com.deliverynow.user.domain.gateway.CustomerGateway;
import com.deliverynow.user.mock.CustomerFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Optional;

import static org.mockito.Mockito.*;

@QuarkusTest
class InsertCustomerUseCaseImplTest {

    @InjectMocks
    public InsertCustomerUseCaseImpl target;
    @Mock
    AddressGateway addressGateway;
    @Mock
    CustomerGateway customerGateway;
    @Spy
    CustomerPresenter customerPresenter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustInsertOneUser() {
        //GIVEN
        var request = CustomerFactory.getCustomerRequest("08197588974");
        var address = CustomerFactory.getAddress();

        //WHEN
        when(customerGateway.getCustomerByDocument(anyString()))
                .thenReturn(Optional.empty());
        when(addressGateway.getAddress(anyString())).thenReturn(address);

        //THEN
        target.insertUser(request);
        verify(customerGateway, times(1)).saveCustomer(any());
    }

    @Test
    void shouldReturnAnErrorWhenInsertingUser() {
        //GIVEN
        var request = CustomerFactory.getCustomerRequest("08197588974");
        var customer = CustomerFactory.getCustomerEntity();

        //WHEN
        when(customerGateway.getCustomerByDocument(anyString()))
                .thenReturn(Optional.of(customer));

        //THEN
        Assertions.assertThrows(CustomerException.class, () -> target.insertUser(request));
        verify(customerGateway, times(0)).saveCustomer(any());
    }
}