package com.example.patient;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Patient.Utility.my_utility;

public class Login extends Activity {
	public EditText Cnic, Password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button signupbutton;

		signupbutton = (Button) findViewById(R.id.signinbutton);
		signupbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				new Thread(new Runnable() {

					@Override
					public void run() {

						Cnic = (EditText) findViewById(R.id.patientcnic);
						Password = (EditText) findViewById(R.id.patientpassword);
						ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
						nvp.add(new BasicNameValuePair("request_type", "signin"));

						nvp.add(new BasicNameValuePair("Password", Password
								.getText().toString()));
						nvp.add(new BasicNameValuePair("Cnic", Cnic.getText()
								.toString()));

						final String result = my_utility.hmsRequest("database.php", nvp);
						if(!result.equals("Invalid user Name and password"))
						{
							try {
								JSONObject json_data = new JSONObject(result);
								SharedPreferences pref = getApplicationContext().getSharedPreferences("Patient", 0); // 0 - for private mode
								   
								   Editor editor = pref.edit();
								   editor.putString("Login","yes" );
									editor.putString("Name", json_data.getString("Name"));
									editor.putString("Cnic",json_data.getString("Cnic"));
									editor.putString("Dob",json_data.getString("Dob"));
									editor.putString("Contact",json_data.getString("Contact"));
									editor.putString("Gender",json_data.getString("Gender") );
									editor.commit();
									
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						
						
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								
								if (!result.equals("Invalid user Name and password")) {
									Intent i = new Intent(
											getApplicationContext(),
											AndroidTabLayoutActivity.class);
									 startActivity(i);
								}
								else
								{
									Toast.makeText(getApplicationContext(),"Invalid Cnic and Password", Toast.LENGTH_LONG).show();
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
		getMenuInflater().inflate(R.menu.login, menu);
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
