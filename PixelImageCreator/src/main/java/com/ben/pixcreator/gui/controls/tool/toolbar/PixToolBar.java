
package com.ben.pixcreator.gui.controls.tool.toolbar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.action.impl.RefreshAllTabsAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.tools.PixTool;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PixToolBar extends ToolBar implements Initializable {

	private final String IMAGEPATH = "images/gui/buttons/tools/";

	@FXML
	private ToggleGroup toggleGroup;

	@FXML
	private ToggleButton selectBut;

	@FXML
	private ToggleButton drawBut;

	@FXML
	private ToggleButton pickBut;

	@FXML
	private ToggleButton moveBut;

	@FXML
	private ToggleButton panBut;

	@FXML
	private ToggleButton showGridBut;

	// get images
	final private Image		selectButSelected	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "selectButSelected.png"));
	final private Image		selectButUnSelected	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "selectButUnSelected.png"));
	final private ImageView	selectButImg		= new ImageView();

	final private Image		drawButSelected		= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "drawButSelected.png"));
	final private Image		drawButUnSelected	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "drawButUnSelected.png"));
	final private ImageView	drawButImg			= new ImageView();

	final private Image		pickButSelected		= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "pickButSelected.png"));
	final private Image		pickButUnSelected	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "pickButUnSelected.png"));
	final private ImageView	pickButImg			= new ImageView();

	final private Image		moveButSelected		= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "moveButSelected.png"));
	final private Image		moveButUnSelected	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "moveButUnSelected.png"));
	final private ImageView	moveButImg			= new ImageView();

	final private Image		panButSelected		= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "panButSelected.png"));
	final private Image		panButUnSelected	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "panButUnSelected.png"));
	final private ImageView	panButImg			= new ImageView();

	final private Image		showGridButSelected		= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "showGridButSelected.png"));
	final private Image		showGridButUnSelected	= new Image(
			getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "showGridButUnSelected.png"));
	final private ImageView	showGridButImg			= new ImageView();

	public PixToolBar() {

		toggleGroup = new ToggleGroup();

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixToolBar.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		GuiFacade.getInstance().setPixToolBar(this);
		GuiFacade.getInstance().toggleToolTo(AppContext.getInstance().getCurrTool());
	}

	@FXML
	private void toggleSelect(ActionEvent event) {

		handleToggle();
	}

	@FXML
	private void toggleDraw(ActionEvent event) {

		handleToggle();

	}

	@FXML
	private void togglePick(ActionEvent event) {

		handleToggle();
	}

	@FXML
	private void toggleMove(ActionEvent event) {

		handleToggle();
	}

	@FXML
	private void togglePan(ActionEvent event) {

		handleToggle();
	}

	@FXML
	private void toggleResize(ActionEvent event) {

		handleToggle();
	}

	@FXML
	private void toggleZoomIn(ActionEvent event) {

		handleToggle();
	}

	@FXML
	private void toggleZoomOut(ActionEvent event) {

		handleToggle();
	}

	private void handleToggle() {

		// handle tool toggle action with gui facade
		GuiFacade guiFacade = GuiFacade.getInstance();

		guiFacade.toggleToolTo((PixTool) toggleGroup.getSelectedToggle().getUserData());

	}

	@FXML
	private void toggleShowGrid(ActionEvent event) {

		handleToggleShowGrid();
	}

	private void handleToggleShowGrid() {

		if (null != GuiFacade.getInstance().getActiveimage()) {
			try {
				Executor.getInstance().executeAction(new RefreshAllTabsAction());
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		selectBut.setGraphic(selectButImg);
		selectBut.setUserData(PixTool.SELECT);
		selectButImg.imageProperty().bind(
				Bindings.when(selectBut.selectedProperty()).then(selectButSelected).otherwise(selectButUnSelected));

		selectBut.addEventFilter(MouseEvent.ANY, event -> {
			if (AppContext.getInstance().getCurrTool() == PixTool.SELECT) {
				event.consume();
			}
		});

		drawBut.setGraphic(drawButImg);
		drawBut.setUserData(PixTool.DRAW);
		drawButImg.imageProperty()
				.bind(Bindings.when(drawBut.selectedProperty()).then(drawButSelected).otherwise(drawButUnSelected));

		drawBut.addEventFilter(MouseEvent.ANY, event -> {
			if (AppContext.getInstance().getCurrTool() == PixTool.DRAW) {
				event.consume();
			}
		});

		pickBut.setGraphic(pickButImg);
		pickBut.setUserData(PixTool.PICK);
		pickButImg.imageProperty()
				.bind(Bindings.when(pickBut.selectedProperty()).then(pickButSelected).otherwise(pickButUnSelected));

		pickBut.addEventFilter(MouseEvent.ANY, event -> {
			if (AppContext.getInstance().getCurrTool() == PixTool.PICK) {
				event.consume();
			}
		});

		moveBut.setGraphic(moveButImg);
		moveBut.setUserData(PixTool.MOVE);
		moveButImg.imageProperty()
				.bind(Bindings.when(moveBut.selectedProperty()).then(moveButSelected).otherwise(moveButUnSelected));

		moveBut.addEventFilter(MouseEvent.ANY, event -> {
			if (AppContext.getInstance().getCurrTool() == PixTool.MOVE) {
				event.consume();
			}
		});

		panBut.setGraphic(panButImg);
		panBut.setUserData(PixTool.PAN);
		panButImg.imageProperty()
				.bind(Bindings.when(panBut.selectedProperty()).then(panButSelected).otherwise(panButUnSelected));

		panBut.addEventFilter(MouseEvent.ANY, event -> {
			if (AppContext.getInstance().getCurrTool() == PixTool.PAN) {
				event.consume();
			}
		});

		showGridBut.setGraphic(showGridButImg);
		showGridButImg.imageProperty().bind(
				Bindings.when(showGridBut.selectedProperty()).then(showGridButSelected)
						.otherwise(showGridButUnSelected));

		showGridBut.selectedProperty().bindBidirectional(GuiFacade.getInstance().showGridProperty());

	}

	public ToggleGroup getToggleGroup() {

		return toggleGroup;
	}

	public void setToggleGroup(ToggleGroup toggleGroup) {

		this.toggleGroup = toggleGroup;
	}

}
