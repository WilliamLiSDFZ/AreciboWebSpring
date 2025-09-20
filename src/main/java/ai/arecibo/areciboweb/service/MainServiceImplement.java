package ai.arecibo.areciboweb.service;

import ai.arecibo.areciboweb.dao.DatabaseController;
import ai.arecibo.areciboweb.entity.SubscribeResult;
import ai.arecibo.areciboweb.tool.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class MainServiceImplement implements MainService {

    @Autowired
    private DatabaseController databaseController;

    @Autowired
    private EmailService emailService;

    /**
     * Adds a message to the database and sends an email notification.
     * Both operations must succeed for the method to return true.
     *
     * @param name    the name of the sender; must not be null
     * @param email   the email address of the sender; must not be null
     * @param message the message content; must not be null
     * @return true if the message was successfully added to the database and the email was sent, false otherwise
     */
    @Override
    public boolean addMessage(String name, String email, String message) {
        if (name == null || email == null || message == null) {
            return false;
        }
        boolean result = databaseController.insertMessageToContact(name, email, message, LocalDateTime.now());
        
        // Try to send confirmation email to user, but don't fail the entire operation if email fails
        try {
            emailService.sendContactConfirmationEmail("team@arecibo.ai", email, name, email, message);
            System.out.println("Confirmation email sent successfully to: " + email);
        } catch (Exception e) {
            System.err.println("Failed to send confirmation email to " + email + ": " + e.getMessage());
        }
        
        // Send HTML notification emails to team members (commented out for now due to server issues)
        emailService.sendInternalNotificationEmail("team@arecibo.ai", "benny@arecibo.ai", name, email, message);
        emailService.sendInternalNotificationEmail("team@arecibo.ai", "sam@arecibo.ai", name, email, message);
        
        // Return true if database operation succeeded, regardless of email status
        return result;
    }

    @Override
    public SubscribeResult addSubscribe(String email) {
        if (email == null)
            return SubscribeResult.FAILED;
        boolean alreadySubscribed = databaseController.emailSubscribed(email);
        if (alreadySubscribed) {
            return SubscribeResult.ALREADY_SUBSCRIBED;
        }
        boolean result = databaseController.addSubscribe(email, LocalDateTime.now());
        
        // Try to send subscription confirmation email, but don't fail the entire operation if email fails
        try {
            emailService.sendSubscriptionConfirmationEmail("team@arecibo.ai", email, email);
            System.out.println("Subscription confirmation email sent successfully to: " + email);
        } catch (Exception e) {
            System.err.println("Failed to send subscription confirmation email to " + email + ": " + e.getMessage());
        }
        
        return result?SubscribeResult.SUCCESS:SubscribeResult.FAILED;
    }
}
