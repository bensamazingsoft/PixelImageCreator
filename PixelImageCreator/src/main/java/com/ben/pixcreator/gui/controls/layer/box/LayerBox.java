
package com.ben.pixcreator.gui.controls.layer.box;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.gui.fx.pile.view.PileView;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.grouplock.GroupLock;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.factory.impl.EffectPileViewItemFactory;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.BakeLayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.TextLayer;
import com.ben.pixcreator.application.pile.Pile;
import com.ben.pixcreator.gui.context.menu.provider.ContextMenuProvider;
import com.ben.pixcreator.gui.context.menu.provider.LayerBoxContextMenuProvider;
import com.ben.pixcreator.gui.controls.layer.panel.LayerPanel;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LayerBox extends VBox implements Initializable, Toggle {

	private static final Logger log = LoggerFactory.getLogger(LayerBox.class);

	private ContextMenuProvider	contextMenuProvider	= new LayerBoxContextMenuProvider();
	private LayerBox			layerBox			= this;

	// properties
	private final int	MINIATUREHEIGHT;
	private final int	MINIATUREWIDTH;
	private final int	BOXHEIGHT;
	private final int	BOXWIDTH;

	// toggle fields
	private ObjectProperty<ToggleGroup>	toggleGroup	= new SimpleObjectProperty<ToggleGroup>();
	private SimpleBooleanProperty		selected	= new SimpleBooleanProperty();
	ObservableMap<Object, Object>		properties;

	// instance fields
	private PixImage				image;
	private ALayer					layer;
	private SimpleBooleanProperty	locked	= new SimpleBooleanProperty();

	private final String IMAGEPATH = "images/gui/buttons/layerbox/";

	private Image	imgTypePicImg	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "imgTypePicImg.png"));
	private Image	imgTypePixImg	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "imgTypePixImg.png"));
	private Image	imgTypeTextImg	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "imgTypeTextImg.png"));
	private Image	imgTypeFxImg	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "imgTypeFxImg.png"));

	@FXML
	private ImageView imgTypeView;

	final private Image		lockSelected	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "lockSelected.png"));
	final private Image		lockUnSelected	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "lockUnSelected.png"));
	final private ImageView	lockButImg		= new ImageView();

	final private Image		eyeSelected		= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "eyeSelected.png"));
	final private Image		eyeUnSelected	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "eyeUnSelected.png"));
	final private ImageView	eyeButImg		= new ImageView();

	@FXML
	private ToggleButton eyeBut;

	@FXML
	private ToggleButton lockBut;

	@FXML
	private StackPane eye;

	@FXML
	private StackPane miniaturePane;

	@FXML
	private ImageView imageView;

	// private Canvas canvas;

	@FXML
	private StackPane titlePane;

	@FXML
	private StackPane lockPane;

	@FXML
	private Pane effectBox;

	public LayerBox(PixImage image, ALayer layer) throws NumberFormatException {

		super();
		getStylesheets().add("/styles/styles.css");
		getStyleClass().add("layerbox");

		MINIATUREHEIGHT = Integer.valueOf(AppContext.getInstance().propertyContext().get("miniatureWH"));
		MINIATUREWIDTH = Integer.valueOf(AppContext.getInstance().propertyContext().get("miniatureWH"));
		BOXHEIGHT = Integer.valueOf(AppContext.getInstance().propertyContext().get("layerBoxH"));
		BOXWIDTH = Integer.valueOf(AppContext.getInstance().propertyContext().get("layerBoxW"));

		this.image = image;
		this.layer = layer;

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LayerBox.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		setLocked(false);

		this.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

			@Override
			public void handle(ContextMenuEvent event) {

				contextMenuProvider.getMenu(layer).show(layerBox, event.getScreenX(), event.getScreenY());
			}
		});

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		eyeBut.setGraphic(eyeButImg);
		eyeBut.selectedProperty().bindBidirectional(layer.visibleProperty());
		eyeBut.selectedProperty().addListener((obs, oldVal, newVal) -> {

			PixTab tab = GuiFacade.getInstance().getActiveTab();

			try {
				Executor.getInstance().executeAction(new RefreshTabAction(tab));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}

		});
		eyeButImg.imageProperty()
				.bind(Bindings.when(eyeBut.selectedProperty()).then(eyeSelected).otherwise(eyeUnSelected));

		lockBut.setGraphic(lockButImg);
		lockButImg.imageProperty()
				.bind(Bindings.when(lockedProperty()).then(lockSelected).otherwise(lockUnSelected));

		locked.addListener((obs, oldVal, newVal) -> {
			try {
				Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}
		});

		imgTypeView.imageProperty().setValue(
				layer instanceof PixLayer ? imgTypePixImg
						: (layer instanceof TextLayer ? imgTypeTextImg
								: (layer instanceof BakeLayer ? imgTypeFxImg : imgTypePicImg)));

		// this.setMaxHeight(BOXHEIGHT);
		this.setMinHeight(BOXHEIGHT);
		this.setMinWidth(BOXWIDTH);
		HBox.setHgrow(titlePane, Priority.ALWAYS);

		miniaturePane.setStyle("-fx-background-color:white");
		miniaturePane.setMinWidth(MINIATUREWIDTH);
		miniaturePane.setMinHeight(MINIATUREWIDTH);
		// canvas.setHeight(MINIATUREHEIGHT);
		// canvas.setWidth(MINIATUREWIDTH);

		selected.addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				setStyle("-fx-background-color:" + AppContext.getInstance().propertyContext().get("selectColor1"));
			} else {
				setStyle("-fx-background-color:" + AppContext.getInstance().propertyContext().get("selectColor2"));
			}
		});

		loadEffectsPane();

	}

	/**
	 * Re-draw the effect pane
	 * 
	 */
	public void loadEffectsPane() {

		effectBox.getChildren().clear();

		Pile<Effect> effects = AppContext.getInstance().getEffectManager().getImageLayerEffects(image, layer);
		if (!effects.isEmpty()) {

			String DISPLAY_TITLE = AppContext.getInstance().getBundle().getString("effectcontentDipslayTitle");

			Map<ALayer, SimpleBooleanProperty> imageEffectPaneExpand = LayerPanel.effectPaneExpand.computeIfAbsent(
					image, piximage -> new HashMap<ALayer, SimpleBooleanProperty>());

			SimpleBooleanProperty expand = imageEffectPaneExpand.computeIfAbsent(layer,
					alayer -> new SimpleBooleanProperty(true));

			PileView pileView = new PileView(DISPLAY_TITLE, effects, new EffectPileViewItemFactory(), expand);
			pileView.getStyleClass().add("effectView");
			effectBox.getChildren().add(pileView);
		}
	}

	@FXML
	private void toggleEye(MouseEvent event) {

	}

	@FXML
	private void toggleLock(MouseEvent event) {

		toggleLayerLock();
	}

	private void toggleLayerLock() {

		GroupLock groupLock = AppContext.getInstance().getGroupLocks().get(GuiFacade.getInstance().getActiveImage());

		if (groupLock.getLockedLayers(GuiFacade.getInstance().getActiveLayer()).contains(layer)) {
			groupLock.unlock(layer);
			setLocked(false);
		} else {
			groupLock.lockToActiveLayer(layer);
			setLocked(true);
		}

	}

	@FXML
	private void handleMiniatureClicked(MouseEvent event) {

		miniatureClicked();

		if (event.isControlDown()) {
			soloLayer();
		}

	}

	private void miniatureClicked() {

		if (!isSelected()) {
			toggleGroup.get().selectToggle(this);
		}

	}

	private void soloLayer() {

		((ALayer) getUserData()).setVisible(true);

		// toggle all other layers visibility to false
		toggleGroup.get().getToggles().stream()
				.map(Toggle::getUserData)
				.map(obj -> (ALayer) obj)
				.filter(layer -> !layer.equals(this.layer))
				.forEach(layer -> layer.setVisible(false));

	}

	@Override
	public ToggleGroup getToggleGroup() {

		return toggleGroup.get();
	}

	public ALayer getUserData() {

		return layer;
	}

	@Override
	public void setToggleGroup(ToggleGroup arg0) {

		toggleGroup.set(arg0);
		toggleGroup.get().getToggles().add(this);
		toggleGroup.get().selectToggle(this);
	}

	public ObjectProperty<ToggleGroup> toggleGroupProperty() {

		return toggleGroup;
	}

	@Override
	public boolean isSelected() {

		return selected.get();
	}

	@Override
	public BooleanProperty selectedProperty() {

		return selected;
	}

	@Override
	public void setSelected(boolean value) {

		selected.set(value);

	}

	public final SimpleBooleanProperty lockedProperty() {

		return this.locked;
	}

	public final boolean isLocked() {

		return this.lockedProperty().get();
	}

	public final void setLocked(final boolean locked) {

		this.lockedProperty().set(locked);
	}

	public ALayer getLayer() {

		return layer;
	}

	public ImageView getImageView() {

		return imageView;
	}

	public void setImageView(ImageView imageView) {

		this.imageView = imageView;
	}

	// public Canvas getCanvas()
	// {
	//
	// return canvas;
	// }

}
