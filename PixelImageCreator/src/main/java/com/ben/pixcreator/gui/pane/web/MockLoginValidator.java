
package com.ben.pixcreator.gui.pane.web;

import com.ben.pixcreator.web.bean.Bean;

public class MockLoginValidator implements LoginValidator
{

      @Override
      public Bean<LogInfo> validate(Bean<LogInfo> bean)
      {

	    bean.setMessage("login successfull");
	    bean.getData().setConnected(true);
	    return bean;
      }

}
