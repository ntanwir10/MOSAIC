package com.example.showsplash;

import com.example.showsplash.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Add_and_View_Clients extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nview_users);
        
        Button obtnview=(Button)findViewById(R.id.btnview);
        obtnview.setOnClickListener(new OnClickListener() 
        {
			
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				
				Intent in=new Intent(getBaseContext(), ViewClients.class);
				startActivity(in);
				
			}
		});
        
        Button obtnadd=(Button)findViewById(R.id.btnadd);
        obtnadd.setOnClickListener(new OnClickListener() 
        {
			
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
			
				Intent in=new Intent(getBaseContext(), AddClients.class);
				startActivity(in);
				
				
			}
		});
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_nview_users, menu);
        return true;
    }
}
