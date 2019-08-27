package com.ben.pixcreator.gui.context.menu.impl;

import com.ben.pixcreator.application.action.impl.AddEffectToLayerAction;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.layer.effect.EffectType;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.BrightnessEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.HueEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.SaturationEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.SizeEffectParams;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class PicLayerBoxContextMenu extends ContextMenu {

	public PicLayerBoxContextMenu(ALayer layer) {

		this.getItems().add(
				menuItem(layer, "pixLayerBoxContextMenuAddOpacity", EffectType.OPACITY, new OpacityEffectParams()));
		this.getItems()
				.add(menuItem(layer, "layerBoxContextMenuAddEnlarge", EffectType.ENLARGE,
						new SizeEffectParams(100, 300, 100)));
		this.getItems()
				.add(menuItem(layer, "layerBoxContextMenuAddShrink", EffectType.SHRINK,
						new SizeEffectParams(0, 100, 100)));

		this.getItems().add(menuItem(layer, "pixLayerBoxContextMenuAddHue", EffectType.HUE, new HueEffectParams()));
		this.getItems().add(menuItem(layer, "pixLayerBoxContextMenuAddSaturation", EffectType.SATURATION,
				new SaturationEffectParams()));

		final BrightnessEffectParams brightnessParam = new BrightnessEffectParams();
		brightnessParam.setBrightness(0.5);
		this.getItems().add(menuItem(layer, "pixLayerBoxContextMenuAddBrightness", EffectType.BRIGHTNESS,
				brightnessParam));

	}

	public static MenuItem menuItem(ALayer layer, String i18nParam, EffectType fxDesign, EffectParams params) {

		MenuItem fxMenuItem = new MenuItem();

		fxMenuItem.setText(AppContext.getInstance().getBundle().getString(i18nParam));

		fxMenuItem.setOnAction(event -> {

			try {

				Executor.getInstance().executeAction(
						new AddEffectToLayerAction(fxDesign, params, layer));

				Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));

			} catch (Exception e) {
				new ExceptionPopUp(e);
			}
		});

		return fxMenuItem;

	}

}
