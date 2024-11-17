# Coding Examples for Spring Boot

This document follows [RFC2119](https://www.ietf.org/rfc/rfc2119.txt) notation.


## APIs

* [POST] `/ip-address` which adds a new IP information to the database.

The JSON payload should be like this:

```json
{
  "type": "ipv4",
  "value": "1.2.3.4",
  "firstSeen": "2022-06-16T08:45:58.539",
  "totalCount": 0
}
```

* [GET]  `/ip-addresses/{ip-value}` which returns the list of IP Information

## Instructions to launch the service:
```shell
java -Denv_var=test -jar assignment-0.0.1-SNAPSHOT.jar
```

## How to validate the API
* launch the browser to view the Swagger API Documentation with URL: `http://localhost:8080/intel/swagger-ui/index.html`
* API Documentation provides schema and other API details.

