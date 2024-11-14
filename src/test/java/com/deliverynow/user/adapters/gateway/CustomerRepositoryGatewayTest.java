package com.deliverynow.user.adapters.gateway;

import com.deliverynow.user.domain.entity.Customer;
import com.deliverynow.user.infrastructure.repository.CustomerRepository;
import com.deliverynow.user.infrastructure.repository.entity.CustomerEntity;
import com.deliverynow.user.mock.CustomerFactory;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import java.util.Optional;
import java.util.UUID;


@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerRepositoryGatewayTest {

    @Inject
    CustomerRepositoryGateway target;

    @Inject
    CustomerRepository customerRepository;

    public static String sessionId = UUID.randomUUID().toString();

    @Test
    @Order(1)
    void mustSaveCustomerTest() {
        //GIVEN
        var customer = CustomerFactory.getCustomerEntity();

        //WHEN
        target.saveCustomer(customer);
        var customerEntity = customerRepository.getUserByDocument(customer.getDocument());

        //THEN
        Assertions.assertTrue(customerEntity.isPresent());
        Assertions.assertEquals(customer.getDocument(), customerEntity.get().getDocument());
    }

    @Test
    @Order(2)
    void updateCustomer() {
        //GIVEN
        var customer = CustomerFactory.getCustomerEntity();

        //WHEN
        target.updateCustomer(customer.getDocument(), sessionId);
        var userBySessionId = customerRepository.getUserBySessionId(sessionId);

        //THEN
        Assertions.assertTrue(userBySessionId.isPresent());
    }

    @Test
    @Order(3)
    void getCustomerByDocument() {
        var customer = CustomerFactory.getCustomerEntity();

        var customerByDocument = target.getCustomerByDocument(customer.getDocument());

        Assertions.assertTrue(customerByDocument.isPresent());
    }

    @Test
    @Order(4)
    void getCustomerById() {

        Optional<Customer> customerById = target.getCustomerById(sessionId);
        Assertions.assertTrue(customerById.isPresent());
    }
}