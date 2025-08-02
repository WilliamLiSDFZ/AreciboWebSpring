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
        boolean sendMessageResult = emailService.sendEmail("team@arecibo.ai", email, message, name) == 1;
        return result && sendMessageResult;
    }
}
