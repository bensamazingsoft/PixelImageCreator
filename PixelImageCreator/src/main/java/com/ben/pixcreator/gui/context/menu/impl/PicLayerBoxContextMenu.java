package com.ben.pixcreator.gui.context.menu.impl;

import com.ben.pixcreator.application.action.impl.AddEffectToLayerAction;
import com.ben.pixcreator.application.action.impl.RefreshTabAction;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.image.layer.effect.EffectDesign;
import com.ben.pixcreator.application.image.layer.effect.params.impl.OpacityEffectParams;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class PicLayerBoxContextMenu extends ContextMenu {

	public PicLayerBoxContextMenu(ALayer layer) {

		MenuItem menuItem = new MenuItem();

		menuItem.setText(AppContext.getInstance().getBundle().getString("pixLayerBoxContextMenuAddOpacity"));

		menuItem.setOnAction(event -> {

			try {

				Executor.getInstance().executeAction(
						new AddEffectToLayerAction(EffectDesign.OPACITY, new OpacityEffectParams(), layer));

				Executor.getInstance().executeAction(new RefreshTabAction(GuiFacade.getInstance().getActiveTab()));

			} catch (Exception e) {
				new ExceptionPopUp(e);
			}
		});

		this.getItems().add(menuItem);

	}

}
