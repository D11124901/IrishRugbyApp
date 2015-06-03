package com.example.irishrugbyapp;

import com.example.irishrugbyapp.PlayersDBMgr;
import com.example.irishrugbyapp.AddPlayer;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditFavourite extends Activity implements OnClickListener {

	TextView text;
	EditText pName;
	EditText pHeight;
	EditText pWeight;
	EditText pPosition;
	EditText pHonours;
	Button btn;
	PlayersDBMgr db;
	long value;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_favourite);
        
        //database manager is declared
        db = new PlayersDBMgr(this);
        
        //buttons and edittexts instantiated
        pName=(EditText)findViewById(R.id.pName1);
        pHeight=(EditText)findViewById(R.id.pHeight1);
        pWeight=(EditText)findViewById(R.id.pWeight1);
        pPosition=(EditText)findViewById(R.id.pPosition1);
        pHonours=(EditText)findViewById(R.id.pHonours1);
        btn=(Button)findViewById(R.id.updateButton);
        
        //Here the extras sent with the intent are retrieved
        Bundle extra = getIntent().getExtras();
        
        //The variable value takes the value of the extra id from the previous activity
        //id contains the row id of the item selected from the list in the previous activity
        value = extra.getLong("id");
        
        //database is opened
        db.open();
        
        //This cursor will get a single favourite using the variable value
        Cursor cur = db.getFavourite(value);
        
        //This strings take the values of the cloumns retrived by the cursor
        String name = cur.getString(cur.getColumnIndexOrThrow("fp_name"));
        String height = cur.getString(cur.getColumnIndexOrThrow("fp_height"));
        String weight = cur.getString(cur.getColumnIndexOrThrow("fp_weight"));
        String position = cur.getString(cur.getColumnIndexOrThrow("fp_position"));
        String honours = cur.getString(cur.getColumnIndexOrThrow("fp_honours"));

        //The edittexts initial values are set to the strings
        //containing the values retrieved by the cursor
        pName.setText(name);
        pHeight.setText(height);
        pWeight.setText(weight);
        pPosition.setText(position);
        pHonours.setText(honours);
        
        //on click listeners set
        btn.setOnClickListener(this);
        
        db.close();
        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	try
	{
		//These strings take the values of the edittexts
		String name = pName.getText().toString();
		String height = pHeight.getText().toString();
		String weight = pWeight.getText().toString();
		String position = pPosition.getText().toString();
		String honours = pHonours.getText().toString();
		
		//The database is opened, and using the values inputed by the user
		//the favourites table is updated using these values
		db.open();
		db.updateFavourite(value, name, height, weight, position, honours);
		db.close();
		
	} 
	catch(Exception e)
	{
		//If there is a problem with the update an exception is thrown
		String error = e.toString();
		final AlertDialog errorDia = new AlertDialog.Builder(EditFavourite.this).create();
		errorDia.setTitle("Error!");
		errorDia.setMessage(error);
		errorDia.setButton(AlertDialog.BUTTON_POSITIVE," Ok", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int id) {
				// TODO Auto-generated method stub
				errorDia.dismiss();
			}
		});
		errorDia.show();
		
	}
	finally
	{
		//If the update is successful this dialog is shown
		final AlertDialog successDia = new AlertDialog.Builder(EditFavourite.this).create();
		successDia.setTitle("Success!");
		successDia.setMessage("Updated!");
		successDia.setButton(AlertDialog.BUTTON_POSITIVE," Ok", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int id) {
				// TODO Auto-generated method stub
				successDia.dismiss();
			}
		});
		successDia.show();
	}

	}
}
