package com.example.patient;


import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Patient.Utility.my_utility;

public class Signup extends Activity {
	
	public EditText Name,Cnic,Password,Gender,Contact,Dob;
	Button signupbutton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		signupbutton=(Button) findViewById(R.id.signupbutton);
		signupbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						
						Name=(EditText) findViewById(R.id.patientname);
						Cnic=(EditText) findViewById(R.id.patientcnic);
						Password=(EditText) findViewById(R.id.patientpassword);
						Gender=(EditText) findViewById(R.id.patientgender);
						Contact=(EditText) findViewById(R.id.patientcontact);
						Dob=(EditText) findViewById(R.id.patientdob);
						 ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
						   nvp.add(new BasicNameValuePair("request_type","signup"));
						   nvp.add(new BasicNameValuePair("Name",Name.getText().toString()));
						   nvp.add(new BasicNameValuePair("Password",Password.getText().toString()));
						   nvp.add(new BasicNameValuePair("Cnic",Cnic.getText().toString()));
						   nvp.add(new BasicNameValuePair("Contact",Contact.getText().toString()));
						   nvp.add(new BasicNameValuePair("Gender",Gender.getText().toString()));
						   nvp.add(new BasicNameValuePair("Dob",Dob.getText().toString()));
						final String result =my_utility.hmsRequest("database.php", nvp);
						Log.d("BUTT","result was "+result);
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								Toast.makeText(getApplicationContext()," "+result, Toast.LENGTH_SHORT).show();
								if(result.equals("Registration Successfull"))
								{
									Intent i=new Intent(getApplicationContext(),Welcome.class);
									startActivity(i);
								}
								
							}
						});
					}
				}).start();
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
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
