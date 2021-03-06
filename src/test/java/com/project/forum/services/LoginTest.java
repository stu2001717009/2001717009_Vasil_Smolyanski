package com.project.forum.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;

import com.project.forum.repo.Repository;
import com.project.forum.model.User;


/**
 * @author Vasil
 *	Tests the Log-in Feature. Only users who have supplied a username, password and repeatPassword may get authenticated
 */
@RunWith(Parameterized.class)
public class LoginTest {
	@Parameters(name = "{index}: with username={0}, password={1}, repeatPassword={2} and expected result={3}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{ null, null, null, "Please enter correct data" }, 
				 { null, "password", "password", "Please enter correct data" }, 
				 { "username", null, "password",  "Please enter correct data"}, 
				 {"username", "password", null,  "Please enter correct data"},
				 {"username", "password", "password",  "User has logged in successfully"},
				 {"username1", "password", "password",  "No such user exists!"},
				 {"username", "pass", "pass",  "No such user exists!"},
				 {"username", "password", "pass",  "Please enter matching passwords"},
				 {"username", "pass", "password",  "Please enter matching passwords"}
		});
	}
	@Parameter(0)
	public String username;
	@Parameter(1)
	public String password;
	@Parameter(2)
	public String repeatPassword;
	@Parameter(3)
	public String expectedResult;
	
	private Services loginService;
	private Repository repo;
	
	/**
	 * initialization
	 */
	@Before
	public void setup() {
		repo = Mockito.mock(Repository.class);
		List<User>users = new ArrayList<User>();
		users.add(new User("username","password","user_role"));
		doReturn(users).when(repo).GetAllUsers();
		loginService = new Services(repo);
	}
	/**
	 *  Test log in feature with null, incorrect and correct entries
	 */
	@Test
	public void testLoginService() {
		final String result = loginService.LoginService(username, password, repeatPassword);
		assertEquals(expectedResult, result);
	}
}
