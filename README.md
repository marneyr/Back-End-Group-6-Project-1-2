# Recipe App Backend

Spring Boot backend for our Recipe Management application.

## Prerequisites

- Java 17+
- Maven
- PostgreSQL 13+

## Database Setup

Before running the application, make sure you have the PostgreSQL database set up:

1. Run the SQL script to initialize the database:
   ```bash
   psql -U postgres -f RecipeAppG6_updated.sql
   ```
   Default password is `database1`

2. Verify the database was created:
   ```bash
   psql -U postgres -d Group6 -c "SELECT * FROM recipes LIMIT 1;"
   ```

## Configuration

The application configuration is located in `src/main/resources/application.properties`. The default settings are:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/Group6
spring.datasource.username=postgres
spring.datasource.password=database1
```

Update these values if your PostgreSQL configuration differs.

## Building and Running

1. Build the project:
   ```bash
   ./mvnw clean install
   ```

2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

3. The API will be available at: http://localhost:8080/api

## API Documentation

### Recipe Endpoints

- `GET /api/recipes` - Get all recipes
- `GET /api/recipes/{id}` - Get a specific recipe by ID
- `POST /api/recipes` - Create a new recipe
- `PUT /api/recipes/{id}` - Update a recipe
- `DELETE /api/recipes/{id}` - Delete a recipe

### User Endpoints

- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get a specific user
- `POST /api/users` - Create a user
- `PUT /api/users/{id}` - Update a user
- `DELETE /api/users/{id}` - Delete a user

## Project Structure

```
Back-End-Group-6-Project-1-2/
├── src/main/java/edu/cmcc/cpt/demo/
│   ├── Recipe/          # Recipe-related classes
│   ├── User/            # User-related classes
│   ├── Step/            # Step-related classes
│   ├── auth/            # Authentication classes
│   ├── config/          # Configuration classes
│   └── DemoApplication.java # Application entry point
├── src/main/resources/  
│   └── application.properties # App configuration
└── RecipeAppG6_updated.sql   # Database setup script
```

## Default Users

- Admin User:
  - Username: admin
  - Password: admin123
