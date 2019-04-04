package com.ben.pixcreator.gui.context.menu.impl;

import com.ben.pixcreator.application.action.impl.AddEffectToLayerAction;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.layer.effect.EffectDesign;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.HueEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.SizeEffectParams;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class PicLayerBoxContextMenu extends ContextMenu {

	public PicLayerBoxContextMenu(ALayer layer) {

		this.getItems().add(
				menuItem(layer, "pixLayerBoxContextMenuAddOpacity", EffectDesign.OPACITY, new OpacityEffectParams()));
		this.getItems()
				.add(menuItem(layer, "layerBoxContextMenuAddEnlarge", EffectDesign.ENLARGE,
						new SizeEffectParams(100, 300, 100)));
		this.getItems()
				.add(menuItem(layer, "layerBoxContextMenuAddShrink", EffectDesign.SHRINK,
						new SizeEffectParams(0, 100, 100)));

		this.getItems().add(menuItem(layer, "pixLayerBoxContextMenuAddHue", EffectDesign.HUE, new HueEffectParams()));

	}

	private MenuItem menuItem(ALayer layer, String i18nParam, EffectDesign fxDesign, EffectParams params) {

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
