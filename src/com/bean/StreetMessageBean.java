package com.bean;

import java.io.Serializable;

public class StreetMessageBean implements Serializable  {
	/**
	 * ���л���
	 */
	private static final long serialVersionUID = 1L;
	private String messageId;
	private String address;
	private String userName;
	private String message;
	private int comments;
	private String datetime;
	private String tag;	//��ǩ
	private float price;
	private String userID;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	public String getMessageId() {
		return messageId;
	}
	
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getComments() {
		return comments;
	}
	
	public void setComments(int comments) {
		this.comments = comments;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tags) {
		this.tag = tags;
	}
	
}
