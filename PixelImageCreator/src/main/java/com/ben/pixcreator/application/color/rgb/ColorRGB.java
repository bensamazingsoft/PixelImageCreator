package com.ben.pixcreator.application.color.rgb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

/**
 * encapsulates a color in RGBA values (RGB:0-255 A:0.0-1.0). Values are stored
 * in a 'rgb' map whose keys are String constants OPACITY,RED,GREEN and BLUE
 * 
 * @author bmo
 *
 */
public class ColorRGB implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private final static String	RED					= "red";
	private final static String	GREEN				= "green";
	private final static String	BLUE				= "blue";
	private final static String	OPACITY				= "opacity";

	private Map<String, Double> rgb = new HashMap<>();

	public ColorRGB() {
		rgb.put(BLUE, 0.0);
		rgb.put(GREEN, 0.0);
		rgb.put(RED, 0.0);
		rgb.put(OPACITY, 1.0);
	}

	public ColorRGB(Color color) {

		rgb.put(BLUE, color.getBlue() * 255);
		rgb.put(GREEN, color.getGreen() * 255);
		rgb.put(RED, color.getRed() * 255);
		rgb.put(OPACITY, color.getOpacity());
	}

	public ColorRGB(double red, double green, double blue, double opacity) {
		this();

		rgb.put(BLUE, blue);
		rgb.put(GREEN, green);
		rgb.put(RED, red);
		rgb.put(OPACITY, opacity);
	}

	/**
	 * @return the javafx.scene.paint.Color represented by this 'rgb' map values
	 */
	public Color getFxColor() {

		return Color.rgb(rgb.get(RED).intValue(), rgb.get(GREEN).intValue(), rgb.get(BLUE).intValue(),
				rgb.get(OPACITY));
	}

	/**
	 * @return a javafx.beans.property.SimpleObjectProperty holding a
	 *         javafx.scene.paint.Color represented by this 'rgb' map values
	 */
	public SimpleObjectProperty<Color> getFxColorProperty() {

		SimpleObjectProperty<Color> prop = new SimpleObjectProperty<>();
		prop.set(getFxColor());

		return prop;
	}

	/**
	 * @return the red value of this color (0-255)
	 */
	public double getRed() {
		return rgb.get(RED);

	}

	/**
	 * @return the blue value of this color (0-255)
	 */
	public double getBlue() {
		return rgb.get(BLUE);

	}

	/**
	 * @return the green value of this color (0-255)
	 */
	public double getGreen() {
		return rgb.get(GREEN);

	}

	/**
	 * @return the opacity value of this color (0.0-1.0)
	 */
	public double getOpacity() {
		return rgb.get(OPACITY);

	}

	@Override
	public String toString() {
		return "ColorRGB [rgb=" + rgb + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rgb == null) ? 0 : rgb.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColorRGB other = (ColorRGB) obj;
		if (rgb == null) {
			if (other.rgb != null)
				return false;
		} else if (!rgb.equals(other.rgb))
			return false;
		return true;
	}
}
