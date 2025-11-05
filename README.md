# Spring Boot 3 Security JWT Application

<img src="/screenshots/springboot-security-jwt.png" alt="Main Information" width="800" height="450">

## Description

This application is a **Spring Boot 3 backend** that implements:

- **Authentication and authorization** using **JWT (JSON Web Tokens)**.
- **User management** (registration and login).
- **Product management** (CRUD: create, read, delete).
- **Endpoint security** with **Spring Security**

---

## Technologies Used

- Java 21
- Spring Boot 3.5.7
- Spring Security
- Spring Data JPA
- Spring Web
- JWT (JSON Web Token)
- PostgreSQL
- Lombok
- Maven
- JUnit 5, Mockito pour les tests

---

## Architecture

### Main Entities

1. **User** : contains `id`, `username`, `password` et `role`
2. **Product** : contains `id`, `name`, `price`, `description`

### Services

- `CustomerUserDetailsService` : implements `UserDetailsService` for Spring Security.
- `JwtService` : handles JWT generation and validation.
- `ProductService` : business logic for products.

### REST Controllers

- `AuthController` : endpoints `/api/auth/register` et `/api/auth/login`
- `ProductController` : CRUD endpoints `/api/products/**`

### Security

- **JwtAuthenticationFilter** validates JWTs on each request.
- **Spring Security configuration** `SpringConfig`.
- Passwords are encoded using **BCrypt**

---

## API Endpoints

### Authentication

| Method  | URL                   | Description               |
|---------|-----------------------|---------------------------|
| POST    | `/api/auth/register`  | Register a new user       |
| POST    | `/api/auth/login`     | Login and generate a JWT  |

### Products

| Method  | URL                                    | Description              |
|---------|----------------------------------------|--------------------------|
| GET     | `/api/products/getAllProducts`         | Retrieve all products    |
| GET     | `/api/products/getProductById`         | Retrieve a product by ID |
| POST    | `/api/products/createProduct`          | Create a new product     |
| DELETE  | `/api/products/deleteProductById/{id}` | Delete a product by ID   |

---

## Tests

- Unit tests with **JUnit** 5 and **Mockito**.

- Integration tests with `@SpringBootTest` and `@WebMvcTest`.

- Examples: `UserRepositoryTest`, `ProductControllerTest`, `JwtServiceTest`.

---

## Running the Project

1. Clone the repository:

```
git clone <URL_DU_REPO>
cd spring-boot3-security-jwt
```

2. Build the project:

```
mvn clean install
```

3. Run the application:

```
docker-compose up --build
```

4. Access the API via: http://localhost:8080

## Example JWT Request

<img src="/screenshots/test.png" alt="Main Information" width="800" height="450">

---

## Author
```
Larbi Aitelhadj
💼 Software Engineer & Java Developer
📧 Contact: larbi.aitelhadj@gmail.com
🧾 Version: 1.0
```
