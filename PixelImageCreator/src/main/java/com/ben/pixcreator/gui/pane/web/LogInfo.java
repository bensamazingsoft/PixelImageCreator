
package com.ben.pixcreator.gui.pane.web;

public class LogInfo
{

      private String  email;

      private String  password;

      private boolean connected;


      public LogInfo()
      {

	    email = "";
	    password = "";
	    connected = false;
      }


      public LogInfo(String email, String password)
      {

	    super();
	    this.email = email;
	    this.password = password;
      }


      public String getEmail()
      {

	    return email;
      }


      public void setEmail(String email)
      {

	    this.email = email;
      }


      public String getPassword()
      {

	    return password;
      }


      public void setPassword(String password)
      {

	    this.password = password;
      }


      public boolean isConnected()
      {

	    return connected;
      }


      public void setConnected(boolean connected)
      {

	    this.connected = connected;
      }


      @Override
      public String toString()
      {

	    return "LogInfo [email=" + email + ", password=" + password + ", connected=" + connected + "]";
      }

}
