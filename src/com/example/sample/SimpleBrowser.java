package com.example.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {

	EditText url;
	WebView browser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);

		browser = (WebView) findViewById(R.id.webViewBrowser);
		browser.getSettings().setJavaScriptEnabled(true);
		browser.getSettings().setLoadWithOverviewMode(true);
		browser.getSettings().setUseWideViewPort(true);
		
		
		browser.setWebViewClient(new browserClient());
		browser.loadUrl("http://www.google.com");

		Button go = (Button) findViewById(R.id.buttonGo);
		Button back = (Button) findViewById(R.id.buttonBack);
		Button forward = (Button) findViewById(R.id.buttonForward);
		Button refresh = (Button) findViewById(R.id.buttonRefresh);
		Button clearH = (Button) findViewById(R.id.buttonClearHistory);
		url = (EditText) findViewById(R.id.editTextWebAddress);
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		forward.setOnClickListener(this);
		refresh.setOnClickListener(this);
		clearH.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonGo:
			String webAdd = url.getText().toString();
			browser.loadUrl(webAdd);
			//imm handles the input method (keyboard)
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			//what is iBinder? 
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0); //hides keyboard
			break;
		case R.id.buttonBack:
			if (browser.canGoBack())
				browser.goBack();
			break;
		case R.id.buttonForward:
			if (browser.canGoForward())
				browser.goForward();
			break;
		case R.id.buttonRefresh:
			browser.reload();
			break;
		case R.id.buttonClearHistory:
			browser.clearHistory();
			break;
		}
	}

}
