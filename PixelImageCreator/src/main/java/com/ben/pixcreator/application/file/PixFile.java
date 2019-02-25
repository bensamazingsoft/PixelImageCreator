package com.ben.pixcreator.application.file;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.pile.Pile;

/**
 * 
 * Encapsulates a .pix file.
 * 
 * @author bmo
 *
 */
public class PixFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PixImage				image;
	private Set<ColorRGB>			colors;
	private Map<UUID, Set<UUID>>	locks;
	private Map<UUID, Boolean>		visibility;
	private Map<UUID, Pile<Effect>>	effects;

	public PixFile(PixImage image, Set<ColorRGB> colors, Map<UUID, Set<UUID>> locks, Map<UUID, Pile<Effect>> effects,
			Map<UUID, Boolean> visibility) {
		super();
		this.image = image;
		this.colors = colors;
		this.locks = locks;
		this.visibility = visibility;
		this.effects = effects;
	}

	public PixImage getImage() {
		return image;
	}

	public Set<ColorRGB> getColors() {
		return colors;
	}

	public Map<UUID, Set<UUID>> getLocks() {
		return locks;
	}

	public Map<UUID, Boolean> getVisibility() {
		return visibility;
	}

	public Map<UUID, Pile<Effect>> getEffects() {
		return effects;
	}

}
