package com.example.sample;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class browserClient extends WebViewClient {
	public boolean shouldOverrideUrlLoading(WebView v, String url){
		v.loadUrl(url);
		return true;
	}
}
