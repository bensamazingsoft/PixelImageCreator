
package com.ben.pixcreator.server.data.services;

import com.ben.pixcreator.server.data.model.User;
import com.ben.pixcreator.server.exception.DataServiceException;

public interface UserService
{

      public User findUserByEmail(String email) throws DataServiceException;


      public Boolean persist(User user) throws DataServiceException;


      public Boolean delete(User user) throws DataServiceException;


      public User update(User user) throws DataServiceException;

}
