
package com.ben.pixcreator.gui.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.controls.color.roster.ColorRoster;
import com.ben.pixcreator.gui.controls.layer.panel.LayerPanel;
import com.ben.pixcreator.gui.controls.menu.bar.PixMenuBar;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.controls.tool.toolbar.PixToolBar;
import com.ben.pixcreator.gui.pane.tabpane.PixTabPane;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Toggle;
import javafx.scene.paint.Color;

public class GuiFacade {

	private static GuiFacade instance;

	private Scene		scene;
	private PixMenuBar	pixMenuBar;
	private PixToolBar	pixToolBar;
	private PixTabPane	pixTabPane;
	private LayerPanel	layerPanel;
	private ColorRoster	colorRoster;

	private Map<PixImage, Set<SimpleObjectProperty<Color>>> imagesColors;

	private SimpleBooleanProperty showGrid = new SimpleBooleanProperty();

	private GuiFacade() {

		imagesColors = new HashMap<>();

		showGrid.set(false);

	}

	public static GuiFacade getInstance() {

		if (instance == null) {

			instance = new GuiFacade();
		}

		return instance;
	}

	public void toggleToolTo(PixTool pixTool) {

		// toggle tool in AppContext and Gui Controls

		AppContext.getInstance().setCurrTool(pixTool);

		List<Toggle> list = pixToolBar.getToggleGroup().getToggles().stream()
				.filter(togBut -> ((PixTool) togBut.getUserData()).name().equals(pixTool.name()))
				.collect(Collectors.toList());

		if (list.size() > 0) {
			Toggle toggle = list.get(0);
			pixToolBar.getToggleGroup().selectToggle(toggle);
		}

	}

	public PixImage getActiveImage() {

		PixTab tab = (PixTab) pixTabPane.getSelectionModel().getSelectedItem();
		PixImage activeImage = tab.getImage();

		return activeImage;
	}

	public ALayer getActiveLayer() {

		return layerPanel.getActiveLayer();
	}

	public Scene getScene() {

		return scene;
	}

	public void setScene(Scene scene) {

		this.scene = scene;
	}

	public PixMenuBar getPixMenuBar() {

		return pixMenuBar;
	}

	public void setPixMenuBar(PixMenuBar pixMenuBar) {

		this.pixMenuBar = pixMenuBar;
	}

	public PixToolBar getPixToolBar() {

		return pixToolBar;
	}

	public void setPixToolBar(PixToolBar pixToolBar) {

		this.pixToolBar = pixToolBar;
	}

	public PixTabPane getPixTabPane() {

		return pixTabPane;
	}

	public void setPixTabPane(PixTabPane pixTabPane) {

		this.pixTabPane = pixTabPane;
	}

	public LayerPanel getLayerPanel() {

		return layerPanel;
	}

	public void setLayerPanel(LayerPanel layerPanel) {

		this.layerPanel = layerPanel;
	}

	public ColorRoster getColorRoster() {
		return colorRoster;
	}

	public void setColorRoster(ColorRoster colorRoster) {
		this.colorRoster = colorRoster;
	}

	public Map<PixImage, Set<SimpleObjectProperty<Color>>> getImagesColors() {
		return imagesColors;
	}

	public void setImagesColors(Map<PixImage, Set<SimpleObjectProperty<Color>>> imagesColors) {
		this.imagesColors = imagesColors;
	}

	public final SimpleBooleanProperty showGridProperty() {
		return this.showGrid;
	}

	public final boolean isShowGrid() {
		return this.showGridProperty().get();
	}

	public final void setShowGrid(final boolean showGrid) {
		this.showGridProperty().set(showGrid);
	}

}
