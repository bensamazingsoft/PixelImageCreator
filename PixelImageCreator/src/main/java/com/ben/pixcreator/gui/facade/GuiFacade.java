
package com.ben.pixcreator.gui.facade;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.impl.SetCursorsAction;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.context.PropertiesContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.pile.BasicPile;
import com.ben.pixcreator.application.pile.Pile;
import com.ben.pixcreator.application.selection.Selection;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.controls.color.box.ColorBox;
import com.ben.pixcreator.gui.controls.color.roster.ColorRoster;
import com.ben.pixcreator.gui.controls.layer.box.LayerBox;
import com.ben.pixcreator.gui.controls.layer.panel.LayerPanel;
import com.ben.pixcreator.gui.controls.menu.bar.PixMenuBar;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.controls.tool.toolbar.PixToolBar;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.miniature.manager.MiniatureManager;
import com.ben.pixcreator.gui.pane.tabpane.PixTabPane;
import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.gui.pane.web.panel.WebPanel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.Toggle;
import javafx.scene.paint.Color;

public class GuiFacade implements ToolGuiFacade, TabGuiFacade {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(GuiFacade.class);

	private static GuiFacade instance;

	private Scene scene;

	private final ToolGuiFacade	toolGuiFacade	= new BasicToolGuiFacade();
	private final TabGuiFacade	tabGuiFacade	= new BasicTabGuiFacade();

	private PixMenuBar	pixMenuBar;
	private LayerPanel	layerPanel;
	private ColorRoster	colorRoster;
	private WebPanel	webPanel;

	private ALayer focusLayer;

	private Map<PixImage, Set<SimpleObjectProperty<Color>>>	imagesColors;
	private Map<PixImage, Selection>						selections;
	private SimpleObjectProperty<Color>						activeColor		= new SimpleObjectProperty<>();
	private SimpleObjectProperty<Color>						backgroundColor	= new SimpleObjectProperty<>();
	private SimpleObjectProperty<Color>						hoverColor		= new SimpleObjectProperty<>();
	private Map<Coord, ColorRGB>							clipboard		= new HashMap<>();

	private SimpleObjectProperty<PixImage> activeImage = new SimpleObjectProperty<>();

	private MiniatureManager miniatureManager;

	private Pile<String> recentFiles;

	private GuiFacade() {

		final PropertiesContext ctxProp = AppContext.getInstance().propertyContext();

		imagesColors = new HashMap<>();
		selections = new HashMap<>();
		miniatureManager = new MiniatureManager();
		toolGuiFacade.setShowGrid(false);
		backgroundColor.set(ctxProp.getBackgroundColor());
		recentFiles = new BasicPile<String>(Integer.valueOf(ctxProp.get("maxRecentFiles")));

		activeColor.addListener((obs, oldVal, newVal) -> {
			try {
				Executor.getInstance().executeAction(new SetCursorsAction());
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}
		});

	}

	public static GuiFacade getInstance() {

		if (instance == null) {

			instance = new GuiFacade();
		}

		return instance;
	}

	// TOOL FACADE

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ben.pixcreator.gui.facade.ToolGuiFacade#toggleToolTo(com.ben.
	 * pixcreator.application.tools.PixTool)
	 */
	@Override
	public void toggleToolTo(PixTool pixTool) {
		toolGuiFacade.toggleToolTo(pixTool);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ben.pixcreator.gui.facade.ToolGuiFacade#setPixToolBar(com.ben.
	 * pixcreator.gui.controls.tool.toolbar.PixToolBar)
	 */
	@Override
	public void setPixToolBar(PixToolBar pixToolBar) {

		toolGuiFacade.setPixToolBar(pixToolBar);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ben.pixcreator.gui.facade.ToolGuiFacade#getPixToolBar()
	 */
	@Override
	public PixToolBar getPixToolBar() {
		return toolGuiFacade.getPixToolBar();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ben.pixcreator.gui.facade.ToolGuiFacade#getSelectedToolInToolbar()
	 */
	@Override
	public PixTool getSelectedToolInToolbar() {
		return toolGuiFacade.getSelectedToolInToolbar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ben.pixcreator.gui.facade.ToolGuiFacade#showGridProperty()
	 */
	@Override
	public final SimpleBooleanProperty showGridProperty() {
		return toolGuiFacade.showGridProperty();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ben.pixcreator.gui.facade.ToolGuiFacade#isShowGrid()
	 */
	@Override
	public final boolean isShowGrid() {

		return toolGuiFacade.isShowGrid();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ben.pixcreator.gui.facade.ToolGuiFacade#setShowGrid(boolean)
	 */
	@Override
	public final void setShowGrid(final boolean showGrid) {
		toolGuiFacade.setShowGrid(showGrid);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ben.pixcreator.gui.facade.ToolGuiFacade#panModeProperty()
	 */
	@Override
	public final SimpleBooleanProperty panModeProperty() {

		return toolGuiFacade.panModeProperty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ben.pixcreator.gui.facade.ToolGuiFacade#isPanMode()
	 */
	@Override
	public final boolean isPanMode() {
		return toolGuiFacade.isPanMode();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ben.pixcreator.gui.facade.ToolGuiFacade#setPanMode(boolean)
	 */
	@Override
	public final void setPanMode(final boolean panMode) {
		toolGuiFacade.setPanMode(panMode);

	}

	// TAB FACADE

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ben.pixcreator.gui.facade.TabGuiFacade#addTab(javafx.scene.control.
	 * Tab)
	 */
	@Override
	public void addTab(Tab tab) {

		tabGuiFacade.addTab(tab);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ben.pixcreator.gui.facade.TabGuiFacade#getTabs()
	 */
	@Override
	public ObservableList<Tab> getTabs() {
		return tabGuiFacade.getTabs();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ben.pixcreator.gui.facade.TabGuiFacade#setPixTabPane(com.ben.
	 * pixcreator.gui.pane.tabpane.PixTabPane)
	 */
	@Override
	public void setPixTabPane(PixTabPane tabPane) {
		tabGuiFacade.setPixTabPane(tabPane);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ben.pixcreator.gui.facade.TabGuiFacade#getActiveTab()
	 */
	@Override
	public PixTab getActiveTab() {

		return tabGuiFacade.getActiveTab();
	}

	public ColorRoster getColorRoster() {

		return colorRoster;
	}

	public void setColorRoster(ColorRoster colorRoster) {

		this.colorRoster = colorRoster;

		colorRoster.imageProperty().bindBidirectional(activeImage);

		activeColor.set(((ColorBox) colorRoster.getToggleGroup().getSelectedToggle()).getColor());

	}

	// COLOR ROSTER FACADE
	public final SimpleObjectProperty<Color> activeColorProperty() {

		return this.activeColor;
	}

	public final Color getActiveColor() {

		return this.activeColorProperty().get();
	}

	public final void setActiveColor(final Color activeColor) {

		this.activeColorProperty().set(activeColor);
	}

	public void selectPrevColorBox() {
		getColorRoster().selectPrevColorBox();

	}

	public void selectNextColorBox() {
		getColorRoster().selectNextColorBox();

	}

	public void selectColor(Color color) {
		getColorRoster().selectColor(color);

	}

	public void reloadColorsInRoster(PixImage activeImage2) {
		getColorRoster().reload(getActiveImage());

	}

	public Map<PixImage, Set<SimpleObjectProperty<Color>>> getImagesColors() {

		return imagesColors;
	}

	public void setImagesColors(Map<PixImage, Set<SimpleObjectProperty<Color>>> imagesColors) {

		this.imagesColors = imagesColors;
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

	public final SimpleObjectProperty<Color> hoverColorProperty() {

		return this.hoverColor;
	}

	public final Color getHoverColor() {

		return this.hoverColorProperty().get();
	}

	public final void setHoverColor(final Color hoverColor) {

		this.hoverColorProperty().set(hoverColor);
	}

	// LAYER FACADE

	public ALayer getActiveLayer() {

		return layerPanel.getActiveLayer();
	}

	public void setLayerPanel(LayerPanel layerPanel) {

		this.layerPanel = layerPanel;

		layerPanel.imageProperty().bindBidirectional(activeImage);

	}

	public LayerPanel getLayerPanel() {

		return layerPanel;
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

	public ALayer getFocusLayer() {

		return focusLayer;
	}

	public void setFocusLayer(ALayer focusLayer) {

		this.focusLayer = focusLayer;
	}

	public void populateLayerPanel() {

		getLayerPanel().populate();

	}

	public PixImage getActiveImage() {

		PixTab tab = getActiveTab();
		PixImage activeImage = tab.getImage();

		return activeImage;
	}

	public Scene getScene() {

		return scene;
	}

	public void setScene(Scene scene) {

		this.scene = scene;
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

	public Map<PixImage, Selection> getSelections() {

		return selections;
	}

	public void setSelections(Map<PixImage, Selection> selections) {

		this.selections = selections;
	}

	public Selection getActiveSelection() {

		final PixImage image = getActiveimage();
		return this.selections.computeIfAbsent(image, key -> new Selection());

	}

	public MiniatureManager getMiniatureManager() {

		return miniatureManager;
	}

	public Map<Coord, ColorRGB> getClipboard() {

		return clipboard;
	}

	public void setClipboard(Map<Coord, ColorRGB> clipboard) {

		this.clipboard = clipboard;
	}

	public Pile<String> getRecentFiles() {

		return recentFiles;
	}

	public void setRecentFiles(Pile<String> recentFiles) {

		this.recentFiles = recentFiles;
	}

	public PixMenuBar getPixMenuBar() {

		return pixMenuBar;
	}

	public void setPixMenuBar(PixMenuBar pixMenuBar) {

		this.pixMenuBar = pixMenuBar;
	}

	public WebPanel getWebPanel() {

		return webPanel;
	}

	public LogInfo getLogInfo() {
		return getWebPanel().getLogInfo();
	}

	public void setWebPanel(WebPanel webPanel) {

		this.webPanel = webPanel;
	}

	public void reloadWebPanel() {
		getWebPanel().reload();

	}

	public void updateRecentFilesMenu() {

		getPixMenuBar().loadRecentFiles();

	}

	public boolean selectionIsEmpty() {

		return getClipboard().isEmpty();

	}

}
