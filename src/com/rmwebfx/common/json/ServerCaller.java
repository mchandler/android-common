package com.rmwebfx.common.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ServerCaller implements Runnable {

	private URL url;
	private IServerRequestor activity;
	private String method;
	
	public ServerCaller(IServerRequestor activity, URL url, String method) {
		this.url = url;
		this.activity = activity;
		this.method = method;
	}
	
	@Override
	public void run() {
		try {
			String jsonResponse = doRestCall();
			Log.d("JSONResponse",jsonResponse);
			JSONObject json = new JSONObject(jsonResponse);
			activity.handleServerResponse(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String doRestCall() {
		HttpURLConnection connection = null;
		String jsonResponse = null;
		
		try {
			if (Thread.interrupted()) {
				throw new InterruptedException();
			}
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(10000);
			connection.setRequestMethod(method);
			connection.setDoInput(true);
			
			// This is where we hit my server and expect a JSON response
			connection.connect();
			
			if (Thread.interrupted()) {
				throw new InterruptedException();
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String payload = reader.readLine();
			reader.close();
			
			jsonResponse = payload;
			// The line below is debug/testing nonsense...
			//jsonResponse = "{\"responseData\":[{\"id\":\"1\",\"lon\":\"-118.749114\",\"lat\":\"34.267620\"},{\"id\":\"2\",\"lon\":\"-118.749618\",\"lat\":\"34.267684\"},{\"id\":\"3\",\"lon\":\"-118.750825\",\"lat\":\"34.269300\"}]}";
			
			if (Thread.interrupted()) {
				throw new InterruptedException();
			}
		} catch (IOException e) {
			Log.e("ServerCaller", "IOException", e);
		} catch (InterruptedException e) {
			Log.d("ServerCaller", "InterruptedException", e);
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		
		return jsonResponse;
	}
	
}