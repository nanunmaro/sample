package com.example.sample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


//draws a ball on the screen
public class Accelerate extends Activity implements SensorEventListener{
	float x, y, sensorX, sensorY;
	Bitmap smiley;
	customSurfaceView smileySurfaceView;
	SensorManager sm;
	
	public class customSurfaceView extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = false;

		public customSurfaceView(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			ourHolder = getHolder();
		}

		public void pause() {
			isRunning = false;
			while (true) {
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			ourThread = null;

		}

		public void resume() {
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isRunning) {
				if (!ourHolder.getSurface().isValid())
					continue;

				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(02, 02, 150);
				float centerX = canvas.getWidth()/2;
				float centerY = canvas.getHeight()/2;
				canvas.drawBitmap(smiley, centerX + sensorX*20, centerY + sensorY*20, null);
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new customSurfaceView(this));
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		//size = 0 is no accelerometer
		if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
			//setting up initial spot
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			//boolean type statement Returns
			//true if the sensor is supported and successfully enabled
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
			
			smiley = BitmapFactory.decodeResource(getResources(), R.drawable.blacksmiley);
			x = y = sensorX = sensorY = 0;
			smileySurfaceView = new customSurfaceView(this);
			smileySurfaceView.resume();
			setContentView(smileySurfaceView);
			
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sm.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		//~60fps
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sensorX = event.values[0];
		sensorY = event.values[1];
	}

}
