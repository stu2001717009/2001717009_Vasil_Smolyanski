package com.project.forum.services;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.project.forum.repo.Repository;

/**
 * @author Vasil
 *Checks the Post Message functionality. Valid messages can be between 3 and 15 characters long
 */
@RunWith(Parameterized.class)
public class PostMessageTest {
	@Parameters(name = "{index}: with text={0}, author={1} and expected result={2}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{ null, null, "Please enter correct data!" }, 
				 { null, "username", "Please enter correct data!" }, 
				 { "message", null,  "Please enter correct data!"}, 
				 {"me",  null,  "Please enter correct data!"},
				 {"me",  "user",  "Message must be between 3 and 15 characters long!"},
				 {"123456789012345","username",  "Message must be between 3 and 15 characters long!"},
				 {"message",  "username",  "Message added successfully"},
		});
	}
	@Parameter(0)
	public String text;
	@Parameter(1)
	public String author;
	@Parameter(2)
	public String expectedResult;
	private Services PostService;
	private Repository repo;
	/**
	 * Initialization
	 */
	@Before
	public void setup() {
		repo = new Repository();
		PostService = new Services(repo);
	}
	/**
	 * Checks the Post Message feature with null, valid and incorrect input entries
	 */
	@Test
	public void testPostMessage() {
		final String result = PostService.PostMessage(text, author);
		assertEquals(expectedResult, result);
	}

}
