package com.rmwebfx.common.ads;

import android.app.Activity;
import android.view.ViewGroup;

import com.google.ads.*;

public class GoogleAds {
	
	public static AdView showAd(Activity activity, ViewGroup layout, String publisherId) {
		AdView adView = new AdView(activity, AdSize.BANNER, publisherId);
		
		layout.addView(adView);
		adView.loadAd(new AdRequest());
		
		return adView;
	}
}