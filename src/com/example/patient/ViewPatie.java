package com.example.patient;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ViewPatie extends Activity {

	TextView name, gender, spec, qual, status, timing, email, address;
	Button sms,call;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_patie);
		name = (TextView) findViewById(R.id.doctorname);
		gender = (TextView) findViewById(R.id.doctorgender);
		spec = (TextView) findViewById(R.id.doctorspecialization);
		qual = (TextView) findViewById(R.id.doctorqualification);
		status = (TextView) findViewById(R.id.doctorstatus);
		timing = (TextView) findViewById(R.id.doctortiming);
		email = (TextView) findViewById(R.id.doctoremail);
		address = (TextView) findViewById(R.id.doctoraddress);
		Intent i = getIntent();
		final String namestr=i.getStringExtra("Name");
		name.setText(" Name	:	"+namestr);
		gender.setText(" Gender	:	"+i.getStringExtra("Gender"));
		spec.setText(" Specialization	:	"+i.getStringExtra("Spec"));
		qual.setText(" Qualification	:	"+i.getStringExtra("Qual"));
		status.setText(" Status	:	"+i.getStringExtra("Status"));
		timing.setText(" Timing	:	"+i.getStringExtra("Timing"));

		email.setText(" Email	:	"+i.getStringExtra("Email"));

		address.setText(" Address	:	"+i.getStringExtra("Address"));
		sms=(Button) findViewById(R.id.sms);
		sms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),LoginActivity.class);
			
				i.putExtra("Name", namestr);
				startActivity(i);
				
			}
		});
		call=(Button) findViewById(R.id.call);
		call.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),LoginActivityCall.class);
				SharedPreferences pref=getSharedPreferences("Patient",0);
				i.putExtra("MyName", pref.getString("Name", "Deafult"));
				i.putExtra("Rece", namestr);
				startActivity(i);
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_patie, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
