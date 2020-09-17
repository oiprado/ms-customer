package com.trinity.ms.customer.config;

import com.trinity.commons.Validator;
import com.trinity.ms.customer.repository.CustomerRepository;
import com.trinity.ms.customer.service.validator.CustomerEmailValidator;
import com.trinity.ms.customer.service.validator.CustomerIdentificationValidator;
import com.trinity.ms.customer.service.validator.CustomerPhoneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CustomerValidatorConfiguration {

    @Autowired
    private CustomerRepository customerRepository;

    @Bean
    public List<Validator> customerValidators() {
        List<Validator> customerValidators = new ArrayList<>();

        customerValidators.add(new CustomerIdentificationValidator(customerRepository));
        customerValidators.add(new CustomerEmailValidator(customerRepository));
        customerValidators.add(new CustomerPhoneValidator(customerRepository));

        return customerValidators;
    }
}
