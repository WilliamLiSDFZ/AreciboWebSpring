package ai.arecibo.areciboweb.tool;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Sends an email with the specified content and sender details.
     *
     * @param from the sender's email address; used for authentication
     * @param to the recipient's email address
     * @param messageStr the content of the email message
     * @param name the sender's name to include in the message content
     * @return 1 if the email was sent successfully, 0 if an error occurred
     */
    public int sendEmail(String from, String to, String messageStr, String name) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("hello");
            helper.setText("This is a test. \n Received message from " + name + " with message :" + messageStr);
            
            javaMailSender.send(message);
            return 1;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return 0;
        }
    }
} 