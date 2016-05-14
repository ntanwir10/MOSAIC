package com.example.showsplash;

import com.example.showsplash.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginPage extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        
        database odatabase=new database(getApplicationContext(), "mosiacdb",null,1);
        final SQLiteDatabase db=odatabase.getWritableDatabase();
        Log.i("initialize","start");
        final TextView otvusername=(TextView)findViewById(R.id.tvusername);
        final EditText oetusername=(EditText)findViewById(R.id.etusername);
        
        final TextView otvpassword=(TextView)findViewById(R.id.tvpassword);
        final TextView oetpassword=(TextView)findViewById(R.id.etpassword);
        
        final TextView otvuserval=(TextView)findViewById(R.id.tvuserval);
        final TextView otvpasswordval=(TextView)findViewById(R.id.tvpasswordval);
        
        
        otvusername.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
        otvpassword.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
        Log.i("initialize","s");
        Button obtnsubmit=(Button)findViewById(R.id.btnsubmit);
        obtnsubmit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int flag=1;
				if(oetusername.getText().toString().equals(" "))
				{
					otvuserval.setText("(*Please Enter Username)");
					Toast.makeText(getApplicationContext(), "(Please Enter Username)", 1000).show();
					flag=0;
				}
				if(oetpassword.getText().toString().equals(" "))
				{
					flag=0;
					otvpasswordval.setText("(*Please Enter Password)");
					Toast.makeText(getApplicationContext(), "(Please Enter Password)", 1000).show();
				}
				
				Cursor c=db.rawQuery("select * from login",null);
				if(c.moveToNext())
				{
					
				}
				else
				{
					Log.i("INSERTING VALUES","STARTS");
				
				db.execSQL("insert into login values('nauman','8765514116')");
				db.execSQL("insert into login values('shagun','9451543967')");		
				db.execSQL("insert into login values('shivam','9506429202')");
				
					Log.i("VALUES INSERTED","STOPPED");
				}
				
				String u=oetusername.getText().toString();
				String p=oetpassword.getText().toString();
				if(flag==1)
				{
					Cursor c1=db.rawQuery("select password from login where username='"+u+"'", null);
					if(c1.moveToNext())
					{
						if(c1.getString(0).equals(p))
								{
							Toast.makeText(getApplication(),"LOGIN SUCCESSFULL",4000).show();
							Intent in=new Intent(getApplicationContext(), Add_and_View_Clients.class);
							startActivity(in);
							finish();
								}
					}
					else
						{	
							Toast.makeText(getApplication(),"Enter Correct Username or Password",8000).show();
						}
				}
				
				
			}
		});
        
        oetusername.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
				otvuserval.setText("");
			}
		});
        
        oetpassword.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				otvpasswordval.setText("");
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login_page, menu);
        return true;
    }
}
