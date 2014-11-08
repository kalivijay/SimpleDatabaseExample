package com.example.simpledatabaseexample;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

	public static String databaseName = "peh.db";
	String tableName = "first_table";
	public String firstField = "FirstName";
	public String secondField = "SecondName";
	public String idField = "id";
	ArrayList<String> array_list = new ArrayList<String>();

	SQLiteDatabase sqLiteDatabase_Object;

	public MyDatabase(Context context) {
		super(context, databaseName, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		String sql = "create table if not exists " + tableName + "(" + idField
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + firstField
				+ " TEXT, " + secondField + ")";
		// sqLiteDatabase_Object.execSQL(sql);
		arg0.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void save(String firstEdittext_value, String secondEdittext_value) {
		sqLiteDatabase_Object = this.getWritableDatabase();
		ContentValues contentValues_object = new ContentValues();
		contentValues_object.put(firstField, firstEdittext_value);
		contentValues_object.put(secondField, secondEdittext_value);
		sqLiteDatabase_Object.insert(tableName, null, contentValues_object);
		sqLiteDatabase_Object.close();
	}

	public ArrayList<String> fetch() {

		String sql = "SELECT * FROM " + tableName;
		sqLiteDatabase_Object = this.getReadableDatabase();
		Cursor resultSet = sqLiteDatabase_Object.rawQuery(sql, null);
		if (resultSet.moveToFirst()) {
			do {
				array_list.add(resultSet.getString(0));
				array_list.add(resultSet.getString(1));
				array_list.add(resultSet.getString(2));
			} while (resultSet.moveToNext());
		}
		resultSet.close();
		return array_list;
	}

	public void update_row(String firstEdittext_value,
			String secondEdittext_value, String row_id) {
		sqLiteDatabase_Object = this.getWritableDatabase();
		ContentValues contentValues_object = new ContentValues();
		contentValues_object.put(firstField, firstEdittext_value);
		contentValues_object.put(secondField, secondEdittext_value);

		sqLiteDatabase_Object.update(tableName, contentValues_object, idField
				+ "=?", new String[] { row_id });

	}

	public void delete_row(String row_id) {
		sqLiteDatabase_Object = this.getWritableDatabase();
		sqLiteDatabase_Object.delete(tableName, idField + "=?",
				new String[] { row_id });
	}

	public void delete_all_records() {
		sqLiteDatabase_Object = this.getWritableDatabase();
		String sql = "Delete from " + tableName;
		sqLiteDatabase_Object.execSQL(sql);
		sqLiteDatabase_Object.close();
	}

	public void delete_table() {
		sqLiteDatabase_Object = this.getWritableDatabase();
		String sql = "drop table " + tableName;
		sqLiteDatabase_Object.execSQL(sql);
		sqLiteDatabase_Object.close();
	}
}
