package com.rmwebfx.common.json;

import org.json.JSONException;
import org.json.JSONObject;

public interface IServerRequestor {
	public void handleServerResponse(JSONObject json) throws JSONException;
}