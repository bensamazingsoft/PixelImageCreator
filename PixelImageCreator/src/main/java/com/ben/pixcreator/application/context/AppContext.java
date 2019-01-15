
package com.ben.pixcreator.application.context;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;

import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.tools.PixTool;

import javafx.scene.paint.Color;

public class AppContext {

	private static AppContext instance;

	private static PropertiesContext properties;

	private static Color gridColor, currDrawColor;

	private static PixTool currTool;

	private static Set<PixImage> openImages;

	private static ResourceBundle bundle;

	private AppContext() {

	}

	public static void init() throws IOException {

		properties = new PropertiesContext();
		bundle = ResourceBundle.getBundle("i18n/trad");

		currTool = PixTool.getTool(properties.get("startTool"));

		gridColor = properties.getColor(properties.get("gridColor"));
		currDrawColor = properties.getColor(properties.get("drawColor"));

	}

	public static AppContext getInstance() {

		if (instance == null) {
			instance = new AppContext();
		}
		return instance;

	}

	public PropertiesContext getProperties() {

		return properties;
	}

	public void setProperties(PropertiesContext properties) {

		AppContext.properties = properties;
	}

	public Color getGridColor() {

		return gridColor;
	}

	public void setGridColor(Color gridColor) {

		AppContext.gridColor = gridColor;
	}

	public Color getCurrDrawColor() {

		return currDrawColor;
	}

	public void setCurrDrawColor(Color currDrawColor) {

		AppContext.currDrawColor = currDrawColor;
	}

	public void setCurrTool(PixTool pixTool) {

		properties.set("startTool", pixTool.name());

		currTool = pixTool;

	}

	public PixTool getCurrTool() {

		return currTool;
	}

	public Set<PixImage> getOpenImages() {

		return openImages;
	}

	public void setOpenImages(Set<PixImage> openImages) {

		AppContext.openImages = openImages;
	}

	public ResourceBundle getBundle() {

		return bundle;
	}

}
