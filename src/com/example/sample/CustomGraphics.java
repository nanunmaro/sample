package com.example.sample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class CustomGraphics extends View{
	
	Bitmap smiley;
	float changingY;
	Typeface font;
	
	public CustomGraphics(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		smiley = BitmapFactory.decodeResource(getResources(), 
				R.drawable.blacksmiley);
		changingY = 0;
		font = Typeface.createFromAsset(context.getAssets(), "colour.ttf");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		Paint textPaint = new Paint();
		textPaint.setColor(Color.CYAN);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(50);
		textPaint.setTypeface(font);
		canvas.drawText("maroPark", canvas.getHeight()/2, 200, textPaint);
		canvas.drawBitmap(smiley, canvas.getWidth()/2, changingY, null);
		if(changingY < canvas.getHeight()){
			changingY += 10;
		}
		else{
			changingY = 0;
		}
		Rect middleRect = new Rect();
		middleRect.set(0, 400, canvas.getWidth(), 550);
		Paint blue = new Paint();
		blue.setColor(Color.BLUE);
		canvas.drawRect(middleRect, blue);
		invalidate();
	}
	
}
