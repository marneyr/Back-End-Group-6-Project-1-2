# Recipe Application - Group 6

A full-stack recipe management application with React frontend and Spring Boot backend.

## Project Overview

This application allows users to:
- Browse recipes
- Create new recipes
- Edit existing recipes
- Delete recipes
- View detailed recipe information

## Tech Stack

- **Frontend**: React with Vite, CSS
- **Backend**: Java Spring Boot
- **Database**: PostgreSQL

## Setup Instructions

### Prerequisites

- Node.js (v14 or higher)
- Java 17 or higher
- Maven
- PostgreSQL 13+ installed
- Git

### Database Setup

1. Install PostgreSQL if you haven't already
   - [PostgreSQL Downloads](https://www.postgresql.org/download/)

2. Set up the database using our SQL script:
   ```bash
   psql -U postgres -f Back-End-Group-6-Project-1-2/RecipeAppG6_updated.sql
   ```
   
   When prompted for password, enter: `database1`

3. Verify the database was created by logging in and checking tables:
   ```bash
   psql -U postgres -d Group6
   ```

   Then in the PostgreSQL shell:
   ```sql
   \dt  -- List tables
   SELECT * FROM recipes LIMIT 1;  -- Check a sample recipe
   \q   -- Quit
   ```

### Backend Setup

1. Navigate to the backend directory:
   ```bash
   cd Back-End-Group-6-Project-1-2
   ```

2. Build the project with Maven:
   ```bash
   ./mvnw clean install
   ```
   
   On Windows:
   ```bash
   mvnw.cmd clean install
   ```

3. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```
   
   On Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

4. The backend will be available at: http://localhost:8080/api

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd Front-End-Group-6-Project-1-2/recipe-frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

4. The frontend will be available at: http://localhost:5173

## Database Backup & Restore

See the [Database Documentation](./docs/database.md) for instructions on backing up and restoring the database.

## Default Users

- Admin User:
  - Username: admin
  - Password: admin123

## Contributors

- [List of team members]
