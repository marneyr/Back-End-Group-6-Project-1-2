# Database Documentation

This document covers all database-related operations for the Recipe Application.

## Database Schema

Our PostgreSQL database consists of the following main tables:

- `users` - Stores user account information
- `recipes` - Stores recipe data with JSON for ingredients and instructions

## Backup & Restore

### Creating a Database Backup

To create a backup of the current database state:

1. **Using the backup script**:

   ```bash
   bash ./backup-db.sh
   ```

   This will create a timestamped backup file in the `database-backups` directory.

2. **Manual backup** using `pg_dump`:

   ```bash
   pg_dump -U postgres -d Group6 > recipe_app_backup_$(date +%Y%m%d_%H%M%S).sql
   ```

   You'll be prompted for your PostgreSQL password.

### Restoring from a Backup

To restore the database from a backup file:

1. **Using the restore script**:

   ```bash
   bash ./restore-db.sh ./database-backups/your_backup_filename.sql
   ```

2. **Manual restore**:

   First, recreate the database:
   ```bash
   psql -U postgres -c "DROP DATABASE IF EXISTS \"Group6\";"
   psql -U postgres -c "CREATE DATABASE \"Group6\";"
   ```

   Then restore from backup:
   ```bash
   psql -U postgres -d Group6 < your_backup_filename.sql
   ```

### Using the Provided SQL Script

You can also reset the database to its initial state using the provided SQL script:

```bash
psql -U postgres -f RecipeAppG6_updated.sql
```

This script will:
1. Drop the existing database if it exists
2. Create a new `Group6` database
3. Set up all tables with proper constraints
4. Populate tables with initial sample data
5. Set up the admin user

## PostgreSQL Configuration

Default connection settings:
- Host: localhost
- Port: 5432
- Database: Group6
- Username: postgres
- Password: database1

If you need to modify these settings for the backend, update the `application.properties` file in the Spring Boot project.
