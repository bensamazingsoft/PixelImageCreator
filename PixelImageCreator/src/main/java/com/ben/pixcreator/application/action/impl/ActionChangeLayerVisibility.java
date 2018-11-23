package com.ben.pixcreator.application.action.impl;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.context.AppContext;

public class ActionChangeLayerVisibility implements IAction {

	private PixImage image;

	private ILayer layer;
	private boolean changed;
	private boolean old;
	
	public ActionChangeLayerVisibility(PixImage image, ILayer layer) {
		super();
		this.image = image;
		this.layer = layer;
		old = image.getLayers().get(layer);
		changed = !old;
		
		if (!AppContext.getInstance().getOpenImages().contains(image)){
			throw new ClosedImageException();
		} else if (!image.getLayers().contains(layer)){
			throw new InexistantLayerException();
		}
	}
	
	
	@Override
	public void execute() {
		
	if (AppContext.getInstance().getOpenImages().contains(image)){
		if (image.getLayers().contains(layer)){
			image.getLayers().put(layer,changed);
		}
	}

	}

	@Override
	public void cancel() {
		if (AppContext.getInstance().getOpenImages().contains(image)){
			if (image.getLayers().contains(layer)){
				image.getLayers().put(layer,old);
			}
		}
	}

}
