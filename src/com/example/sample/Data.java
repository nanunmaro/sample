package com.example.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements OnClickListener {

	EditText editTextSend;
	Button startActivity, forResult;
	TextView got;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		editTextSend = (EditText) findViewById(R.id.editTextSend);
		startActivity = (Button) findViewById(R.id.buttonStartActivity);
		forResult = (Button) findViewById(R.id.buttonForResult);
		got = (TextView) findViewById(R.id.textViewGot);
		startActivity.setOnClickListener(this);
		forResult.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonStartActivity:
			String bread = editTextSend.getText().toString();
			Bundle basket = new Bundle();
			basket.putString("subway", bread);
			Intent transfer = new Intent(Data.this, OpenedClass.class);
			transfer.putExtras(basket);
			startActivity(transfer);
			break;
		case R.id.buttonForResult:
			Intent i = new Intent(Data.this, OpenedClass.class);

			startActivityForResult(i, 0);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle basket = data.getExtras();
			String s = basket.getString("description");
			got.setText(s);
		}
	}

}
