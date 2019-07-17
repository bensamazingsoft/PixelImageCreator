
package com.ben.pixcreator.gui.pane.web.panel.state.impl;

import java.util.Map;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.loginfoservice.ILogInfoService;
import com.ben.pixcreator.gui.pane.web.panel.WebPanel;
import com.ben.pixcreator.gui.pane.web.panel.state.WebPanelState;
import com.ben.pixcreator.web.bean.Bean;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SubscribeWebPanelState implements WebPanelState
{

      private WebPanel	      webPanel;
      private Bean<LogInfo>   logInfoBean;
      private ILogInfoService logValidator;

      private String	      regex = "^(.+)@(.+)$";

      private BorderPane      pane;
      private AppContext      ctx   = AppContext.getInstance();


      public SubscribeWebPanelState(WebPanel webPanel, ILogInfoService iLogInfoManager)
      {

	    this.webPanel = webPanel;
	    this.logInfoBean = webPanel.getLogBean();
	    this.logValidator = iLogInfoManager;

      }


      private BorderPane build()
      {

	    BorderPane bp = new BorderPane();

	    VBox box = new VBox();

	    Bean<LogInfo> logBean = webPanel.getLogBean();
	    String message = logBean.getMessage();

	    if (null != message && message.length() > 0)
	    {
		  Label messageLbl = new Label(message);
		  messageLbl.setStyle("-fx-font-color : green");
		  bp.setTop(messageLbl);
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

	    Label confPasswordLbl = new Label(ctx.getBundle().getString("conf_password"));
	    box.getChildren().add(confPasswordLbl);

	    TextField confPasswordTf = new TextField();
	    confPasswordTf.setText(logBean.getData().getPassword());
	    // confPasswordTf.textProperty().addListener((obs, oldVal, newVal) -> logBean.getData().setPassword(newVal));
	    box.getChildren().add(confPasswordTf);

	    Button subscribeBtn = new Button(ctx.getBundle().getString("subscribe"));
	    subscribeBtn.setOnAction(event -> validate(logBean, confPasswordTf.getText()));
	    Button backBtn = new Button(ctx.getBundle().getString("back"));
	    backBtn.setOnAction(event -> changeState(new UnLoggedState(webPanel)));
	    HBox btnBox = new HBox();
	    btnBox.getChildren().addAll(subscribeBtn, backBtn);
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

	    bp.setCenter(box);

	    return bp;
      }


      private void validate(Bean<LogInfo> logBean, String confPassword)
      {

	    logBean.getErrors().clear();
	    logBean.setMessage("");

	    boolean valid1 = validEmail(logBean.getData().getEmail(), logBean);
	    boolean valid2 = validPassword(logBean.getData().getPassword(), logBean, confPassword);

	    if (valid1 && valid2)
	    {

		  webPanel.setLogBean(logValidator.registerLogInfo(logBean));

		  if (logBean.getData().isConnected())
		  {

			logBean.setMessage("Registered successfully");
			logBean.getData().setConnected(false);
			changeState(new UnLoggedState(webPanel));

		  }
	    }

	    webPanel.reload();
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


      @Override
      public Node load()
      {

	    return build();
      }


      @Override
      public void changeState(WebPanelState newState)
      {

	    webPanel.setState(newState);
	    webPanel.reload();

      }

}
