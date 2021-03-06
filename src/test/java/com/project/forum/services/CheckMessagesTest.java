package com.project.forum.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.project.forum.model.Message;
import com.project.forum.model.User;
import com.project.forum.repo.Repository;

/**
 * @author Vasil
 * Unit test for CheckMessage feature. Users can only see their own messages
 */
@RunWith(Parameterized.class)
public class CheckMessagesTest {
	@Parameters(name = "{index}: with user={0} and expected result={1}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{  null, "user cant be null!" }, 
				 { new User("username","password","role_user"), "text2text3" }, 
				 { new User("someone","password","role_user"),  "text"},
				 { new User("admin","password","role_admin"),  "No messages have been fouund!"}
				 

		});
	}
	@Parameter(0)
	public User user;
	@Parameter(1)
	public String expectedResult;
	private Services CheckMessageService;
	private Repository repo;
	/**
	 * Initialization
	 */
	@Before
	public void setup() {
		repo = Mockito.mock(Repository.class);
		List<Message> messages = new ArrayList<Message>();
		Message message = new Message("text","someone");
		Message message2 = new Message("text2", "username");
		Message message3 = new Message("text3", "username");
		messages.add(message);
		messages.add(message2);
		messages.add(message3);
		doReturn(messages).when(repo).GetAllMessages();
		
		CheckMessageService = new Services(repo);
	}
	/**
	 * Tests Check message feature with null, valid critical and invalid input parameters
	 */
	@Test
	public void testCheckMessageService() {
		final String result = CheckMessageService.CheckMessages(user);
		assertEquals(expectedResult, result);
	}

}
