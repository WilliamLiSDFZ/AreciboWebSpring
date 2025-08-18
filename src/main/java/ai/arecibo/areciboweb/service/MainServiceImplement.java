package ai.arecibo.areciboweb.service;

import ai.arecibo.areciboweb.dao.DatabaseController;
import ai.arecibo.areciboweb.tool.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MainServiceImplement implements MainService{

    @Autowired
    private DatabaseController databaseController;

    @Autowired
    private EmailService emailService;

    /**
     * Adds a message to the database and sends an email notification.
     * Both operations must succeed for the method to return true.
     *
     * @param name the name of the sender; must not be null
     * @param email the email address of the sender; must not be null
     * @param message the message content; must not be null
     * @return true if the message was successfully added to the database and the email was sent, false otherwise
     */
    @Override
    public boolean addMessage(String name, String email, String message) {
        if (name == null || email == null || message == null) {
            return false;
        }
        boolean result = databaseController.insertMessageToContact(name, email, message, LocalDateTime.now());
        String messageStr = "Thank you for contacting us. We will get back to you as soon as possible.\nPlease do not reply to this email.";
        boolean sendMessageResult = emailService.sendEmail("team@arecibo.ai", email, messageStr, name) == 1;
        emailService.sendEmail("team@arecibo.ai", "benny@arecibo.ai", message, name);
        emailService.sendEmail("team@arecibo.ai","sam@arecibo.ai", message, name);
        return result && sendMessageResult;
    }

    @Override
    public boolean addSubscribe(String email) {
        if (email == null) return false;
        boolean result = databaseController.addSubscribe(email, LocalDateTime.now());
        return result;
    }
}
