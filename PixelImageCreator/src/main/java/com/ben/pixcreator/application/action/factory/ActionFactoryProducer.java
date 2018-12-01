
package com.ben.pixcreator.application.action.factory;

import com.ben.pixcreator.application.action.factory.impl.DrawActionFactory;

public class ActionFactoryProducer
{

      public AActionFactory getActionFactory(PixTool tool)
      {


switch (tool){
case PixTool.DRAW : return new DrawActionFactory(); 
//TODO all other tool cases

}


return null;
 	  
      }

}
