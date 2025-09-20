# API Documentation

## Base URL
- **Google App Engine**: https://YOUR_PROJECT_ID.appspot.com

## API Endpoints

### Contact Form
**POST /api/contact**
```javascript
$.ajax({
    url: 'https://YOUR_PROJECT_ID.appspot.com/api/contact',
    type: 'POST',
    data: $('#contactForm').serialize(),
    success: function(response) {
        if (response.success) {
            alert('Message sent successfully!');
            $('#contactForm')[0].reset();
        }
    },
    error: function() {
        alert('Failed to send message');
    }
});
```

**Parameters**:
- `name` (string): User name
- `email` (string): User email  
- `message` (string): Message content

### Email Subscription
**POST /api/subscribe**
```javascript
$.ajax({
    url: 'https://YOUR_PROJECT_ID.appspot.com/api/subscribe',
    type: 'POST',
    data: $('#subscribeForm').serialize(),
    success: function(response) {
        if (response.success) {
            alert('Subscription successful!');
            $('#subscribeForm')[0].reset();
        }
    },
    error: function() {
        alert('Subscription failed');
    }
});
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