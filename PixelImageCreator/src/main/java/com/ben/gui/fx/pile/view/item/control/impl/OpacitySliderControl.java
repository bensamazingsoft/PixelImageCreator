
package com.ben.gui.fx.pile.view.item.control.impl;

import com.ben.gui.fx.pile.view.item.control.factory.EffectPileViewItemControl;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.params.EffectParams.Param;
import com.ben.pixcreator.application.image.layer.effect.params.param.value.ParamValue;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

public class OpacitySliderControl extends StackPane implements EffectPileViewItemControl
{

      private Slider	 slider	= new Slider();
      ParamValue<Double> params;


      @SuppressWarnings("unchecked")
      public OpacitySliderControl(Effect fx)
      {

	    params = (ParamValue<Double>) fx.getParams().get(Param.OPACITY);

	    slider.setMin(params.getMin());
	    slider.setMax(params.getMax());
	    slider.setValue(params.getValue());

	    slider.valueProperty().addListener((obs, oldVal, newVal) -> {

		  params.setValue(slider.getValue());

	    });
      }


      @Override
      public void bypass()
      {

	    slider.setValue(params.getBypass());
	    slider.setDisable(true);

      }


      @Override
      public void enable()
      {

	    slider.setDisable(false);

      }


      @Override
      public void delete()
      {

	    // TODO Auto-generated method stub

      }


      @Override
      public Node node()
      {

	    return this;
      }

}
