package com.rmwebfx.common.persistent;

public interface IPersistent {
	public void setId(int id);
	public int getId();
	public void validate();
	public void save();
	public IPersistent load(int id);
	public IPersistent load(String column);
	public void delete();
	public IPersistent getLatestRecord();
}