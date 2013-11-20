package com.example.sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener, OnClickListener {

	private TextView canWrite, canRead;
	private String state;
	boolean writable, readable;
	Spinner spinny;
	String[] paths = {"Music", "Picture", "Download"};
	File path, file = null;
	EditText saveFile;
	Button confirm, save;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		canWrite =  (TextView) findViewById(R.id.textViewCanWrite);
		canRead =  (TextView) findViewById(R.id.textViewCanRead);
		confirm = (Button) findViewById(R.id.buttonConfirmSaveAs);
		save = (Button)findViewById(R.id.buttonSaveFile);
		saveFile = (EditText) findViewById(R.id.editTextSaveAs);
		confirm.setOnClickListener(this);
		save.setOnClickListener(this);
		saveFile = (EditText)findViewById(R.id.editTextSaveAs);
		
		checkState();
		spinny = (Spinner)findViewById(R.id.spinnerReadWrite);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, 
				android.R.layout.simple_spinner_item, paths); 
		spinny.setAdapter(adapter);
		spinny.setOnItemSelectedListener(this);
		
	}

	private void checkState() {
		//obtains the state (writable, readable, etc) of the ESS
				state = Environment.getExternalStorageState();
				//MEDIA_MOUNTED means you can read and write
				if(state.equals(Environment.MEDIA_MOUNTED)){
					canWrite.setText("true");
					canRead.setText("true");
					writable = readable = true;
				}else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
					canWrite.setText("false");
					canRead.setText("true");
					writable = false;
					readable = true;
				}else{
					canWrite.setText("false");
					canRead.setText("false");
					writable = readable = false;
				}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int position = spinny.getSelectedItemPosition();
		switch(position){
		case 0:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.buttonSaveFile:
			String fileName = saveFile.getText().toString();
			//constructor with two parameters
			file = new File(path, fileName + ".png");
			checkState();
			if(writable == readable == true){
				path.mkdirs();
				try {
					InputStream inputstream = getResources().openRawResource(R.drawable.blacksmiley);
					OutputStream outstream = new FileOutputStream(file);
					byte[] data = new byte[inputstream.available()];
					inputstream.read(data);
					outstream.write(data);
					inputstream.close();
					outstream.close();
					
					Toast t = Toast.makeText(ExternalData.this, "file has been saved", Toast.LENGTH_SHORT);
					t.show();
					//update files for the user to use
					MediaScannerConnection.scanFile(ExternalData.this, 
							new String[] {file.toString()}, null,
							new MediaScannerConnection.OnScanCompletedListener() {
								
								@Override
								public void onScanCompleted(String path, Uri uri) {
									// TODO Auto-generated method stub
									Toast t = Toast.makeText(ExternalData.this, "scan complete", Toast.LENGTH_SHORT);
									t.show();
								}
							});
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case R.id.buttonConfirmSaveAs:
			save.setVisibility(View.VISIBLE);
			break;
		}
	}

}
