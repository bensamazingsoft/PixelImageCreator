package com.ben.pixcreator.application.pile;

public interface Pair<T> {

	public void incr();

	public void decr();

	public int getIdx();

	public T getItem();

}
