package com.github.draperaj.thelsamar.service

import com.github.draperaj.thelsamar.model.CustomerAddress
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository extends JpaRepository<CustomerAddress, Long> {
  @Query(value = "SELECT * FROM address WHERE customer_id = ?",
  nativeQuery = true)
  CustomerAddress findByCustomerId(int customer_id)
}
