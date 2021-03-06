package com.project.forum.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import com.project.forum.model.Message;
import com.project.forum.model.Topic;
import com.project.forum.model.User;
import com.project.forum.repo.Repository;

@RunWith(Parameterized.class)
public class DeleteMessageTest {
	/**
	 * Unit test for Delete Message Feature. Only Admins can delete Messages
	 */
	@Parameters(name = "{index}: with Message={0} user= {1} and expected result={2}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{  null,null, "Input parameters cant be null!" },
				{  new Message("text","username"),null, "Input parameters cant be null!" }, 
				{  null,new User("username","password","role_admin"), "Input parameters cant be null!" }, 
				 { new Message("text","username"),new User("username","password","role_user"), "Users cant delete Messages. Admin rights required!" }, 
				 { new Message("text","username"),new User("someone","password","role_admin"),  "Message has been deleted successfully"}
				 

		});
	}
	@Parameter(0)
	public Message message;
	@Parameter(1)
	public User user;
	@Parameter(2)
	public String expectedResult;
	private Services DeleteMessageService;
	private Repository repo;
	/**
	 * Initialization
	 */
	@Before
	public void setup() {
		repo = new Repository();
		DeleteMessageService = new Services(repo);
		Message message = new Message("text","userame");
		 
	}
	
	/**
	 * Checks Delete Message feature with null entries, critical entries - user trying to delete a message, and correct entries
	 */
	@Test
	public void testDeleteTopic() {
		final String result = DeleteMessageService.DeleteMessage(message, user);
		assertEquals(expectedResult, result);
	}

}
