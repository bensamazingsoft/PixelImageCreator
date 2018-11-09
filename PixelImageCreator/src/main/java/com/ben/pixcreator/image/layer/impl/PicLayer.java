
package com.ben.pixcreator.image.layer.impl;

import java.io.File;

import com.ben.pixcreator.image.layer.ILayer;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class PicLayer implements ILayer
{

      private File  imageFile;
      private Image image;


      public PicLayer(File imageFile)
      {

	    super();
	    this.imageFile = imageFile;
      }


      public void show(Canvas canvas)
      {

	    // TODO Auto-generated method stub

      }


      @Override
      public String toString()
      {

	    return "PicLayer [imageFile=" + imageFile + "]";
      }


      @Override
      public int hashCode()
      {

	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((imageFile == null) ? 0 : imageFile.hashCode());
	    return result;
      }


      @Override
      public boolean equals(Object obj)
      {

	    if (this == obj)
		  return true;
	    if (obj == null)
		  return false;
	    if (!(obj instanceof PicLayer))
		  return false;
	    PicLayer other = (PicLayer) obj;
	    if (imageFile == null)
	    {
		  if (other.imageFile != null)
			return false;
	    }
	    else if (!imageFile.equals(other.imageFile))
		  return false;
	    return true;
      }


      public File getImageFile()
      {

	    return imageFile;
      }


      public void setImageFile(File imageFile)
      {

	    this.imageFile = imageFile;
      }


      public Image getImage()
      {

	    return image;
      }


      public void setImage(Image image)
      {

	    this.image = image;
      }

}
