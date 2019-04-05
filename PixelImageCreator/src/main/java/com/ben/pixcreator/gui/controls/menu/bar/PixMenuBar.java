
package com.ben.pixcreator.gui.controls.menu.bar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.impl.ActionChangeCellColor;
import com.ben.pixcreator.application.action.impl.ActionDeleteCell;
import com.ben.pixcreator.application.action.impl.LoadFileAction;
import com.ben.pixcreator.application.action.impl.OpenNewImageAction;
import com.ben.pixcreator.application.action.impl.PixellateAction;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.action.impl.SaveAction;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.application.pile.Pile;
import com.ben.pixcreator.application.selection.Selection;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class PixMenuBar extends MenuBar implements Initializable {

	private static final Logger log = LoggerFactory.getLogger(PixMenuBar.class);

	@FXML
	Menu fileMenu;

	public PixMenuBar() {

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixMenuBar.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@FXML
	private void newAction(final ActionEvent event) {

		handleNewAction();
	}

	private void handleNewAction() {

		try {

			Executor.getInstance().executeAction(new OpenNewImageAction());
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

	@FXML
	private void openAction(final ActionEvent event) {

		handleOpenAction();
	}

	private void handleOpenAction() {

		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("pix", "*.pix"));

		File file = fc.showOpenDialog(null);

		if (null != file) {

			if (file.exists()) {

				try {
					Executor.getInstance().executeAction(new LoadFileAction(file));
				} catch (Exception e) {
					new ExceptionPopUp(e);
				}

			}

		}

	}

	@FXML
	private void saveAction(ActionEvent event) {

		handleSaveAction();
	}

	private void handleSaveAction() {

		PixImage image = GuiFacade.getInstance().getActiveimage();

		if (!AppContext.getInstance().getFiles().containsKey(image)) {
			handleSaveAsAction();
		} else {
			try {
				Executor.getInstance().executeAction(new SaveAction(image));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}
		}

	}

	@FXML
	private void saveAsAction(ActionEvent event) {

		handleSaveAsAction();
	}

	private void handleSaveAsAction() {

		PixImage image = GuiFacade.getInstance().getActiveimage();

		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("pix", "*.pix"));

		File file = fc.showSaveDialog(null);
		if (null != file) {
			log.debug("saving image to : " + file.toString());
			if (null != file) {
				AppContext.getInstance().getFiles().put(image, file);
				handleSaveAction();
				GuiFacade.getInstance().getActiveTab().setText(file.getName());
			}
		}
	}

	@FXML
	private void exportAction(ActionEvent event) {
		handleExportAction();
	}

	private void handleExportAction() {

		final PixImage activeImage = GuiFacade.getInstance().getActiveImage();
		final Canvas canvas = new Canvas(activeImage.getxSize(), activeImage.getySize());
		activeImage.draw(canvas);
		WritableImage wi = canvas.snapshot(null, null);

		final FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PNG", "*.png"));
		File file = fileChooser.showSaveDialog(null);

		BufferedImage bi = SwingFXUtils.fromFXImage(wi, null);

		try {
			ImageIO.write(bi, "png", file);
		} catch (IOException e) {
			new ExceptionPopUp(e);
		}

	}

	// public static void saveToFile(Image image) {
	// File outputFile = new File("C:/JavaFX/");
	// BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
	// try {
	// ImageIO.write(bImage, "png", outputFile);
	// } catch (IOException e) {
	// throw new RuntimeException(e);
	// }
	// }

	@FXML
	public void cancel(final ActionEvent event) {

		handleCancel();
	}

	private void handleCancel() {

		try {
			Executor.getInstance().cancel();
			Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

	@FXML
	public void redo(final ActionEvent event) {

		handleRedo();
	}

	private void handleRedo() {

		try {
			Executor.getInstance().redo();
			Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

	@FXML
	public void unSelect(ActionEvent event) {

		handleUnSelect();
	}

	private void handleUnSelect() {

		PixImage activeImage = GuiFacade.getInstance().getActiveimage();
		GuiFacade.getInstance().getSelections().remove(activeImage);
		PixTab tab = GuiFacade.getInstance().getActiveTab();

		try {
			// Executor.getInstance().executeAction(new
			// ActionUpdateSelection(activeImage));
			Executor.getInstance().executeAction(new RefreshTabAction(tab));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

	@FXML
	public void extractAction(ActionEvent event) {
		handleExtractAction();
	}

	private void handleExtractAction() {

		PixImage activeImage = GuiFacade.getInstance().getActiveimage();
		Selection selection = GuiFacade.getInstance().getActiveSelection();
		PixTab tab = GuiFacade.getInstance().getActiveTab();

		try {

			Executor.getInstance()
					.executeAction(new PixellateAction(PixellateAction.actionType.CENTERPICK, tab, activeImage));

			final Pile<ALayer> layerList = activeImage.getLayerList();
			ALayer newLayer = layerList.getItem(layerList.getAllItems().size() - 1);

			if (null != newLayer && newLayer instanceof PixLayer && !selection.getCoords().isEmpty()) {

				PixLayer pixL = (PixLayer) newLayer;
				Map<Coord, ColorRGB> grid = new HashMap<>(pixL.getGrid());
				final Map<Coord, ColorRGB> filteredGrid = grid.entrySet().stream()
						.filter(entry -> selection.getCoords().contains(entry.getKey()))
						.collect(Collectors.toMap(Entry::getKey, Entry::getValue));

				pixL.setGrid(filteredGrid);

			}

			GuiFacade.getInstance().getLayerPanel().populate();
			Executor.getInstance().executeAction(new RefreshTabAction(tab));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

	@FXML
	public void copy() {
		handleCopy();
	}

	private void handleCopy() {

		final GuiFacade gui = GuiFacade.getInstance();
		PixImage activeImage = gui.getActiveimage();
		Selection selection = gui.getActiveSelection();

		if (!selection.getCoords().isEmpty()) {
			ALayer activeLayer = gui.getActiveLayer();
			if (activeLayer instanceof PixLayer) {

				PixLayer pixLayer = (PixLayer) activeLayer;

				Map<Coord, ColorRGB> clipboard = pixLayer.getGrid().entrySet().stream()
						.filter(entry -> selection.getCoords().contains(entry.getKey()))
						.collect(Collectors.toMap(Entry::getKey, Entry::getValue));

				gui.setClipboard(clipboard);
			}
			gui.getSelections().remove(activeImage);

			try {
				Executor.getInstance().executeAction(new RefreshTabAction(gui.getActiveTab()));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}
		}

	}

	@FXML
	public void cut() {
		handleCut();
	}

	private void handleCut() {

		final GuiFacade gui = GuiFacade.getInstance();

		handleCopy();

		if (!gui.getClipboard().isEmpty() && gui.getActiveLayer() instanceof PixLayer) {

			PixImage activeImage = gui.getActiveimage();

			PixLayer pixLayer = (PixLayer) gui.getActiveLayer();

			final Executor exec = Executor.getInstance();
			exec.startOperation();

			gui.getClipboard().forEach((k, v) -> {

				try {
					exec.continueOperation(new ActionDeleteCell(activeImage, pixLayer, k));
				} catch (Exception e) {
					exec.endOperation();
					new ExceptionPopUp(e);
				}

			});

			exec.endOperation();

		}
		try {
			Executor.getInstance().executeAction(new RefreshTabAction(gui.getActiveTab()));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}
	}

	@FXML
	public void paste() {
		handlePaste();
	}

	private void handlePaste() {

		final GuiFacade gui = GuiFacade.getInstance();

		if (!gui.getClipboard().isEmpty() && gui.getActiveLayer() instanceof PixLayer) {

			PixLayer pixLayer = (PixLayer) gui.getActiveLayer();

			final Executor exec = Executor.getInstance();
			exec.startOperation();

			gui.getClipboard().forEach((coord, colorRGB) -> {

				try {
					exec.continueOperation(
							new ActionChangeCellColor(gui.getActiveimage(), pixLayer, coord, colorRGB.getFxColor()));
				} catch (Exception e) {
					exec.endOperation();
					new ExceptionPopUp(e);
				}

			});

			exec.endOperation();

		}
		try {
			Executor.getInstance().executeAction(new RefreshTabAction(gui.getActiveTab()));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}
	}

	@FXML
	private void fillAction(ActionEvent event) {

		final GuiFacade gui = GuiFacade.getInstance();
		Selection selection = gui.getActiveSelection();

		if (!selection.getCoords().isEmpty() && gui.getActiveLayer() instanceof PixLayer) {

			Color color = gui.getActiveColor();

			final Executor exec = Executor.getInstance();
			exec.startOperation();

			for (Coord coord : selection.getCoords()) {

				try {

					exec.continueOperation(
							new ActionChangeCellColor(
									gui.getActiveimage(),
									(PixLayer) gui.getActiveLayer(),
									coord,
									color));

				} catch (Exception e) {
					exec.endOperation();
					new ExceptionPopUp(e);
					break;
				}

			}

			if (exec.isOperationStarted()) {
				exec.endOperation();
			}

		}
	}

	private void handlePixellateAction(PixellateAction.actionType type) {

		PixImage activeImage = GuiFacade.getInstance().getActiveimage();
		// GuiFacade.getInstance().getSelections().remove(activeImage);
		PixTab tab = GuiFacade.getInstance().getActiveTab();

		try {
			Executor.getInstance().executeAction(new PixellateAction(type, tab, activeImage));
			GuiFacade.getInstance().getLayerPanel().populate();
			Executor.getInstance().executeAction(new RefreshTabAction(tab));
		} catch (Exception e) {
			new ExceptionPopUp(e);
		}

	}

	@FXML
	private void centerPickPixellateAction(ActionEvent event) {
		handlePixellateAction(PixellateAction.actionType.CENTERPICK);
	}

	@FXML
	private void averagePixellateAction(ActionEvent event) {
		handlePixellateAction(PixellateAction.actionType.AVERAGE);
	}

	public Pile<String> getRecentFiles() {
		return GuiFacade.getInstance().getRecentFiles();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		GuiFacade.getInstance().setPixMenuBar(this);

		// loadRecentFiles();

	}

	public void loadRecentFiles() {

		fileMenu.getItems().clear();

		if (!getRecentFiles().isEmpty()) {

			int count = 0;
			for (int i = getRecentFiles().getAllItems().size() - 1; i >= 0; i--) {

				File file = new File(getRecentFiles().getItem(i));
				if (file.exists()) {
					fileMenu.getItems().add(menuItem(count++, file));
				}

			}

		}
	}

	private MenuItem menuItem(int i, File file) {

		MenuItem item = new MenuItem();

		item.setText(i + "-" + file.getName());

		item.setOnAction(event -> {

			try {
				Executor.getInstance().executeAction(new LoadFileAction(file));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}

		});

		return item;
	}
}
