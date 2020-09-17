package com.trinity.ms.customer.service.impl;

import com.trinity.commons.Validator;
import com.trinity.commons.model.Customer;
import com.trinity.ms.customer.exception.CustomerValidatorException;
import com.trinity.ms.customer.payload.CustomerResponse;
import com.trinity.ms.customer.repository.CustomerRepository;
import com.trinity.ms.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private List<Validator> customerValidators;

    @Override
    public CustomerResponse create(Customer customer) {

        CustomerResponse response = new CustomerResponse();
        List<String> validatorMessage = null;
        try{
            validatorMessage = customerValidators
                .stream()
                .filter(data -> !data.validate(customer))
                    .map(customerValidator -> customerValidator.getMessage())
                    .collect(Collectors.toList());

            if(validatorMessage.size() > 0){
                throw new CustomerValidatorException();
            }

            customer.setCreationDate(new Date());
            customerRepository.save(customer);

            response.setContent(true);
            return response;
        }catch (Exception ex){
            response.setValidator(validatorMessage);
            return response;
        }
    }

    @Override
    public CustomerResponse edit(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        try{
            customer.setCreationDate(new Date());
            customerRepository.save(customer);
            response.setContent(true);
            return response;
        }catch (Exception ex){
            response.setContent(false);
            return response;
        }
    }

    @Override
    public CustomerResponse remove(Integer id) {
        CustomerResponse response = new CustomerResponse();
        try{
            customerRepository.deleteById(id);
            response.setContent(true);
            return response;
        }catch (Exception ex){
            response.setContent(false);
            return response;
        }
    }

    @Override
    public Page<Customer> getCustomers(Pageable pageable, Map<String, String> searchParams) {

        Specification specification = (Specification) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate[] args = searchParams
                .keySet()
                    .stream()
                        .map(key-> criteriaBuilder.equal(root.get(key), searchParams.get(key)))
                        .toArray(Predicate[]::new);
            return criteriaBuilder.and(args);
        };

        return customerRepository.findAll(specification, pageable);
    }
}
