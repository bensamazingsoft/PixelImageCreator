package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.collection.LayerList;
import com.ben.pixcreator.gui.controls.layer.panel.actions.LayerActions;

public class LayerAction implements IAction, ICancelable {

	private final LayerList		layerList;
	private final ALayer		layer;
	private final LayerActions	action;

	public LayerAction(PixImage image, ALayer layer, LayerActions action) {
		super();
		this.layerList = image.getLayerList();
		this.layer = layer;
		this.action = action;
	}

	@Override
	public void execute() throws Exception {
		// TODO unlock layer if locked & delete layer from image layerList

		switch (action) {
		case ADDNEW:
			addNew();
			break;
		case DELETE:
			delete();
			break;
		case DUPLICATE:
			duplicate();
			break;
		case MOVEDOWN:
			moveDown();
			break;
		case MOVEUP:
			moveUp();
			break;
		default:
			break;

		}

	}

	private void moveUp() {
		// TODO Auto-generated method stub

	}

	private void moveDown() {
		// TODO Auto-generated method stub

	}

	private void duplicate() {
		// TODO Auto-generated method stub

	}

	private void delete() {
		// TODO Auto-generated method stub

	}

	private void addNew() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel() throws Exception {

	}

}
