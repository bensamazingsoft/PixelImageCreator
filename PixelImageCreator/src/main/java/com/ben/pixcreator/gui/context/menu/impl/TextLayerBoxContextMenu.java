package com.ben.pixcreator.gui.context.menu.impl;

import com.ben.pixcreator.application.image.layer.effect.EffectType;
import com.ben.pixcreator.application.image.layer.effect.params.impl.TextEffectParams;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;

import javafx.scene.control.ContextMenu;

public class TextLayerBoxContextMenu extends ContextMenu {

	public TextLayerBoxContextMenu(ALayer layer) {
		this.getItems().add(
				PicLayerBoxContextMenu.menuItem(layer, "txtLayerBoxContextMenuAddText", EffectType.TEXT,
						new TextEffectParams()));
	}

}
