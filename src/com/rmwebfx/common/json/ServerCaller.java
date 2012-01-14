package com.rmwebfx.common.json;

import java.net.URL;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
		String jsonResponse = null;
		
		Log.d("BUG",method.toLowerCase());
		doRestGet();
	}
	
	private String doRestGet() {
		Client client = new Client();
		WebResource resource = client.resource(url.toString());
		
		String response = resource.get(String.class);
		Log.d("Response", response);
		
		return "";
	}
	
}