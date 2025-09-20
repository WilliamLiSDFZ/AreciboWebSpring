# Arecibo AI Backend Management System

A simple web application for contact form processing, email subscriptions, and admin management.

## ✨ Features
- Contact form processing
- Email subscription management  
- Admin dashboard
- Automatic email notifications

## 🚀 Quick Deploy to Google App Engine

### 1. Setup Google Cloud
```bash
# Install Google Cloud SDK
curl https://sdk.cloud.google.com | bash
exec -l $SHELL

# Login and set project
gcloud auth login
gcloud config set project YOUR_PROJECT_ID

# Enable APIs
gcloud services enable appengine.googleapis.com
gcloud services enable sqladmin.googleapis.com
```

### 2. Create Database
```bash
# Create Cloud SQL instance
gcloud sql instances create arecibo-mysql \
    --database-version=MYSQL_8_0 \
    --tier=db-f1-micro \
    --region=us-central1

# Create database and user
gcloud sql databases create arecibo_web --instance=arecibo-mysql
gcloud sql users create arecibo_user --instance=arecibo-mysql --password=YOUR_PASSWORD
```

### 3. Configure Database
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

### 4. Deploy
```bash
gcloud app deploy
```

## 🌐 Access Your App

After deployment, your app will be available at:
- **Homepage**: https://YOUR_PROJECT_ID.appspot.com
- **Admin Dashboard**: https://YOUR_PROJECT_ID.appspot.com/admin
- **Default Admin Password**: `arecibo_admin_2025`

## 📱 API Usage

### Contact Form
```javascript
fetch('https://YOUR_PROJECT_ID.appspot.com/api/contact', {
    method: 'POST',
    body: new FormData(contactForm)
});
```

### Email Subscription
```javascript
fetch('https://YOUR_PROJECT_ID.appspot.com/api/subscribe', {
    method: 'POST', 
    body: new FormData(subscribeForm)
});
```

## 🔧 Setup Database Tables

Connect to your Cloud SQL instance and run:

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

That's it! Your app is ready to use.
