package com.ben.pixcreator.application.action.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.effect.manager.EffectManager;
import com.ben.pixcreator.application.image.layer.effect.EffectDesign;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class AddEffectToLayerAction implements IAction {

	private static final Logger log = LoggerFactory.getLogger(AddEffectToLayerAction.class);

	private final GuiFacade gui = GuiFacade.getInstance();

	private final EffectDesign	fxDesign;
	private EffectParams		fxParams;
	private ALayer				layer;

	public AddEffectToLayerAction(EffectDesign fxDesign, EffectParams fxParams, ALayer layer) {
		this.fxDesign = fxDesign;
		this.fxParams = fxParams;
		this.layer = layer;
	}

	@Override
	public void execute() throws Exception {

		log.debug("adding " + fxDesign + " to " + layer);

		PixImage activeImage = gui.getActiveImage();
		EffectManager effectManager = AppContext.getInstance().getEffectManager();
		Effect effect = new Effect(fxDesign, fxParams);

		effectManager.getImageLayerEffects(activeImage, layer).add(effect);

		gui.populateLayerPanel();

	}

}
