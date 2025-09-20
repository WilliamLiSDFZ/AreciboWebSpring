# Arecibo AI Backend Management System

A simple backend application for contact form processing, email subscriptions, and admin management.

## ✨ Features
- Contact form processing with Google reCAPTCHA protection
- Email subscription management  
- Admin dashboard
- Automatic email notifications
- Spam protection and security

## 🚀 Quick Deploy to Google App Engine

### 1. Setup Google Cloud
```bash
# Install Google Cloud SDK first

# Login and set project
gcloud auth login
gcloud config set project YOUR_PROJECT_ID # Remember this project ID

# Enable APIs
gcloud services enable appengine.googleapis.com
gcloud services enable sqladmin.googleapis.com
```

### 2. Create MySQL Database on Google Cloud Platform
Go to Google Cloud Console and create a MySQL 8.0 database:

1. **Go to Cloud SQL**: https://console.cloud.google.com/sql
2. **Create Instance**: Click "Create Instance"
3. **Choose MySQL**: Select "MySQL"
4. **Version**: Choose MySQL 8.0
5. **Instance ID**: `arecibo`
6. **Password**: Set a secure password
7. **Region**: Choose `us-central1`
8. **Create**: Click "Create"

### 3. Get Ready to Deploy
Open `application.yml` and change the `spring.datasource.url`, `spring.datasource.username` and `spring.datasource.password` to the new database credentials.

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

## 🔒 Google reCAPTCHA Setup

### 1. Get reCAPTCHA Keys
1. Go to Google reCAPTCHA Admin Console: https://www.google.com/recaptcha/admin
2. Click "Create" to add a new site
3. Choose "reCAPTCHA v2" → "I'm not a robot" Checkbox
4. Add your domain: `YOUR_PROJECT_ID.appspot.com`
5. Get your **Site Key** and **Secret Key**

### 2. Configure Backend
Update the `MainController` line 34 with new reCAPTCHA secret key:

### 3. Frontend Integration
Add reCAPTCHA to your contact form:
```html
<div class="g-recaptcha" data-sitekey="YOUR_KEY"></div>

<!-- Include reCAPTCHA script -->
<script src="https://www.google.com/recaptcha/api.js"></script>
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
