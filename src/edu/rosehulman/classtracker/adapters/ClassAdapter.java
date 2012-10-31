package edu.rosehulman.classtracker.adapters;

import java.util.ArrayList;
import java.util.Collection;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.rosehulman.classtracker.classview.Class;

public class ClassAdapter extends BaseAdapter {
	
	private ArrayList<Class> classes;
	
	public ClassAdapter(Collection<Class> collection)
	{
		this.classes = new ArrayList<Class>();
	    this.classes.addAll(collection);
	}
	
	public int getCount() {
		return classes.size();
	}

	public Object getItem(int position) {
		return classes.get(position);
	}

	public long getItemId(int position) {
		return classes.get(position).hashCode();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TextView retVal = new TextView(parent.getContext());
		retVal.setText(this.classes.get(position).getName());
		return retVal;
	}

}
