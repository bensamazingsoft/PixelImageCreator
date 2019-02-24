
package com.ben.gui.fx.pile.view.item.impl;

import com.ben.gui.fx.pile.view.item.control.factory.EffectPileViewItemControl;
import com.ben.gui.fx.pile.view.item.control.factory.EffectPileViewItemControlFactory;
import com.ben.pixcreator.application.image.effect.Effect;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class EffectPileViewItem extends HBox
{

      private final String	    IMAGEPATH	     = "images/gui/buttons/effectItem/";

      final private Image	    bypassSelected   = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "bypassSelected.png"));
      final private Image	    bypassUnSelected = new Image(
		  getClass().getClassLoader().getResourceAsStream(IMAGEPATH + "bypassUnSelected.png"));
      final private ImageView	    bypassButImg     = new ImageView();

      ToggleButton		    bypassBut	     = new ToggleButton();
      StackPane			    content	     = new StackPane();
      Button			    deleteBut	     = new Button("X");

      private SimpleBooleanProperty bypass	     = new SimpleBooleanProperty();


      public EffectPileViewItem(Effect fx)
      {

	    setBypass(false);

	    bypassBut.setGraphic(bypassButImg);
	    bypassButImg.imageProperty()
			.bind(Bindings.when(bypass).then(bypassSelected).otherwise(bypassUnSelected));
	    // bypassBut.selectedProperty().bindBidirectional(bypass);
	    bypassBut.setOnAction(event -> setBypass(!isBypass()));

	    EffectPileViewItemControl control = EffectPileViewItemControlFactory.getControl(fx);
	    content.getChildren().add(control.node());

	    bypass.addListener((obs, oldVal, newVal) -> {
		  if (newVal)
		  {
			control.bypass();
		  }
		  else
		  {
			control.enable();
		  }
	    });

	    deleteBut.setOnAction(event -> {
		  control.delete();
	    });

	    getChildren().add(bypassBut);
	    getChildren().add(new Separator());
	    getChildren().add(content);
	    getChildren().add(new Separator());
	    getChildren().add(deleteBut);

      }


      public final SimpleBooleanProperty bypassProperty()
      {

	    return this.bypass;
      }


      public final boolean isBypass()
      {

	    return this.bypassProperty().get();
      }


      public final void setBypass(final boolean bypass)
      {

	    this.bypassProperty().set(bypass);
      }

}
