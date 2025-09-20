# Google App Engine Deployment Guide

## 🚀 Quick Setup

### 1. Setup Project
```bash
gcloud auth login
gcloud config set project YOUR_PROJECT_ID
gcloud services enable appengine.googleapis.com
```

### 2. Create MySQL Database on Google Cloud Platform
Go to Google Cloud Console and create a MySQL 8.0 database:

1. **Go to Cloud SQL**: https://console.cloud.google.com/sql
2. **Create Instance**: Click "Create Instance"
3. **Choose MySQL**: Select "MySQL"
4. **Version**: Choose MySQL 8.0
5. **Instance ID**: `arecibo-mysql`
6. **Password**: Set a secure password
7. **Region**: Choose `us-central1`
8. **Create**: Click "Create" button

### 3. Get Ready to Deploy
Open `application.yml` and change the `spring.datasource.url`, `spring.datasource.username` and `spring.datasource.password` to the new database credentials.

### 4. Deploy
```bash
gcloud app deploy
```

## 🔧 Setup Database Tables
Connect to your Cloud SQL MySQL instance and create the database:

1. **Connect to MySQL**: Use any MySQL client (MySQL Workbench, phpMyAdmin, or command line)
2. **Create Database**: 
   ```sql
   CREATE DATABASE areciboai;
   ```
3. **Use Database and Create Tables**:
   ```sql
   USE areciboai;
    
    create table contact
    (
    contact_id int auto_increment
        primary key,
    name       varchar(255) not null,
    email      varchar(255) not null,
    message    text         not null,
    time       timestamp    not null
    )
    engine = InnoDB;
    
    create table subscribe
    (
    subscribe_id int auto_increment
        primary key,
    email        varchar(255) not null,
    time         timestamp    not null
    )
    engine = InnoDB;
    
    create table passcode
    (
    passcode_id int auto_increment
        primary key,
    passcode    varchar(255) not null,
    time        timestamp    not null default current_timestamp(),
    last_loin   timestamp    null
    )
    engine = InnoDB;
    
    INSERT INTO passcode (passcode) VALUES ('arecibo_admin_2025');
    ```
