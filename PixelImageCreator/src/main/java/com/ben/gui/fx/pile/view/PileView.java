
package com.ben.gui.fx.pile.view;

import com.ben.gui.fx.pile.view.item.factory.PileViewItemFactory;
import com.ben.pixcreator.application.pile.Pile;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 
 * Represents a Pile<T>. Has a title element and is expandable to reveal
 * PileViewItems. Elements are provided by a PileViewItemFactory, wich you can
 * extend and then provide.
 * 
 * @author ben
 *
 * @param <T>
 */
public class PileView extends VBox {

	private SimpleBooleanProperty	expand		= new SimpleBooleanProperty();
	private ToggleButton			expandBut	= new ToggleButton();

	private final HBox			titleBox	= new HBox();
	private Label				titleLabel	= new Label();
	private VBox				content		= new VBox();
	private PileViewItemFactory	factory;

	public PileView(String displayTitle, Pile<?> pile, PileViewItemFactory factory, SimpleBooleanProperty expand) {

		this.factory = factory;

		this.expand = expand;

		titleLabel.setText(displayTitle);
		titleLabel.getStyleClass().add("pileViewTitle");
		expandBut.selectedProperty().bindBidirectional(expand);
		expandBut.textProperty().bind(Bindings.when(expandProperty()).then("-").otherwise("+"));
		expandBut.setOnAction(event -> {
			expand(pile);
		});

		titleBox.getChildren().add(expandBut);
		// titleBox.getChildren().add(new Separator());
		titleBox.getChildren().add(titleLabel);

		getChildren().add(titleBox);

		// getChildren().add(new Separator());

		getChildren().add(content);

		if (isExpand()) {
			expand(pile);
		}

	}

	private void expand(Pile<?> pile) {

		content.getChildren().clear();
		if (isExpand()) {
			for (int i = 0; i < pile.getAllItems().size(); i++) {
				content.getChildren().add(factory.getItem(pile.getItem(i)));
			}
		} else {
			content.getChildren().clear();
		}
	}

	public final SimpleBooleanProperty expandProperty() {

		return this.expand;
	}

	public final boolean isExpand() {

		return this.expandProperty().get();
	}

	public final void setExpand(final boolean expand) {

		this.expandProperty().set(expand);
	}

}
