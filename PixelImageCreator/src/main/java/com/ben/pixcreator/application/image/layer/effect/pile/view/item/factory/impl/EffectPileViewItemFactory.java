
package com.ben.pixcreator.application.image.layer.effect.pile.view.item.factory.impl;

import com.ben.gui.fx.pile.view.item.factory.PileViewItemFactory;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.pile.view.item.impl.EffectPileViewItem;

import javafx.scene.layout.HBox;

public class EffectPileViewItemFactory implements PileViewItemFactory {

	// bypassButImg.imageProperty()
	// .bind(Bindings.when(bypassBut.selectedProperty()).then(bypassSelected).otherwise(bypassUnSelected));

	public EffectPileViewItemFactory() {

	}

	@Override
	public HBox getItem(Object object) {

		if (object instanceof Effect) {
			Effect fx = (Effect) object;
			HBox fxBox = new EffectPileViewItem(fx);
			return fxBox;
		} else {
			throw new ClassCastException("not a com.ben.pixcreator.application.image.effect.Effect");
		}

	}

}
