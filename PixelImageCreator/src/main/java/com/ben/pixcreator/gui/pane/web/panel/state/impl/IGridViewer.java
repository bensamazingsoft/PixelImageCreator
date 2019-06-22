
package com.ben.pixcreator.gui.pane.web.panel.state.impl;

import java.util.Set;

import com.ben.pixcreator.gui.pane.web.PixelGrid;

import javafx.scene.Node;

public interface IGridViewer
{

      public Node build(Set<PixelGrid> grids);

}
