
package com.ben.pixcreator.application.tools;

public enum PixTool {

      DRAW, SELECT, MOVE, PAN, PICK, RESIZE, ZOOMIN, ZOOMOUT;

      public static PixTool getTool(String name)
      {

	    PixTool pixTool = PixTool.SELECT;

	    switch (name)
	    {

	    case "DRAW":
		  pixTool = PixTool.DRAW;
		  break;
	    case "MOVE":
		  pixTool = PixTool.MOVE;
		  break;
	    case "PAN":
		  pixTool = PixTool.PAN;
		  break;
	    case "PICK":
		  pixTool = PixTool.PICK;
		  break;
	    case "RESIZE":
		  pixTool = PixTool.RESIZE;
		  break;
	    case "ZOOMIN":
		  pixTool = PixTool.ZOOMIN;
		  break;
	    case "ZOOMOUT":
		  pixTool = PixTool.ZOOMOUT;
		  break;
	    }

	    return pixTool;

      }

}
