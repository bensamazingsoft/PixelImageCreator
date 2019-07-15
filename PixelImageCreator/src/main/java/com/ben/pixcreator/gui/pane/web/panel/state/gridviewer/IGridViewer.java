
package com.ben.pixcreator.gui.pane.web.panel.state.gridviewer;

import java.util.Set;

import com.ben.pixcreator.gui.pane.web.PixelGrid;
import com.ben.pixcreator.gui.pane.web.panel.state.gridviewer.impl.GridViewBuilder.view;

import javafx.scene.Node;

public interface IGridViewer
{

      public Node build(Set<PixelGrid> grids, view view);

}
