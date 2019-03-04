
package com.ben.pixcreator.application.image.layer.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.imageio.ImageIO;

import com.ben.pixcreator.application.image.coords.Coord;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class PicLayer extends ALayer implements Serializable {

	/**
	 * 
	 */
	private static final long				serialVersionUID	= 1L;
	private File							imageFile;
	private transient Image					image;
	private Coord							position;
	private double							sizeFactor;
	private transient SimpleDoubleProperty	zoomFactor			= new SimpleDoubleProperty();

	public PicLayer(File imageFile) {

		super();
		this.imageFile = imageFile;
		position = new Coord();
		sizeFactor = 1d;
		setVisible(true);

		if (imageFile.exists()) {
			try {
				image = new Image(
						new BufferedInputStream(Files.newInputStream(imageFile.toPath(), StandardOpenOption.READ)));
			} catch (IOException e) {
				new ExceptionPopUp(e);
			}

		}

	}

	public PicLayer() {
		super();
	}

	/*
	 * public void drawImage(Image img, double sx, double sy, double sw, double
	 * sh, double dx, double dy, double dw, double dh)
	 * 
	 * Draws the specified source rectangle of the given image to the given
	 * destination rectangle of the Canvas.A null image value or an image still
	 * in progress will be ignored. This method will be affected by any of the
	 * global common attributes as specified in the Rendering Attributes Table.
	 * Parameters: img - the image to be drawn or null. sx - the source
	 * rectangle's X coordinate position. sy - the source rectangle's Y
	 * coordinate position. sw - the source rectangle's width. sh - the source
	 * rectangle's height. dx - the destination rectangle's X coordinate
	 * position. dy - the destination rectangle's Y coordinate position. dw -
	 * the destination rectangle's width. dh - the destination rectangle's
	 * height.
	 * 
	 */
	public void draw(Canvas canvas, int xGridResolution, int yGridResolution) {

		int xCellSize = (int) (Math.floor(canvas.getWidth()) / xGridResolution);
		int yCellSize = (int) (Math.floor(canvas.getHeight()) / yGridResolution);

		canvas.getGraphicsContext2D().drawImage(
				image,
				0, 0, image.getWidth() * sizeFactor, image.getHeight() * sizeFactor,
				position.getX() * xCellSize,
				position.getY() * yCellSize,
				image.getWidth() * sizeFactor * zoomFactor.get(),
				image.getHeight() * sizeFactor * zoomFactor.get());

	}

	@Override
	public String toString() {

		return "PicLayer [imageFile=" + imageFile + "]";
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

	public final SimpleDoubleProperty zoomFactorProperty() {

		return this.zoomFactor;
	}

	public final double getZoomFactor() {

		return this.zoomFactorProperty().get();
	}

	public final void setZoomFactor(final double zoomFactor) {

		this.zoomFactorProperty().set(zoomFactor);
	}

	public class Memento extends ALayer.Memento {

		protected Memento(ALayer layer) {

			super(layer);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void init(ALayer layer) {
			// TODO Auto-generated method stub

		}

		@Override
		public void restore() {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public Memento getMemento() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALayer offset(Coord min) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALayer duplicate() {

		PicLayer clone = new PicLayer();

		clone.setVisible(isVisible());

		clone.setPosition(getPosition());

		clone.setSizeFactor(getSizeFactor());

		clone.setZoomFactor(getZoomFactor());

		clone.setImage(getImage());

		clone.setImageFile(getImageFile());

		return clone;
	}

	private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException {
		s.defaultReadObject();
		image = SwingFXUtils.toFXImage(ImageIO.read(s), null);
		zoomFactor = new SimpleDoubleProperty();
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", s);
	}

}
