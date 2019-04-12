package com.ben.pixcreator.application.image.layer.modifier.impl;

import java.util.HashMap;
import java.util.Map;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.effect.EffectDesign;
import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.effect.params.impl.SaturationEffectParams;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PicLayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;
import com.ben.pixcreator.application.image.layer.modifier.IModifier;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SaturationModifier implements IModifier {

	private Effect fx;

	public SaturationModifier(Effect effect) {
		this.fx = effect;
	}

	@Override
	public ALayer modify(ALayer layer) throws EffectException {

		if (fx.getEffect() != EffectDesign.SATURATION) {
			throw new EffectException("Effect design is not SATURATION");
		}

		SaturationEffectParams saturationParam = (SaturationEffectParams) fx.getParams();
		double saturationFactor = saturationParam.getSaturation();

		if ((layer instanceof PixLayer)) {

			PixLayer pxLayer = (PixLayer) layer.duplicate();

			Map<Coord, ColorRGB> newGrid = new HashMap<>();

			pxLayer.getGrid().forEach((coord, colorRGB) -> {
				newGrid.put(coord, new ColorRGB(colorRGB.getFxColor().deriveColor(0, saturationFactor, 1, 1)));
			});

			pxLayer.setGrid(newGrid);

			return pxLayer;
		}

		if (layer instanceof PicLayer) {

			PicLayer pcLayer = (PicLayer) layer.duplicate();

			ImageView view = new ImageView(pcLayer.getImage());
			view.setEffect(new ColorAdjust(0, saturationFactor - 1, 0, 0));

			Image snapshot = view.snapshot(null, null);

			pcLayer.setImage(snapshot);

			return pcLayer;
		}

		return layer;

	}

}
