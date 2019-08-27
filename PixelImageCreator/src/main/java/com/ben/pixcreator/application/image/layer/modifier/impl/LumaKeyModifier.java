package com.ben.pixcreator.application.image.layer.modifier.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.analysis.function.Logistic;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.EffectType;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.effect.params.impl.LumaKeyEffectParams;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;
import com.ben.pixcreator.application.image.layer.modifier.IModifier;

import javafx.scene.paint.Color;

public class LumaKeyModifier implements IModifier {

	private Effect			fx;
	private final Logistic	logistic	= new Logistic(1.0, 0.695, 4.107, 0.07, 0.0, 1.139);

	public LumaKeyModifier(Effect effect) {
		super();
		this.fx = effect;
	}

	@Override
	public ALayer modify(ALayer layer) throws EffectException {
		if (fx.getEffect() != EffectType.LUMAKEY) {
			throw new EffectException("Effect design is not LUMAKEY");
		}

		if (fx.getParams() instanceof LumaKeyEffectParams) {

			LumaKeyEffectParams param = (LumaKeyEffectParams) fx.getParams();

			if (layer instanceof PixLayer) {

				PixLayer pxLayer = ((PixLayer) layer).duplicate();

				double factor = param.getFactor() * 2 - 1;

				Map<Coord, ColorRGB> newGrid = new HashMap<>();

				for (Coord coord : pxLayer.getGrid().keySet()) {

					Color color = pxLayer.getGrid().get(coord).getFxColor();

					double logFactor = logistic.value(factor);

					double key = Math.max(0d, (Math.min(1, logFactor / color.getBrightness())));

					if (param.isInvert()) {
						key = 1 - key;
					}

					ColorRGB newColor = new ColorRGB(
							Color.hsb(color.getHue(), color.getSaturation(), color.getBrightness(), key));
					newGrid.put(coord, newColor);

				}
				pxLayer.setGrid(newGrid);
				return pxLayer;
			}

		}

		return layer;
	}

}
