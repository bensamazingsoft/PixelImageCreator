
package com.ben.pixcreator.application.image.layer.modifier.impl;

import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.effect.params.impl.TextEffectParams;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.TextLayer;
import com.ben.pixcreator.application.image.layer.modifier.IModifier;

public class TextModifier implements IModifier
{

      private Effect fx;


      public TextModifier(Effect effect)
      {

	    this.fx = effect;
      }


      @Override
      public ALayer modify(ALayer layer) throws EffectException
      {

	    TextLayer txtLayer = new TextLayer();

	    if (layer instanceof TextLayer)
	    {

		  txtLayer = (TextLayer) layer.duplicate();

		  final TextEffectParams params = (TextEffectParams) fx.getParams();
		  txtLayer.setColor(params.getColor());
		  txtLayer.setFontSize(params.getFontSize());
		  txtLayer.setAlign(params.getTextAlign());
		  txtLayer.setBaseline(params.getBaseline());
		  txtLayer.setText(params.getText());
		  txtLayer.setFontFamily(params.getFontFamily());
		  txtLayer.setPosture(params.getPosture());
		  txtLayer.setWeight(params.getFontWeight());

	    }
	    else
	    {
		  throw new EffectException("Not a TEXT layer");
	    }

	    return txtLayer;
      }

}
