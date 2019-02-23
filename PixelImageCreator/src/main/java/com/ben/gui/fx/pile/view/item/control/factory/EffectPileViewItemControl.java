
package com.ben.gui.fx.pile.view.item.control.factory;

import javafx.scene.Node;

public interface EffectPileViewItemControl
{

      public void bypass();


      public void enable();


      public void delete();


      /**
       * builder
       * 
       * @return a javafx.scene.Node
       */
      public Node node();
}
