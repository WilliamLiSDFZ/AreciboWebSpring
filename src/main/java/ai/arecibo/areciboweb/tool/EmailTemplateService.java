package ai.arecibo.areciboweb.tool;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailTemplateService {

    private static final String TEMPLATE_FILE = "templates/email-templates.html";
    private static final Pattern TEMPLATE_PATTERN = Pattern.compile("id=\"([^\"]+)\"[^>]*>([\\s\\S]*?)</div>\\s*</div>\\s*</div>\\s*</body>");
    private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\{\\{([^}]+)\\}\\}");

    /**
     * Loads the HTML template file and extracts a specific template by ID
     */
    public String loadTemplate(String templateId) throws IOException {
        ClassPathResource resource = new ClassPathResource(TEMPLATE_FILE);
        String content = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        
        Matcher matcher = TEMPLATE_PATTERN.matcher(content);
        while (matcher.find()) {
            if (templateId.equals(matcher.group(1))) {
                return matcher.group(2);
            }
        }
        
        throw new IllegalArgumentException("Template with ID '" + templateId + "' not found");
    }

    /**
     * Replaces variables in the template with actual values
     */
    public String processTemplate(String template, Map<String, String> variables) {
        String result = template;
        Matcher matcher = VARIABLE_PATTERN.matcher(template);
        
        while (matcher.find()) {
            String variable = matcher.group(1);
            String value = variables.getOrDefault(variable, "");
            result = result.replace("{{" + variable + "}}", value);
        }
        
        return result;
    }

    /**
     * Generates a contact confirmation email HTML
     */
    public String generateContactConfirmationEmail(String name, String email, String message) throws IOException {
        Map<String, String> variables = new HashMap<>();
        variables.put("name", name != null ? name : "there");
        variables.put("email", email != null ? email : "");
        variables.put("message", message != null ? message : "");
        variables.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        String template = loadTemplate("contact-confirmation-template");
        return processTemplate(template, variables);
    }

    /**
     * Generates a subscription confirmation email HTML
     */
    public String generateSubscriptionConfirmationEmail(String email) throws IOException {
        Map<String, String> variables = new HashMap<>();
        variables.put("email", email != null ? email : "");
        variables.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        String template = loadTemplate("subscription-confirmation-template");
        return processTemplate(template, variables);
    }

    /**
     * Generates an internal notification email HTML
     */
    public String generateInternalNotificationEmail(String name, String email, String message) throws IOException {
        Map<String, String> variables = new HashMap<>();
        variables.put("name", name != null ? name : "Unknown");
        variables.put("email", email != null ? email : "");
        variables.put("message", message != null ? message : "");
        variables.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        String template = loadTemplate("internal-notification-template");
        return processTemplate(template, variables);
    }
}
