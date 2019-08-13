
package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.gui.facade.GuiFacade;
import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.loginfoservice.ILogInfoService;
import com.ben.pixcreator.gui.pane.web.loginfoservice.impl.LogInfoHibService;
import com.ben.pixcreator.gui.pane.web.panel.WebPanel;
import com.ben.pixcreator.web.bean.Bean;

import javafx.concurrent.Task;

public class LoginTask extends Task<Void>
{

      private final Bean<LogInfo> logBean;
      private WebPanel		  webPanel = GuiFacade.getInstance().getWebPanel();
      private String		  regex	   = "^(.+)@(.+)$";
      private ILogInfoService	  logInfoService;


      public LoginTask(Bean<LogInfo> logBean)
      {

	    this.logBean = logBean;
	    logInfoService = new LogInfoHibService();

	    stateProperty().addListener((obs, old, newval) -> {
		  System.out.println("STATE : " + newval);
	    });
      }


      private boolean validPassword(String pass, Bean<LogInfo> logBean)
      {

	    if (null == pass || pass.length() == 0)
	    {
		  logBean.getErrors().put("Password", "must not be blank");
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


      @Override
      protected Void call() throws Exception
      {

	    updateTitle("Validating Log infos.");
	    Thread.sleep(500);
	    updateMessage("Starting");
	    Thread.sleep(500);

	    logBean.getErrors().clear();
	    logBean.setMessage("");

	    boolean valid1 = validEmail(logBean.getData().getEmail(), logBean);

	    updateMessage("email : " + valid1);
	    Thread.sleep(500);

	    boolean valid2 = validPassword(logBean.getData().getPassword(), logBean);
	    updateMessage("password : " + valid2);
	    Thread.sleep(500);

	    if (valid1 && valid2)
	    {

		  updateMessage("Calling server");
		  webPanel.setLogBean(logInfoService.validate(logBean));

		  if (logBean.getData().isConnected())
		  {

			updateMessage("Login successful");
			Thread.sleep(500);
			updateProgress(100, 100);

			return null;
		  }
	    }

	    updateMessage("Login failed");
	    Thread.sleep(500);
	    updateProgress(100, 100);
	    return null;
      }
}
