
package com.ben.pixcreator.application.image;

import java.io.Serializable;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.layer.ILayer;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PicLayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.application.pile.Pile;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class PixImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(PixImage.class);

	// TODO manage the 'changed' state : do you save on close ? is there a '*'
	// beside the name ?
	private static final int	DEFAULTSIZE				= 800;
	private static final int	DEFAULTGRIDRESOLUTION	= 80;

	private String		name;
	private LocalDate	dateCre;

	private int	xSize, ySize;
	private int	xGridResolution, yGridResolution;

	private PixLayer ghost, select;
	// layer and its visibility

	private Pile<ALayer> layerList;

	public PixImage() {

		name = "sans_titre";
		dateCre = LocalDate.now();
		ghost = new PixLayer();
		select = new PixLayer();

		layerList = new Pile<>();

		layerList.add(new PixLayer());

		xSize = ySize = DEFAULTSIZE;

		xGridResolution = yGridResolution = DEFAULTGRIDRESOLUTION;

	}

	public PixImage(String name) {

		this();
		this.name = name;
	}

	public PixImage(String name, int xSize, int ySize) {

		super();
		this.name = name;
		this.xSize = xSize;
		this.ySize = ySize;
	}

	public PixImage(String name, PicLayer basePic) {

		this();
		this.name = name;
		// this.basePic = basePic;
	}

	public void draw(Canvas canvas) {

		final GraphicsContext graphic = canvas.getGraphicsContext2D();
		graphic.setFill(GuiFacade.getInstance().getBackgroundColor());
		graphic.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		for (int i = 0; i < layerList.getItems().size(); i++) {

			ALayer layer = layerList.getItem(i);

			if (layer.isVisible()) {
				// log.debug("drawing " + layer.toString());
				layer.draw(canvas, xGridResolution, yGridResolution);
			}
		}

		select.draw(canvas, xGridResolution, yGridResolution);

		ghost.draw(canvas, xGridResolution, yGridResolution);

		if (GuiFacade.getInstance().isShowGrid()) {
			showGrid(canvas);
		}

	}

	// show layer grid in canvas if option is toggled on
	private void showGrid(Canvas canvas) {

		GraphicsContext graphics = canvas.getGraphicsContext2D();

		double xCanvasSize = canvas.getWidth();
		int xCellSize = (int) xCanvasSize / xGridResolution;
		double yCanvasSize = canvas.getHeight();
		int yCellSize = (int) yCanvasSize / yGridResolution;

		graphics.setStroke(AppContext.getInstance().getGridColor());

		for (int x = 0; x < xCanvasSize; x += xCellSize) {
			graphics.strokeLine(x, 0, x, yCanvasSize);

		}
		for (int y = 0; y < yCanvasSize; y += yCellSize) {
			graphics.strokeLine(0, y, xCanvasSize, y);
		}

	}

	@Override
	public String toString() {

		return "PixImage [name=" + name + ", xSize=" + xSize + ", ySize=" + ySize + ", xGridResolution="
				+ xGridResolution + ", yGridResolution=" + yGridResolution + "]";
	}

	/**
	 * @return a clone of the image, including a clone for each layers
	 */
	public PixImage duplicate() {

		PixImage clone = new PixImage();

		clone.name = this.name + "_copie";
		clone.dateCre = LocalDate.now();
		clone.ghost = this.ghost.duplicate();
		clone.select = this.select.duplicate();

		clone.layerList = new Pile<ALayer>();

		for (int i = 0; i < this.layerList.getItems().size(); i++) {
			ALayer layer = this.layerList.getItem(i);
			clone.layerList.add(layer.duplicate());
		}

		clone.xSize = this.xSize;
		clone.ySize = this.ySize;

		clone.xGridResolution = this.xGridResolution;
		clone.yGridResolution = yGridResolution;

		return clone;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public int getxSize() {

		return xSize;
	}

	public void setxSize(int xSize) {

		this.xSize = xSize;
	}

	public int getySize() {

		return ySize;
	}

	public void setySize(int ySize) {

		this.ySize = ySize;
	}

	public int getxGridResolution() {

		return xGridResolution;
	}

	public void setxGridResolution(int xGridResolution) {

		this.xGridResolution = xGridResolution;
	}

	public int getyGridResolution() {

		return yGridResolution;
	}

	public void setyGridResolution(int yGridResolution) {

		this.yGridResolution = yGridResolution;
	}

	public ILayer getGhost() {

		return ghost;
	}

	public void setGhost(PixLayer ghost) {

		this.ghost = ghost;
	}

	public PixLayer getSelect() {

		return select;
	}

	public void setSelect(PixLayer select) {

		this.select = select;
	}

	public Pile<ALayer> getLayerList() {

		return layerList;
	}

	public void setLayerList(Pile<ALayer> layers) {

		this.layerList = layers;
	}

}
