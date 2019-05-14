
package com.ben.pixcreator.application.action.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PicLayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;
import com.ben.pixcreator.application.selection.Selection;

public class TranslateLayerAction implements IAction, ICancelable {

	private static final Logger log = LoggerFactory.getLogger(TranslateLayerAction.class);

	private final int				translateX;
	private final int				translateY;
	private final ALayer			layer;
	private Map<Coord, ColorRGB>	originalCells	= new HashMap<>();
	private Map<Coord, ColorRGB>	newCells		= new HashMap<>();
	private Map<Coord, ColorRGB>	unselectedCells	= new HashMap<>();;

	public TranslateLayerAction(int translateX, int translateY, ALayer layer, Selection selection) {

		super();
		this.translateX = translateX;
		this.translateY = translateY;
		this.layer = layer;
		if (layer instanceof PixLayer) {

			PixLayer pix = (PixLayer) layer;

			// compute cells to translate (selected or not)
			if (null == selection || selection.getCoords().isEmpty()) {

				originalCells = pix.getGrid();

			}

			else if (!selection.getCoords().isEmpty()) {

				originalCells = pix.getGrid().entrySet().stream()
						.filter(entry -> selection.getCoords().contains(entry.getKey()))
						.collect(Collectors.toMap(Entry::getKey, Entry::getValue));

				unselectedCells = pix.getGrid().entrySet().stream()
						.filter(entry -> !selection.getCoords().contains(entry.getKey()))
						.collect(Collectors.toMap(Entry::getKey, Entry::getValue));

			}

			// compute new cells position and add unselected cells
			newCells = originalCells.entrySet().stream()
					.collect(Collectors.toMap(entry -> new Coord(entry.getKey().getX() + translateX,
							entry.getKey().getY() + translateY), Entry::getValue));
			newCells.putAll(unselectedCells);
		}

	}

	@Override
	public void execute() throws Exception {

		if (layer instanceof PixLayer) {

			((PixLayer) layer).setGrid(newCells);

		}

		else if (layer instanceof PicLayer) {

			PicLayer pic = (PicLayer) layer;

			pic.setPosition(new Coord(pic.getPosition().getX() + translateX, pic.getPosition().getY() + translateY));

		}
		log.debug("layer : " + layer + " translateX : " + translateX + " translateY : " + translateY);
	}

	@Override
	public void cancel() throws Exception {

		if (layer instanceof PixLayer) {

			((PixLayer) layer).setGrid(originalCells);
			((PixLayer) layer).getGrid().putAll(unselectedCells);

		} else if (layer instanceof PicLayer) {

			PicLayer pic = (PicLayer) layer;
			pic.setPosition(new Coord(pic.getPosition().getX() - translateX, pic.getPosition().getY() - translateY));
		}

		log.debug("cancelling -> layer : " + layer + " translateX : " + translateX + " translateY : " + translateY);
	}

}
