package com.deliverynow.user.adapters.gateway;

import com.deliverynow.user.application.presenter.CustomerPresenter;
import com.deliverynow.user.domain.entity.Customer;
import com.deliverynow.user.domain.gateway.CustomerGateway;
import com.deliverynow.user.infrastructure.repository.CustomerRepository;
import com.deliverynow.user.infrastructure.repository.entity.CustomerEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class CustomerRepositoryGateway implements CustomerGateway {

    CustomerRepository customerRepository;
    CustomerPresenter customerPresenter;

    public CustomerRepositoryGateway(CustomerRepository customerRepository, CustomerPresenter customerPresenter) {
        this.customerRepository = customerRepository;
        this.customerPresenter = customerPresenter;
    }

    @Override
    @Transactional
    public void saveCustomer(Customer client) {
        CustomerEntity entity = customerPresenter.userToUserEntity(client);
        customerRepository.persist(entity);
    }

    @Override
    @Transactional
    public void updateCustomer(String document, String sessionId) {
        customerRepository.update("sessionId", sessionId);
    }

    @Override
    @Transactional
    public Optional<Customer> getCustomerByDocument(String document) {
        var userByDocument = customerRepository.getUserByDocument(document);
        return userByDocument.map(user -> customerPresenter.toDomain(user));
    }

    @Override
    @Transactional
    public Optional<Customer> getCustomerById(String sessionId) {
        var customerEntity = customerRepository.getUserBySessionId(sessionId);
        return customerEntity
                .map(customer -> customerPresenter.toDomain(customer));
    }
}

