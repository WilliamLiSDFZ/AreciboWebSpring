package ai.arecibo.areciboweb.service;

import ai.arecibo.areciboweb.dao.DatabaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImplement implements AdminService {

    @Autowired
    private DatabaseController databaseController;

    @Override
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        int contactCount = databaseController.getContactCount();
        int subscriberCount = databaseController.getSubscriberCount();
        
        stats.put("totalContacts", contactCount);
        stats.put("totalSubscribers", subscriberCount);
        stats.put("lastUpdate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        return stats;
    }

    @Override
    public List<Map<String, Object>> getContacts(int page, int size) {
        int offset = (page - 1) * size;
        return databaseController.getAllContacts(offset, size);
    }

    @Override
    public int getContactCount() {
        return databaseController.getContactCount();
    }

    @Override
    public List<Map<String, Object>> getSubscribers(int page, int size) {
        int offset = (page - 1) * size;
        return databaseController.getAllSubscribers(offset, size);
    }

    @Override
    public int getSubscriberCount() {
        return databaseController.getSubscriberCount();
    }

    @Override
    public boolean deleteContact(int contactId) {
        return databaseController.deleteContact(contactId);
    }

    @Override
    public boolean deleteSubscriber(int subscriberId) {
        return databaseController.deleteSubscriber(subscriberId);
    }

    @Override
    public String exportContactsToCsv() {
        List<Map<String, Object>> contacts = databaseController.getAllContacts(0, Integer.MAX_VALUE);
        
        StringBuilder csv = new StringBuilder();
        csv.append("ID,Name,Email,Message,Time\n");
        
        for (Map<String, Object> contact : contacts) {
            csv.append(contact.get("contact_id")).append(",");
            csv.append("\"").append(contact.get("name")).append("\",");
            csv.append("\"").append(contact.get("email")).append("\",");
            csv.append("\"").append(contact.get("message").toString().replace("\"", "\"\"")).append("\",");
            csv.append(contact.get("time")).append("\n");
        }
        
        return csv.toString();
    }

    @Override
    public String exportSubscribersToCsv() {
        List<Map<String, Object>> subscribers = databaseController.getAllSubscribers(0, Integer.MAX_VALUE);
        
        StringBuilder csv = new StringBuilder();
        csv.append("ID,Email,Time\n");
        
        for (Map<String, Object> subscriber : subscribers) {
            csv.append(subscriber.get("subscribe_id")).append(",");
            csv.append("\"").append(subscriber.get("email")).append("\",");
            csv.append(subscriber.get("time")).append("\n");
        }
        
        return csv.toString();
    }
}
