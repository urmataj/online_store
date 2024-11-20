# Online Store API

This project is an online store API built with **Spring Framework** and **PostgreSQL**. The API allows users to manage products and orders, providing basic CRUD operations for both, as well as order status management.

## Table of Contents
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Entities](#entities)
  - [OrderEntity](#orderentity)
  - [UserEntity](#userentity)
  - [ProductEntity](#productentity)
- [Repositories](#repositories)
  - [OrderRepository](#orderrepository)
  - [UserRepository](#userrepository)
  - [ProductRepository](#productrepository)
- [API Endpoints](#api-endpoints)
- [Setup and Installation](#setup-and-installation)

## Technologies
- **Java** 17
- **Spring Boot** 2.x
- **Spring Data JPA** for database interaction
- **PostgreSQL** for the database
- **Lombok** for boilerplate code reduction
- **JUnit** for unit testing

## Project Structure

```
src/
 ├── main/
 │   ├── java/
 │   │   ├── com/
 │   │   │   ├── example/
 │   │   │   │   ├── online_store/
 │   │   │   │   │   ├── entity/         # Entity classes
 │   │   │   │   │   ├── repository/     # Repository interfaces
 │   │   │   │   │   ├── service/        # Service classes
 │   │   │   │   │   └── controller/     # Controllers
 │   ├── resources/
 │   │   ├── application.properties
```

## Entities

### OrderEntity
Represents an order in the online store.

- **Fields:**
  - `id`: Order ID (Primary Key)
  - `status`: The current status of the order (e.g., "in process", "shipped", "completed")
  - `orderDate`: The date when the order was placed
  - `products`: A list of products in the order (Many-to-Many relationship with `ProductEntity`)
  - `user`: The user who placed the order (Many-to-One relationship with `UserEntity`)

- **Key Methods:**
  - `init()`: Initializes the order date to the current timestamp when a new order is created.

### UserEntity
Represents a user in the system.

- **Fields:**
  - `id`: User ID (Primary Key)
  - `username`: The unique username of the user
  - `password`: The user's password (stored securely)
  - `email`: The user's email address
  - `createdAt`: Timestamp when the user account was created

- **Key Methods:**
  - `init()`: Initializes the created date when a new user is created.

### ProductEntity
Represents a product in the online store.

- **Fields:**
  - `id`: Product ID (Primary Key)
  - `name`: Name of the product
  - `price`: Price of the product
  - `stock`: Quantity of the product available in stock

## Repositories

### OrderRepository
An interface for performing CRUD operations on `OrderEntity`.

- **Methods:**
  - `findAllByStatus(String status)`: Finds all orders with a given status.
  - `findById(Long id)`: Finds a specific order by its ID.

### UserRepository
An interface for performing CRUD operations on `UserEntity`.

- **Methods:**
  - `findByUsername(String username)`: Finds a user by their username.

### ProductRepository
An interface for performing CRUD operations on `ProductEntity`.

- **Methods:**
  - `findById(Long id)`: Finds a product by its ID.
  - `findAll()`: Retrieves all products.

## API Endpoints

The following are the available API endpoints:

### Users
- `POST /users`: Create a new user.
- `GET /users/{username}`: Get a user by username.

### Orders
- `GET /orders`: Get a list of all orders.
- `GET /orders/status/{status}`: Get orders by their status.
- `GET /orders/{id}`: Get a specific order by its ID.
- `POST /orders`: Create a new order.

### Products
- `GET /products`: Get a list of all products.
- `GET /products/{id}`: Get a specific product by its ID.
- `POST /products`: Add a new product.
- `PUT /products/{id}`: Update a product.
- `DELETE /products/{id}`: Delete a product.

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/online-store.git
   ```

2. Navigate into the project directory:
   ```bash
   cd online-store
   ```

3. Configure your `application.properties` with your PostgreSQL credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/online_store
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```

4. Build and run the project:
   ```bash
   ./mvnw spring-boot:run
   ```

5. The API will be available at `http://localhost:8080`.
