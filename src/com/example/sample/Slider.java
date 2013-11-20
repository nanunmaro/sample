package com.example.sample;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class Slider extends Activity implements OnCheckedChangeListener, OnClickListener, OnDrawerOpenListener{

	SlidingDrawer slider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		Button button1 = (Button) findViewById(R.id.buttonOpen);
		Button button2 = (Button) findViewById(R.id.buttonTBA);
		Button button3 = (Button) findViewById(R.id.buttonToggle);
		Button button4 = (Button) findViewById(R.id.buttonClose);
		CheckBox checkbox = (CheckBox) findViewById(R.id.checkBoxLock);
		checkbox.setOnCheckedChangeListener(this);
		slider = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		slider.setOnDrawerOpenListener(this);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(buttonView.isChecked()){
			slider.lock();
		}else{
			slider.unlock();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.buttonOpen:
			slider.open();
			break;
		case R.id.buttonTBA:
			break;
		case R.id.buttonToggle:
			slider.toggle();
			break;
		case R.id.buttonClose:
			slider.close();
			break;
		}
	}

	@Override
	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		MediaPlayer mp  = MediaPlayer.create(this, R.raw.explosion);
		mp.start();
	}

}
