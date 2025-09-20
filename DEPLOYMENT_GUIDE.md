# Google App Engine Deployment Guide

## 🚀 Quick Setup

### 1. Install Google Cloud SDK
```bash
curl https://sdk.cloud.google.com | bash
exec -l $SHELL
```

### 2. Setup Project
```bash
gcloud auth login
gcloud config set project YOUR_PROJECT_ID
gcloud services enable appengine.googleapis.com
```

### 4. Configure Database
Update `src/main/resources/application-cloud.yml` with your details (app.yaml is already configured):
```yaml
spring:
  datasource:
    url: jdbc:mysql://google/arecibo_web?cloudSqlInstance=/cloudsql/YOUR_PROJECT_ID:us-central1:arecibo-mysql&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false
    username: arecibo_user
    password: YOUR_PASSWORD
  
  mail:
    username: your_email@gmail.com
    password: YOUR_GMAIL_APP_PASSWORD
```

### 5. Deploy
```bash
gcloud app deploy
```

## 🔧 Setup Database Tables
Connect to Cloud SQL and run:
```sql
USE arecibo_web;

CREATE TABLE contact (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE subscribe (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE passcode (
    passcode_id INT AUTO_INCREMENT PRIMARY KEY,
    passcode VARCHAR(100) NOT NULL UNIQUE,
    time DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_login DATETIME NULL
);

INSERT INTO passcode (passcode) VALUES ('arecibo_admin_2025');
```

## 🌐 Access Your App
- **Homepage**: https://YOUR_PROJECT_ID.appspot.com
- **Admin**: https://YOUR_PROJECT_ID.appspot.com/admin
- **Password**: `arecibo_admin_2025`
