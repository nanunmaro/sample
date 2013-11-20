package com.example.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener {

	TabHost tabhost;
	TextView showResults;
	long start, stop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		tabhost = (TabHost) findViewById(R.id.tabhost);
		Button addTab = (Button) findViewById(R.id.buttonAddTab);
		Button btonStart = (Button) findViewById(R.id.buttonStart);
		Button btonStop = (Button) findViewById(R.id.buttonStop);
		showResults = (TextView) findViewById(R.id.textViewGetTime);
		addTab.setOnClickListener(this);
		btonStart.setOnClickListener(this);
		btonStop.setOnClickListener(this);
		//initializes tabhost
		tabhost.setup();
		//then you set up the content of the tabs in new tabspecs
		TabSpec tabspecs = tabhost.newTabSpec("tag1");
		tabspecs.setContent(R.id.tab1);
		tabspecs.setIndicator("StopWatch");
		tabhost.addTab(tabspecs);
		tabspecs = tabhost.newTabSpec("tag2");
		tabspecs.setContent(R.id.tab2);
		tabspecs.setIndicator("tab2");
		tabhost.addTab(tabspecs);
		tabspecs = tabhost.newTabSpec("tag3");
		tabspecs.setContent(R.id.tab3);
		tabspecs.setIndicator("Add a Tab");
		tabhost.addTab(tabspecs);
		start = 0;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.buttonAddTab:
			TabSpec ourSpec = tabhost.newTabSpec("newTab");
			ourSpec.setContent(new TabHost.TabContentFactory() {
				
				@Override
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView text = new TextView(Tabs.this);//why tabs.this?
					text.setText("new tab yayayay!");
					return null;
				}
			});
			ourSpec.setIndicator("New");
			tabhost.addTab(ourSpec);
			break;
		case R.id.buttonStart:
			start = System.currentTimeMillis();
			break;
		case R.id.buttonStop:
			stop = System.currentTimeMillis();
			if(start != 0){
				long result = stop-start;
				int millis = (int) result % 100;
				int seconds = (int) result/1000 % 60;
				int minutes = (int) seconds/60;
				showResults.setText(String.format("%d:%02d:%02d", minutes, seconds, millis));
			}
			break;
		}
	}

}
