package com.fynuls.utils;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class NotificationSender {
    public static boolean sendNotification(String fcmToken, String title, String body) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://exp.host/--/api/v2/push/send");

        // Set the request headers
        httpPost.setHeader("Content-Type", "application/json");

        // Create the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("to", fcmToken);
        requestBody.put("title", title);
        requestBody.put("body", body);

        try {
            // Convert the request body to JSON string
            String jsonBody = new Gson().toJson(requestBody);

            // Set the request body
            StringEntity stringEntity = new StringEntity(jsonBody);
            httpPost.setEntity(stringEntity);

            // Execute the request
            HttpResponse response = httpClient.execute(httpPost);

            // Check the response status code
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                // Success: Notification delivered
                return true;
            } else {
                // Failure: Notification delivery failed
                return false;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
