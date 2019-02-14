package com.ben.pixcreator.application.file;

import java.io.File;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.ben.pixcreator.application.image.PixImage;

import javafx.scene.paint.Color;

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
	private Set<Color>				colors;
	private Map<UUID, Set<UUID>>	locks;
	private File					file;

	public PixFile(PixImage image, Set<Color> colors, Map<UUID, Set<UUID>> locks) {
		super();
		this.image = image;
		this.colors = colors;
		this.locks = locks;
	}

	public PixFile(File file) {
		super();
		this.file = file;

	}

	@Override
	public String toString() {
		return "PixFile [file=" + file + "]";
	}

}
