
package com.ben.pixcreator.gui.pane.web.panel.state.impl;

import java.util.Map;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.LoginValidator;
import com.ben.pixcreator.gui.pane.web.MockLoginValidator;
import com.ben.pixcreator.gui.pane.web.panel.WebPanel;
import com.ben.pixcreator.gui.pane.web.panel.state.WebPanelState;
import com.ben.pixcreator.web.bean.Bean;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class unLoggedState implements WebPanelState
{

      private AppContext     ctx   = AppContext.getInstance();

      private WebPanel	     webPanel;

      private LoginValidator loginValidator;

      private StackPane	     pane;

      private String	     regex = "^(.+)@(.+)$";


      public unLoggedState(WebPanel webPanel)
      {

	    this.webPanel = webPanel;

	    // TODO inject REST impl
	    loginValidator = new MockLoginValidator();

	    buildPane();

      }


      private void buildPane()
      {

	    pane = new StackPane();
	    VBox box = new VBox();

	    Bean<LogInfo> logBean = webPanel.getLogBean();
	    String message = logBean.getMessage();

	    if (null != message && message.length() > 0)
	    {
		  Label messageLbl = new Label(message);
		  messageLbl.setStyle("-fx-font-color : green");
		  box.getChildren().add(messageLbl);
	    }

	    Label emailLbl = new Label(ctx.getBundle().getString("email"));
	    box.getChildren().add(emailLbl);

	    TextField emailTf = new TextField();
	    emailTf.setText(logBean.getData().getEmail());
	    emailTf.textProperty().addListener((obs, oldVal, newVal) -> logBean.getData().setEmail(newVal));
	    box.getChildren().add(emailTf);

	    Label passwordLbl = new Label(ctx.getBundle().getString("password"));
	    box.getChildren().add(passwordLbl);

	    TextField passwordTf = new TextField();
	    passwordTf.setText(logBean.getData().getPassword());
	    passwordTf.textProperty().addListener((obs, oldVal, newVal) -> logBean.getData().setPassword(newVal));
	    box.getChildren().add(passwordTf);

	    Button subscribeBtn = new Button(ctx.getBundle().getString("subscribe"));
	    subscribeBtn.setOnAction(event -> suscribe());
	    Button okBtn = new Button(ctx.getBundle().getString("ok"));
	    okBtn.setOnAction(event -> validateLogInfo(logBean));
	    HBox btnBox = new HBox();
	    btnBox.getChildren().addAll(subscribeBtn, okBtn);
	    box.getChildren().add(btnBox);

	    Map<String, String> errors = logBean.getErrors();
	    if (errors.size() > 0)
	    {
		  for (String errKey : errors.keySet())
		  {
			Label errLbl = new Label(errKey + " : " + errors.get(errKey));
			errLbl.setStyle("-fx-text-fill : red");
			box.getChildren().add(errLbl);
		  }
	    }

	    pane.getChildren().add(box);
      }


      private void suscribe()
      {

	    changeState(new SubscribeWebPanelState(webPanel));
      }


      @Override
      public Node load()
      {

	    buildPane();
	    return pane;
      }


      @Override
      public void changeState(WebPanelState newState)
      {

	    webPanel.setState(newState);
	    webPanel.reload();
      }


      private void validateLogInfo(Bean<LogInfo> logBean)
      {

	    logBean.getErrors().clear();
	    logBean.setMessage("");

	    boolean valid1 = validEmail(logBean.getData().getEmail(), logBean);
	    boolean valid2 = validPassword(logBean.getData().getPassword(), logBean);

	    if (valid1 && valid2)
	    {

		  webPanel.setLogBean(loginValidator.validate(logBean));

		  if (logBean.getData().isConnected())
		  {

			changeState(new LoggedWebPanelState(webPanel));

		  }
	    }

	    webPanel.reload();
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

}
