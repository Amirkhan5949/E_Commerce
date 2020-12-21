package com.example.e_commerce_admin.model.notificationrequest;

import com.google.gson.annotations.SerializedName;

public class NotificationBody{

	@SerializedName("notification")
	private Notification notification;

	@SerializedName("to")
	private String to;

	public void setNotification(Notification notification){
		this.notification = notification;
	}

	public Notification getNotification(){
		return notification;
	}

	public void setTo(String to){
		this.to = to;
	}

	public String getTo(){
		return to;
	}

	@Override
 	public String toString(){
		return 
			"NotificationBody{" + 
			"notification = '" + notification + '\'' + 
			",to = '" + to + '\'' + 
			"}";
		}
}