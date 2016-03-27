package com.Patient.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Patient.model.Doctor;
import com.example.patient.R;

public class CustomListAdapter extends BaseAdapter {
	Context context;

	int layoutResourceId;
	List<Doctor> list;

	public CustomListAdapter(Context co, int ResourceID, List<Doctor> l) {
		context = co;
		layoutResourceId = ResourceID;
		list = l;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ListviewHolder holder;
		if (convertView == null) {

			holder = new ListviewHolder();
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(layoutResourceId, parent, false);

			holder.img = (ImageView) convertView.findViewById(R.id.thumbnail);
			holder.name = (TextView) convertView.findViewById(R.id.docname);
			holder.spec = (TextView) convertView.findViewById(R.id.docspecialiazation);
			holder.email = (TextView) convertView.findViewById(R.id.docemail);
			holder.status = (TextView) convertView.findViewById(R.id.docstatus);

			holder.img.setBackground(context.getResources().getDrawable(R.drawable.female));
			holder.name.setText(list.get(position).getDocName());
			holder.spec.setText(list.get(position).getDocSpecialization());
			holder.email.setText(list.get(position).getDocEmail());
			holder.status.setText(list.get(position).getDocStatus());

			convertView.setTag(holder);

		} else {
			holder = (ListviewHolder) convertView.getTag();
		}

		return convertView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Doctor> getItem(int arg0) {

		return (List<Doctor>) list.get(arg0);

	}

	public void remove(int pos) {
		list.remove(pos);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	public Doctor GetItemAtPosition(int pos)
	{
		return list.get(pos);
	}

	static class ListviewHolder {
		ImageView img;
		TextView name;
		TextView spec;
		TextView email;
		TextView status;
	}

}
