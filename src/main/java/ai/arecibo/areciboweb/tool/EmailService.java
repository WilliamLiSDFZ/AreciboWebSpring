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

    @Autowired
    private EmailTemplateService emailTemplateService;

    /**
     * Sends an email with the specified content and sender details.
     *
     * @param from       the sender's email address; used for authentication
     * @param to         the recipient's email address
     * @param messageStr the content of the email message
     * @param name       the sender's name to include in the message content
     * @return 1 if the email was sent successfully, 0 if an error occurred
     */
    public int sendEmail(String from, String to, String subject, String messageStr, String name) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText("Hi " + name + ", \n" + messageStr);

            javaMailSender.send(message);
            return 1;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return 0;
        }
    }

    /**
     * Sends an HTML email using the specified template.
     *
     * @param from       the sender's email address
     * @param to         the recipient's email address
     * @param subject    the email subject
     * @param htmlContent the HTML content of the email
     * @return 1 if the email was sent successfully, 0 if an error occurred
     */
    public int sendHtmlEmail(String from, String to, String subject, String htmlContent) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true indicates HTML content

            javaMailSender.send(message);
            return 1;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return 0;
        }
    }

    /**
     * Sends a contact confirmation email with HTML template.
     */
    public int sendContactConfirmationEmail(String from, String to, String name, String email, String message) {
        try {
            String htmlContent = emailTemplateService.generateContactConfirmationEmail(name, email, message);
            return sendHtmlEmail(from, to, "Thank you for contacting Arecibo AI", htmlContent);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Sends a subscription confirmation email with HTML template.
     */
    public int sendSubscriptionConfirmationEmail(String from, String to, String email) {
        try {
            String htmlContent = emailTemplateService.generateSubscriptionConfirmationEmail(email);
            return sendHtmlEmail(from, to, "Welcome to Arecibo AI Newsletter", htmlContent);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Sends an internal notification email with HTML template.
     */
    public int sendInternalNotificationEmail(String from, String to, String name, String email, String message) {
        try {
            String htmlContent = emailTemplateService.generateInternalNotificationEmail(name, email, message);
            return sendHtmlEmail(from, to, "New Contact Form Submission - Arecibo AI", htmlContent);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
} 