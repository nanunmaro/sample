package com.example.sample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {

	customSurfaceView ourSurfaceView;
	float x, y, sX, sY, fX, fY = 0;
	float dX, dY, animatedX, animatedY, scaledX, scaledY = 0;
	Bitmap test, heart;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new customSurfaceView(this);
		ourSurfaceView.setOnTouchListener(this);
		test = BitmapFactory.decodeResource(getResources(), R.drawable.blacksmiley);
		heart = BitmapFactory.decodeResource(getResources(), R.drawable.blackheart);
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = event.getX();
		y = event.getY();
		
		switch(event.getAction()){
		//when you press down for the first time
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			fX = fY = dX = dY = animatedX = animatedY = scaledX = scaledY = 0;
			break;
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			dX = fX - sX;
			dY = fY - sY;
			scaledX = dX/30;
			scaledY = dY/30;
			x = y = 0;
			break;
		}
		return true;
	}

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
				
				if(x != 0 && y != 0){
					canvas.drawBitmap(test, x-test.getWidth()/2, y-test.getWidth()/2, null);
				}
				if(sX != 0 && sY != 0){
					canvas.drawBitmap(heart, sX-test.getWidth()/2, sY-test.getWidth()/2, null);
				}
				if(fX != 0 && fY != 0){
					canvas.drawBitmap(test, fX-test.getWidth()/2-animatedX, fY-test.getWidth()/2-animatedY, null);
					canvas.drawBitmap(heart, fX-test.getWidth()/2, fY-test.getWidth()/2, null);
				}
				animatedX = animatedX + scaledX;
				animatedY = animatedY + scaledY;
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}

	}

}
