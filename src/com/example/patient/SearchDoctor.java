package com.example.patient;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.Patient.Utility.my_utility;
import com.Patient.adapter.CustomListAdapter;
import com.Patient.model.Doctor;

public class SearchDoctor extends Activity {

	List<Doctor> doctorlist = new ArrayList<Doctor>();
	List<Doctor> filterlist = new ArrayList<Doctor>();
	Doctor doctor;
	ListView list;
	CustomListAdapter adapter;
	ImageView refresh;
	EditText searchtext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_doctor);
		list = (ListView) findViewById(R.id.list);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getApplicationContext(), ViewPatie.class);
				Doctor doc = (Doctor) adapter.GetItemAtPosition(arg2);
				i.putExtra("Name", doc.getDocName());
				i.putExtra("Gender", doc.getDocGender());
				i.putExtra("Spec", doc.getDocSpecialization());
				i.putExtra("Qual", doc.getDocQualification());
				i.putExtra("Status", doc.getDocStatus());
				i.putExtra("Timing", doc.getDocTiming());
				i.putExtra("Email", doc.getDocEmail());
				i.putExtra("Address", doc.getDocAddress());
				startActivity(i);

			}
		});
		refresh = (ImageView) findViewById(R.id.searchimage);
		searchtext = (EditText) findViewById(R.id.searchdoctor);

		refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Filter(searchtext.getText().toString());
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

			}
		});

		new Thread(new Runnable() {

			@Override
			public void run() {
				ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
				nvp.add(new BasicNameValuePair("request_type", "searchall"));
				String result = my_utility.hmsRequest("database.php", nvp);
				try {
					JSONArray json_array = new JSONArray(result);
					doctorlist = new ArrayList<Doctor>();
					for (int i = 0; i < json_array.length(); i++) {
						JSONObject json_object = json_array.getJSONObject(i);
						doctor = new Doctor();
						doctor.setDocid(json_object.getInt("DocId"));
						doctor.setDocName(json_object.getString("DocName"));
						doctor.setDocCnic(json_object.getString("DocCnic"));
						doctor.setDocGender(json_object.getString("DocGender"));
						doctor.setDocQualification(json_object
								.getString("DocQualification"));
						doctor.setDocSpecialization(json_object
								.getString("DocSpecialization"));
						doctor.setDocStatus(json_object.getString("DocStatus"));
						doctor.setDocTiming(json_object.getString("DocTiming"));
						doctor.setDocDob(json_object.getString("DocDOB"));
						doctor.setDocEmail(json_object.getString("DocEmail"));
						doctor.setDocPassword(json_object
								.getString("DocPassword"));
						doctor.setDocAddress(json_object
								.getString("DocAddress"));
						doctor.setDocName(json_object.getString("DocName"));
						doctorlist.add(doctor);
					}
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							adapter = new CustomListAdapter(
									getApplicationContext(),
									R.layout.customlistview, doctorlist);
							list.setAdapter(adapter);

						}
					});

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_doctor, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_refresh) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					ArrayList<NameValuePair> nvp = new ArrayList<NameValuePair>();
					nvp.add(new BasicNameValuePair("request_type", "searchall"));
					String result = my_utility.hmsRequest("database.php", nvp);
					try {
						if (result != null) {
							JSONArray json_array = new JSONArray(result);
							doctorlist = new ArrayList<Doctor>();
							for (int i = 0; i < json_array.length(); i++) {
								JSONObject json_object = json_array
										.getJSONObject(i);
								doctor = new Doctor();
								doctor.setDocid(json_object.getInt("DocId"));
								doctor.setDocName(json_object
										.getString("DocName"));
								doctor.setDocCnic(json_object
										.getString("DocCnic"));
								doctor.setDocGender(json_object
										.getString("DocGender"));
								doctor.setDocQualification(json_object
										.getString("DocQualification"));
								doctor.setDocSpecialization(json_object
										.getString("DocSpecialization"));
								doctor.setDocStatus(json_object
										.getString("DocStatus"));
								doctor.setDocTiming(json_object
										.getString("DocTiming"));
								doctor.setDocDob(json_object
										.getString("DocDOB"));
								doctor.setDocEmail(json_object
										.getString("DocEmail"));
								doctor.setDocPassword(json_object
										.getString("DocPassword"));
								doctor.setDocAddress(json_object
										.getString("DocAddress"));
								doctor.setDocName(json_object
										.getString("DocName"));
								doctorlist.add(doctor);
							}
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									adapter = new CustomListAdapter(
											getApplicationContext(),
											R.layout.customlistview, doctorlist);
									list.setAdapter(adapter);

								}
							});
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}).start();
		}
		return super.onOptionsItemSelected(item);
	}

	public void Filter(String name) {
		for (int i = 0; i < doctorlist.size(); i++) {
			if (doctorlist.get(i).getDocName().contains(name)) {
				filterlist.add(doctorlist.get(i));
			}
		}
		Toast.makeText(getApplicationContext(),
				"length isa " + filterlist.size(), Toast.LENGTH_LONG).show();
		adapter = new CustomListAdapter(this, R.layout.customlistview,
				filterlist);
		list.setAdapter(adapter);
	}

}
