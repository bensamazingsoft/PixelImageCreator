
package com.ben.pixcreator.gui.pane.web.panel;

import java.util.HashSet;
import java.util.Set;

import com.ben.pixcreator.gui.facade.GuiFacade;
import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.panel.state.WebPanelState;
import com.ben.pixcreator.gui.pane.web.panel.state.impl.UnLoggedState;
import com.ben.pixcreator.web.bean.Bean;

import javafx.scene.layout.BorderPane;

public class WebPanel extends BorderPane {

	private WebPanelState			state;
	private Bean<LogInfo>			logBean;
	private Bean<Set<PixelGrid>>	pixelGridBean;

	public WebPanel() {

		GuiFacade.getInstance().setWebPanel(this);

		logBean = new Bean<LogInfo>();
		logBean.setData(new LogInfo());

		pixelGridBean = new Bean<>();
		pixelGridBean.setData(new HashSet<>());

		state = new UnLoggedState(this);

		setCenter(state.load());
	}

	public void reload() {

		setCenter(state.load());

	}

	public LogInfo getLogInfo() {
		return logBean.getData();
	}

	public WebPanelState getState() {

		return state;
	}

	public void setState(WebPanelState state) {

		this.state = state;
	}

	public Bean<LogInfo> getLogBean() {

		return logBean;
	}

	public Boolean isConnected() {
		return getLogInfo().isConnected();
	}

	public void setLogBean(Bean<LogInfo> logBean) {

		this.logBean = logBean;
	}

	public Bean<Set<PixelGrid>> getPixelGridBean() {

		return pixelGridBean;
	}

	public void setPixelGridBean(Bean<Set<PixelGrid>> pixelGridBean) {

		this.pixelGridBean = pixelGridBean;
	}

}
