package com.ben.pixcreator.application.image.creator;

import com.ben.pixcreator.application.context.AppContext;
import com.ben.pixcreator.application.image.PixImage;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PixImageCreator {

	private static final String	_1200PX_900PX	= "1200px * 900px";
	private static final String	_800PX_600PX	= "800px * 600px";
	private static final String	_400PX_300PX	= "400px * 300px";
	private Alert				pop;
	ToggleGroup					toggleGroup		= new ToggleGroup();

	public PixImageCreator() {

		String txt = AppContext.getInstance().getBundle().getString("newImageDialogContextText");

		pop = new Alert(AlertType.CONFIRMATION, txt, ButtonType.OK);
		pop.getDialogPane().setContent(content());

	}

	public PixImage get() {

		pop.showAndWait();

		switch (((RadioButton) toggleGroup.getSelectedToggle()).getText()) {
		case _1200PX_900PX:
			return new PixImage(_1200PX_900PX, 1200, 900);
		case _800PX_600PX:
			return new PixImage(_800PX_600PX, 800, 600);

		case _400PX_300PX:
			return new PixImage(_400PX_300PX, 400, 300);
		default:
			break;
		}
		return new PixImage();
	}

	private Node content() {

		VBox box = new VBox();
		StackPane stack = new StackPane(box);

		RadioButton A = new RadioButton();
		A.setToggleGroup(toggleGroup);
		A.setText(_400PX_300PX);
		RadioButton B = new RadioButton();
		B.setToggleGroup(toggleGroup);
		B.setText(_800PX_600PX);
		RadioButton C = new RadioButton();
		C.setToggleGroup(toggleGroup);
		C.setText(_1200PX_900PX);
		box.getChildren().add(new Label(AppContext.getInstance().getBundle().getString("imageSize")));
		box.getChildren().add(A);
		box.getChildren().add(B);
		box.getChildren().add(C);

		toggleGroup.selectToggle(B);

		return stack;
	}

}
