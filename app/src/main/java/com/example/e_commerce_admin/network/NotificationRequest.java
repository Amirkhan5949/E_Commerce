package com.example.e_commerce_admin.network;


import com.example.e_commerce_admin.model.notificationrequest.NotificationBody;
import com.example.e_commerce_admin.model.notificationresponse.NotificationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NotificationRequest {

    @Headers({"Content-Type: application/json","Authorization: key=AAAAvPJBmH4:APA91bG30L55CfqNhXkyrZ14nbLD5KIwpVKP3J-RJldQnADOnc8DVxYE8u7eEhlY3JHSG5_bnTpS2yDgXXhDNInSv67DJU2wLwHzfjKiHiMnZTY0FYzNFekrkld6mV--Y-jIGYshUr9e"})
    @POST("send")
    Call<NotificationResponse> sent(@Body NotificationBody body);
}
