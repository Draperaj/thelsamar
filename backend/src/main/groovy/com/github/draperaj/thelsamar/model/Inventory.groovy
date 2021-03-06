package com.github.draperaj.thelsamar.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table (name = "inventory")
class Inventory {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column (name = "id")
  Integer id

  @Column (name = "name")
  String name
  @Column (name = "price")
  BigDecimal price
}
