package com.trinity.ms.customer.service.validator;

import com.trinity.commons.Validator;
import com.trinity.commons.model.Customer;
import com.trinity.ms.customer.repository.CustomerRepository;

public class CustomerPhoneValidator implements Validator<Customer> {
    private CustomerRepository customerRepository;

    public CustomerPhoneValidator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean validate(Customer customer) {
        Customer result = customerRepository.findCustomerByPhone(customer.getPhone());
        return result == null;
    }

    @Override
    public String getMessage() {
        return "Phone already exists.";
    }
}
