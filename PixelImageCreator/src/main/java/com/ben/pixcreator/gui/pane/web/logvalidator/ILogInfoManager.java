
package com.ben.pixcreator.gui.pane.web.logvalidator;

import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.web.bean.Bean;

public interface ILogInfoManager
{

      public Bean<LogInfo> validate(Bean<LogInfo> logInfoBean);


      public Bean<LogInfo> addLogInfo(Bean<LogInfo> logInfoBean);


      public boolean deleteLogInfo(Bean<LogInfo> logInfoBean);


      public Bean<LogInfo> updateLogInfo(Bean<LogInfo> logInfoBean);

}
