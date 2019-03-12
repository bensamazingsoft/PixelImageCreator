
package com.ben.pixcreator.application.action.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.paint.Color;

public class ActionChangeCellColor implements IAction, ICancelable {

	private static final Logger log = LoggerFactory.getLogger(ActionChangeCellColor.class);

	private PixImage	image;
	private PixLayer	layer;
	private Coord		coord;
	private Color		color;

	private Color prevColor;

	private final boolean newCell;

	public ActionChangeCellColor(PixImage image, PixLayer layer, Coord coord, Color color) {

		this.image = image;
		this.layer = layer;
		this.coord = coord;
		this.color = color;

		this.newCell = !layer.getGrid().containsKey(coord);

		if (!newCell) {
			prevColor = layer.getGrid().get(coord).getFxColor();
		}

	}

	public void execute() throws Exception {

		if (GuiFacade.getInstance().getActiveimage() == image) {
			if (image.getLayerList().getIdx(layer) > -1) {

				layer.getGrid().put(coord, new ColorRGB(color));
				// log.debug("Change cell color at coord : " + coord);

			}
		}
	}

	public void cancel() throws Exception {

		if (GuiFacade.getInstance().getActiveimage() == image) {
			if (image.getLayerList().getIdx(layer) > -1) {
				if (newCell) {
					layer.getGrid().remove(coord);
				} else {
					layer.getGrid().put(coord, new ColorRGB(prevColor));
				}
			}

		}

	}

	@Override
	public String toString() {

		return "ActionChangeCellColor (" + newCell + ") [layer=" + layer + ", coord=" + coord + ", color=" + color
				+ "]";
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + (newCell ? 0 : color.hashCode());
		result = prime * result + (newCell ? 0 : coord.hashCode());
		result = prime * result + (newCell ? 0 : layer.hashCode());
		result = prime * result + (newCell ? 0 : prevColor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ActionChangeCellColor)) {
			return false;
		}
		ActionChangeCellColor other = (ActionChangeCellColor) obj;
		if (color == null) {
			if (other.color != null) {
				return false;
			}
		} else if (!color.equals(other.color)) {
			return false;
		}
		if (coord == null) {
			if (other.coord != null) {
				return false;
			}
		} else if (!coord.equals(other.coord)) {
			return false;
		}
		if (layer == null) {
			if (other.layer != null) {
				return false;
			}
		} else if (!layer.equals(other.layer)) {
			return false;
		}
		if (prevColor == null) {
			if (other.prevColor != null) {
				return false;
			}
		} else if (!prevColor.equals(other.prevColor)) {
			return false;
		}
		return true;
	}

}
