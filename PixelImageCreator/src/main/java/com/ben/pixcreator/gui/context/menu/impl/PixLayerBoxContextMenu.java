
package com.ben.pixcreator.gui.context.menu.impl;

import java.util.ResourceBundle;

import com.ben.pixcreator.application.action.impl.AddEffectToLayerAction;
import com.ben.pixcreator.application.action.impl.ExportGridOnWebAction;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.layer.effect.EffectType;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.BrightnessEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.HueEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.LumaKeyEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.ResampleEffectParams;
import com.ben.pixcreator.application.image.layer.effect.params.impl.SaturationEffectParams;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;
import com.ben.pixcreator.gui.pane.web.LogInfo;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class PixLayerBoxContextMenu extends ContextMenu {

	private final ResourceBundle bundle = AppContext.getInstance().getBundle();

	public PixLayerBoxContextMenu(ALayer layer) {

		addEffectMenuItem(layer, "pixLayerBoxContextMenuAddOpacity", EffectType.OPACITY, new OpacityEffectParams());
		addEffectMenuItem(layer, "pixLayerBoxContextMenuAddHue", EffectType.HUE, new HueEffectParams());
		addEffectMenuItem(layer, "pixLayerBoxContextMenuAddSaturation", EffectType.SATURATION,
				new SaturationEffectParams());
		addEffectMenuItem(layer, "pixLayerBoxContextMenuAddBrightness", EffectType.BRIGHTNESS,
				new BrightnessEffectParams());
		addEffectMenuItem(layer, "pixLayerBoxContextMenuAddResample", EffectType.RESAMPLE,
				new ResampleEffectParams());
		addEffectMenuItem(layer, "pixLayerBoxContextMenuAddLumaKey", EffectType.LUMAKEY,
				new LumaKeyEffectParams());
		getItems().add(new SeparatorMenuItem());
		getItems().add(webExportMenuItem(layer));
	}

	private MenuItem webExportMenuItem(ALayer layer) {

		MenuItem item = new MenuItem(bundle.getString("pixLayerBoxContextWebExportMenuItemTitle"));
		LogInfo logInfo = GuiFacade.getInstance().getLogInfo();

		item.setOnAction(event -> {

			try {
				Executor.getInstance().executeAction(new ExportGridOnWebAction(logInfo, layer));
			} catch (Exception e) {
				new ExceptionPopUp(e);
			}

		});

		item.setDisable(!logInfo.isConnected());

		return item;
	}

	public void addEffectMenuItem(ALayer layer, String i18String, EffectType effectType, EffectParams effectParam) {

		MenuItem menuItem = new MenuItem();

		menuItem.setText(bundle.getString(i18String));

		menuItem.setOnAction(event -> {

			try {

				Executor.getInstance().executeAction(
						new AddEffectToLayerAction(effectType, effectParam, layer));

				Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));

			} catch (Exception e) {
				new ExceptionPopUp(e);
			}
		});

		this.getItems().add(menuItem);
	}

}
