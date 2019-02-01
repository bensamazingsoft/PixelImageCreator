package com.ben.pixcreator.application.validation;

import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.ALayer;

public class Validators {

	/**
	 * 
	 * Checks if the image is open in a tab and active
	 * 
	 * @param image
	 * @return true if the image is open and active
	 */
	public static boolean isActive(PixImage image) {
		// TODO validate image
		return true;
	}

	/**
	 * 
	 * Checks if the layer is active
	 * 
	 * @param layer
	 * @return true if the layer is active
	 */
	public static boolean isActive(ALayer layer) {
		// TODO validate layer
		return true;
	}

	/**
	 * 
	 * Convenience method to validate an image and a layer.<br/>
	 * <br/>
	 * Checks if the layer is active.<br/>
	 * Checks if the image is open in a tab and active.
	 * 
	 * @param image
	 * @param layer
	 * @return true if both image and layer are active
	 */
	public static boolean areActive(PixImage image, ALayer layer) {

		return isActive(layer) && isActive(image);

	}

}
