package ai.arecibo.areciboweb.controller;

import ai.arecibo.areciboweb.entity.ContactRequest;
import ai.arecibo.areciboweb.entity.SubscribeRequest;
import ai.arecibo.areciboweb.entity.SubscribeResult;
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
@CrossOrigin(origins = "https://arecibo.ai")
@RequestMapping("/api")
public class MainController {

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

    @PostMapping("/subscribe")
    @ResponseBody
    public Map<String, Object> handleSubscribe(@RequestBody SubscribeRequest request) {
        Map<String, Object> response = new HashMap<>();

        String name = request.getName();
        if (name != null) {
            response.put("code",3);
            response.put("result", "spam");
            return response;
        }

        SubscribeResult result = mainService.addSubscribe(request.getEmail());

        switch (result){
            case SUCCESS:
                response.put("code",0);
                response.put("result", "success");
                break;
            case FAILED:
                response.put("code",1);
                response.put("result", "error");
                break;
            case ALREADY_SUBSCRIBED:
                response.put("code",2);
                response.put("result", "already subscribed");
                break;
        }
        return response;
    }
} 