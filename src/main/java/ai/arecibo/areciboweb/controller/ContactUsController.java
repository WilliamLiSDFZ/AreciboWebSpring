package ai.arecibo.areciboweb.controller;

import ai.arecibo.areciboweb.entity.ContactRequest;
import ai.arecibo.areciboweb.service.MainService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
//@CrossOrigin(origins = "http://localhost", allowCredentials = "true")
@RequestMapping("/api")
public class ContactUsController {

    @Autowired
    private MainService mainService;

    @PostMapping("/contact")
    @ResponseBody
    public Map<String, Object> handleContact(@RequestBody ContactRequest contactRequest) throws IOException {
        Map<String, Object> response = new HashMap<>();

        String googleResponse = Request.post("https://www.google.com/recaptcha/api/siteverify")
                .bodyForm(Form.form()
                        .add("secret", "6LdLkKArAAAAAJcY-4H-bofOFZO8pxHEWRo82cpQ")
                        .add("response", contactRequest.getToken())
                        .build())
                .execute().returnContent().asString();
        JsonObject recaptcha_json = JsonParser.parseString(googleResponse).getAsJsonObject();
        System.out.println(recaptcha_json);

        if (!recaptcha_json.get("success").getAsBoolean()) {
            response.put("code",2);
            response.put("result", "reCaptcha not verified");
            return response;
        }

        boolean result = mainService.addMessage(contactRequest.getName(), contactRequest.getEmail(), contactRequest.getMessage());
        
        if (result) {
            response.put("code",0);
            response.put("result", "success");
        } else {
            response.put("code",1);
            response.put("result", "error");
        }
        return response;
    }

    @GetMapping("/contact")
    @ResponseBody
    public Map<String, Object> handleContactGet(@RequestBody ContactRequest contactRequest) throws IOException {
        return handleContact(contactRequest);
    }
} 