import React, {useState, useEffect} from "react";
import { Form } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import './OrderForm.css';

export default function OrderForm(props) {
    const navigate = useNavigate();
    const [customerId, setCustomerId] = useState(1);
    const [orders, setOrders] = useState({});

    const updateOrders = (event) => {
        let productId = event.target.id;
        let newOrders = orders;

        let quantity = parseInt(event.target.value, 10);

        newOrders[productId] = !isNaN(quantity) ? quantity : 0;
        setOrders(newOrders);
    }

    const updateOrderId = (event) => {
        setCustomerId(event.target.value);
    }

    const submitOrder = (event) => {
        event.preventDefault();
        let ordersList = [];
        for (const [key, value] of Object.entries(orders)) {
            ordersList.push({id: key, quantity: value});
        }
        navigate("/payment", {state: {customerId: customerId, orders: ordersList}});
    }

    useEffect(() => {
        document.title = "Order | Thelsamar"
    }, []);

    return(<div className="orderForm">
        <Form size="lg" className="mx-auto" onSubmit={submitOrder}>
            <Form.Group size="lg" controlId="customerId">
                <Form.Label>Select Cusomter</Form.Label>
                <Form.Control
                    autoFocus
                    as="select"
                    onChange={updateOrderId}
                    className="mx-auto">
                    <option value="1">draperaj</option>
                    <option value="2">bobmct</option>
                    <option value="3">sam</option>
                    <option value="4">martha</option>
                </Form.Control>
            </Form.Group>
            <Form.Group className="py-5" size="lg" controlId="products">
                <h1>Products</h1>
                <Form.Group controlId="1" className="my-3">
                    <Form.Label>Grapes - $10.00</Form.Label>
                    <Form.Control
                        type="number"
                        onChange={updateOrders}
                        value={orders[1]}
                    />
                </Form.Group>
                <Form.Group controlId="2" className="my-3">
                    <Form.Label>Strawberries - $12.50</Form.Label>
                    <Form.Control
                        type="number"
                        onChange={updateOrders}
                        value={orders[2]}
                    />
                </Form.Group>
                <Form.Group controlId="3" className="my-3">
                    <Form.Label>Orange - $20.40</Form.Label>
                    <Form.Control
                        type="number"
                        onChange={updateOrders}
                        value={orders[3]}
                    />
                </Form.Group>
                <Form.Group controlId="4" className="my-3">
                    <Form.Label>Banana - $29.99</Form.Label>
                    <Form.Control
                        type="number"
                        onChange={updateOrders}
                        value={orders[4]}
                    />
                </Form.Group>
                <Form.Group controlId="5" className="my-3">
                    <Form.Label>Mango - $54.99</Form.Label>
                    <Form.Control
                        type="number"
                        onChange={updateOrders}
                        value={orders[5]}
                    />
                </Form.Group>
                <Form.Group controlId="6" className="my-3">
                    <Form.Label>Apple - $100.00</Form.Label>
                    <Form.Control
                        type="number"
                        onChange={updateOrders}
                        value={orders[6]}
                    />
                </Form.Group>
            </Form.Group>
            <Form.Control
                type="submit"
                value="Order"
                className="btn-primary"/>
        </Form>
    </div>);
}