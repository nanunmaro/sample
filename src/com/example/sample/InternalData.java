package com.example.sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener {

	EditText sharedData;
	TextView dataResults;
	FileOutputStream fos;
	String fileName = "Internal String";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVariables();
	}

	private void setupVariables() {
		Button save = (Button) findViewById(R.id.buttonSave);
		Button load = (Button) findViewById(R.id.buttonLoad);
		sharedData = (EditText) findViewById(R.id.editTextSharedPrefs);
		dataResults = (TextView) findViewById(R.id.textViewSharedPrefs);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
		try {
			fos = openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonSave:
			String data = sharedData.getText().toString();
			// saving data via string > File
			/*
			 * File f = new File(fileName); try { fos = new FileOutputStream(f);
			 * fos.close(); } catch (FileNotFoundException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } catch
			 * (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			try {
				fos = openFileOutput(fileName, Context.MODE_PRIVATE);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fos.write(data.getBytes());
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.buttonLoad:
			new loadSomeStuff().execute(fileName);
			break;
		}
	}
	//creates a secondary thread for file load.
	public class loadSomeStuff extends AsyncTask<String, Integer, String> {
		
		ProgressDialog dialog;
		
		protected void onPreExecute(String f) {
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String collected = null;
			FileInputStream fis = null;
			
			for (int i = 0; i < 20; i++){
				publishProgress(5);
				try {
					Thread.sleep(88);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//get lid of dialog after the loop work
			dialog.dismiss();
			try {
				fis = openFileInput(fileName);
				byte[] dataArray = new byte[fis.available()];
				while (fis.read(dataArray) != -1) {
					collected = new String(dataArray);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					return collected;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			dialog.incrementProgressBy(progress[0]);
		}

		protected void onPostExecute(String result) {
			dataResults.setText(result);
		}

	}
}
