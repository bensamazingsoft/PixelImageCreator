
package com.ben.pixcreator.application.action.impl;

import java.util.ArrayList;

import com.ben.pixcreator.application.action.IAction;

public class Operation implements IAction
{

      ArrayList<IAction> actions;


      public Operation()
      {

	    actions = new ArrayList<IAction>();
      }


      public Operation(ArrayList<IAction> actions)
      {

	    super();
	    this.actions = actions;
      }


      public void addAction(IAction action)
      {

	    if (actions == null)
	    {
		  actions = new ArrayList<IAction>();
	    }
	    actions.add(action);
      }


      public void execute() throws Exception
      {

	    if (actions != null && actions.size() > 0)
	    {
		  for (int i = 0; i < actions.size(); i++)
		  {
			actions.get(i).execute();
		  }
	    }

      }


      public void cancel() throws Exception
      {

	    if (actions != null && actions.size() > 0)
	    {
		  for (int j = actions.size(); j > 0; j--)
		  {
			actions.get(j).cancel();
		  }
	    }
      }


      public ArrayList<IAction> getActions()
      {

	    return actions;
      }


      public void setActions(ArrayList<IAction> actions)
      {

	    this.actions = actions;
      }

}
