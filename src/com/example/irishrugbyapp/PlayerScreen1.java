package com.example.irishrugbyapp;

import com.example.irishrugbyapp.PlayersDBMgr;

import com.example.irishrugbyapp.HomeScreen;
import com.example.irishrugbyapp.R;
import android.app.Activity;
import android.app.ListActivity;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PlayerScreen1 extends ListActivity {
	
	TextView txt;
	PlayersDBMgr db;
	ImageView img;
	int imageRes;
	SimpleCursorAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_ps);
		
		//Gets all players
		Cursor cur = db.getAllPlayers();
		
		//Adapter for list view
		String[] Columns; 
		Columns = new String[] {"Name", "image"};
		int [] to = new int [] {R.id.rowText, R.id.img};
		adapter = new SimpleCursorAdapter(this, R.layout.fragment_ppl, cur, Columns, to, 0);
		this.setListAdapter(adapter);
		
		db.close();
		
		
		txt = (TextView)findViewById(R.id.t1);
		img = (ImageView)findViewById(R.id.img);
		
		//Here data from the previous activity is received
		Bundle extras = getIntent().getExtras();
		long value = extras.getLong("name");
		
		db = new PlayersDBMgr(this);
		db.open();
		
		//This cursor gets a player using the value from the list view
		//sent from the previous activity
		Cursor cur1 = db.getPlayer(value);
		
		//the name from the image column is set to s
		String s = cur1.getString(cur1.getColumnIndexOrThrow("image"));
		
		//here the image name is used to get the relevant resource
		//which will set an individual picture for each 
		imageRes = this.getResources().getIdentifier(s, "drawable", this.getPackageName());
				
		//image resource is set 
		img.setImageResource(imageRes);
			
		//Move the cursor to the first row
		cur1.moveToFirst();
		
		//A new array-list is declared
		new ArrayList<String>();
		
		//This do while loop sets each column to appear in the text view
		do
		{
			txt.append("Name: " + cur1.getString(cur1.getColumnIndexOrThrow("Name"))+ "\n\n" 
						+ "Height: " + cur1.getString(cur1.getColumnIndexOrThrow("Height")) + "\n\n"
						+ "Weight: " + cur1.getString(cur1.getColumnIndexOrThrow("Weight")) + "\n\n"
						+ "Position: " + cur1.getString(cur1.getColumnIndexOrThrow("Position")) + "\n\n"
						+ "Honours: " + cur1.getString(cur1.getColumnIndexOrThrow("Honours")));
		}
		while(cur1.moveToNext());
		
	}	


	public PlayerScreen1() {
		// TODO Auto-generated constructor stub
	}

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_ps, container,
				false);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
