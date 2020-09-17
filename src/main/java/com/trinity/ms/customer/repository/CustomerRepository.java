package com.trinity.ms.customer.repository;

import com.trinity.commons.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor {

    Customer findCustomerByIdentification(String identification);

    Customer findCustomerByEmail(String email);

    Customer findCustomerByPhone(String phone);

}
