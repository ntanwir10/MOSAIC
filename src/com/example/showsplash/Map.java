package com.example.showsplash;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Map extends Activity {
     
	String name, email, phone, add, lg, lt;
	Integer id=0;
	boolean IsSame = false, IsSaved= true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        final TextView etlg = (TextView)findViewById(R.id.lgText);
		final TextView etlt = (TextView)findViewById(R.id.ltText);
		Button bt = (Button) findViewById(R.id.update);

		Intent i = getIntent();
		name = i.getStringExtra("name");
		email = i.getStringExtra("email");
		phone = i.getStringExtra("phone");
		add = i.getStringExtra("add");

		

		database odata = new database(getApplicationContext(), "db_MOSAIC1",null, 1);
		final SQLiteDatabase db = odata.getWritableDatabase();

		//**********************Get location through G-map*************************//
		
		
        GoogleMap ogmap=((MapFragment)getFragmentManager().findFragmentById(R.id.g_map)).getMap(); 
		ogmap.setOnMapLongClickListener(new OnMapLongClickListener() 
        {
			
			public void onMapLongClick(LatLng arg0) 
			{
				// TODO Auto-generated method stub
				String clientlat=Double.toString(arg0.latitude);
				String clientlog=Double.toString(arg0.longitude);
				
				etlg.setText(clientlog);
				etlt.setText(clientlat);
				//registeremployee.execSQL("insert into enterclientdetail values('"+enterclientname+"','"+enterclientnumber+"','"+clientlog+"','"+clientlat+"')");
				//finish();
			}
		});
		
		//****************************************************************************//
		
		bt.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(IsSame==false)
				{
				try{
					final Cursor c = db.rawQuery("select count(*) from client_details", null);
					Log.i("Start","Logging");
					if(c.getCount()>0)
					{
						Log.i("Start","id willbe 1");
						final Cursor cr = db.rawQuery("select id from client_details", null);
						Log.i("Cr","ture");
						cr.moveToLast();
						id=cr.getInt(0);
						id=id+1;
						Log.i("Stop","id = 1");
						cr.close();
						
					}else{
						Log.i("Start","id will be 0");
						id=0;
						Log.i("Stop","id=0");
						
					}
					
				}catch (SQLiteException e){
					Log.i("Stop cursor",e.getMessage().toString());
				}
				finally{
					try{
						lg = etlg.getText().toString().trim();
						lt = etlt.getText().toString().trim();
						db.execSQL("insert into client_details values(" + id + ",'" + name + "','" + email + "','" + phone + "','" + add + "','" + lt + "','" + lg + "')");
						IsSame=true;
					}catch (SQLiteException e){
						
						Toast.makeText(getApplicationContext(), e.getMessage().toString(), 5000).show();
						IsSaved=false;
						
					}finally
					{
						if(IsSaved==true){
							Toast.makeText(getApplicationContext(), "Record Saved!!", 3000).show();
						}
						
							
					}
				}
						
				}else{
					Toast.makeText(getApplicationContext(), "Please Enter New Record !!", 3000).show();
				}
				
					
				}
				
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_map, menu);
        return true;
    }
}
