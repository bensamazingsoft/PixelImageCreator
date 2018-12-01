package com.ben.pixcreator.gui.pane.tabpane;



public class PixTabPane extends TabPane implements Initializable
{

      private final String     IMAGEPATH	    = "images/gui/buttons/tabpane/";



      public PixTabPane()
      {

	    super();
	    ResourceBundle bundle = ResourceBundle.getBundle("i18n/trad");

	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PixTabPane.fxml"), bundle);
	    fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);

	    try
	    {
		  fxmlLoader.load();
	    }
	    catch (IOException e)
	    {
		  throw new RuntimeException(e);
	    }

      }

      
      public void initialize(URL arg0, ResourceBundle arg1)
      {

	    // TODO initialize
	    populate();
	    

      }


	private void populate() {
		// TODO populate tabpane
		
	}
      
}