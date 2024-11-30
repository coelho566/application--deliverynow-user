package com.deliverynow.user.application.usecase;

import com.deliverynow.user.domain.entity.Customer;

public interface GetCustomerBySessionUseCase {

    Customer getUserBySession(String document);
}
