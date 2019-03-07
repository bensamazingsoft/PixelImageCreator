package com.ben.pixcreator.application.image.layer.modifier;

import com.ben.pixcreator.application.image.layer.effect.exception.EffectException;
import com.ben.pixcreator.application.image.layer.impl.ALayer;

public interface IModifier {

	ALayer modify(ALayer layer) throws EffectException;

}
