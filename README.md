## Creating an Order ##

`curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST -d '{"name" : "Latte", "location": "Manchester", "quantity": "2", "milk" : "whole", "size": "large"}'`


## Retrieving an Order ##

`curl -i -H "Accept: application/json" -X GET http://localhost:8080/order/1`

## Changing quantity of an Order ##

`curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X PUT -d '{"quantity": "5"}' http://localhost:8080/order/1`