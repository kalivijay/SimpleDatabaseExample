package com.example.simpledatabaseexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.databaseexample.R;
import com.example.simpledatabaseexample.MainActivity;
import com.example.simpledatabaseexample.MyDatabase;

public class UpdateActivity extends Activity {

	EditText edittext1, edittext2;
	Button button1;

	String value_from_promptBox;

	MyDatabase myDatabase_object;
	SQLiteDatabase sqLiteDatabase_object;

	Intent myIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);

		edittext1 = (EditText) findViewById(R.id.edittext1);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		button1 = (Button) findViewById(R.id.button1);

		myDatabase_object = new MyDatabase(getApplicationContext());
		sqLiteDatabase_object = this.openOrCreateDatabase(
				MyDatabase.databaseName, 0, null);
		myDatabase_object.onCreate(sqLiteDatabase_object);

		AlertDialog.Builder alert = new AlertDialog.Builder(UpdateActivity.this);

		alert.setTitle("Update");
		alert.setMessage("Enter Row Id");

		// Set an EditText view to get user input
		final EditText input = new EditText(UpdateActivity.this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				value_from_promptBox = input.getText().toString();
				// Do something with value!

			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
						myIntent = new Intent(UpdateActivity.this,
								MainActivity.class);
						startActivity(myIntent);
					}
				});

		alert.show();

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				myDatabase_object.update_row(edittext1.getText().toString(),
						edittext2.getText().toString(),
						value_from_promptBox.trim());
				myIntent = new Intent(UpdateActivity.this, MainActivity.class);
				startActivity(myIntent);
			}
		});

	}
}
