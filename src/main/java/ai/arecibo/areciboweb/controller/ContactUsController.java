package ai.arecibo.areciboweb.controller;

import ai.arecibo.areciboweb.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:63343", allowCredentials = "true")
public class ContactUsController {

    @Autowired
    private MainService mainService;

    @PostMapping("/contact")
    @ResponseBody
    public Map<String, String> handleContact(@RequestParam String name,
                                           @RequestParam String email,
                                           @RequestParam String message) {
        Map<String, String> response = new HashMap<>();
        boolean result = mainService.addMessage(name, email, message);
        
        if (result) {
            response.put("result", "success");
        } else {
            response.put("result", "error");
        }
        return response;
    }

    @GetMapping("/contact")
    @ResponseBody
    public Map<String, String> handleContactGet(@RequestParam String name,
                                              @RequestParam String email,
                                              @RequestParam String message) {
        return handleContact(name, email, message);
    }
} 