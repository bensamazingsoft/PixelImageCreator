
package com.ben.pixcreator.application.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;

public class PropertiesContext {

	private Properties		defaultProp, properties;
	private File			propFile;
	private final String	defaultPropertiesFileName	= "./properties/default.properties";

	public PropertiesContext() throws IOException {

		initProperties();
	}

	private void initProperties() throws IOException {

		// initialize default properties

		defaultProp = new Properties();
		properties = new Properties();

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(defaultPropertiesFileName);
		defaultProp.load(is);

		// initialize properties with default in case there are new ones
		properties = new Properties(defaultProp);

		propFile = new File("data/config.properties");
		if (propFile.exists()) {

			properties.load(new FileInputStream(propFile.toString()));

		}

	}

	public void reset() {

		properties = new Properties(defaultProp);
	}

	public void reset(String key) {
		properties.setProperty(key, defaultProp.getProperty(key));
	}

	public String getDefault(String key) {

		return defaultProp.getProperty(key);
	}

	public String get(String key) {

		return properties.getProperty(key);
	}

	public void set(String key, String value) {

		properties.setProperty(key, value);
	}

	public Set<Color> getStartRosterColors() {

		// get the rosteColorors

		Set<Color> result = new HashSet<>();

		String[] stringColors = properties.getProperty("rosterColors").split("X");

		for (String stringColor : stringColors) {

			Color color = getColor(stringColor);

			result.add(color);

		}

		return result;

	}

	// convert color string property to color object
	Color getColor(String string) {

		Color color = Color.BLACK;
		String[] rgbValues = string.split(",");

		if (rgbValues.length == 3) {
			color = Color.rgb(Integer.valueOf(rgbValues[0]), Integer.valueOf(rgbValues[1]),
					Integer.valueOf(rgbValues[2]));
		} else if (rgbValues.length == 4) {
			color = Color.rgb(Integer.valueOf(rgbValues[0]), Integer.valueOf(rgbValues[1]),
					Integer.valueOf(rgbValues[2]), Double.valueOf(rgbValues[3]));
		}

		return color;
	}

	public void save() throws FileNotFoundException, IOException {
		propFile.getParentFile().mkdirs();
		properties.store(new FileOutputStream(propFile), null);

	}

	public Set<String> getProps(String string) {
		Collection<Object> objs = properties.values();
		Set<String> props = new HashSet<>();

		props = objs.stream().map(obj -> (String) obj).filter(prop -> prop.startsWith("color_"))
				.collect(Collectors.toSet());

		return props;
	}

}
