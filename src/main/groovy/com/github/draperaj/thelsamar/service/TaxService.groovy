package com.github.draperaj.thelsamar.service

import com.github.draperaj.thelsamar.model.CustomerAddress
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.springframework.stereotype.Service

@Service
class TaxService {

  BigDecimal calculateTaxRateForAddress(CustomerAddress address) {
    String api = "https://api.taxjar.com/v2/taxes"

    String body = JsonOutput.toJson([
      from_country: "CA",
      from_zip: "R3C0V4",
      from_state: "WPG",
      to_country: address.country,
      to_zip: address.zip,
      to_state: address.state,
      amount: 0,
      shipping: 0,
      line_items: [
        [
          quantity: 1,
          unit_price: 1
        ]
      ]
    ])

    String postResult = new String()
    ((HttpURLConnection) new URL(api).openConnection()).with({
      requestMethod = 'POST'
      doOutput = true
      setRequestProperty("Content-Type", "application/json")
      setRequestProperty("Authorization", "Token token=\"${System.getenv("taxjar_token")}\"")
      setRequestProperty("x-api-version", "2020-08-07")
      outputStream.withPrintWriter({printWriter ->
        printWriter.write(body)
      })
      postResult = inputStream.text
    })

    Map response = new JsonSlurper().parseText(postResult) as Map

    return response?.tax?.getAt("breakdown")?.getAt("combined_tax_rate") as BigDecimal
  }

}
