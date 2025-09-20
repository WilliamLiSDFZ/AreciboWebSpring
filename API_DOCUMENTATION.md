# API Documentation

## 🌐 Base URL
- **Google App Engine**: https://YOUR_PROJECT_ID.appspot.com

## 🔌 API Endpoints

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

## 📱 Frontend Integration

### HTML Forms
```html
<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Contact Form -->
<form id="contactForm">
    <input type="text" name="name" placeholder="Name" required>
    <input type="email" name="email" placeholder="Email" required>
    <textarea name="message" placeholder="Message" required></textarea>
    <button type="submit">Send Message</button>
</form>

<!-- Subscribe Form -->
<form id="subscribeForm">
    <input type="email" name="email" placeholder="Email Address" required>
    <button type="submit">Subscribe</button>
</form>
```

### jQuery Integration
```javascript
// Contact form submission
$('#contactForm').on('submit', function(e) {
    e.preventDefault();
    
    $.ajax({
        url: 'https://YOUR_PROJECT_ID.appspot.com/api/contact',
        type: 'POST',
        data: $(this).serialize(),
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
});

// Subscribe form submission  
$('#subscribeForm').on('submit', function(e) {
    e.preventDefault();
    
    $.ajax({
        url: 'https://YOUR_PROJECT_ID.appspot.com/api/subscribe',
        type: 'POST',
        data: $(this).serialize(),
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
});
```

## 📊 Response Format
```json
{
    "success": true,
    "message": "Message sent successfully"
}
```

## 🧪 Testing
```bash
# Test contact API
curl -X POST https://YOUR_PROJECT_ID.appspot.com/api/contact \
  -d "name=TestUser&email=test@example.com&message=Test message"

# Test subscription API
curl -X POST https://YOUR_PROJECT_ID.appspot.com/api/subscribe \
  -d "email=test@example.com"
```