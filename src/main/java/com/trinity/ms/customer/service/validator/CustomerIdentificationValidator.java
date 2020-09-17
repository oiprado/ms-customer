package com.trinity.ms.customer.service.validator;

import com.trinity.commons.Validator;
import com.trinity.commons.model.Customer;
import com.trinity.ms.customer.repository.CustomerRepository;

public class CustomerIdentificationValidator implements Validator<Customer> {

    private CustomerRepository customerRepository;

    public CustomerIdentificationValidator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean validate(Customer customer) {
        Customer result = customerRepository.findCustomerByIdentification(customer.getIdentification());
        return result == null;
    }

    @Override
    public String getMessage() {
        return "Identification already exist.";
    }
}
