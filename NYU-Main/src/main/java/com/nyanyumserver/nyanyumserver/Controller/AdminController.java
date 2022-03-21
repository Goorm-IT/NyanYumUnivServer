package com.nyanyumserver.nyanyumserver.Controller;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@RestController
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "파이어베이스 테스트")
    @GetMapping("/firebase")
    public void getMessage(){
        GoogleCredential googleCredential = null;
        InputStream serviceAccount = null;
        String SCOPES = "https://www.googleapis.com/auth/firebase.messaging";
        try {
            ClassPathResource resource = new ClassPathResource("fcm.json");
            serviceAccount = resource.getInputStream();
            googleCredential = GoogleCredential
                    .fromStream(serviceAccount)
                    .createScoped(Arrays.asList(SCOPES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("token", "토큰정보");
        JsonObject notification = new JsonObject();
        notification.addProperty("body", "\uD83D\uDE03바디V1");
        notification.addProperty("title", "\uD83D\uDE03타이틀V1");
        jsonObj.add("notification", notification);
        JsonObject message = new JsonObject();
        message.add("message", jsonObj);
    }



}
