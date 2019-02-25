
package com.ben.pixcreator.application.action.impl;

import static java.util.stream.Collectors.toSet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.executor.Executor;
import com.ben.pixcreator.application.file.PixFile;
import com.ben.pixcreator.application.grouplock.GroupLock;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.impl.ALayer;
import com.ben.pixcreator.application.pile.Pile;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

public class LoadFileAction implements IAction {

	private final File file;

	public LoadFileAction(File file) {

		this.file = file;
	}

	@Override
	public void execute() throws Exception {

		try (FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fileIn);) {

			PixFile pixFile = (PixFile) in.readObject();

			PixImage image = getImage(pixFile);

			Executor.getInstance().executeAction(new OpenTabAction(image));

		} catch (IOException e) {
			new ExceptionPopUp(e);
		}

	}

	private PixImage getImage(PixFile pixFile) {

		GuiFacade gui = GuiFacade.getInstance();

		PixImage image = pixFile.getImage();
		GroupLock groupLock = new GroupLock();
		AppContext.getInstance().getGroupLocks().put(image, groupLock);

		Map<UUID, Set<UUID>> locks = pixFile.getLocks();
		Map<UUID, Boolean> visibility = pixFile.getVisibility();
		Map<UUID, Pile<Effect>> effects = pixFile.getEffects();

		List<ALayer> imageLayers = image.getLayerList().getAllItems();
		for (ALayer layer : imageLayers) {

			// set each layers visibility
			layer.setVisible(visibility.get(layer.getUUID()));

			// set the lock relations between each "active" layers and the
			// others
			if (locks.containsKey(layer.getUUID())) {
				if (!locks.get(layer.getUUID()).isEmpty()) {
					Set<ALayer> locked = locks.get(layer.getUUID()).stream()
							.map(uuid -> getLayerByUUID(uuid, imageLayers))
							.collect(toSet());
					groupLock.getGroup().put(layer, locked);
				}
			}
		}

		// set image effects in app context
		AppContext.getInstance().getEffectManager().getManager().put(
				image,
				effects.entrySet().stream()
						.collect(Collectors.toMap(
								entry -> getLayerByUUID(entry.getKey(), imageLayers),
								Entry<UUID, Pile<Effect>>::getValue)));

		// set image colors in the gui facade map (checked when a tab is opened)
		Set<ColorRGB> colors = pixFile.getColors();
		gui.getImagesColors().put(image, colors.stream().map(ColorRGB::getColorProperty).collect(toSet()));

		// set file path in files context map
		AppContext.getInstance().getFiles().put(image, file);

		return image;
	}

	private ALayer getLayerByUUID(UUID uuid, List<ALayer> set) {

		for (ALayer layer : set) {
			if (layer.getUUID().equals(uuid)) {
				return layer;
			}
		}

		return null;
	}

}
