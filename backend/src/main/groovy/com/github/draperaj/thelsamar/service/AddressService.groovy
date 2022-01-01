package com.github.draperaj.thelsamar.service

import com.github.draperaj.thelsamar.model.CustomerAddress
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddressService {
  @Autowired
  private AddressRepository addressRepository

  CustomerAddress findAddressByUserId(Integer customerId) {
    addressRepository.findByCustomerId(customerId)
  }
}
