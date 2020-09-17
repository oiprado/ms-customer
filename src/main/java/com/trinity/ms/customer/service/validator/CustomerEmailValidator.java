package com.trinity.ms.customer.service.validator;

import com.trinity.commons.Validator;
import com.trinity.commons.model.Customer;
import com.trinity.ms.customer.repository.CustomerRepository;

public class CustomerEmailValidator implements Validator<Customer> {

    private CustomerRepository customerRepository;

    public CustomerEmailValidator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean validate(Customer customer) {
        Customer result = customerRepository.findCustomerByEmail(customer.getEmail());
        return result == null;
    }

    @Override
    public String getMessage() {
        return "Email already exists.";
    }
}
