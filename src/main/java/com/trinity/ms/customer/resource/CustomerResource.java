package com.trinity.ms.customer.resource;

import com.trinity.commons.model.Customer;
import com.trinity.ms.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST, value = "/customer")
    @ResponseBody
    public ResponseEntity create(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.create(customer));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/customer")
    @ResponseBody
    public ResponseEntity edit(@RequestBody Customer customer){
        
        return ResponseEntity.ok(customerService.edit(customer));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/customer/{id}")
    @ResponseBody
    public ResponseEntity remove(@PathVariable Integer id){
        return ResponseEntity.ok(customerService.remove(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public Page<Customer> getCustomersByIdentification(Pageable pageable, @RequestParam Map<String,String> searchParams) {
        return customerService.getCustomers(pageable, searchParams);
    }


}
