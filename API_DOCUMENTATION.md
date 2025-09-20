# API Documentation

## Base URL
- **Google App Engine**: https://YOUR_PROJECT_ID.appspot.com

## API Endpoints

### Contact Form
**POST /api/contact**
```javascript
$$.ajax({
    type: "POST",
    url: "https://YOUR_PROJECT_ID.appspot.com/api/contact",
    contentType: "application/json",
    dataType: "json",
    data: JSON.stringify({
        name: $("#contact-name").val(),
        email: $("#contact-email").val(),
        message: $("#contact-message").val(),
        token: token
    }),
    success: function (data) {
        
    },
    error: function (data) {
        
    }
})
```

**Parameters**:
- `name` (string): User name
- `email` (string): User email  
- `message` (string): Message content

### Email Subscription
**POST /api/subscribe**
```javascript
$.ajax({
    type: "POST",
    url: "https://YOUR_PROJECT_ID.appspot.com/api/subscribe",
    contentType: "application/json",
    dataType: "json",
    data: JSON.stringify({
        email: email
    }),
    success: function (data) {
        
    },
    error: function (data) {
        
    }
})
```

**Parameters**:
- `email` (string): Subscription email

## Response Format
```json
{
    "success": true,
    "message": "Message sent successfully"
}
```