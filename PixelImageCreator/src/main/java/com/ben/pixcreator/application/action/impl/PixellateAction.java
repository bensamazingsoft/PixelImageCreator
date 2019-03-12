
package com.ben.pixcreator.application.action.impl;

import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.application.selection.Selection;
import com.ben.pixcreator.gui.controls.tab.PixTab;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class PixellateAction implements IAction {

	private final PixImage	image;
	private final PixTab	tab;
	final GuiFacade			gui	= GuiFacade.getInstance();

	public PixellateAction(PixTab tab, PixImage activeImage) {

		this.image = activeImage;
		this.tab = tab;
	}

	@Override
	public void execute() throws Exception {

		Canvas canvas = tab.getCanvas();

		WritableImage snap = makeSnapShot(canvas);

		PixelReader reader = snap.getPixelReader();

		int xCellSize = (int) (canvas.getWidth() / image.getxGridResolution());
		int yCellSize = (int) (canvas.getHeight() / image.getyGridResolution());

		Map<Coord, ColorRGB> grid = new HashMap<>();
		for (int x = 0; x < canvas.getWidth(); x += xCellSize) {
			for (int y = 0; y < canvas.getHeight(); y += yCellSize) {

				ColorRGB average = pickCellCenterPixelColor(reader, x, y, xCellSize, yCellSize);

				// exclude BG color
				if (!average.getColor().equals(gui.getBackgroundColor())) {
					grid.put(new Coord(x / xCellSize, y / yCellSize), average);
				}

			}
		}

		image.getLayerList().add(new PixLayer(grid));

	}

	private WritableImage makeSnapShot(Canvas canvas) throws Exception {

		// deactivate grid lest it pollutes the color averaging
		boolean showGrid = gui.isShowGrid();
		Selection selection = GuiFacade.getInstance().getSelections().computeIfAbsent(image,
				img -> new Selection());

		gui.getSelections().remove(image);
		gui.setShowGrid(false);

		Executor.getInstance().executeAction(new RefreshTabAction(tab));

		WritableImage snap = canvas.snapshot(null, null);

		gui.setShowGrid(showGrid);
		gui.getSelections().put(image, selection);

		return snap;
	}

	// private ColorRGB averageColor(PixelReader reader, int x, int y, int
	// xCellSize, int yCellSize)
	// {
	//
	// Color average = Color.WHITE;
	// List<Color> colors = new ArrayList<>();
	//
	// for (int i = 0; i < xCellSize; i++)
	// {
	// for (int j = 0; j < yCellSize; j++)
	// {
	//
	// colors.add(reader.getColor(x + i, y + j));
	//
	// }
	// }
	//
	// average = ColorUtils.averageColor(colors);
	//
	// return new ColorRGB(average);
	// }

	private ColorRGB pickCellCenterPixelColor(PixelReader reader, int x, int y, int xCellSize, int yCellSize) {

		int centerX = x + xCellSize / 2;
		int centerY = y + yCellSize / 2;

		return new ColorRGB(reader.getColor(centerX, centerY));
	}

}