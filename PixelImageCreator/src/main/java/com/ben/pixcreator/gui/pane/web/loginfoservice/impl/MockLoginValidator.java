
package com.ben.pixcreator.gui.pane.web.loginfoservice.impl;

import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.loginfoservice.ILogInfoService;
import com.ben.pixcreator.web.bean.Bean;

public class MockLoginValidator implements ILogInfoService
{

      @Override
      public Bean<LogInfo> validate(Bean<LogInfo> bean)
      {

	    bean.setMessage("login successfull");
	    bean.getData().setConnected(true);
	    return bean;
      }


      @Override
      public Bean<LogInfo> registerLogInfo(Bean<LogInfo> logInfoBean)
      {

	    // TODO Auto-generated method stub
	    return null;
      }


      @Override
      public boolean deleteLogInfo(Bean<LogInfo> logInfoBean)
      {

	    // TODO Auto-generated method stub
	    return false;
      }


      @Override
      public Bean<LogInfo> updateLogInfo(Bean<LogInfo> logInfoBean)
      {

	    // TODO Auto-generated method stub
	    return null;
      }

}
