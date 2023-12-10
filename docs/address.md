# Address API Spec

## Create Address
Endpoint: POST /api/contacts/{idContact}/addresses

Request Header:
- X-API-TOKEN: Token (mandatory)

Request Body:
```json
{
  "street": "Jl.....",
  "city": "cityName",
  "province": "provinceName",
  "country": "countryName",
  "zipCode": "12345",
}
```

Response Body (success):
```json
{
  "data": {
    "id": "randomString",
    "street": "Jl.....",
    "city": "cityName",
    "province": "provinceName",
    "country": "countryName",
    "zipCode": "12345",
  }
}
```

Response Body (failure):
```json
{
  "errors": "Contact is not found, ..."
}
```

## Update Address
Endpoint: PUT /api/contacts/{idContact}/addresses/{idAddress}

Request Header:
- X-API-TOKEN: Token (mandatory)

Request Body:
```json
{
  "street": "Jl.....",
  "city": "cityName",
  "province": "provinceName",
  "country": "countryName",
  "zipCode": "12345",
}
```

Response Body (success):
```json
{
  "data": {
    "id": "randomString",
    "street": "Jl.....",
    "city": "cityName",
    "province": "provinceName",
    "country": "countryName",
    "zipCode": "12345",
  }
}
```

Response Body (failure):
```json
{
  "errors": "Contact is not found, ..."
}
```

## Get Address
Endpoint: GET /api/contacts/{idContact}/addresses/{idAddress}

Request Header:
- X-API-TOKEN: Token (mandatory)

Response Body (success):
```json
{
  "data": {
    "id": "randomString",
    "street": "Jl.....",
    "city": "cityName",
    "province": "provinceName",
    "country": "countryName",
    "zipCode": "12345",
  }
}
```

Response Body (failure):
```json
{
  "errors": "Contact is not found, ..."
}
```

## List Addresses
Endpoint: GET /api/contacts/{idContact}/addresses

Request Header:
- X-API-TOKEN: Token (mandatory)

Response Body (success):
```json
{
  "data": [
    {
      "id": "randomString",
      "street": "Jl.....",
      "city": "cityName",
      "province": "provinceName",
      "country": "countryName",
      "zipCode": "12345",
    }
  ]
}
```

Response Body (failure):
```json
"errors": "Unauthorized"
```

## Remove Address
Endpoint: DELETE /api/contacts/{idContact}/addresses/{idAddress}

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
