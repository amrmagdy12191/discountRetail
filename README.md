# Retail Discount Application

## Overview

This application calculates discounts based on various criteria for a retail website. 
It uses Java 21, Spring Boot 3.3.2, Maven

Project apply the following discounts:

1. 30% discount for employees
2. 10% discount for affiliates
3. 5% discount for customers with more than 2 years of registration
4. $5 discount for every $100 spent


## Building the Project

To build the project, run:

```sh
mvn clean install
mvn clean test
mvn spring-boot:run
```

## API URL : http://localhost:8080/api/bill/calculate

## Test Cases:
1. 5% discount on not grocery for customers with more than 2 years of registration + fixed discount (5$ for every 100) = 7.5$
Amount = 150

Sample Request:
{
  "id": 1,
  "amount": 150.0,
  "date": "2024-07-29T10:30:00Z",
  "user": {
    "userId": 456,
    "name": "John Doe",
    "userType": "CUSTOMER",
    "joinDate": "2021-01-15T09:00:00Z"
  },
  "items": [
    {
      "id": 1,
      "name": "Milk",
      "itemCategory": "GROCERY",
      "price": 50
    },
    {
      "id": 2,
      "name": "Bread",
      "itemCategory": "GROCERY",
      "price": 50
    },
    {
      "id": 3,
      "name": "Notebook",
      "itemCategory": "OTHERS",
      "price": 50
    }
  ]
}

Sample Response:
{
    "status": "Success",
    "errorCode": null,
    "errorDescription": null,
    "result": {
        "amount": 142.5
    }
}

2. 30% discount on not grocery items for employees + + fixed discount (5$) = 20$
Amount: 150$

Sample Request:
{
  "id": 1,
  "amount": 150.0,
  "date": "2024-07-29T10:30:00Z",
  "user": {
    "userId": 456,
    "name": "John Doe",
    "userType": "EMPLOYEE",
    "joinDate": "2021-01-15T09:00:00Z"
  },
  "items": [
    {
      "id": 1,
      "name": "Milk",
      "itemCategory": "GROCERY",
      "price": 50
    },
    {
      "id": 2,
      "name": "Bread",
      "itemCategory": "GROCERY",
      "price": 50
    },
    {
      "id": 3,
      "name": "Notebook",
      "itemCategory": "OTHERS",
      "price": 50
    }
  ]
}

Sample Response:
{
    "status": "Success",
    "errorCode": null,
    "errorDescription": null,
    "result": {
        "amount": 130.0
    }
}

3. 10% discount on not grocery for affiliates + 5$ on every 100 = 20$
Amount: 250$

Sample Request:
{
  "id": 1,
  "amount": 250.0,
  "date": "2024-07-29T10:30:00Z",
  "user": {
    "userId": 456,
    "name": "John Doe",
    "userType": "AFFILIATE",
    "joinDate": "2021-01-15T09:00:00Z"
  },
  "items": [
    {
      "id": 1,
      "name": "Milk",
      "itemCategory": "GROCERY",
      "price": 50
    },
    {
      "id": 2,
      "name": "Bread",
      "itemCategory": "GROCERY",
      "price": 50
    },
    {
      "id": 3,
      "name": "Notebook",
      "itemCategory": "OTHERS",
      "price": 100
    }
  ]
}


Sample Response:
{
    "status": "Success",
    "errorCode": null,
    "errorDescription": null,
    "result": {
        "amount": 230.0
    }
}

4. only $5 discount for every $100 spent and no percentage = 10$
Amount: 250$

Sample Request:
{
  "id": 1,
  "amount": 250.0,
  "date": "2024-07-29T10:30:00Z",
  "user": {
    "userId": 456,
    "name": "John Doe",
    "userType": "CUSTOMER",
    "joinDate": "2023-01-15T09:00:00Z"
  },
  "items": [
    {
      "id": 1,
      "name": "Milk",
      "itemCategory": "GROCERY",
      "price": 50
    },
    {
      "id": 2,
      "name": "Bread",
      "itemCategory": "GROCERY",
      "price": 50
    },
    {
      "id": 3,
      "name": "Notebook",
      "itemCategory": "OTHERS",
      "price": 100
    }
  ]
}

4. No Discount
Amount: 98$

Sample Request:
{
  "id": 1,
  "amount": 98,
  "date": "2024-07-29T10:30:00Z",
  "user": {
    "userId": 456,
    "name": "John Doe",
    "userType": "CUSTOMER",
    "joinDate": "2024-01-15T09:00:00Z"
  },
  "items": [
    {
      "id": 1,
      "name": "Milk",
      "itemCategory": "GROCERY",
      "price": 50
    },
    {
      "id": 2,
      "name": "Bread",
      "itemCategory": "GROCERY",
      "price": 50
    },
    {
      "id": 3,
      "name": "Notebook",
      "itemCategory": "OTHERS",
      "price": 100
    }
  ]
}

Sample Response:
{
    "status": "Success",
    "errorCode": null,
    "errorDescription": null,
    "result": {
        "amount": 98.0
    }
}
