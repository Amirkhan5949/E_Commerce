package com.example.e_commerce_admin.model.notificationresponse;

import com.google.gson.annotations.SerializedName;

public class NotificationResponse{

	@SerializedName("message_id")
	private long messageId;

	public void setMessageId(long messageId){
		this.messageId = messageId;
	}

	public long getMessageId(){
		return messageId;
	}

	@Override
 	public String toString(){
		return 
			"NotificationResponse{" + 
			"message_id = '" + messageId + '\'' + 
			"}";
		}
}