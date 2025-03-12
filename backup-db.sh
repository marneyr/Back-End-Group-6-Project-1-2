#!/bin/bash

# Create backup directory if it doesn't exist
BACKUP_DIR="../database-backups"
mkdir -p $BACKUP_DIR

# Generate timestamp for the backup file
TIMESTAMP=$(date +%Y%m%d_%H%M%S)
BACKUP_FILE="$BACKUP_DIR/recipe_app_backup_$TIMESTAMP.sql"

# Create the backup
echo "Creating database backup..."
pg_dump -U postgres -d Group6 > $BACKUP_FILE

# Check if backup was successful
if [ $? -eq 0 ]; then
  echo "Backup created successfully: $BACKUP_FILE"
  
  # Get the file size
  FILE_SIZE=$(du -h "$BACKUP_FILE" | cut -f1)
  echo "Backup size: $FILE_SIZE"
else
  echo "Error: Backup failed"
  exit 1
fi
