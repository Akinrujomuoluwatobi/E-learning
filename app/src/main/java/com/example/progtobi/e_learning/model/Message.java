package com.example.progtobi.e_learning.model;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -4385311719464397125L;
	private int id;
	private int type;
	private int status;
	private int receiverid;
	private int senderid;
	private String sender;
	private String message;
	private String thumbnail;
	private String time;

	public Message() {

	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setReceiverId(int receiverid) {
		this.receiverid = receiverid;
	}

	public void setSenderId(int senderid) {
		this.senderid = senderid;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public int getStatus() {
		return status;
	}

	public int getReceiverId() {
		return receiverid;
	}

	public int getSenderId() {
		return senderid;
	}

	public String getSender() {
		return sender;
	}

	public String getMessage() {
		return message;
	}

	public String getTime() {
		return time;
	}

	public String getThumbnail() {
		return thumbnail;
	}
}
