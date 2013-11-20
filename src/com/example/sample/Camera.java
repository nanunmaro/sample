package com.example.sample;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener {
	ImageButton imageButton;
	Button button;
	ImageView imageView;
	Intent i;
	private int cameraData = 0;
	Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialize();
		InputStream inputStream = getResources().openRawResource(R.drawable.wom_logo);
		bitmap = BitmapFactory.decodeStream(inputStream);
	}

	private void initialize() {
		// TODO Auto-generated method stub
		imageButton = (ImageButton) findViewById(R.id.imageButtonTakePicture);
		button = (Button) findViewById(R.id.buttonSetWallpaper);
		imageView = (ImageView) findViewById(R.id.imageViewReturnedPic);
		button.setOnClickListener(this);
		imageButton.setOnClickListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonSetWallpaper:
			try {
				getApplicationContext().setWallpaper(bitmap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.imageButtonTakePicture:
			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i, cameraData);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK){
			Bundle extras = data.getExtras();
			bitmap = (Bitmap) extras.get("data");
			imageView.setImageBitmap(bitmap);
		}
	}

}
