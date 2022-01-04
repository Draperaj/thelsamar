import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import './Payment.css';

export default function Payment() {
    const {state} = useLocation();
    const [paymentData, setPaymentData] = useState({
        "customer": {
            "fullName": ""
        },
        "subtotal": 0.00,
        "tax": 0.00,
        "total": 0.00
    });

      useEffect(() => {
        async function getData() {
          const response = await fetch(`http://localhost:1901/api/payment`,{
            method: 'POST',
            body: JSON.stringify({customerId: state.customerId, orders: state.orders}),
            headers: {
                'Content-Type': 'application/json'
            }
          });
          let actualData = await response.json();
          setPaymentData(actualData);
        }
        getData();
      }, [state.customerId, state.orders]);

    return (<div className="receipt">
        <div className="p-5 mb-4 rounded-3">
        <div className="container-fluid py-5">
            <h1 className="display-5 fw-bold">🛒 Your Order,</h1>
            <p className="fs-2">{paymentData.customer.fullName}</p>
            <div className="text-justify">
                <p className="fs-5 my-0">Sub-total: ${parseFloat(paymentData.subtotal).toFixed(2)}</p>
                <p className="fs-5 my-0">Taxes: ${parseFloat(paymentData.tax).toFixed(2)}</p>
                <p className="fs-5 my-0">Total: ${parseFloat(paymentData.total).toFixed(2)}</p>
            </div>
        </div>
        </div>
    </div>);
}