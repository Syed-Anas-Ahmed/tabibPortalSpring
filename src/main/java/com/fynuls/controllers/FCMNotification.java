package com.fynuls.controllers;

import com.fynuls.dal.Response;
import com.fynuls.utils.HibernateUtil;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class FCMNotification {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/"+Codes.API_UPDATEFCMTOKEN, method = RequestMethod.GET, params = {"fcmToken","token"})
    @ResponseBody
    public String updateFCMToken(String fcmToken, String token) {

        String query = "UPDATE LOGINSTATUS set FCMTOKEN='"+fcmToken+"' where TOKEN='"+token+"'";
        boolean isSuccessful = HibernateUtil.executeQuery(query);
        Response response = new Response();
        if(isSuccessful){
            response.setStatus(Codes.ALL_OK);
        }else{
            response.setStatus(Codes.SOMETHING_WENT_WRONG);
        }
        return new Gson().toJson(response);
    }

    public String sendNotification(String token, String subject, String content, String jsonFile, String appName) throws FirebaseMessagingException {
        FirebaseMessaging fcm = null;
        try {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new ClassPathResource(jsonFile).getInputStream());
            FirebaseOptions firebaseOptions = FirebaseOptions
                    .builder()
                    .setCredentials(googleCredentials)
                    .build();
            List<FirebaseApp> apps = FirebaseApp.getApps();
            if(apps!=null){
                for(FirebaseApp app :apps){
                    if(appName.equals(app.getName())){
                        fcm = FirebaseMessaging.getInstance(app);
                    }
                }
            }
            if(fcm==null){
                FirebaseApp newApp = FirebaseApp.initializeApp(firebaseOptions, appName);
                fcm = FirebaseMessaging.getInstance(newApp);
            }


        }catch (Exception ex){
int i=9;
i=8;
        }
        Notification notification = Notification
                .builder()
                .setTitle(subject)
                .setBody(content)
                .build();

        HashMap<String, String> data = new HashMap<>();
        data.put("data1","data1");
        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .putAllData(data)
                .build();

        return fcm.send(message);
    }
}
