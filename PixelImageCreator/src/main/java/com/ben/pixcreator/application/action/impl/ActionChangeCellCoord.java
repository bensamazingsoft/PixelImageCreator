package com.ben.pixcreator.application.action.impl;

import javafx.scene.paint.Color;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;

public class ActionChangeCellCoord implements IAction {

	private PixImage image;
	private PixLayer layer;
	private Coord oldCoord;
	private Coord newCoord;
	private Color color;
	
	public ActionChangeCellCoord(){
		layer = new PixLayer();
		oldCoord = new Coord();
		newCoord = new Coord();
		color = Color.BLACK;
	}
	
	public ActionChangeCellCoord(PixImage image, PixLayer layer, Coord oldCoord, Coord newCoord) {
		
		if (!AppContext.getInstance().getOpenImages().contains(image)){
			throw new ClosedImageException();
		} else if (!image.getLayers().contains(layer)){
			throw new InexistantLayerException();
		}

		this.layer = layer;
		this.oldCoord = oldCoord;
		this.newCoord = newCoord;
		
		color = layer.getGrid().get(oldCoord);
		
	}

	public void execute() {
		
		if (AppContext.getInstance().getOpenImages().contains(image)){
			if (image.getLayers().contains(layer)){
		layer.getGrid().remove(oldCoord);
		layer.getGrid().put(newCoord,color);
			}
		}
	}

	public void cancel() {
		if (AppContext.getInstance().getOpenImages().contains(image)){
			if (image.getLayers().contains(layer)){
		layer.getGrid().remove(newCoord);
		layer.getGrid().put(oldCoord,color);
			}}
	}

}
