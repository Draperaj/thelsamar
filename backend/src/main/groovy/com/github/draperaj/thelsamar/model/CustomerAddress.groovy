package com.github.draperaj.thelsamar.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "address")
class CustomerAddress {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column (name = "id")
  Integer id

  @Column (name = "customer_id")
  Integer customerId
  @Column (name = "street")
  String street
  @Column (name = "city")
  String city
  @Column (name = "state")
  String state
  @Column (name = "zip")
  String zip
  @Column (name = "country")
  String country
}
