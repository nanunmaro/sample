package com.example.sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_HOTNESS = "persons_hotness";

	private static final String DATABASE_NAME = "HotOrNotdb";
	private static final String DATABASE_TABLE = "peopleTable";
	private static final int DATABASE_VERSION = 1;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDb;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT NOT NULL, "
					+ KEY_HOTNESS + " TEXT NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}

	}

	// initialize
	public HotOrNot(Context c) {
		ourContext = c;
	}

	public HotOrNot open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDb = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	public long createEntry(String name, String hotness) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_HOTNESS, hotness);
		return ourDb.insert(DATABASE_TABLE, null, cv);
	}

	public String getData() throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS };
		// read information through a cursor when using a DB
		Cursor pointy = ourDb.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		String result = "";
		int iRow = pointy.getColumnIndex(KEY_ROWID);
		int iName = pointy.getColumnIndex(KEY_NAME);
		int iHotness = pointy.getColumnIndex(KEY_HOTNESS);
		// moves cursor around string[]
		for (pointy.moveToFirst(); !pointy.isAfterLast(); pointy.moveToNext()) {
			// read data
			result = result + pointy.getString(iRow) + " "
					+ pointy.getString(iName) + " "
					+ pointy.getString(iHotness) + "\n";
		}
		return result;
	}

	public String getName(long l) throws SQLException{
		// cursor reads data for us
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS };
		Cursor pointy = ourDb.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if(pointy != null){
			pointy.moveToFirst();
			String name = pointy.getString(1);
			return name;
		}
		return null;
	}

	public String getHotness(long l) throws SQLException{
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS };
		Cursor pointy = ourDb.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if(pointy != null){
			pointy.moveToFirst();
			String hot = pointy.getString(2);
			return hot;
		}
		return null;
	}

	public void deleteEntry(long lDel) throws SQLException{
		// TODO Auto-generated method stub
		ourDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + lDel, null);
	}

	public void updateEntry(long lEdit, String editName, String editHotness) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, editName);
		cvUpdate.put(KEY_HOTNESS, editHotness);
		ourDb.update(DATABASE_TABLE, cvUpdate, KEY_ROWID +"="+lEdit, null);
	}
}
