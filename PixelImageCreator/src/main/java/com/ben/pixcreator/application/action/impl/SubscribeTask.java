
package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.gui.facade.GuiFacade;
import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.loginfoservice.ILogInfoService;
import com.ben.pixcreator.gui.pane.web.loginfoservice.impl.LogInfoHibService;
import com.ben.pixcreator.gui.pane.web.panel.WebPanel;
import com.ben.pixcreator.web.bean.Bean;

import javafx.concurrent.Task;

public class SubscribeTask extends Task<Void>
{

      private ILogInfoService logValidator;

      private String	      regex    = "^(.+)@(.+)$";

      private Bean<LogInfo>   logBean;

      private String	      confPassword;

      private WebPanel	      webPanel = GuiFacade.getInstance().getWebPanel();


      public SubscribeTask(String confPassword)
      {

	    this.confPassword = confPassword;
	    this.logValidator = new LogInfoHibService();
      }


      @Override
      protected Void call() throws Exception
      {

	    updateTitle("Validating subscribe infos.");
	    Thread.sleep(500);
	    updateMessage("Starting");
	    Thread.sleep(500);

	    logBean.getErrors().clear();
	    logBean.setMessage("");

	    boolean valid1 = validEmail(logBean.getData().getEmail(), logBean);
	    updateMessage("email : " + valid1);
	    Thread.sleep(500);

	    boolean valid2 = validPassword(logBean.getData().getPassword(), logBean, confPassword);
	    updateMessage("password : " + valid2);
	    Thread.sleep(500);

	    if (valid1 && valid2)
	    {
		  updateMessage("Calling server");
		  webPanel.setLogBean(logValidator.registerLogInfo(logBean));

		  if (logBean.getData().isConnected())
		  {
			updateMessage("Registered successfully");
			Thread.sleep(500);
			updateProgress(100, 100);
			logBean.setMessage("Registered successfully");
			logBean.getData().setConnected(false);
			return null;

		  }
	    }

	    updateMessage("Registered failed");
	    Thread.sleep(500);
	    updateProgress(100, 100);
	    return null;
      }


      private boolean validPassword(String pass, Bean<LogInfo> logBean, String confPass)
      {

	    if (null == pass || pass.length() == 0)
	    {
		  logBean.getErrors().put("Password", "must not be blank");
		  return false;
	    }

	    if (!pass.equals(confPass))
	    {
		  logBean.getErrors().put("Password", "confirmation must match password");
		  return false;

	    }
	    return true;
      }


      private boolean validEmail(String email, Bean<LogInfo> logBean)
      {

	    if (null == email || email.length() == 0)
	    {
		  logBean.getErrors().put("email", "must not be blank");
		  return false;
	    }
	    if (!email.matches(regex))
	    {
		  logBean.getErrors().put("email", "must be of format abc@xyz.org");
		  return false;

	    }

	    return true;
      }

}
