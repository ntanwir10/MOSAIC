package com.example.showsplash;

import java.util.ArrayList;

import com.example.showsplash.R;
import com.example.showsplash.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewClients extends ListActivity {

	int flag;
	ArrayList<String> values = new ArrayList<String>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clients);
        
        flag = 0;
		final ListView lv=getListView();
		database odb = new database(getApplicationContext(), "db_MOSAIC",null, 1);
		final SQLiteDatabase db = odb.getWritableDatabase();
		Cursor c = db.rawQuery("select name from client_details", null);
		c.moveToFirst();
		values.clear();
		while (c.moveToNext()) 
		{
			values.add(c.getString(0));
			Log.i("Start", "Looping");
		}
        
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values));
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				int itemPosition = position;

				String itemValue = (String) lv.getItemAtPosition(position);

				Toast.makeText(getApplicationContext(),"Position :" + itemPosition + "  ListItem : "+ itemValue, Toast.LENGTH_LONG).show();

				Intent i = new Intent(getApplicationContext(),Client_Details.class);
				
				i.putExtra("name", itemValue);
				
				startActivity(i);
				
			}
		});
		
}
    
    



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_clients, menu);
        return true;
    }
}
