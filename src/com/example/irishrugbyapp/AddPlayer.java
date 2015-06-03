package com.example.irishrugbyapp;

import com.example.irishrugbyapp.R;
import com.example.irishrugbyapp.PlayersDBMgr;
import com.example.irishrugbyapp.AddPlayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;


public class AddPlayer extends Fragment implements  OnItemSelectedListener, OnClickListener {
	
	TextView text;
	EditText pName;
	EditText pHeight;
	EditText pWeight;
	EditText pPosition;
	EditText pHonours;
	//EditText Image;
	Button btn;
	PlayersDBMgr db;
	
	/*public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
        setContentView(R.layout.fragment_apl);
        */
	 @Override
	    public View onCreateView(LayoutInflater inflater, 
        ViewGroup container, Bundle savedInstanceState) {

	        // Inflate the layout for this fragment
	        View view =  inflater.inflate(R.layout.fragment_apl, 
                                  container, false);
	 
	   

        
      //Here all the edit texts and buttons are instantiated
        pName=(EditText)view.findViewById(R.id.pName);
	        
        pHeight=(EditText)view.findViewById(R.id.pHeight);
        pWeight=(EditText)view.findViewById(R.id.pWeight);
        pPosition=(EditText)view.findViewById(R.id.pPosition);
        pHonours=(EditText)view.findViewById(R.id.pHonours);
        //Image=(EditText)findViewById(R.id.Image);
        btn=(Button)view.findViewById(R.id.addButton);

        
        //The database Manager is instantiated
        db = new PlayersDBMgr(getActivity());

        //On click listeners are set
        btn.setOnClickListener(this);
        return view;
        
    }
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	try
	{
		//These strings will take the values of the edit texts
		String Name = pName.getText().toString();
		String Height = pHeight.getText().toString();
		String Weight = pWeight.getText().toString();
		String Position = pPosition.getText().toString();
		String Honours = pHonours.getText().toString();
		//String Image = Image.getText.toString();
		
		//The database is opened here, the users inputed values are inserted into the database
		//and the database is then closed
		db.open();
		db.insertFavourite(Name, Height, Weight, Position, Honours);
		db.close();
		
	}
	
	catch(Exception e)
	{
	/*	//If there is a problem inserting the values an exception is thrown
		//A dialog appears when the exception is thrown
		String error = e.toString();
		final AlertDialog errorDia = new AlertDialog.Builder(AddPlayer.create();
		errorDia.setTitle("Error!"); //sets dialog title
		errorDia.setMessage(error); //sets dialog message
		errorDia.setButton(AlertDialog.BUTTON_POSITIVE," Ok", new DialogInterface.OnClickListener() {
			
			//This method controls dialog clicks
			public void onClick(DialogInterface dialog, int id) {
				// TODO Auto-generated method stub
				errorDia.dismiss();
			}*/
		}//);
		//errorDia.show();//shows the dialog
		
	}
	//finally
	{
	/*	
		//If the insert was successful this dialog will appear telling the user they inserted the values
		final AlertDialog successDia = new AlertDialog.Builder(AddPlayer.create();
		successDia.setTitle("Success!");
		successDia.setMessage("Inserted");
		successDia.setButton(AlertDialog.BUTTON_POSITIVE," Ok", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int id) {
				// TODO Auto-generated method stub
				successDia.dismiss();
			}*/
		}//);
		//successDia.show();*/
	//}

	
  }

//}
