
package com.ben.pixcreator.application.image.layer.effect.pile.view.item.control;

import javafx.scene.Node;

public interface IEffectPileViewItemControl {

	public void bypass();

	public void enable();

	public void reset();

	public void save();

	public void restore();

	/**
	 * builder
	 * 
	 * @return a javafx.scene.Node
	 */
	public Node node();
}
