package com.ben.pixcreator.application.image.layer.modifier.impl;

import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.EffectDesign;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.effect.params.impl.HueEffectParams;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.image.layer.impl.PixLayer;
import com.ben.pixcreator.application.image.layer.modifier.IModifier;

public class HueModifier implements IModifier {

	private Effect fx;

	public HueModifier(Effect effect) {
		this.fx = effect;
	}

	@Override
	public ALayer modify(ALayer layer) throws EffectException {

		if (fx.getEffect() != EffectDesign.HUE) {
			throw new EffectException("Effect design is not HUE");
		}

		if (!(layer instanceof PixLayer)) {
			throw new EffectException("Not a PixLayer");
		}

		HueEffectParams hueParam = (HueEffectParams) fx.getParams();
		double hueShift = hueParam.getHue();

		PixLayer fxLayer = ((PixLayer) layer).duplicate();

		Map<Coord, ColorRGB> newGrid = new HashMap<>();

		fxLayer.getGrid().forEach((coord, colorRGB) -> {
			newGrid.put(coord, new ColorRGB(colorRGB.getFxColor().deriveColor(hueShift, 1, 1, 1)));
		});

		fxLayer.setGrid(newGrid);

		return fxLayer;
	}

}
