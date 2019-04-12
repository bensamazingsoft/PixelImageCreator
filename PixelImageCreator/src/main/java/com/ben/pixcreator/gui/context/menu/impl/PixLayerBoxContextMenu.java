
package com.ben.pixcreator.gui.context.menu.impl;

import com.ben.pixcreator.application.action.impl.AddEffectToLayerAction;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.layer.effect.EffectDesign;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.BrightnessEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.HueEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.ResampleEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.SaturationEffectParams;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class PixLayerBoxContextMenu extends ContextMenu {

	public PixLayerBoxContextMenu(ALayer layer) {

		addMenuItem(layer, "pixLayerBoxContextMenuAddOpacity", EffectDesign.OPACITY, new OpacityEffectParams());
		addMenuItem(layer, "pixLayerBoxContextMenuAddHue", EffectDesign.HUE, new HueEffectParams());
		addMenuItem(layer, "pixLayerBoxContextMenuAddSaturation", EffectDesign.SATURATION,
				new SaturationEffectParams());
		addMenuItem(layer, "pixLayerBoxContextMenuAddBrightness", EffectDesign.BRIGHTNESS,
				new BrightnessEffectParams());
		addMenuItem(layer, "pixLayerBoxContextMenuAddResample", EffectDesign.RESAMPLE,
				new ResampleEffectParams());

	}

	public void addMenuItem(ALayer layer, String i18String, EffectDesign effectDesign, EffectParams effectParam) {
		MenuItem menuItem = new MenuItem();

		menuItem.setText(AppContext.getInstance().getBundle().getString(i18String));

		menuItem.setOnAction(event -> {

			try {

				Executor.getInstance().executeAction(
						new AddEffectToLayerAction(effectDesign, effectParam, layer));

				Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));

			} catch (Exception e) {
				new ExceptionPopUp(e);
			}
		});

		this.getItems().add(menuItem);
	}

}
