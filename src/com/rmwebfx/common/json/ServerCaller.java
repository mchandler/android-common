package com.rmwebfx.common.json;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ServerCaller implements Runnable {

	private URL url;
	private IServerRequestor activity;
	private String method;
	
	public ServerCaller(IServerRequestor activity, URL url) {
		this.url = url;
		this.activity = activity;
	}
	
	@Override
	public void run() {
		String jsonResponse = doRestGet().trim();
		JSONObject json = null;
		
		try {
			json = new JSONObject(jsonResponse);
			activity.handleServerResponse(json); // delegates to the calling activity
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String doRestGet(){  
        URI uri = null;
        String response = null;
        
		try {
			uri = new URI(url.toString());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpClient httpClient = new DefaultHttpClient();  
        HttpGet request = new HttpGet(uri);  
        ResponseHandler<String> handler = new BasicResponseHandler();  
        
        try {  
            response = httpClient.execute(request, handler);  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        
        httpClient.getConnectionManager().shutdown();
        
        return response;
    }
	
}