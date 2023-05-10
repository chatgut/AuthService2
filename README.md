**Docker image requries** mysql database
## API Reference

```http
  GET /api/auth/login
  DELETE /api/auth/login
  POST /api/auth/login
  
```
**Requires** json

example 

        {
            "username":"admin"
            "password":"pass123"
        }
#### Get information from token 
```http
  GET /api/auth/validate/UUID
  GET /api/auth/validate/user
```
**Requires** plaintext of a valid token

