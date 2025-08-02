package ai.arecibo.areciboweb.controller;

import ai.arecibo.areciboweb.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:63343", allowCredentials = "true")
public class ContactUsController {

    @Autowired
    private MainService mainService;

    @PostMapping
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

    @GetMapping
    public Map<String, String> handleContactGet(@RequestParam String name,
                                              @RequestParam String email,
                                              @RequestParam String message) {
        return handleContact(name, email, message);
    }
} 