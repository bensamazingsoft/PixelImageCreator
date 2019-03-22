
package com.ben.pixcreator.gui.controls.tab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.math3.analysis.function.Logistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.factory.ActionFactoryProducer;
import com.ben.pixcreator.application.action.factory.IActionFactory;
import com.ben.pixcreator.application.action.impl.ActionNoOp;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PicLayer;
import com.ben.pixcreator.gui.cursor.factory.CanvasCursorFactory;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;

/**
 * public Logistic(double k, double m, double b, double q, double a, double n)
 * throws NotStrictlyPositiveException Parameters: k - If b > 0, value of the
 * function for x going towards inf If b < 0, value of the function for x going
 * towards -inf. m - Abscissa of maximum growth. b - Growth rate. q - Parameter
 * that affects the position of the curve along the ordinate axis. a - If b > 0,
 * value of the function for x going towards -inf. If b < 0, value of the
 * function for x going towards inf. n - Parameter that affects near which
 * asymptote the maximum growth occurs.
 * 
 * @author bmo
 *
 */
public class PixTab extends Tab implements Initializable {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(PixTab.class);

	// private final String IMAGEPATH = "images/gui/buttons/tab/";

	private SimpleObjectProperty<PixImage>	image				= new SimpleObjectProperty<PixImage>();
	private SimpleDoubleProperty			zoomFactor			= new SimpleDoubleProperty();
	private SimpleDoubleProperty			zoomFactorAdjusted	= new SimpleDoubleProperty();
	private SimpleBooleanProperty			panMode				= new SimpleBooleanProperty();

	private Logistic	logistic;
	private double		x, step;	// x is the abscissa of the logistic
	// function and step is the amount added/sub
	// of it for each scroll event

	@FXML
	private ScrollPane	scrollPane;
	@FXML
	private Canvas		canvas;

	// private static PixTool savedTool;

	public PixTab(PixImage image) {

		super();
		x = 0d;
		step = 0.5;
		setImage(image);
		setZoomFactor(1d);

		logistic = new Logistic(10.0, 2.7, 1.0, 1.0, 0.2, 1.0);

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixTab.fxml"), bundle);
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		panMode.bindBidirectional(GuiFacade.getInstance().panModeProperty());
		panMode.addListener((obs, oldVal, newVal) -> togglePanMode(newVal));

		setPanMode(GuiFacade.getInstance().isPanMode());

		canvas.setCursor(new CanvasCursorFactory().getCursor());
		scrollPane.setCursor(new CanvasCursorFactory().getCursor());

	}

	@FXML
	public void handleClose(Event event) {

		// onClose
		onClose();
	}

	private void onClose() {

		GuiFacade.getInstance().getImagesColors().remove(getUserData());
		Executor.getInstance().getHistoryMap().remove(getUserData());

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.setText(getImage().getName());
		this.setUserData(canvas);

		canvas = new Canvas(getImage().getxSize(), getImage().getySize());
		canvas.addEventHandler(MouseEvent.ANY, new MouseManager(this));

		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		StackPane stackpane = new StackPane(canvas);
		scrollPane.setContent(stackpane);
		scrollPane.addEventFilter(ScrollEvent.ANY, new ZoomControl());

		bindPicLayersZoomFactor();
		zoomFactor.addListener(new ZoomListener(this));

		setZoomFactorAdjusted(getZoomFactor());

		scrollPane.getStylesheets().add("/styles/styles.css");
		scrollPane.getStyleClass().add("scrollpane");

		stackpane.getStylesheets().add("/styles/styles.css");
		stackpane.getStyleClass().add("stackpane");

	}

	private class MouseManager implements EventHandler<MouseEvent> {

		protected PixTab tab;

		public MouseManager(PixTab tab) {

			this.tab = tab;
		}

		@Override
		public void handle(MouseEvent event) {

			IActionFactory factory = ActionFactoryProducer.getActionFactory(AppContext.getInstance().getCurrTool());

			try {

				IAction action = factory.getAction(event);

				if (action instanceof ActionNoOp) {
				} else {

					Executor.getInstance().executeAction(action);
					Executor.getInstance().executeAction(new RefreshTabAction(tab));

				}

			} catch (Exception e) {
				new ExceptionPopUp(e);
			}

		}

	}

	public class ZoomControl implements EventHandler<ScrollEvent> {

		@Override
		public void handle(ScrollEvent event) {

			zoom(event);
		}

		private void zoom(ScrollEvent event) {

			if (event.getDeltaY() > 0) {
				zoomIn();
			} else {
				zoomOut();
			}
			event.consume();
		}

	}

	class ZoomListener implements ChangeListener<Number> {

		private PixTab tab;

		public ZoomListener(PixTab tab) {

			super();
			this.tab = tab;
		}

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {

			{

				int resultX = (int) (getImage().getxSize() * (double) newVal);
				int xGridRes = getImage().getxGridResolution();

				while (resultX % xGridRes != 0) {

					resultX--;

				}

				int resultY = (int) (getImage().getySize() * (double) newVal);
				int yGridRes = getImage().getyGridResolution();

				while (resultY % yGridRes != 0) {

					resultY--;

				}

				setZoomFactorAdjusted((double) resultX / (double) getImage().getxSize());

				// log.debug("ZoomFactorAdjusted = " + getZoomFactorAdjusted());

				canvas.setWidth(resultX);
				canvas.setHeight(resultY);

				try {
					Executor.getInstance().executeAction(new RefreshTabAction(tab));
				} catch (Exception e) {
					new ExceptionPopUp(e);
				}

			}

		}

	}

	private void bindPicLayersZoomFactor() {

		for (ALayer layer : image.get().getLayerList().getAllItems())

		{
			if (layer instanceof PicLayer) {
				PicLayer picLayer = (PicLayer) layer;
				picLayer.zoomFactorProperty().bindBidirectional(zoomFactorAdjusted);

			}
		}

	}

	private void zoomIn() {

		Bounds viewPort = scrollPane.getViewportBounds();
		Bounds contentSize = scrollPane.getContent().getBoundsInParent();

		double centerPosX = (contentSize.getWidth() - viewPort.getWidth()) *
				scrollPane.getHvalue()
				+ viewPort.getWidth() / 2;
		double centerPosY = (contentSize.getHeight() - viewPort.getHeight())
				* scrollPane.getVvalue()
				+ viewPort.getHeight() / 2;

		x += step;
		zoomFactor.set(logistic.value(x));
		// log.debug("zoom in : X = " + x + " factor = " + zoomFactor.get());

		double newCenterX = centerPosX * zoomFactor.get() / (zoomFactor.get()
				- 1);
		double newCenterY = centerPosY * zoomFactor.get() / (zoomFactor.get()
				- 1);

		scrollPane.setHvalue((newCenterX - viewPort.getWidth() / 2)
				/ (contentSize.getWidth() * zoomFactor.get() / (zoomFactor.get() - 1)
						- viewPort.getWidth()));
		scrollPane.setVvalue((newCenterY - viewPort.getHeight() / 2)
				/ (contentSize.getHeight() * zoomFactor.get() / (zoomFactor.get() -
						1) - viewPort.getHeight()));

		// log.debug("scrollPane.getVvalue() : " + scrollPane.getVvalue());
		// log.debug("scrollPane.getVmin() : " + scrollPane.getVmin());
		// log.debug("scrollPane.getVmax() : " + scrollPane.getVmax());

	}

	private void zoomOut() {

		Bounds viewPort = scrollPane.getViewportBounds();
		Bounds contentSize = scrollPane.getContent().getBoundsInParent();

		double centerPosX = (contentSize.getWidth() - viewPort.getWidth()) *
				scrollPane.getHvalue()
				+ viewPort.getWidth() / 2;
		double centerPosY = (contentSize.getHeight() - viewPort.getHeight())
				* scrollPane.getVvalue()
				+ viewPort.getHeight() / 2;

		x -= step;
		zoomFactor.set(logistic.value(x));
		// log.debug("zoom out : X = " + x + " factor = " + zoomFactor.get());

		double newCenterX = centerPosX * zoomFactor.get() / (zoomFactor.get()
				+ 1);
		double newCenterY = centerPosY * zoomFactor.get() / (zoomFactor.get()
				+ 1);

		scrollPane.setHvalue((newCenterX - viewPort.getWidth() / 2)
				/ (contentSize.getWidth() * zoomFactor.get() / (zoomFactor.get() + 1)
						- viewPort.getWidth()));
		scrollPane.setVvalue((newCenterY - viewPort.getHeight() / 2)
				/ (contentSize.getHeight() * zoomFactor.get() / (zoomFactor.get() +
						1) - viewPort.getHeight()));

	}

	public Canvas getCanvas() {

		return canvas;
	}

	public final SimpleObjectProperty<PixImage> imageProperty() {

		return this.image;
	}

	public final PixImage getImage() {

		return this.imageProperty().get();
	}

	public final void setImage(final PixImage image) {

		this.imageProperty().set(image);
	}

	public final SimpleDoubleProperty zoomFactorProperty() {

		return this.zoomFactor;
	}

	public final double getZoomFactor() {

		return this.zoomFactorProperty().get();
	}

	public final void setZoomFactor(final double zoomFactor) {

		this.zoomFactorProperty().set(zoomFactor);
	}

	public final SimpleDoubleProperty zoomFactorAdjustedProperty() {

		return this.zoomFactorAdjusted;
	}

	public final double getZoomFactorAdjusted() {

		return this.zoomFactorAdjustedProperty().get();
	}

	public final void setZoomFactorAdjusted(final double zoomFactorAdjusted) {

		this.zoomFactorAdjustedProperty().set(zoomFactorAdjusted);
	}

	/**
	 * enables or disables the pan mode
	 * 
	 * @param b
	 */
	public void togglePanMode(boolean pan) {

		canvas.setMouseTransparent(pan);
		scrollPane.setPannable(pan);

	}

	public ScrollPane getScrollPane() {

		return scrollPane;
	}

	public final SimpleBooleanProperty panModeProperty() {

		return this.panMode;
	}

	public final boolean isPanMode() {

		return this.panModeProperty().get();
	}

	public final void setPanMode(final boolean panMode) {

		this.panModeProperty().set(panMode);
	}

}