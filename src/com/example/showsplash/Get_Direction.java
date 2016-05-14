package com.example.showsplash;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Get_Direction extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__direction);
        
        Intent i = getIntent();
		
		final String callinglog = i.getStringExtra("lg");
		final String callinglat = i.getStringExtra("lt");
		final String callingname = i.getStringExtra("name");
		GoogleMap ogmap = ((MapFragment) getFragmentManager().findFragmentById(	R.id.show_map)).getMap();
		LatLng ocreatelat = new LatLng(Double.parseDouble(callinglat),Double.parseDouble(callinglog));
		ogmap.moveCamera(CameraUpdateFactory.newLatLng(ocreatelat));
		ogmap.animateCamera(CameraUpdateFactory.zoomTo(10));
		ogmap.addMarker(new MarkerOptions().position(ocreatelat).title(callingname));
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_get_direction, menu);
        return true;
    }
}
