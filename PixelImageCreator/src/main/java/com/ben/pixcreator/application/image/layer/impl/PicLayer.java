
package com.ben.pixcreator.application.image.layer.impl;

import java.io.File;

import com.ben.pixcreator.application.image.coords.Coord;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class PicLayer extends ALayer {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private File				imageFile;
	private Image				image;
	private Coord				position;
	private double				sizeFactor;

	public PicLayer(File imageFile) {

		super();
		this.imageFile = imageFile;
		position = new Coord();
		sizeFactor = 1d;
	}

	public void draw(Canvas canvas, int xGridResolution, int yGridResolution) {

		// TODO draw picimage to canvas

	}

	@Override
	public String toString() {

		return "PicLayer [imageFile=" + imageFile + "]";
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((imageFile == null) ? 0 : imageFile.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		long temp;
		temp = Double.doubleToLongBits(sizeFactor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PicLayer)) {
			return false;
		}
		PicLayer other = (PicLayer) obj;
		if (image == null) {
			if (other.image != null) {
				return false;
			}
		} else if (!image.equals(other.image)) {
			return false;
		}
		if (imageFile == null) {
			if (other.imageFile != null) {
				return false;
			}
		} else if (!imageFile.equals(other.imageFile)) {
			return false;
		}
		if (position == null) {
			if (other.position != null) {
				return false;
			}
		} else if (!position.equals(other.position)) {
			return false;
		}
		if (Double.doubleToLongBits(sizeFactor) != Double.doubleToLongBits(other.sizeFactor)) {
			return false;
		}
		return true;
	}

	public File getImageFile() {

		return imageFile;
	}

	public void setImageFile(File imageFile) {

		this.imageFile = imageFile;
	}

	public Image getImage() {

		return image;
	}

	public void setImage(Image image) {

		this.image = image;
	}

	public Coord getPosition() {

		return position;
	}

	public void setPosition(Coord position) {

		this.position = position;
	}

	public double getSizeFactor() {

		return sizeFactor;
	}

	public void setSizeFactor(double sizeFactor) {

		this.sizeFactor = sizeFactor;
	}

}
