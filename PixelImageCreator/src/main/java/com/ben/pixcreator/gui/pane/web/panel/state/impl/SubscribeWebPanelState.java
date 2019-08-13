
package com.ben.pixcreator.gui.pane.web.panel.state.impl;

import java.util.Map;

import com.ben.pixcreator.application.action.impl.MonitoredAction;
import com.ben.pixcreator.application.action.impl.SubscribeTask;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;
import com.ben.pixcreator.gui.pane.web.LogInfo;
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

      private WebPanel	 webPanel;

      private AppContext ctx = AppContext.getInstance();


      public SubscribeWebPanelState()
      {

	    this.webPanel = GuiFacade.getInstance().getWebPanel();

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

	    SubscribeTask task = new SubscribeTask(confPassword);

	    try
	    {
		  Executor.getInstance().executeAction(
			      new MonitoredAction(task)
					  .success(evt -> {

						if (webPanel.getLogBean().getData().isConnected())
						{
						      changeState(new UnLoggedState(webPanel));
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
