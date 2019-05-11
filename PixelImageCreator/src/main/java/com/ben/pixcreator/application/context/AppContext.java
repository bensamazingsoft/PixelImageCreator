
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
import com.ben.pixcreator.gui.cursor.updater.CursorUpdater;
import com.ben.pixcreator.gui.cursor.updater.SnapshotUpdater;
import com.ben.pixcreator.gui.tooltip.provider.ToolTipProvider;

import javafx.scene.paint.Color;

public class AppContext {

	private static CursorUpdater cursorUpdater;

	private static SnapshotUpdater snapshotUpdater;

	private static AppContext instance;

	private static PropertiesContext properties;

	private static Color gridColor;

	private static PixTool currTool;

	private static ResourceBundle bundle;

	private static ResourceBundle tipBundle;

	private static Map<PixImage, File> files;

	private static Map<PixImage, GroupLock> groupLocks;

	private static EffectManager effectManager;

	private static ToolTipProvider toolTipProvider;

	private static boolean initialized = false;

	private AppContext() {

	}

	public static void init() throws IOException {

		cursorUpdater = new CursorUpdater();
		cursorUpdater.start();

		snapshotUpdater = new SnapshotUpdater();

		properties = new PropertiesContext();
		bundle = ResourceBundle.getBundle("i18n/trad");
		tipBundle = ResourceBundle.getBundle("i18n/tips");
		currTool = PixTool.valueOf(properties.get("startTool"));

		gridColor = properties.getColor(properties.get("gridColor"));

		files = new HashMap<>();
		groupLocks = new HashMap<>();
		effectManager = new EffectManager();

		toolTipProvider = new ToolTipProvider(tipBundle);

		initialized = true;

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

	public static boolean isInitialized() {
		return initialized;
	}

	public static void setInitialized(boolean initialized) {
		AppContext.initialized = initialized;
	}

	public CursorUpdater getCursorUpdater() {

		return cursorUpdater;
	}

	public static void setCursorUpdater(CursorUpdater cursorUpdater) {
		AppContext.cursorUpdater = cursorUpdater;
	}

	public static SnapshotUpdater getSnapshotUpdater() {
		return snapshotUpdater;
	}

	public ToolTipProvider getToolTipProvider() {
		return toolTipProvider;
	}

	public static void setToolTipProvider(ToolTipProvider toolTipProvider) {
		AppContext.toolTipProvider = toolTipProvider;
	}

}
