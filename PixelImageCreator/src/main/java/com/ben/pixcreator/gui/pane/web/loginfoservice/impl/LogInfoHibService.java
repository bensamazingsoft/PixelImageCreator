
package com.ben.pixcreator.gui.pane.web.loginfoservice.impl;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.loginfoservice.ILogInfoService;
import com.ben.pixcreator.web.bean.Bean;
import com.ben.pixcreator.web.target.provider.RestTargetProvider;

public class LogInfoHibService implements ILogInfoService
{

      @Override
      public Bean<LogInfo> validate(Bean<LogInfo> logInfoBean)
      {

	    RestTargetProvider restTargetProvider = new RestTargetProvider();
	    WebTarget target = restTargetProvider.getBaseTarget().path("checkin");

	    Response response = target.request().get();

	    switch (response.getStatus())
	    {

	    case 200:
		  logInfoBean.getData().setConnected(true);
		  break;
	    case 401:
		  logInfoBean.getData().setConnected(false);
		  logInfoBean.getErrors().put("auht", response.readEntity(String.class));
		  break;
	    case 500:
		  logInfoBean.getData().setConnected(false);
		  logInfoBean.getErrors().put("server", response.readEntity(String.class));
		  break;

	    default:
		  logInfoBean.getData().setConnected(false);
		  logInfoBean.getErrors().put("error", response.readEntity(String.class));
		  break;
	    }

	    return logInfoBean;
      }


      @Override
      public Bean<LogInfo> registerLogInfo(Bean<LogInfo> logInfoBean)
      {

	    RestTargetProvider restTargetProvider = new RestTargetProvider();
	    WebTarget target = restTargetProvider.getBaseTarget().path("subscribe");

	    Form form = new Form()
			.param("email", logInfoBean.getData().getEmail())
			.param("password", logInfoBean.getData().getPassword());

	    Response response = target.request(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form));

	    switch (response.getStatus())
	    {

	    case 200:
		  logInfoBean.getData().setConnected(true);
		  break;
	    case 412:
		  logInfoBean.getData().setConnected(false);
		  logInfoBean.getErrors().put("validation", response.readEntity(String.class));
		  break;
	    case 500:
		  logInfoBean.getData().setConnected(false);
		  logInfoBean.getErrors().put("server", response.readEntity(String.class));
		  break;

	    default:
		  logInfoBean.getData().setConnected(false);
		  logInfoBean.getErrors().put("error", response.readEntity(String.class));
		  break;
	    }

	    return logInfoBean;
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
