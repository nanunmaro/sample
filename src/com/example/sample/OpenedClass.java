package com.example.sample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener,
		OnCheckedChangeListener {

	TextView question, test;
	Button returnData;
	RadioGroup threeAdj;
	String gotBread, setData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String editText = getData.getString("name", "Travis is ____");
		String values = getData.getString("list", "4");
		if(values.contentEquals("1")){
			question.setText(editText);
		}
		Bundle gotBasket = getIntent().getExtras();
		gotBread = gotBasket.getString("subway");
		question.setText(gotBread);
	}

	private void initialize() {
		// TODO Auto-generated method stub
		question = (TextView) findViewById(R.id.textViewQuestion);
		test = (TextView) findViewById(R.id.textViewTest);
		returnData = (Button) findViewById(R.id.buttonReturn);
		returnData.setOnClickListener(this);
		threeAdj = (RadioGroup) findViewById(R.id.radioGroupAnswers);
		threeAdj.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent person = new Intent();
		Bundle backpack = new Bundle();
		backpack.putString("description", setData);
		person.putExtras(backpack);
		setResult(RESULT_OK, person);
		finish();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.radioCrazy:
			setData = "Probably so";
			break;
		case R.id.radioSexy:
			setData = "Definitely";
			break;
		case R.id.radioGenius:
			setData = "Yes";
			break;
		}
		test.setText(setData);
	}

}
