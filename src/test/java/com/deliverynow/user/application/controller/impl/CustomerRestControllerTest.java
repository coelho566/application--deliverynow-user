package com.deliverynow.user.application.controller.impl;

import com.deliverynow.user.application.presenter.CustomerPresenter;
import com.deliverynow.user.application.presenter.CustomerPresenterImpl;
import com.deliverynow.user.application.usecase.CreateSessionCustomerUseCase;
import com.deliverynow.user.application.usecase.GetCustomerByDocumentUseCase;
import com.deliverynow.user.application.usecase.InsertCustomerUseCase;
import com.deliverynow.user.mock.CustomerFactory;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;


@QuarkusTest
class CustomerRestControllerTest {

    @InjectMocks
    CustomerRestController target;
    @Mock
    InsertCustomerUseCase insertCustomerUseCase;
    @Mock
    GetCustomerByDocumentUseCase getCustomerByDocumentUseCase;
    @Mock
    CreateSessionCustomerUseCase createSessionCustomerUseCase;
    @Spy
    CustomerPresenter customerPresenter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustInsertCustomerTest() {
        //GIVEN
        var customerRequest = CustomerFactory.getCustomerRequest("08197588974");

        //WHEN
        target.insertCustomer(customerRequest);

        //THEN
        Mockito.verify(insertCustomerUseCase, Mockito.times(1)).insertUser(any());
    }

    @Test
    void mustGetCustomerTest() {

        //GIVEN
        var customerRequest = CustomerFactory.getCustomerRequest("08197588974");
        var customer = CustomerFactory.getCustomerEntity();

        //WHEN
        Mockito.when(getCustomerByDocumentUseCase.getUserByDocument(customerRequest.document())).thenReturn(customer);

        //THEN
        var response = target.getCustomer(customerRequest.document());
        Assertions.assertNull(response);
    }

    @Test
    void mustGetCustomerSessionTest() {

        //GIVEN
        var customerRequest = CustomerFactory.getCustomerRequest("08197588974");
        var sessionId = UUID.randomUUID().toString();
        //WHEN
        Mockito.when(createSessionCustomerUseCase.createSession(customerRequest.document())).thenReturn(sessionId);

        //THEN
        var response = target.getCustomerSession(customerRequest.document());
        Assertions.assertEquals(sessionId, response);
    }
}