package com.example.irishrugbyapp;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class PlayersDBMgr {

	private final Context context;	
	private  DatabaseHelper DBHelper; 
	public  SQLiteDatabase db;
	
	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "Name";
	public static final String KEY_HEIGHT = "Height";
	public static final String KEY_WEIGHT = "Weight";
	public static final String KEY_POSITION = "Position";
	public static final String KEY_HONOURS = "Honours";
	public static final String KEY_IMAGE = "Image";
	
	
	public static final String KEY_FPLAYID = "_id";
	public static final String KEY_FPLAYNAME = "fp_name";
	public static final String KEY_FPLAYH = "fp_height";
	public static final String KEY_FPLAYW = "fp_weight";
	public static final String KEY_FPLAYP = "fp_position";
	public static final String KEY_FPLAYHON = "fp_honours";
	
	public static final String DATABASE_NAME = "rugby";
	public static final String PLAYER_TABLE = "players";
	private static final String FAVOURITES_TABLE = "favourites";
	
	
	public static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = 
			"create table players (_id integer primary key autoincrement, " +
					"Name text not null," + 
					"Height text not null, " + 
					"Weight text not null," +
					"Position text not null," +
					"Honours text not null," +
					"Image text not null" +
					");";
	
	private static final String FAVOURITES_CREATE = 
			"create table favourites (_id integer primary key autoincrement, " +
			        "fp_name text null, " +
			        "fp_height text null, " +
			        "fp_weight text null, " +
			        "fp_position text null, " +
			        "fp_honours text null" +
			        ");"; 
	
	public PlayersDBMgr(Context ctx)
	{
		
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper 
    {
    	public DatabaseHelper(Context cont) 
         {
    		// Call constructor of superclass of SQLLiteOpenHelper
    		super(cont, DATABASE_NAME, null, DATABASE_VERSION);
         }
    	
    	@Override
    	public void onCreate(SQLiteDatabase db) 
        {
       	 	// execute SQL Create table
    	
       	
       	 db.execSQL(DATABASE_CREATE);
       	 db.execSQL(FAVOURITES_CREATE);
       	 try{
       	 db.execSQL(DATABASE_CREATE);
       	 db.execSQL(FAVOURITES_CREATE);
       	
       	 }
       	 catch (SQLException e)
       	 {
       		 e.printStackTrace();
       	 }
        }

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
			//db.execSQL("DROP TABLE IF EXISTS players");
			
		}
		
    }
	
		//open the database
    	public PlayersDBMgr open()
    	{
    		DBHelper = new DatabaseHelper(context);
	    	 db = DBHelper.getWritableDatabase();
	    	 return this;
    		
    	}
    	//close the database
    	public void close() {
    		// TODO Auto-generated method stub
    		DBHelper.close();
    		
    	}
    	
    	//insert the player
    	public long insertPlayer(String Name, String Height,String Weight,String Position,String Honours, String Image)
	     {

	         ContentValues addplayer = new ContentValues();

	  	// setup the column values
	         addplayer.put(KEY_NAME, Name);
	         addplayer.put(KEY_HEIGHT, Height);
	         addplayer.put(KEY_WEIGHT, Weight);
	         addplayer.put(KEY_POSITION, Position);
	         addplayer.put(KEY_HONOURS, Honours);
	         addplayer.put(KEY_IMAGE, Image);
	         
	         
	         return db.insert(PLAYER_TABLE, null, addplayer);
	     }
    	
    	//insert new favourite player
    	public long insertFavourite(String fp_name, String fp_height, String fp_weight, String fp_position, String fp_honours)
    	{
    		ContentValues addplayer = new ContentValues();
    		
    		addplayer.put(KEY_FPLAYNAME, fp_name);
    		addplayer.put(KEY_FPLAYH, fp_height);
    		addplayer.put(KEY_FPLAYW, fp_weight);
    		addplayer.put(KEY_FPLAYP, fp_position);
    		addplayer.put(KEY_FPLAYHON, fp_honours);
    		return db.insert(FAVOURITES_TABLE, null, addplayer);
    	}
    	
    	
    	// delete a player
    	public boolean deletePlayer(long rowId)
    	{
    		return db.delete(PLAYER_TABLE, KEY_ID +
    						 "=" + rowId, null) > 0;
    	}
    	
    	//delete a favourite player
    	public boolean deleteFavourite(long rowId)
    	{
    		return db.delete(FAVOURITES_TABLE, KEY_FPLAYID +
    						 "=" + rowId, null) > 0;
    	}
    	
    	//gets all players
    	public Cursor getAllPlayers()
    	{
    		return db.query(PLAYER_TABLE, new String[] {
    						KEY_ID,
    						KEY_NAME,
    						KEY_HEIGHT,
    						KEY_WEIGHT,
    						KEY_POSITION,
    						KEY_HONOURS,
    						KEY_IMAGE},
    						
    						null, null, null, null, null,null);
    	}
    	
    	//gets all favourite players
    	public Cursor getAllFavourites()
    	{
    		return db.query(FAVOURITES_TABLE, new String[] {
    						KEY_FPLAYID,
    						KEY_FPLAYNAME,
    						KEY_FPLAYH,
    						KEY_FPLAYW,
    						KEY_FPLAYP,
    						KEY_FPLAYHON},
    						
    						null, null, null, null, null, null);
    	}
    	
    	
    	
    	//gets a single player
    	public Cursor getPlayer(long rowId) throws SQLException
    	{
    		Cursor pCursor =
    				db.query(true, PLAYER_TABLE, new String[] {
    						KEY_ID,
    						KEY_NAME,
    						KEY_HEIGHT,
    						KEY_WEIGHT,
    						KEY_POSITION,
    						KEY_HONOURS,
    						KEY_IMAGE,
    						},
    						KEY_ID + "=" +rowId, null, null, null, null, null);
    		
    		if (pCursor != null) {
    			pCursor.moveToFirst();
    		}
    		return pCursor;
    	}
    	
    	//get a single favourite
    	public Cursor getFavourite(long rowId) throws SQLException
    	{
    		Cursor pCursor =
    				db.query(true, FAVOURITES_TABLE, new String[] {
    						KEY_FPLAYNAME,
    						KEY_FPLAYH,
    						KEY_FPLAYW,
    						KEY_FPLAYP,
    						KEY_FPLAYHON,
    						},
    						KEY_FPLAYID + "=" +rowId, null, null, null, null, null);
    		
    		if (pCursor != null) {
    			pCursor.moveToFirst();
    		}
    		return pCursor;
    	}
    	
    	
    	//update a player
    	public boolean updatePlayer(long rowId, String Name, String Height, String Weight, String Position, String Honours)
    	{
    		ContentValues args = new ContentValues();
    		args.put(KEY_NAME, Name);
    		args.put(KEY_HEIGHT, Height);
    		args.put(KEY_WEIGHT, Weight);
    		args.put(KEY_POSITION, Position);
    		return db.update(PLAYER_TABLE, args,
    						KEY_ID + "=" + rowId, null) > 0;
    	}
    	
    	//update a favourite player
    	public boolean updateFavourite(long rowId, String fp_name, String fp_height, String fp_weight, String fp_position, String fp_honours)
    	{
    		ContentValues args = new ContentValues();
    		args.put(KEY_FPLAYNAME, fp_name);
    		args.put(KEY_FPLAYH, fp_height);
    		args.put(KEY_FPLAYW, fp_weight);
    		args.put(KEY_FPLAYP, fp_position);
    		args.put(KEY_FPLAYHON, fp_honours);
    		return db.update(FAVOURITES_TABLE, args,
    						KEY_FPLAYID + "=" + rowId, null) > 0;
    	}
    	
   
    	
    	private SQLiteDatabase getWritableDatabase() 
    	{
			// TODO Auto-generated method stub
			return null;
		}
    	

}

