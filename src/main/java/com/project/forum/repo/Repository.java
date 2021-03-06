package com.project.forum.repo;

import java.util.ArrayList;
import java.util.List;

import com.project.forum.model.Message;
import com.project.forum.model.Topic;
import com.project.forum.model.User;

/**
 * @author Vasil
 * Contains methods used for initializing, accessing and processing the stored project model data
 *
 */
public class Repository {
	private List<User> Users = new ArrayList<User>();
	private List<Message> Messages = new ArrayList<Message>();
	private List<Topic> Topics = new ArrayList<Topic>();
	
	
	/**
	 * Initializes the model with sample data
	 */
	public Repository() {
		Users.add(new User("username", "password", "role_user"));
		Users.add(new User("admin", "pass", "role_admin"));
		Messages.add(new Message("sample text","username"));
		Messages.add(new Message("sample text2","username"));
		Messages.add(new Message("sample text3","admin"));
		Topics.add(new Topic("This is a sample topic desc",Messages));
	}
	
	/**
	 * @return returns all saved users
	 */
	public List<User> GetAllUsers() {
		return Users;
		
	}
	
	/**
	 * @return returns all saved Messages
	 */
	public List<Message> GetAllMessages(){
		return Messages;
	}
	public List<Topic> GetAllTopics(){
		return Topics;
	}

	/**
	 * Deletes a message from the local store
	 * @param message Identifies the message object to be deleted.
	 * 
	 */
	public void RemoveMessage(Message message) {
		
			 Messages.remove(message);
	 
	}
	/**
	 * Deletes a topic from the local store. It deletes all  messages related to the topic as well
	 * @param topic Identifies the topic object to be deleted.
	 * 
	 */
	public void RemoveTopic(Topic topic) {
		
			Topics.remove(topic);
				
	}
	/** Retrieves a single Topic object based on its description
	 * @param description Used to search for a topic object containing the provided description
	 * @return null - if no topics have been found
	 * @return topic - if a matching topic object is found
	 */
	public Topic getTopicByDescription(String description) {
		for (Topic topic : Topics) {
			if(topic.getDescription().equals(description)) {
				return topic;
			}
		}
		return null;
		
	}
	/**Retrieves a single Message object based on its text field
	 * @param text Used to search for a message object containing the provided text
	 * @return if no messages have been found
	 * @return message - if a matching message is found
	 */
	public Message getMessageByText(String text) {
		for (Message message : Messages) {
			if(message.getText().equals(text)) {
				return message;
			}
		}
		return null;
		
	}
	/**
	 * Saves a user-generated message to the local store
	 * @param message 
	 * @return true or false - whether the message has been saved successfully
	 */
	public void PostMessage(Message message) {
		Messages.add(message);
	}

}
