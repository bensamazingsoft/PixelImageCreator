
package com.ben.pixcreator.gui.pane.web.panel.state;

import javafx.scene.Node;

public interface WebPanelState
{

      public Node load();


      public void changeState(WebPanelState newState);

}
