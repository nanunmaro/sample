package com.example.sample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener{
	
	EditText sharedData;
	TextView dataResults;
	public static String fileName = "sharedPrefsData";
	SharedPreferences someData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVariables();
		//second parameter (mode) is mode for writing, reading, etc
		someData = getSharedPreferences(fileName, 0);
	}

	private void setupVariables() {
		Button save = (Button)findViewById(R.id.buttonSave);
		Button load = (Button)findViewById(R.id.buttonLoad);
		sharedData = (EditText)findViewById(R.id.editTextSharedPrefs);
		dataResults = (TextView)findViewById(R.id.textViewSharedPrefs);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.buttonSave:
			String stringData = sharedData.getText().toString();
			//Editor editor
			SharedPreferences.Editor editor = someData.edit();
			editor.putString("someData", stringData);
			editor.commit();
			break;
		case R.id.buttonLoad:
			someData = getSharedPreferences(fileName, 0);
			String dataRead = someData.getString("someData", "Couldn't Load Data");
			dataResults.setText(dataRead);
			break;
		}
	}
	
	
}
