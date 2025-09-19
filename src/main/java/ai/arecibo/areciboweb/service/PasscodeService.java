package ai.arecibo.areciboweb.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface PasscodeService {
    /**
     * 验证passcode是否有效
     */
    boolean validatePasscode(String passcode);
    
    /**
     * 更新最后登录时间
     */
    boolean updateLastLogin(String passcode);
    
    /**
     * 获取所有passcode列表（分页）
     */
    List<Map<String, Object>> getPasscodes(int page, int size);
    
    /**
     * 获取passcode总数
     */
    int getPasscodeCount();
    
    /**
     * 添加新的passcode
     */
    boolean addPasscode(String passcode);
    
    /**
     * 删除passcode
     */
    boolean deletePasscode(int passcodeId);
}
