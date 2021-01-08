
# Spring Boot Task 2

## Spring Boot: Stok Trades API
In this challenge your task is to implement a Simple REST API to manage a collection of stock trades.<br>
Each trade is JSON entry with the following keys:
- <code>id</code>: the unique trade ID. (Integer)
- <code>type</code>: the trade type, either “buy” or “sell”. (String)
- <code>userId</code>: the unique user ID. (Integer)
- <code>symbol</code>: the stock symbol. (String)
- <code>shares</code>: the total number of shares traded. The traded shares value is between 1 and 100, inclusive. (Integer)
- <code>price</code>: the price of one share of stock at the time of one trade. (Integer)
- <code>timestamp</code>: the epoch time of the stock trade in milliseconds. (Long)

Here is an example of a trade JSON object:<br>
```
{
"id": 1,
"type": "buy",
"userId": 23,
"symbol": "ABX",
"shares": 30,
"price": 134s,
"timestamp": 1531522701000
}
```

The task is to implement a model for the Trade object and the REST service that exposes the <b>/trades</b> endpoint, which allows for managing the collection of trade records in the following way:<br>

<b><code>POST</code></b> request to <b><code>/trades</code></b>:<br>
- creates a new trade
- expects a JSON trade object without an id property as a body payload. If the <i>shares</i> value is out of the accepted range [1, 100], or the <i>type</i> value is invalid (i.e., not 'buy' or 'sell'), the API must return error code 400. Besides those cases, you can assume that the given payload is always valid.
- adds the given trade object to the collection of trades and assigns a unique integer id to it. The first created trade must have id 1, the second one 2, and so on.
- the response code is 201, and the response body is the created trade object

<b><code>GET</code></b> request to <b><code>/trades</code></b>:<br>
- returns a collection of all trades
- the response code is 200, and the response body is an array of all trade objects ordered by their ids in increasing order
- optionally accepts query parameters type and userId, for example <b>/trades/?type=buy&userId=122</b>. All these parameters are optional. In case they are present, only objects matching the parameters must be returned.

<b><code>GET</code></b> request to <b><code>/trades/\<id\></code></b>:<br>
- returns a trade with the given id
- if the matching trade exists, the response code is 200 and the response body is the matching trade object
- if there is no trade with the given id in the collection, the response code is 404

<b><code>DELETE, PUT, PATCH</code></b> request to <b><code>/trades/\<id\></code></b>:<br>
- the response code is 405 because the API does not allow deleting or modifying trades for any id value


You should complete the given project so that it passes all the test cases when running the provided <i>unit</i> tests. The project by default supports the use of the H2 database.


<b>Example requests and responses</b><br>
POST <b>request to</b> <code>/trade</code><br>
Request body:<br>
```
{
"type": "buy",
"userId": 1,
"symbol": "AC",
"shares": 28,
"price": 162,
"timestamp": 1591514264000
}
```

The response code 201, and when converted to JSON, the response body is:<br>
```
{
"id": 1,
"type": "buy",
"userId": 1,
"symbol": "AC",
"shares": 28,
"price": 162,
"timestamp": 1591514264000
}
```

This adds a new Object to the collection with the given properties and id 1. If the <i>share</i> value is not in the range [1, 100], or the <i>type</i> value is not 'buy' or 'share', then the response code is 400.<br>

GET <b>request to</b> <code>/trades</code><br>
The response code is 200, and when converted to JSON, the response body (assuming that the below objects are all objects matching the filter) is as follows:<br>
```
[
{
"id": 1,
 "type": "buy",
"userId": 1,
"symbol": "AC",
"shares": 28,
"price": 162,
"timestamp": 1591514264000
},
{
"id": 2,
 "type": "sell",
"userId": 1,
"symbol": "AC",
"shares": 28,
"price": 162,
"timestamp": 1591514264000
}
]
```

GET <b>request to</b> <code>/trades/?type=buy</code><br>
The response code is 200, and when converted to JSON, the response body (assuming that the below objects are all objects matching the filter) is as follows:<br>
```
[
{
"id": 1,
 "type": "buy",
"userId": 1,
"symbol": "AC",
"shares": 28,
"price": 162,
"timestamp": 1591514264000
}
]
```

GET <b>request to</b> <code>/trades/?userId=122</code><br>
The response code is 200, and when converted to JSON, the response body (assuming that the below objects are all objects matching the filter) is as follows:<br>
```
[
{
"id": 1,
 "type": "buy",
"userId": 1,
"symbol": "AC",
"shares": 28,
"price": 162,
"timestamp": 1591514264000
},
{
"id": 2,
 "type": "sell",
"userId": 1,
"symbol": "AC",
"shares": 28,
"price": 162,
"timestamp": 1591514264000
}
]
```

GET <b>request to</b> <code>/trades/1</code><br>
Assuming that the object with id 1 exists, then the response code is 200 and the response body, when converted to JSON, is as follows:<br>
```
[
{
"id": 1,
 "type": "buy",
"userId": 1,
"symbol": "AC",
"shares": 28,
"price": 162,
"timestamp": 1591514264000
}
]
```

If an object with id 1 doesn't exist, then the response code is 404 and there are no
particular requirements for the response body.<br>

DELETE <b>request to</b> <code>/trades/1</code><br>
The response code is 405 and there are no particular requirements for the response body.


## Software Instructions
The question(s) requires Java Development Kit 8.<br>
- Install-QpenJOK.8-on-MacQS-Qr-Linux
- InstallJDK.8-on-W.1ndows

## Git Instructions
Use the following commands to work with this project:<br>
<b>Run</b><br>
```
mvn clean spring-boot:run
```
<b>Test</b><br>
```
mvn clean test
```
<b>Install</b><br>
```
mvn clean install
```




