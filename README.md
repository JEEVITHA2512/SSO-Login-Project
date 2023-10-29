# cognizant-sso-login

### Keycloak docker
```
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:22.0.4 start-dev
```
### Initializing roles and users
```
1. Create a new realm
2. Configure the clients for the backend and frontend of each app
3. Create user roles
4. Create users and assign them roles
```