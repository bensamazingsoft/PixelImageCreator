
package com.ben.pixcreator.gui.context.menu.impl;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.effect.manager.EffectManager;
import com.ben.pixcreator.application.image.layer.effect.EffectDesign;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class PixLayerBoxContextMenu extends ContextMenu
{

      public PixLayerBoxContextMenu(ALayer layer)
      {

	    MenuItem menuItem = new MenuItem();

	    menuItem.setText(AppContext.getInstance().getBundle().getString("pixLayerBoxContextMenuAddOpacity"));

	    menuItem.setOnAction(event -> {

		  PixImage activeImage = GuiFacade.getInstance().getActiveImage();
		  EffectManager effectManager = AppContext.getInstance().getEffectManager();
		  Effect opacityEffect = new Effect(EffectDesign.OPACITY, new OpacityEffectParams());

		  effectManager.getImageLayerEffects(activeImage, layer).add(opacityEffect);

		  GuiFacade.getInstance().getLayerPanel().populate();

	    });

	    this.getItems().add(menuItem);

      }

}
