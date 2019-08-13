
package com.ben.pixcreator.gui.pane.web.panel.state.impl;

import java.util.Map;

import com.ben.pixcreator.application.action.impl.LoginTask;
import com.ben.pixcreator.application.action.impl.MonitoredAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.pane.web.LogInfo;
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

public class UnLoggedState implements WebPanelState
{

      private AppContext ctx = AppContext.getInstance();

      private WebPanel	 webPanel;

      private StackPane	 pane;


      public UnLoggedState(WebPanel webPanel)
      {

	    this.webPanel = webPanel;

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
	    subscribeBtn.setOnAction(event -> subscribe());
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


      private void subscribe()
      {

	    changeState(new SubscribeWebPanelState());
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

	    LoginTask task = new LoginTask(logBean);

	    try
	    {
		  Executor.getInstance().executeAction(new MonitoredAction(task)
			      .success(evt -> {

				    if (webPanel.getLogBean().getData().isConnected())
				    {
					  changeState(new LoggedWebPanelState(webPanel));
					  return;
				    }
				    webPanel.reload();
			      })
			      .fail(evt -> {

				    new ExceptionPopUp((Exception) task.getException());

				    webPanel.reload();
			      }));

	    }
	    catch (Exception e)
	    {
		  new ExceptionPopUp(e);
	    }

      }

}
