package com.project.forum.model;

public class Message {
	public Message(String text, String author) {
		this.text = text;
		this.author=author;
	}
	private String text;
	private String author;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

}
