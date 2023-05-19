**Docker image requries** mysql database


runs on port 8003 


**enviourment variables**
example

      DB_URL: authservice-mysql:3306/authservice-mysql
      DB_USER: developer
      DB_PASS: password


## API Reference
**Create accunt**
```http
  POST /api/auth
```
**Login**
```http
  POST /api/auth/login
```
**Delete account**
```http
  DELETE /api/auth/login
```  
**All of them requires** json of a user
example

        {
            "username":"admin"
            "password":"pass123"
        }
#### Get information from token 
```http
  POST /api/auth/validate/UUID
  POST /api/auth/validate/user
```
**Both requires** plaintext of a valid token

