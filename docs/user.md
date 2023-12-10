# User API Specs

## Register User

Endpoint: POST /api/users

Request Body:
```json
{
  "username": "Husnul",
  "password": "secret",
  "name": "Husnul Nawafil"
}
```

Response Body (Success):
```json
{
  "data": "ok"
}
```

Response Body (Failure):
```json
{
  "errors": "Username must not be blank, ???"
}
```

## Login User

Endpoint: POST /api/auth/login

Request Body:
```json
{
  "username": "Husnul",
  "password": "secret"
}
```

Response Body (Success):
```json
{
  "data": {
    "token": "TOKEN",
    "expiredAt": 3487435834 // miliseconds
  }
}
```

Response Body (Failure):
```json
{
  "errors": "Username or password wrong"
}
```

## GET User

Endpoint: GET /api/users/current

Request Header:
- X-API-TOKEN: Token (mandatory)

Response Body (Success):
```json
{
  "data": {
    "username": "husnul",
    "name": "Husnul Nawafil"
  }
}
```

Response Body (Failure, 401):
```json
{
  "errors": "Unauthorized"
}
```

## Update User

Endpoint: PATCH /api/users/current

Request Header:
- X-API-TOKEN: Token (mandatory)

Request Body:
```json
{
  "password": "secretnew", // put if only update password
  "name": "Husnul Nawafil, S.T." // put if only update name
}
```

Response Body (Success):
```json
{
  "data": {
    "username": "husnul",
    "name": "Husnul Nawafil"
  }
}
```

Response Body (Failure, 401):
```json
{
  "errors": "Unauthorized"
}
```

## Logout User

Endpoint: POST /api/auth/logout

Request Header:
- X-API-TOKEN: Token (mandatory)


Request Body:
```json
{
  "data": "OK"
}
```
