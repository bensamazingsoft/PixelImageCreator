package com.ben.pixcreator.action.impl;

import javafx.scene.paint.Color;

import com.ben.pixcreator.action.IAction;
import com.ben.pixcreator.image.coords.Coord;
import com.ben.pixcreator.image.layer.ILayer;
import com.ben.pixcreator.image.layer.impl.PixLayer;


public class ActionChangeCellColor implements IAction {

	
	private PixLayer layer;
	private Coord coord;
	private Color color;
	
	private Color prevColor;
	
	public ActionChangeCellColor(PixLayer layer, Coord coord, Color color) {
		

		this.layer = layer;
		this.coord = coord;
		this.color = color;
		
		prevColor = layer.getGrid().get(coord);
	
	}

	
	
	
	public void execute() {
		layer.getGrid().put(coord,color);

	}

	public void cancel() {
		layer.getGrid().put(coord,prevColor);

	}

}
