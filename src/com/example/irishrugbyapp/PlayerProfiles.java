package com.example.irishrugbyapp;

import com.example.irishrugbyapp.PlayerScreen1;
import com.example.irishrugbyapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class PlayerProfiles extends Fragment  {

	public PlayerProfiles() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_ppl,
				container, false);
	}

	//if a player is clicked, it sends user to PlayerScreen
		
		public void onListItemClick(ListView parent, View v, int position, long id) 
		{
			Intent i = new Intent(v.getContext(), PlayerScreen1.class);
			i.putExtra("Name", id);
			startActivity(i);
		}
}
