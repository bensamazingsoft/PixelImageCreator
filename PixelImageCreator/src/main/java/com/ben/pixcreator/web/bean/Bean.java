
package com.ben.pixcreator.web.bean;

import java.util.HashMap;
import java.util.Map;

public class Bean<T>
{

      private String		  message;
      private Map<String, String> errors;

      private T			  data;


      public Bean()
      {

	    super();
	    errors = new HashMap<>();
      }


      public String getMessage()
      {

	    return message;
      }


      public void setMessage(String message)
      {

	    this.message = message;
      }


      public Map<String, String> getErrors()
      {

	    return errors;
      }


      public void setErrors(Map<String, String> errors)
      {

	    this.errors = errors;
      }


      public T getData()
      {

	    return data;
      }


      public void setData(T data)
      {

	    this.data = data;
      }

}
