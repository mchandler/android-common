package com.rmwebfx.common.persistent;

public interface IDao {
	public void save(IPersistent obj);
	public IPersistent load(IPersistent obj, int id);
	public IPersistent load(IPersistent obj, String column);
	public void delete(IPersistent obj);
	public IPersistent getLatestRecord(IPersistent obj);
}