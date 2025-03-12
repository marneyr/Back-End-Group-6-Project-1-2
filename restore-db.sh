#!/bin/bash

# Check if a backup file is provided
if [ -z "$1" ]; then
  echo "Error: No backup file specified"
  echo "Usage: $0 <backup_file.sql>"
  exit 1
fi

BACKUP_FILE=$1

# Check if the backup file exists
if [ ! -f "$BACKUP_FILE" ]; then
  echo "Error: Backup file '$BACKUP_FILE' not found"
  exit 1
fi

# Confirm restoration
echo "WARNING: This will overwrite the current 'Group6' database!"
read -p "Do you want to proceed? (y/n): " -n 1 -r
echo
if [[ ! $REPLY =~ ^[Yy]$ ]]; then
  echo "Restoration cancelled"
  exit 1
fi

# Drop and recreate the database
echo "Recreating database..."
psql -U postgres -c "DROP DATABASE IF EXISTS \"Group6\";" &&
psql -U postgres -c "CREATE DATABASE \"Group6\";"

# Restore from backup
echo "Restoring from backup: $BACKUP_FILE"
psql -U postgres -d Group6 < "$BACKUP_FILE"

# Check if restore was successful
if [ $? -eq 0 ]; then
  echo "Database restored successfully!"
else
  echo "Error: Database restoration failed"
  exit 1
fi
