package com.example.irishrugbyapp;


import com.example.irishrugbyapp.R;

import com.example.irishrugbyapp.HomeScreen;
import com.example.irishrugbyapp.PlayerProfiles;

import com.example.irishrugbyapp.AddPlayer;
import com.example.irishrugbyapp.FindClub;
import com.example.irishrugbyapp.MyAdapter;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ImageView;

public class HomeScreenMA extends FragmentActivity implements TabListener {

	ViewPager viewPager;
	ActionBar actionBar;
	SimpleCursorAdapter adapter;
	PlayersDBMgr db;
	ImageView img;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_home_screen);
		
		viewPager=(ViewPager) findViewById(R.id.pager);
		
		viewPager.setAdapter(new MyAdapter (getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
viewPager.setAdapter(new MyAdapter (getSupportFragmentManager()));
		
		actionBar=getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.Tab tab1=actionBar.newTab();
		tab1.setText("Home");
		tab1.setTabListener(this); 
		
		ActionBar.Tab tab2=actionBar.newTab();
		tab2.setText("Player Profiles");
		tab2.setTabListener(this);
		
		ActionBar.Tab tab3=actionBar.newTab();
		tab3.setText("Add Player");
		tab3.setTabListener(this);
		
		ActionBar.Tab tab4=actionBar.newTab();
		tab4.setText("Find Club");
		tab4.setTabListener(this);
		
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
		actionBar.addTab(tab4);
		
		db = new PlayersDBMgr(this);
		db.open();
		
		img = (ImageView)findViewById(R.id.img);
		
		//Inserts for players table
				//N.B uncomment on first use the recomment or tables will fill with duplicate
				//values every time the users goes to the view players screen
		
		        db.insertPlayer("Rory Best", "1.80m (5'11)", "110kg (17st 4lb)", "Hooker","75 caps", "rb");
				db.insertPlayer("Isaac Boss", "1.78m (5'10)", "88kg (13st 12lb)", "Scrum half","20 caps","ib");
				db.insertPlayer("Tommy Bowe", "1.91m (6'3)", "96kg (15st 2lb)", "Wing","54 caps", "tb");
				db.insertPlayer("Sean Cronnin", "1.80m (5'11)", "101kg (15st 12lb)", "Hooker","35 caps", "sc");
				db.insertPlayer("Gordon D'Arcy", "1.80m (5'11)", "91kg (14st 4lb)", "Centre","79 caps", "gd");
				db.insertPlayer("Luke Fitzgarld", "1.85m (6'1)", "91kg (14st 4lb)", "Wing/Full back","27 caps", "lf");
				db.insertPlayer("Cian Healy", "1.85m (6'1)", "112kg (17st 8lb)", "Prop","47 caps", "ch");
				db.insertPlayer("Jamie Heaslip", "1.93m (6'4)", "110kg (17st 4lb)", "No.8","65 caps", "jh");
				db.insertPlayer("Dave Kearney", "1.80m (5'11)", "91kg (14st 4lb)", "Wing/Full back","7 caps", "dk");
				db.insertPlayer("Rob Kearney", "1.85m (6'1)", "95kg (15st 0lb)", "Full back/Wing","54 caps", "rk");
		      
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

}

class MyAdapter extends FragmentPagerAdapter
{

	public MyAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Fragment getItem(int arg0){
		Fragment fragment=null; 
		
		if(arg0==0)
		{
			fragment=new HomeScreen();
		}
		if(arg0==1)
		{
			fragment=new PlayerProfiles();
		}
		if(arg0==2)
		{
			fragment=new AddPlayer();
		}
		if(arg0==3)
		{
			fragment=new FindClub();
		}
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	
}