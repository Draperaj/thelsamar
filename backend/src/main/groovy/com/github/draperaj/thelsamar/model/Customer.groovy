package com.github.draperaj.thelsamar.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "customer")
class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column (name = "id")
  Integer id

  @Column (name = "user_name")
  String userName
  @Column (name = "first_name")
  String firstName
  @Column (name = "last_name")
  String lastName

}
