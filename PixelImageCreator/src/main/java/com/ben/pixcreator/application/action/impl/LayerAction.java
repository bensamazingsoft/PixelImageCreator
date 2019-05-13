
package com.ben.pixcreator.application.action.impl;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ben.pixcreator.application.action.IAction;
import com.ben.pixcreator.application.action.ICancelable;
import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;
import com.ben.pixcreator.application.image.layer.impl.alayer.ALayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.BakeLayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PicLayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.PixLayer;
import com.ben.pixcreator.application.image.layer.impl.alayer.impl.TextLayer;
import com.ben.pixcreator.application.pile.Pile;
import com.ben.pixcreator.gui.controls.layer.panel.actions.LayerActions;
import com.ben.pixcreator.gui.facade.GuiFacade;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class LayerAction implements IAction, ICancelable {

	private static final Logger log = LoggerFactory.getLogger(LayerAction.class);

	final AppContext	ctx	= AppContext.getInstance();
	final GuiFacade		gui	= GuiFacade.getInstance();

	private final Pile<ALayer>	layerList;
	private final ALayer		layer;
	private final LayerActions	action;

	public LayerAction(PixImage image, ALayer layer, LayerActions action) {

		super();
		this.layerList = image.getLayerList();
		this.layer = layer;
		this.action = action;
	}

	@Override
	public void execute() throws Exception {

		switch (action) {
		case ADDNEW:
			addNewPix();
			break;
		case ADDNEWPIC:
			addNewPic();
			break;
		case ADDNEWTEXT:
			addNewText();
			break;
		case ADDNEWBAKE:
			addNewBake();
			break;
		case DELETE:
			delete();
			break;
		case DUPLICATE:
			duplicate();
			break;
		case MOVEDOWN:
			moveDown();
			break;
		case MOVEUP:
			moveUp();
			break;
		default:
			break;

		}

	}

	private void moveUp() {

		log.debug("moveUp : " + layer);
		layerList.moveUp(layer);
		gui.setFocusLayer(layer);

	}

	private void moveDown() {

		log.debug("moveDown : " + layer);
		layerList.moveDown(layer);
		gui.setFocusLayer(layer);

	}

	private void duplicate() {

		log.debug("duplicate : " + layer);
		ALayer dup = layer.duplicate();
		dup.setUuid(UUID.randomUUID());

		layerList.insertUnder(layer, dup);
		gui.setFocusLayer(layer);

	}

	private void delete() {

		log.debug("delete : " + layer);
		ctx.getGroupLocks().get(gui.getActiveimage()).unlock(layer);

		ctx.getEffectManager().getImageEffects(gui.getActiveimage())
				.remove(gui.getActiveLayer());

		layerList.removeOfItem(layer);

	}

	private void addNewPix() {

		log.debug("addNewPix");
		final PixLayer pixLayer = new PixLayer();
		insertNewLayer(pixLayer);

	}

	private void addNewText() {

		log.debug("addNewText");
		final TextLayer txtLayer = new TextLayer();
		txtLayer.zoomFactorProperty()
				.bindBidirectional(gui.getActiveTab().zoomFactorAdjustedProperty());
		insertNewLayer(txtLayer);

	}

	private void addNewBake() {

		log.debug("addNewBake");
		final BakeLayer bakeLayer = new BakeLayer();

		bakeLayer.zoomFactorProperty()
				.bindBidirectional(gui.getActiveTab().zoomFactorAdjustedProperty());

		insertNewLayer(bakeLayer);

	}

	private void addNewPic() {

		log.debug("addNewPic");
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(
				Arrays.asList(new ExtensionFilter("pics extensions", Arrays.asList("*.jpg", "*.png", "*.bmp"))));

		File picfile = fc.showOpenDialog(null);
		;

		if (null != picfile && picfile.exists()) {

			PicLayer picLayer = new PicLayer(picfile);

			picLayer.zoomFactorProperty()
					.bindBidirectional(gui.getActiveTab().zoomFactorAdjustedProperty());

			insertNewLayer(picLayer);
		}

	}

	private void insertNewLayer(ALayer newLayer) {

		log.debug("insertNewLayer");
		layerList.insertOver(layer, newLayer);
		gui.setFocusLayer(newLayer);

	}

	@Override
	public void cancel() throws Exception {

	}

}
