package ai.arecibo.areciboweb.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface DatabaseController {
    boolean insertMessageToContact(String name, String email, String message, LocalDateTime time);

    boolean emailSubscribed(String email);

    boolean addSubscribe(String email, LocalDateTime time);
    
    // 新增的后台管理方法
    List<Map<String, Object>> getAllContacts(int offset, int limit);
    
    int getContactCount();
    
    List<Map<String, Object>> getAllSubscribers(int offset, int limit);
    
    int getSubscriberCount();
    
    boolean deleteContact(int contactId);
    
    boolean deleteSubscriber(int subscriberId);
    
    Map<String, Object> getContactById(int contactId);
    
    Map<String, Object> getSubscriberById(int subscriberId);
    
    // Passcode related methods
    boolean validatePasscode(String passcode);
    
    boolean updateLastLogin(String passcode, LocalDateTime loginTime);
    
    List<Map<String, Object>> getAllPasscodes(int offset, int limit);
    
    int getPasscodeCount();
    
    boolean addPasscode(String passcode, LocalDateTime time);
    
    boolean deletePasscode(int passcodeId);
}
