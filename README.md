# Thelsamar
Thelsamar is an application used to order a cart of items from an inventory and apply tax for the customer's region to the order.

## Running locally
### Before you run

The following ports will need to be available on your local machine: `1901`, `3540`, `3000`

You will need a taxjar API token before running. You can get one here:
https://developers.taxjar.com/api/reference

Now that you have an API token you will need to replace the following value in the `docker-compose.yml` file
```
taxjar_token: YOUR_TOKEN_HERE
```

### Running the Containers
To run the containers for the application you will simply need to run
```
docker compose up -d
```
If you plan on changing the db schema or you enter records that bungle the system up, re-run the container with the following commands
```
docker compose down && docker compose up -d --force-recreate
```
The `--force-recreate` flag will reset the database back to its defaults.

### How to contact for help
If you need help getting the application up and running after following this readme, feel free to contact the auther Draperaj

## Backend API
The backend API for this application provides you with the following endpoints:

### Payment
`/api/payment/` - POST

This endpoint will take a JSON body with the following format
```json
{
  "customerId": 1,
  "orders": [
    {
      "id": 1,
      "quantity": 4
    },
    {
      "id": 6,
      "quantity": 1
    },
    {
      "id": 3,
      "quantity": 2
    },
    {
      "id": 4,
      "quantity": 1
    }
  ]
}
```
In the request body you will provide a customer id that must match an existing record in the customer table of the database. You will then provide a list of orders that must match an item in the inventory table of the database, and you must specify a quantity of that item.

### Actuator Endpoints
The backend application uses Spring actuator endpoints, you can find a complete list of the endpoints here:

https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints

## Useful DB Queries

### User Info Lookup
```sql
SELECT c.id as id, CONCAT(c.first_name, ' ', c.last_name) as name, a.street as street, a.city as city, a.state as state, a.zip as zip, a.country as country
FROM customer c
JOIN address a
ON c.id = a.customer_id
```