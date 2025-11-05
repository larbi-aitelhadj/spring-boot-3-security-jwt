# Spring Boot 3 Security JWT Application

<img src="/screenshots/springboot-security-jwt.png" alt="Main Information" width="800" height="450">

## Description

Cette application est un **backend Spring Boot 3** qui implémente:

- Authentification et autorisation avec **JWT (JSON Web Tokens)**
- Gestion des **utilisateurs** (inscription et connexion)
- Gestion des **produits** (CRUD : création, lecture, suppression)
- Sécurisation des endpoints avec **Spring Security**

---

## Technologies utilisées

- Java 21
- Spring Boot 3.5.7
- Spring Security
- Spring Data JPA
- JWT (io.jsonwebtoken:jjwt)
- PostgreSQL
- Lombok
- Maven
- JUnit 5, Mockito pour les tests

---

## Architecture

### Entités principales

1. **User** : contient `id`, `username`, `password` et `role`
2. **Product** : contient `id`, `name`, `price`, `description`

### Services

- `CustomerUserDetailsService` : implémente `UserDetailsService` pour Spring Security
- `JwtService` : génération et validation des JWT
- `ProductService` : logique métier pour les produits

### Contrôleurs REST

- `AuthController` : endpoints `/api/auth/register` et `/api/auth/login`
- `ProductController` : endpoints CRUD `/api/products/**`

### Sécurité

- Filtre `JwtAuthenticationFilter` pour valider les JWT sur chaque requête
- Configuration Spring Security dans `SpringConfig`
- Passwords encodés avec **BCrypt**

---

## Endpoints API

### Authentification

| Méthode | URL                   | Description                          |
|---------|----------------------|--------------------------------------|
| POST    | `/api/auth/register`  | Inscription d’un nouvel utilisateur |
| POST    | `/api/auth/login`     | Connexion et génération d’un JWT    |

### Produits

| Méthode | URL                                  | Description                       |
|---------|-------------------------------------|-----------------------------------|
| GET     | `/api/products/getAllProducts`       | Récupère tous les produits       |
| GET     | `/api/products/getProductById`      | Récupère un produit par ID       |
| POST    | `/api/products/createProduct`       | Crée un nouveau produit          |
| DELETE  | `/api/products/deleteProductById/{id}` | Supprime un produit par ID      |

---

## Tests

- Tests unitaires avec JUnit 5 et Mockito

- Tests d’intégration avec @SpringBootTest et @WebMvcTest

- Exemples: UserRepositoryTest, ProductControllerTest, JwtServiceTest

---

## Lancer le projet

1. Cloner le dépôt :

```
git clone <URL_DU_REPO>
cd spring-boot3-security-jwt
```

2. Construire le projet :

```
mvn clean install
```

3. Lancer l’application :

```
docker-compose up --build
```

4. Accéder à l’API via http://localhost:8080

## Exemple de requête JWT

<img src="/screenshots/test.png" alt="Main Information" width="800" height="450">

---

## Auteur
```
Larbi Aitelhadj
💼 Software Engineer & Java Developer
📧 Contact: larbi.aitelhadj@gmail.com
🧾 Version: 1.0
```
