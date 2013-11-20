package com.example.sample;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView, getInfo, editEntry, deleteEntry;
	EditText sqlName, sqlHotness, sqlRowID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteex);
		sqlUpdate = (Button) findViewById(R.id.buttonSQLupdate);
		sqlView = (Button) findViewById(R.id.buttonSQLview);
		getInfo = (Button) findViewById(R.id.buttonGetInformation);
		editEntry = (Button) findViewById(R.id.buttonEditEntry);
		deleteEntry = (Button) findViewById(R.id.buttonDeleteEntry);
		sqlUpdate.setOnClickListener(this);
		sqlView.setOnClickListener(this);
		getInfo.setOnClickListener(this);
		editEntry.setOnClickListener(this);
		deleteEntry.setOnClickListener(this);
		sqlName = (EditText) findViewById(R.id.editTextSQLName);
		sqlHotness = (EditText) findViewById(R.id.editTextSQLHot);
		sqlRowID = (EditText) findViewById(R.id.editTextRowID);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonSQLupdate:
			boolean didItWork = true;
			try {
				String name = sqlName.getText().toString();
				String hotness = sqlHotness.getText().toString();
				HotOrNot entry = new HotOrNot(SQLiteExample.this);
				entry.open();
				entry.createEntry(name, hotness);
				entry.close();
			} catch (Exception e) {
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("FAIL");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Hell Yeah!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
		case R.id.buttonSQLview:
			Intent i = new Intent("com.example.sample.SQLVIEW");
			startActivity(i);
			break;
		case R.id.buttonEditEntry:
			try {
				String editName = sqlName.getText().toString();
				String editHotness = sqlHotness.getText().toString();
				String sEdit = sqlRowID.getText().toString();
				long lEdit = Long.parseLong(sEdit);
				HotOrNot honHotness = new HotOrNot(this);
				honHotness.open();
				honHotness.updateEntry(lEdit, editName, editHotness);
				honHotness.close();
				break;
			} catch (Exception e) {
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("FAIL");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
		case R.id.buttonGetInformation:
			try {
				String s = sqlRowID.getText().toString();
				long l = Long.parseLong(s);
				HotOrNot hon = new HotOrNot(this);
				hon.open();
				String returnedName = hon.getName(l);
				String returnedHotness = hon.getHotness(l);
				hon.close();
				sqlName.setText(returnedName);
				sqlHotness.setText(returnedHotness);
			} catch (Exception e) {
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("FAIL");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
		case R.id.buttonDeleteEntry:
			try {
				String sDel = sqlRowID.getText().toString();
				long lDel = Long.parseLong(sDel);
				HotOrNot honDel = new HotOrNot(this);
				honDel.open();
				honDel.deleteEntry(lDel);
				honDel.close();
				break;
			} catch (Exception e) {
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("FAIL");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
		}
	}

}
