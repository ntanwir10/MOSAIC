package com.example.showsplash;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Client_Details extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client__details);
        Intent i = getIntent();
		final String n,lg,lt;
		
		n = i.getStringExtra("name");
		
		final TextView name = (TextView) findViewById(R.id.tv_name);
		final TextView contact = (TextView) findViewById(R.id.tv_contact);
		
		final EditText msg=(EditText)findViewById(R.id.et_msg);
		
		Button map=(Button)findViewById(R.id.bt_map);
		Button call=(Button)findViewById(R.id.bt_call);
		Button send=(Button)findViewById(R.id.bt_msg);
        
		Log.i("Start", "Logging");
		database odb = new database(getApplicationContext(), "db_MOSAIC1",null, 1);
		final SQLiteDatabase db = odb.getWritableDatabase();
		Cursor c = db.rawQuery("select * from client_details where name = '" + n + "'", null);
		c.moveToFirst();

		name.setText(c.getString(1));
		contact.setText(c.getString(3));
		lt = c.getString(5);
		lg = c.getString(6);
        
		map.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),Get_Direction.class);
				i.putExtra("lt", lt);
				i.putExtra("lg",lg);
				i.putExtra("name", n);
				startActivity(i);
			}
		});
		//*********************SEND MESSAGE**********************//
				send.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						String no=contact.getText().toString();
						String mg=msg.getText().toString();
						
						try
						{
							android.telephony.SmsManager obmsg= android.telephony.SmsManager.getDefault();
							 obmsg.sendTextMessage(no, null, mg, null, null);
							 Toast.makeText(getApplicationContext(), "Send", 5000).show();
						}
						catch(Exception e)
						{
							Toast.makeText(getApplicationContext(), "Not Send", 5000).show();
						}
						
					}
				});

		//********************** Calling Function ***************************//

				Phone obphone = new Phone();
				TelephonyManager obtell = (TelephonyManager) this
						.getSystemService(Context.TELEPHONY_SERVICE);
				obtell.listen(obphone, PhoneStateListener.LISTEN_CALL_STATE);

				call.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						String no = contact.getText().toString();
						Intent i = new Intent(Intent.ACTION_CALL);
						i.setData(Uri.parse("tel:+91" + no));
						startActivity(i);
					}
				});

			}

		}

		class Phone extends PhoneStateListener {
			private boolean isCalling = false;

			String LOG_TAG = "LOGGING 123";

			public void onCallStateChanged(int state, String incomingNumber) {
				// TODO Auto-generated method stub
				if (TelephonyManager.CALL_STATE_RINGING == state) {
					Log.i(LOG_TAG, "RINGING, number:" + incomingNumber);
				}
				if (TelephonyManager.CALL_STATE_OFFHOOK == state)

				{
					Log.i(LOG_TAG, "OFFHOOK");
					isCalling = true;

				}
				if (TelephonyManager.CALL_STATE_IDLE == state) {
					Log.i(LOG_TAG, "IDLE");
					if (isCalling) {
						Log.i(LOG_TAG, "Repeat");

						Intent in = getBaseContext().getPackageManager()
								.getLaunchIntentForPackage(
										getBaseContext().getPackageName());
						in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						start(in);
					}
				}

			}

			private void start(Intent i)
			{

			}

			private ContextWrapper getBaseContext() 
			{
				return null;
			}
		}

    
