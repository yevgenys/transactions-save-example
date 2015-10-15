Description
=========
RESTful web service based on spring-boot + gradle. 
Service stores some transactions(in memory) and returns information about those transactions.

Api details
========

Create transaction
-----------

PUT /transactionservice/transaction/$transaction_id
Body:
{ "amount":double,"type":string,"parent_id":long }

where:
* __transaction_id__ is a long specifying a new transaction
* __amount__ is a double specifying the amount
* __type__ is a string specifying a type of the transaction.
* __parent_id__ is an optional long that may specify the parent transaction of this transaction.

returns:
{"status": String}

Get transaction details
----------
GET /transactionservice/transaction/$transaction_id

Returns:
{ "status": String, "amount":double,"type":string,"parent_id":long }

Get transaction ids by type
-----------

GET /transactionservice/types/$type

Returns:
{ "status": String, ids: long[] }

Get sum
-----------

GET /transactionservice/sum/$transaction_id

Returns:
{ "status": String, sum: double }

Examples
------------
```
PUT /transactionservice/transaction/10 --data { "amount": 5000, "type": "cars" }
=> { "status": "ok" }

PUT /transactionservice/transaction/11
{ "amount": 10000, "type": "shopping", "parent_id": 10 }
=> { "status": "ok" }

GET /transactionservice/types/cars 
=> [10]

GET /transactionservice/sum/10 
=> {"sum":15000}

GET /transactionservice/sum/11
=> {"sum":10000}
```
