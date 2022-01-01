# Thelsamar



## Useful DB Queries

### User Info Lookup
```sql
SELECT c.id as id, CONCAT(c.first_name, ' ', c.last_name) as name, a.street as street, a.city as city, a.state as state, a.zip as zip, a.country as country
FROM customer c
JOIN address a
ON c.id = a.customer_id
```