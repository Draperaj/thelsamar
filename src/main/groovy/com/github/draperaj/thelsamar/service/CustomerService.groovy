package com.github.draperaj.thelsamar.service

import com.github.draperaj.thelsamar.model.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService {
  @Autowired
  private CustomerRepository customerRepository

  Customer findCustomerById(Integer id) {
    def customer = customerRepository.findById(id)
    return ( !customer.empty ? customer.get() : null )
  }
}
