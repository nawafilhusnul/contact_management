# Contact API Spec

## Create Contact
Endpoint: POST /api/contacts

Request Header:
- X-API-TOKEN: Token (mandatory)

Request Body:
```json
{
  "firstName": "Husnul",
  "lastName": "Nawafil",
  "email": "husnul@example.com",
  "phone": "+6282249907755"
}
```

Response Body (success):
```json
{
  "data": {
    "id": "randomString",
    "firstName": "Husnul",
    "lastName": "Nawafil",
    "email": "husnul@example.com",
    "phone": "+6282249907755"
  }
}
```

Response Body (failure):
```json
{
  "errors": "Email format is invalid, phone format is invalid, ..."
}
```

## Update Contact
Endpoint: PUT /api/contacts/{idContact}

Request Header:
- X-API-TOKEN: Token (mandatory)

Request Body:
```json
{
  "firstName": "Husnul",
  "lastName": "Nawafil",
  "email": "husnul@example.com",
  "phone": "+6282249907755"
}
```

Response Body (success):
```json
{
  "data": {
    "id": "randomString",
    "firstName": "Husnul",
    "lastName": "Nawafil",
    "email": "husnul@example.com",
    "phone": "+6282249907755"
  }
}
```

Response Body (failure):
```json
{
  "errors": "Email format is invalid, phone format is invalid, ..."
}
```

## Get Contact
Endpoint: GET /api/contacts/{idContact}

Request Header:
- X-API-TOKEN: Token (mandatory)

Response Body (success):
```json
{
  "data": {
    "id": "randomString",
    "firstName": "Husnul",
    "lastName": "Nawafil",
    "email": "husnul@example.com",
    "phone": "+6282249907755"
  }
}
```

Response Body (failure):
```json
{
  "errors": "contact not found"
}
```

## Search Contact
Endpoint: GET /api/contacts

Request Header:
- X-API-TOKEN: Token (mandatory)

Query Parameters:
- name: String, contact first name or last name, using like query (optional)
- phone: String, contact phone, using like query (optional)
- email: String, contact email, using like query (optional)
- page: Interger, start from 0, default 0
- size: Integer, default 10

Response Body (success):
```json
{
  "data": [
    {
      "id": "randomString",
      "firstName": "Husnul",
      "lastName": "Nawafil",
      "email": "husnul@example.com",
      "phone": "+6282249907755"
    }
  ],
  "pagination": {
    "currentPage": 0,
    "totalPage": 10,
    "size": 10
  }
}
```

Response Body (failure):
```json
"errors": "Unauthorized"
```

## Remove Contact
Endpoint: DELETE /api/contacts/{idContacts}

Request Header:
- X-API-TOKEN: Token (mandatory)

Response Body (success):
```json
{
  "data": "OK"
}
```

Response Body (failure):
```json
{
  "errors": "contact not found"
}
```
