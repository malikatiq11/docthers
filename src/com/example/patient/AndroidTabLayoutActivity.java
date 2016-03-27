package com.example.patient;


import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabLayoutActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabHost = getTabHost();
        
       
        
        // Tab for Songs
        TabSpec doctorspec = tabHost.newTabSpec("Doctors");
        // setting Title and Icon for the Tab
        doctorspec.setIndicator("Doctors", getResources().getDrawable(R.drawable.icon_doctors_tab));
        Intent songsIntent = new Intent(this, SearchDoctor.class);
        doctorspec.setContent(songsIntent);
        
        // Tab for Videos
        TabSpec chatspec = tabHost.newTabSpec("Messenger");
        
        chatspec.setIndicator("Messenger", getResources().getDrawable(R.drawable.icon_chat_tab));
        Intent videosIntent = new Intent(this,LoginActivity.class);
        chatspec.setContent(videosIntent);
        
        // Tab for Photos
        TabSpec homespec = tabHost.newTabSpec("Home");
        homespec.setIndicator("Home", getResources().getDrawable(R.drawable.icon_home_tab));
        Intent photosIntent = new Intent(this, HomeActivity.class);
        homespec.setContent(photosIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(homespec); // Adding photos tab
        tabHost.addTab(doctorspec); // Adding songs tab
        tabHost.addTab(chatspec); // Adding videos tab
        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#03CFAE")); //unselected
        }
    
    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.working, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_logout) {
			SharedPreferences pref = getApplicationContext().getSharedPreferences("Patient", 0);
			Editor edit=pref.edit();
			edit.clear();
			edit.commit();
			Intent i=new Intent(this,MainActivity.class);
			startActivity(i);
			return true;
			
		}
		return false;
	}
}