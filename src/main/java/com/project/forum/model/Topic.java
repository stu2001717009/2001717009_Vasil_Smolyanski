package com.project.forum.model;

import java.util.List;

public class Topic {
	public Topic(String description,List<Message> messages) {
		this.description = description;
		this.messages = messages;
		
	}
	private String description;
	List<Message> messages;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
