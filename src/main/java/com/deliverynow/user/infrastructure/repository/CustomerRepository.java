package com.deliverynow.user.infrastructure.repository;

import com.deliverynow.user.infrastructure.repository.entity.CustomerEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<CustomerEntity> {

    public Optional<CustomerEntity> getUserByDocument(String document){
        return find("document", document).firstResultOptional();
    }

    public Optional<CustomerEntity> getUserBySessionId(String sessionId){

        return find("sessionId", sessionId).firstResultOptional();
    }
}
