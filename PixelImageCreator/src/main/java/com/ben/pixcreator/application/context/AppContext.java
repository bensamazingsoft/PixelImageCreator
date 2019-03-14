
package com.ben.pixcreator.application.context;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.ben.pixcreator.application.grouplock.GroupLock;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.manager.EffectManager;
import com.ben.pixcreator.application.tools.PixTool;

import javafx.scene.paint.Color;

public class AppContext {

	private static AppContext instance;

	private static PropertiesContext properties;

	private static Color gridColor;

	private static PixTool currTool;

	private static ResourceBundle bundle;

	private static Map<PixImage, File> files;

	private static Map<PixImage, GroupLock> groupLocks;

	private static EffectManager effectManager;

	private AppContext() {

	}

	public static void init() throws IOException {

		properties = new PropertiesContext();
		bundle = ResourceBundle.getBundle("i18n/trad");
		currTool = PixTool.valueOf(properties.get("startTool"));

		gridColor = properties.getColor(properties.get("gridColor"));

		files = new HashMap<>();
		groupLocks = new HashMap<>();
		effectManager = new EffectManager();

	}

	public static AppContext getInstance() {

		if (instance == null) {
			instance = new AppContext();
		}
		return instance;

	}

	public PropertiesContext propertyContext() {

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

	public void setCurrTool(PixTool pixTool) {

		properties.set("startTool", pixTool.name());

		currTool = pixTool;

	}

	public PixTool getCurrTool() {

		return currTool;
	}

	public ResourceBundle getBundle() {

		return bundle;
	}

	public Map<PixImage, File> getFiles() {

		return files;
	}

	public Map<PixImage, GroupLock> getGroupLocks() {

		return groupLocks;
	}

	public EffectManager getEffectManager() {

		return effectManager;
	}

}
