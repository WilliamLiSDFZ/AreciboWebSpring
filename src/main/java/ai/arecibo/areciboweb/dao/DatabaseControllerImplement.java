package ai.arecibo.areciboweb.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class DatabaseControllerImplement implements DatabaseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger;

    public DatabaseControllerImplement() {
        logger = LogManager.getLogger(DatabaseControllerImplement.class);
    }

    /**
     * Inserts a message into the contact database.
     *
     * @param name    the name of the contact person
     * @param email   the email address of the contact person
     * @param message the message content to be stored
     * @param time    the timestamp when the message was created
     * @return true if the message is successfully inserted, false otherwise
     */
    @Override
    public boolean insertMessageToContact(String name, String email, String message, LocalDateTime time) {
        try {
            jdbcTemplate.update("INSERT INTO contact (name, email, message, time) VALUES (?, ?, ?, ?)", name, email, message, time);
        }catch (Exception e) {
            logger.error("Error inserting message to database", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean emailSubscribed(String email) {
        Integer count;
        try {
            count = jdbcTemplate.queryForObject("select count(*) from subscribe where email=?", Integer.class, email);
        } catch (Exception e) {
            logger.error("Error inserting message to database", e);
            return false;
        }
        return count >= 1;
    }

    @Override
    public boolean addSubscribe(String email, LocalDateTime time) {
        try {
            jdbcTemplate.update("INSERT INTO subscribe (email, time) VALUES (?, ?)", email, time);
        } catch (Exception e) {
            logger.error("Error inserting subscribe to database", e);
            return false;
        }
        return true;
    }

    // 新增的后台管理方法实现
    @Override
    public List<Map<String, Object>> getAllContacts(int offset, int limit) {
        try {
            return jdbcTemplate.queryForList(
                "SELECT contact_id, name, email, message, time FROM contact ORDER BY time DESC LIMIT ? OFFSET ?", 
                limit, offset
            );
        } catch (Exception e) {
            logger.error("Error getting contacts from database", e);
            return List.of();
        }
    }

    @Override
    public int getContactCount() {
        try {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM contact", Integer.class);
            return count != null ? count : 0;
        } catch (Exception e) {
            logger.error("Error getting contact count from database", e);
            return 0;
        }
    }

    @Override
    public List<Map<String, Object>> getAllSubscribers(int offset, int limit) {
        try {
            return jdbcTemplate.queryForList(
                "SELECT subscribe_id, email, time FROM subscribe ORDER BY time DESC LIMIT ? OFFSET ?", 
                limit, offset
            );
        } catch (Exception e) {
            logger.error("Error getting subscribers from database", e);
            return List.of();
        }
    }

    @Override
    public int getSubscriberCount() {
        try {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM subscribe", Integer.class);
            return count != null ? count : 0;
        } catch (Exception e) {
            logger.error("Error getting subscriber count from database", e);
            return 0;
        }
    }

    @Override
    public boolean deleteContact(int contactId) {
        try {
            int rowsAffected = jdbcTemplate.update("DELETE FROM contact WHERE contact_id = ?", contactId);
            return rowsAffected > 0;
        } catch (Exception e) {
            logger.error("Error deleting contact from database", e);
            return false;
        }
    }

    @Override
    public boolean deleteSubscriber(int subscriberId) {
        try {
            int rowsAffected = jdbcTemplate.update("DELETE FROM subscribe WHERE subscribe_id = ?", subscriberId);
            return rowsAffected > 0;
        } catch (Exception e) {
            logger.error("Error deleting subscriber from database", e);
            return false;
        }
    }

    @Override
    public Map<String, Object> getContactById(int contactId) {
        try {
            List<Map<String, Object>> results = jdbcTemplate.queryForList(
                "SELECT contact_id, name, email, message, time FROM contact WHERE contact_id = ?", 
                contactId
            );
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            logger.error("Error getting contact by id from database", e);
            return null;
        }
    }

    @Override
    public Map<String, Object> getSubscriberById(int subscriberId) {
        try {
            List<Map<String, Object>> results = jdbcTemplate.queryForList(
                "SELECT subscribe_id, email, time FROM subscribe WHERE subscribe_id = ?", 
                subscriberId
            );
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            logger.error("Error getting subscriber by id from database", e);
            return null;
        }
    }
}
