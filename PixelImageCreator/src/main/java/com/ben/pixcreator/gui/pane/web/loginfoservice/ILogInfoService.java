
package com.ben.pixcreator.gui.pane.web.loginfoservice;

import com.ben.pixcreator.gui.pane.web.LogInfo;
import com.ben.pixcreator.web.bean.Bean;

public interface ILogInfoService
{

      public Bean<LogInfo> validate(Bean<LogInfo> logInfoBean);


      public Bean<LogInfo> registerLogInfo(Bean<LogInfo> logInfoBean);


      public boolean deleteLogInfo(Bean<LogInfo> logInfoBean);


      public Bean<LogInfo> updateLogInfo(Bean<LogInfo> logInfoBean);

}
