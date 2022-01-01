package com.github.draperaj.thelsamar.controller

import com.github.draperaj.thelsamar.model.Customer
import com.github.draperaj.thelsamar.model.CustomerAddress
import com.github.draperaj.thelsamar.model.Inventory
import com.github.draperaj.thelsamar.model.Order
import com.github.draperaj.thelsamar.model.PaymentCalculationRequest
import com.github.draperaj.thelsamar.service.AddressService
import com.github.draperaj.thelsamar.service.CustomerService
import com.github.draperaj.thelsamar.service.InventoryService
import com.github.draperaj.thelsamar.service.TaxService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.math.RoundingMode

@RestController
@RequestMapping("/payment")
class PaymentController {

  @Autowired
  private CustomerService customerService
  @Autowired
  private AddressService addressService
  @Autowired
  private InventoryService inventoryService
  @Autowired
  private TaxService taxService

  @PostMapping ResponseEntity calculatePayment(@RequestBody PaymentCalculationRequest request) {
    Customer customer = customerService.findCustomerById(request.customerId)

    Map responseObject = [:]

    if (!customer) {
      return ResponseEntity.status(400).body([
        message: "invalid customer id"
      ])
    }

    CustomerAddress address = addressService.findAddressByUserId(customer.id)

    if (!address) {
      return ResponseEntity.status(400).body([
        message: "customer had not set up an address"
      ])
    }

    def customerObject = [
      id: customer.id,
      username: customer.userName,
      fullName: customer.firstName + " " + customer.lastName,
      address: address
    ]

    responseObject.put("customer", customerObject)

    List<Map> cart = []

    request.orders.each { Order order ->
      Inventory item = inventoryService.getItemById(order.id)

      if (!item) {
        return ResponseEntity.status(400).body([
          message: "the order includes an item not in the inventory"
        ])
      }

      def addToCart = [
        id: item.id,
        name: item.name,
        quantity: order.quantity,
        price: item.price * order.quantity
      ]

      cart << addToCart
    }

    responseObject.put("cart", cart)
    BigDecimal subTotal = cart.price.sum() as BigDecimal
    responseObject.put("subtotal", subTotal.setScale(2, RoundingMode.HALF_UP))
    BigDecimal taxRate = taxService.calculateTaxRateForAddress(address)
    BigDecimal taxAmount = taxRate * subTotal
    responseObject.put("tax", (taxAmount as BigDecimal).setScale(2, RoundingMode.HALF_UP))
    responseObject.put("total", ((subTotal + taxAmount) as BigDecimal).setScale(2, RoundingMode.HALF_UP))

    return ResponseEntity.ok(responseObject)
  }
}
