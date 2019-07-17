
package com.ben.pixcreator.server.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ben.pixcreator.server.EntityManagerProvider;
import com.ben.pixcreator.server.PasswordUtils;
import com.ben.pixcreator.server.data.model.User;
import com.ben.pixcreator.server.data.services.impl.UserHibService;
import com.ben.pixcreator.server.exception.DataServiceException;

public class UserHibServiceTest
{

      @BeforeAll
      static void before()
      {

	    EntityManagerProvider.getInstance();
      }


      @Test
      void findUserByEmailTest() throws DataServiceException
      {

	    UserHibService userService = new UserHibService();

	    Assertions.assertNull(userService.findUserByEmail("abc@foo.com"));

      }


      @Test
      void persistTest() throws DataServiceException
      {

	    UserHibService userService = new UserHibService();

	    Assertions.assertTrue(userService.persist(new User("ben@home.fr", "p4ss")));

      }


      @Test
      void persistAndFetchByEmailTest() throws DataServiceException
      {

	    UserHibService userService = new UserHibService();

	    String testEmail = String.valueOf(System.currentTimeMillis()) + "@test.fr";

	    User user = new User(testEmail, "password");

	    Assertions.assertTrue(userService.persist(user));

	    User fetchedByEmailUser = userService.findUserByEmail(testEmail);

	    Assertions.assertTrue(user.toString().equals(fetchedByEmailUser.toString()));

      }


      @Test
      void deleteTest() throws DataServiceException
      {

	    UserHibService userService = new UserHibService();

	    String testEmail = String.valueOf(System.currentTimeMillis()) + "@deleteTest.fr";

	    User user = new User(testEmail, "password");

	    Assertions.assertTrue(userService.persist(user));

	    Assertions.assertTrue(userService.delete(user));

      }


      @Test
      void updateTest() throws DataServiceException
      {

	    UserHibService userService = new UserHibService();

	    String testEmail = String.valueOf(System.currentTimeMillis()) + "@deleteTest.fr";

	    User user = new User(testEmail, "oldPassword");

	    Assertions.assertTrue(userService.persist(user));

	    user.setPassword("newPassword");
	    user.setSalt(PasswordUtils.getSalt(128));

	    Assertions.assertNotNull(userService.update(user));

      }

}
