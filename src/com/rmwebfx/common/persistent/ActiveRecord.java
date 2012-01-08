package com.rmwebfx.common.persistent;

import java.util.HashMap;


public class ActiveRecord implements IPersistent{
	protected int id;
	protected IDao dao;
	protected HashMap<String, String> errors;
	
	/*public ActiveRecord() {
		
	}*/

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}

	public IDao getDao() {
		return dao;
	}
	
	public HashMap<String, String> getErrors() {
		return errors;
	}
	
	public void save() {
		validate();
		
		getDao().save(this);
	}

	public IPersistent load(int id) {
		return getDao().load(this, id);
	}

	public IPersistent load(String column) {
		return getDao().load(this, column);
	}
	
	public void delete() {
		getDao().delete(this);
	}

	public void validate() {
		// TODO Auto-generated method stub
	}

	@Override
	public IPersistent getLatestRecord() {
		return getDao().getLatestRecord(this);
	}
}