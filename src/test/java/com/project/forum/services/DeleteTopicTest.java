package com.project.forum.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
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

/**
 * @author Vasil
 *  Tests the Delete topic feature. Only admins can delete topics
 */
@RunWith(Parameterized.class)
public class DeleteTopicTest {
	
	@Parameters(name = "{index}: with topic={0} user= {1} and expected result={2}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{  null,null, "Input parameters cant be null!" },
				{  new Topic("desc",null),null, "Input parameters cant be null!" }, 
				{  null,new User("username","password","role_admin"), "Input parameters cant be null!" }, 
				 { new Topic("desc",null),new User("username","password","role_user"), "Users cant delete Topics. Admin rights required!" }, 
				 { new Topic("desc",null),new User("someone","password","role_admin"),  "Topic has been deleted successfully"}
				 

		});
	}
	@Parameter(0)
	public Topic topic;
	@Parameter(1)
	public User user;
	@Parameter(2)
	public String expectedResult;
	private Services DeleteTopicService;
	private Repository repo;
	/**
	 * Initialization
	 */
	@Before
	public void setup() {
		repo = new Repository();
		DeleteTopicService = new Services(repo);
	}
	/**
	 * 	 Checks Delete Topic feature with null entries, critical entries - user trying to delete a message, and correct entries

	 */
	@Test
	public void testDeleteTopic() {
		final String result = DeleteTopicService.DeleteTopic(topic, user);
		assertEquals(expectedResult, result);
	}
}
