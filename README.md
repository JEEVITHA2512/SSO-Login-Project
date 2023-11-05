# cognizant-sso-login

### Postgres docker
```
docker run --name postgres-sso -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=sso-login -p 5434:5432 -d postgres
```

### Keycloak docker
```
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:22.0.4 start-dev
```
### Initializing roles and users
```
1. Open the link: http://localhost:8080/admin/master/console/#/master
2. Use the username "admin" and password "admin"
3. Create a new realm using the sso-login-realm-export.json as the resource
4. Create user "admin" and assign credentials and "admin-access" role.
```

### Run apps
```
cd app1/backend
mvn spring-boot:run
cd ../frontend
npm start
cd ../../app2/backend
mvn spring-boot:run
cd ../frontend
npm start
```