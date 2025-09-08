package ai.arecibo.areciboweb.service;

import java.util.List;
import java.util.Map;

public interface AdminService {
    /**
     * 获取系统统计数据
     */
    Map<String, Object> getStatistics();
    
    /**
     * 获取联系消息列表（分页）
     */
    List<Map<String, Object>> getContacts(int page, int size);
    
    /**
     * 获取联系消息总数
     */
    int getContactCount();
    
    /**
     * 获取订阅用户列表（分页）
     */
    List<Map<String, Object>> getSubscribers(int page, int size);
    
    /**
     * 获取订阅用户总数
     */
    int getSubscriberCount();
    
    /**
     * 删除联系消息
     */
    boolean deleteContact(int contactId);
    
    /**
     * 删除订阅用户
     */
    boolean deleteSubscriber(int subscriberId);
    
    /**
     * 导出联系消息为CSV格式
     */
    String exportContactsToCsv();
    
    /**
     * 导出订阅用户为CSV格式
     */
    String exportSubscribersToCsv();
}
