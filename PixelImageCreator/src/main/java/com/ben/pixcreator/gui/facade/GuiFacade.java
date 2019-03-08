
package com.ben.pixcreator.gui.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.selection.Selection;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.controls.color.box.ColorBox;
import com.ben.pixcreator.gui.controls.color.roster.ColorRoster;
import com.ben.pixcreator.gui.controls.layer.box.LayerBox;
import com.ben.pixcreator.gui.controls.layer.panel.LayerPanel;
import com.ben.pixcreator.gui.controls.menu.bar.PixMenuBar;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.controls.tool.toolbar.PixToolBar;
import com.ben.pixcreator.gui.miniature.manager.MiniatureManager;
import com.ben.pixcreator.gui.pane.tabpane.PixTabPane;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.Toggle;
import javafx.scene.paint.Color;

public class GuiFacade {

	private static final Logger log = LoggerFactory.getLogger(GuiFacade.class);

	private static GuiFacade instance;

	private Scene scene;

	private PixMenuBar	pixMenuBar;
	private PixToolBar	pixToolBar;
	private PixTabPane	pixTabPane;
	private LayerPanel	layerPanel;
	private ColorRoster	colorRoster;

	private Map<PixImage, Set<SimpleObjectProperty<Color>>>	imagesColors;
	private Map<PixImage, Selection>						selections;
	private SimpleObjectProperty<Color>						activeColor		= new SimpleObjectProperty<>();
	private SimpleObjectProperty<Color>						backgroundColor	= new SimpleObjectProperty<>();

	private SimpleObjectProperty<PixImage>	activeImage	= new SimpleObjectProperty<>();
	private SimpleBooleanProperty			showGrid	= new SimpleBooleanProperty();

	private MiniatureManager miniatureManager;

	private GuiFacade() {

		imagesColors = new HashMap<>();
		selections = new HashMap<>();
		miniatureManager = new MiniatureManager();
		showGrid.set(false);
		backgroundColor.set(AppContext.getInstance().propertyContext().getBackgroundColor());

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

	public void addTab(Tab tab) {

		pixTabPane.getTabs().add(tab);
		pixTabPane.getSelectionModel().select(tab);

	}

	public ObservableList<Tab> getTabs() {

		return pixTabPane.getTabs();
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

	public final SimpleObjectProperty<PixImage> activeImageProperty() {

		return this.activeImage;
	}

	public PixImage getActiveimage() {

		return this.activeImage.get();
	}

	public void setActiveImage(PixImage image) {

		this.activeImage.set(image);
	}

	public final SimpleObjectProperty<Color> activeColorProperty() {

		return this.activeColor;
	}

	public final Color getActiveColor() {

		return this.activeColorProperty().get();
	}

	public final void setActiveColor(final Color activeColor) {

		this.activeColorProperty().set(activeColor);
	}

	public void setPixTabPane(PixTabPane tabPane) {

		this.pixTabPane = tabPane;
	}

	public void setLayerPanel(LayerPanel layerPanel) {

		this.layerPanel = layerPanel;

		layerPanel.imageProperty().bindBidirectional(activeImage);

	}

	public void setColorRoster(ColorRoster colorRoster) {

		this.colorRoster = colorRoster;

		colorRoster.imageProperty().bindBidirectional(activeImage);

		activeColor.set(((ColorBox) colorRoster.getToggleGroup().getSelectedToggle()).getColor());

		colorRoster.getToggleGroup().selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
			Color color = ((ColorBox) newVal).getColor();
			activeColor.set(color);
		});

	}

	public void setPixToolBar(PixToolBar pixToolBar) {

		this.pixToolBar = pixToolBar;
	}

	public PixTab getActiveTab() {

		return (PixTab) pixTabPane.getSelectionModel().getSelectedItem();
	}

	public Map<PixImage, Selection> getSelections() {

		return selections;
	}

	public void setSelections(Map<PixImage, Selection> selections) {

		this.selections = selections;
	}

	public MiniatureManager getMiniatureManager() {

		return miniatureManager;
	}

	public LayerPanel getLayerPanel() {

		return layerPanel;
	}

	public final SimpleObjectProperty<Color> backgroundColorProperty() {
		return this.backgroundColor;
	}

	public final Color getBackgroundColor() {
		return this.backgroundColorProperty().get();
	}

	public final void setBackgroundColor(final Color backgroundColor) {
		this.backgroundColorProperty().set(backgroundColor);
	}

	/**
	 * selects the layer in the layer panel if it exists in the panel.
	 * 
	 * @param layer
	 */
	public void selectLayer(ALayer layer) {

		for (Toggle tog : layerPanel.getTogglegroup().getToggles()) {
			LayerBox box = (LayerBox) tog;

			if (box.getLayer().equals(layer)) {
				layerPanel.getTogglegroup().selectToggle(box);
			}
		}
	}

}
