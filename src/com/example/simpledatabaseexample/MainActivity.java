package com.example.simpledatabaseexample;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databaseexample.R;

//NOTICE com.example.databaseexample package is in gen folder
public class MainActivity extends Activity {

	EditText edittext1, edittext2;
	Button save_button, fetch_button, update_button, delete_button,
			delete_all_records, delete_table;

	MyDatabase myDatabase_object;
	SQLiteDatabase sqLiteDatabase_object;

	ArrayList<String> saves_all_table_data = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edittext1 = (EditText) findViewById(R.id.editText1);
		edittext2 = (EditText) findViewById(R.id.editText2);
		save_button = (Button) findViewById(R.id.button1);
		fetch_button = (Button) findViewById(R.id.button2);
		update_button = (Button) findViewById(R.id.button3);
		delete_button = (Button) findViewById(R.id.button4);
		delete_all_records = (Button) findViewById(R.id.button5);
		delete_table = (Button) findViewById(R.id.button6);

		myDatabase_object = new MyDatabase(getApplicationContext());
		sqLiteDatabase_object = this.openOrCreateDatabase(
				MyDatabase.databaseName, 0, null);
		myDatabase_object.onCreate(sqLiteDatabase_object);

		save_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (edittext1.getText().toString().equalsIgnoreCase("")
						|| edittext2.getText().toString().equalsIgnoreCase("")) {
					Toast.makeText(getApplicationContext(),
							"Please Fill All Fields !!!", Toast.LENGTH_SHORT)
							.show();
				} else {
					myDatabase_object.save(edittext1.getText().toString(),
							edittext2.getText().toString());
					Toast.makeText(getApplicationContext(), "Data Saved",
							Toast.LENGTH_SHORT).show();
					edittext1.setText("");
					edittext2.setText("");
				}
			}
		});

		fetch_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				saves_all_table_data = myDatabase_object.fetch();
				Log.d("Time", saves_all_table_data + "");
				saves_all_table_data.clear();
			}
		});

		update_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// TODO Auto-generated method stub
				Intent jump_to_updateActivity = new Intent(MainActivity.this,
						UpdateActivity.class);

				startActivity(jump_to_updateActivity);

			}

		});

		delete_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alert = new AlertDialog.Builder(
						MainActivity.this);

				alert.setTitle("Delete");
				alert.setMessage("Enter Row Id");

				// Set an EditText view to get user input
				final EditText input = new EditText(MainActivity.this);
				alert.setView(input);

				alert.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								// Do something with value!
								myDatabase_object.delete_row(input.getText()
										.toString());
								Toast.makeText(getApplicationContext(),
										"Data Deleted", Toast.LENGTH_SHORT)
										.show();
							}
						});

				alert.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// Canceled.

							}
						});

				alert.show();

			}
		});

		delete_all_records.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				myDatabase_object.delete_all_records();
				Toast.makeText(getApplicationContext(), "All Records Deleted",
						Toast.LENGTH_SHORT).show();
			}
		});

		delete_table.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myDatabase_object.delete_table();
				Toast.makeText(getApplicationContext(), "Table Deleted",
						Toast.LENGTH_SHORT).show();
			}
		});

	}
}
