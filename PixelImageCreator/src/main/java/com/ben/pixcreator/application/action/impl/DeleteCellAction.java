
package com.ben.pixcreator.application.action.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class DeleteCellAction implements IAction, ICancelable {
	private static final Logger log = LoggerFactory.getLogger(DeleteCellAction.class);

	private final PixImage	image;
	private final PixLayer	layer;
	private final Coord		coord;
	private final ColorRGB	color;

	public DeleteCellAction(PixImage image, PixLayer layer, Coord coord) {

		super();
		this.image = image;
		this.layer = layer;
		this.coord = coord;
		this.color = layer.getGrid().get(coord);
	}

	@Override
	public void cancel() throws Exception {

		if (GuiFacade.getInstance().getActiveimage() == image) {
			if (image.getLayerList().getIdx(layer) > -1) {
				log.debug("layer : " + layer + "coord : " + coord);
				layer.getGrid().put(coord, color);
			}
		}
	}

	@Override
	public void execute() throws Exception {

		if (GuiFacade.getInstance().getActiveimage() == image) {
			if (image.getLayerList().getIdx(layer) > -1) {
				log.debug("cancelling -> layer : " + layer + "coord : " + coord);
				layer.getGrid().remove(coord);
			}
		}
	}

}
