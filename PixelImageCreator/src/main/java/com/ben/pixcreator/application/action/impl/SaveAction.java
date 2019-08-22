
package com.ben.pixcreator.application.action.impl;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.color.rgb.ColorRGB;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.file.PixFile;
import com.ben.pixcreator.application.grouplock.GroupLock;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.effect.Effect;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.pile.BasicPile;
import com.ben.pixcreator.application.pile.Pile;
import com.ben.pixcreator.gui.exception.popup.ExceptionPopUp;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.scene.paint.Color;

public class SaveAction implements IAction {

	private final PixImage		image;
	private final AppContext	ctx	= AppContext.getInstance();
	private final GuiFacade		gui	= GuiFacade.getInstance();

	public SaveAction(PixImage image) {

		this.image = image;
	}

	@Override
	public void execute() throws Exception {

		File file = ctx.getPixImageFiles().get(image);

		image.setName(file.getName());

		PixFile pixFile = makePixFile(file);

		serializePixFile(file, pixFile);

	}

	private PixFile makePixFile(File file) {

		Set<ColorRGB> colors;
		Map<UUID, Set<UUID>> locks;
		Map<UUID, BasicPile<Effect>> effects;
		Map<UUID, Boolean> visibility;

		GuiFacade gui = GuiFacade.getInstance();
		GroupLock groupLock = AppContext.getInstance().getGroupLocks().get(gui.getActiveImage());

		colors = gui.getImagesColors().get(image).stream()
				.map(prop -> new ColorRGB((Color) prop.get())).collect(toSet());

		locks = image.getLayerPile().getAllItems().stream()
				.collect(toMap(
						ALayer::getUUID,
						layer -> (Set<UUID>) groupLock.getLockedLayers(layer).stream()
								.map(ALayer::getUUID)
								.collect(toSet())));

		effects = AppContext.getInstance().getEffectManager().getImageEffects(image).entrySet().stream()
				.collect(toMap(
						entry -> entry.getKey().getUUID(),
						Entry<ALayer, BasicPile<Effect>>::getValue));

		visibility = image.getLayerPile().getAllItems().stream()
				.collect(toMap(
						ALayer::getUUID,
						ALayer::isVisible));

		PixFile pixFile = new PixFile(image, colors, locks, effects, visibility);

		return pixFile;
	}

	private void updateRecentFileOrder(File file) {
	
		final Pile<String> recentFiles = gui.getRecentFiles();
	
		recentFiles.removeIfPresent(file.toString());
	
		recentFiles.add(file.toString());
	
	}

	private void serializePixFile(File file, PixFile pixFile) {
		try (FileOutputStream fileOut = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);) {
	
			out.writeObject(pixFile);
	
			updateRecentFileOrder(file);
	
			gui.updateRecentFilesMenu();
	
		} catch (IOException e) {
			new ExceptionPopUp(e);
		}
	}

}
