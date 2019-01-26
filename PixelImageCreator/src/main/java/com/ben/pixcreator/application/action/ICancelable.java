package com.ben.pixcreator.application.action;

public interface ICancelable {

	public void execute() throws Exception;

	public void cancel() throws Exception;

}
