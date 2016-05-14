package com.example.showsplash;

import com.example.showsplash.R;
import com.example.showsplash.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddClients extends Activity {
	String n="";
	String p="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clients);
        
        final TextView otvname=(TextView)findViewById(R.id.tvname);
        final TextView otvcontact=(TextView)findViewById(R.id.tvcontact);
        
        final EditText oetname=(EditText)findViewById(R.id.etname);
        final EditText oetcontact=(EditText)findViewById(R.id.etcontact);
        
        final ImageView oiv=(ImageView)findViewById(R.id.iv);
        
        Button obtnsubmit=(Button)findViewById(R.id.btnsubmit);
        obtnsubmit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				n = oetname.getText().toString().trim();
				p = oetcontact.getText().toString().trim();
				

				int flag = 1;
				if (n.isEmpty()) {
					Toast.makeText(getApplicationContext(),"Please Enter Name", 3000).show();
					flag = 0;
				}
				
				if (p.isEmpty()) {
					Toast.makeText(getApplicationContext(),"Please Enter Contact No.", 3000).show();
					flag = 0;
				}
				
				oiv.setVisibility(View.VISIBLE);
				
			}
		});
        
        oiv.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent in=new Intent(getBaseContext(), Map.class);	
			
			startActivity(in);
			}
		});
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_clients, menu);
        return true;
    }
}
