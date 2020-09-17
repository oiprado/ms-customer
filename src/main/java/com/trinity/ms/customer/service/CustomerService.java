package com.trinity.ms.customer.service;

import com.trinity.commons.model.Customer;
import com.trinity.ms.customer.payload.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CustomerService {

    CustomerResponse create(Customer customer) throws RuntimeException;

    CustomerResponse edit(Customer customer);

    CustomerResponse remove(Integer id);

    Page<Customer> getCustomers(Pageable pageable, Map<String, String> searchParams);

}
