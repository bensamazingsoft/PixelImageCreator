
package com.ben.pixcreator.server.data.services;

import java.util.Set;

import com.ben.pixcreator.server.data.model.PixelGridDto;
import com.ben.pixcreator.server.exception.DataServiceException;

public interface GridService
{

      public PixelGridDto find(int id) throws DataServiceException;


      public Set<PixelGridDto> findFiltered(String email, Boolean userOnly, Set<String> filters) throws DataServiceException;;


      public Boolean persist(PixelGridDto grid) throws DataServiceException;


      public Boolean delete(PixelGridDto grid) throws DataServiceException;


      public PixelGridDto update(PixelGridDto grid) throws DataServiceException;

}
