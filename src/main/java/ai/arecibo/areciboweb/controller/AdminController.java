package ai.arecibo.areciboweb.controller;

import ai.arecibo.areciboweb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 管理员登录页面
     */
    @GetMapping("/login")
    public String adminLogin() {
        return "admin/login";
    }

    /**
     * 后台管理首页
     */
    @GetMapping("")
    public String adminIndex(Model model) {
        // 获取统计数据
        Map<String, Object> stats = adminService.getStatistics();
        model.addAttribute("stats", stats);
        return "admin/index";
    }

    /**
     * 联系消息管理页面
     */
    @GetMapping("/contacts")
    public String contacts(@RequestParam(defaultValue = "1") int page, 
                          @RequestParam(defaultValue = "10") int size,
                          Model model) {
        List<Map<String, Object>> contacts = adminService.getContacts(page, size);
        int totalCount = adminService.getContactCount();
        int totalPages = (int) Math.ceil((double) totalCount / size);
        
        model.addAttribute("contacts", contacts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pageSize", size);
        
        return "admin/contacts";
    }

    /**
     * 订阅用户管理页面
     */
    @GetMapping("/subscribers")
    public String subscribers(@RequestParam(defaultValue = "1") int page, 
                             @RequestParam(defaultValue = "10") int size,
                             Model model) {
        List<Map<String, Object>> subscribers = adminService.getSubscribers(page, size);
        int totalCount = adminService.getSubscriberCount();
        int totalPages = (int) Math.ceil((double) totalCount / size);
        
        model.addAttribute("subscribers", subscribers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pageSize", size);
        
        return "admin/subscribers";
    }

    /**
     * 删除联系消息
     */
    @PostMapping("/contacts/{id}/delete")
    @ResponseBody
    public Map<String, Object> deleteContact(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        boolean success = adminService.deleteContact(id);
        
        if (success) {
            response.put("code", 0);
            response.put("message", "删除成功");
        } else {
            response.put("code", 1);
            response.put("message", "删除失败");
        }
        
        return response;
    }

    /**
     * 删除订阅用户
     */
    @PostMapping("/subscribers/{id}/delete")
    @ResponseBody
    public Map<String, Object> deleteSubscriber(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        boolean success = adminService.deleteSubscriber(id);
        
        if (success) {
            response.put("code", 0);
            response.put("message", "删除成功");
        } else {
            response.put("code", 1);
            response.put("message", "删除失败");
        }
        
        return response;
    }

    /**
     * 导出联系消息
     */
    @GetMapping("/contacts/export")
    @ResponseBody
    public String exportContacts() {
        return adminService.exportContactsToCsv();
    }

    /**
     * 导出订阅用户
     */
    @GetMapping("/subscribers/export")
    @ResponseBody
    public String exportSubscribers() {
        return adminService.exportSubscribersToCsv();
    }
}
