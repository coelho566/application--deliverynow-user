package com.deliverynow.user.application.usecase.impl;

import com.deliverynow.user.application.exception.CustomerException;
import com.deliverynow.user.application.usecase.GetCustomerByDocumentUseCase;
import com.deliverynow.user.application.usecase.GetCustomerBySessionUseCase;
import com.deliverynow.user.domain.entity.Customer;
import com.deliverynow.user.domain.gateway.CustomerGateway;

public class GetCustomerBySessionUseCaseImpl implements GetCustomerBySessionUseCase {

    CustomerGateway customerGateway;

    public GetCustomerBySessionUseCaseImpl(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public Customer getUserBySession(String session) {
        var customerByDocument = customerGateway.getCustomerById(session);
        return customerByDocument.orElseThrow(() -> new CustomerException("User not found with the session provided."));
    }
}
