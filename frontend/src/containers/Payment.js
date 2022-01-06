import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import { Truck } from 'react-bootstrap-icons';
import './Payment.css';

export default function Payment() {
    const {state} = useLocation();
    const [paymentData, setPaymentData] = useState({
        "customer": {
            "fullName": "",
            "address": {
                "street": "",
                "city": "",
                "state": "",
                "zip": "",
                "country": ""
            }
        },
        "cart": [

        ],
        "subtotal": 0.00,
        "tax": 0.00,
        "total": 0.00
    });

    useEffect(() => {
        document.title = "Payment | Thelsamar"
    }, []);

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
        <div className="">
        <div className="container-fluid py-5">
            <h1 className="display-1 fw-bold my-5 border-bottom"> Your Order<Truck className="mx-3"/></h1>
            <p className="fs-2 my-0">{paymentData.customer.fullName}</p>
            <p className="fs-6 text-secondary">
             {paymentData.customer.address.street} {paymentData.customer.address.city} {paymentData.customer.address.state}, {paymentData.customer.address.zip} {paymentData.customer.address.country}
            </p>
            <table className="table my-5 fs-5 border-bottom">
                <thead>
                    <tr>
                    <th className="text-left">Product</th>
                    <th className="text-center">Quantity</th>
                    <th className="text-center">Amount</th>
                    </tr>
                </thead>
                <tbody>
                    {paymentData.cart.map((item) => {
                        return (<tr>
                            <td className="text-left">{item.name}</td>
                            <td className="text-center">{item.quantity}</td>
                            <td className="text-center">${item.price.toFixed(2)}</td>
                        </tr>);
                    })}
                </tbody>
            </table>
            <table className="table my-5 fs-2">
                <tr>
                    <th>Subtotal:</th>
                    <th className="text-left px-5">${parseFloat(paymentData.subtotal).toFixed(2)}</th>
                </tr>
                <tr>
                    <th>Tax:</th>
                    <th className="text-left px-5">${parseFloat(paymentData.tax).toFixed(2)}</th>
                </tr>
                <tr>
                    <th>Total:</th>
                    <th className="text-left px-5">${parseFloat(paymentData.total).toFixed(2)}</th>
                </tr>
            </table>
        </div>
        </div>
    </div>);
}