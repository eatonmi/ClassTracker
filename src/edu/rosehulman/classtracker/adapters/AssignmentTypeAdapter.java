package edu.rosehulman.classtracker.adapters;

import java.util.ArrayList;

import edu.rosehulman.classtracker.R;
import edu.rosehulman.classtracker.classview.AssignmentType;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AssignmentTypeAdapter extends BaseAdapter {
	
	private ArrayList<AssignmentType> types;
	
	public AssignmentTypeAdapter(ArrayList<AssignmentType> types)
	{
		this.types = types;
	}
	
	public AssignmentTypeAdapter()
	{
		this.types = new ArrayList<AssignmentType>();
	}
	
	public void replaceData(ArrayList<AssignmentType> types)
	{
		this.types = types;
		this.notifyDataSetChanged();
	}
	
	public int getCount() {
		return types.size();
	}

	public Object getItem(int position) {
		return types.get(position);
	}

	public long getItemId(int position) {
		return types.get(position).hashCode();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TextView newView = new TextView(parent.getContext());
		AssignmentType type = this.types.get(position);
		Resources res = parent.getContext().getResources();
		newView.setText(String.format(res.getString(R.string.classEntry_assignmentTypeAdapter_viewText), 
				type.getName(), (int)(type.getWeight() * 100)));
		return newView;
	}
	
	public void replaceItem(int position, AssignmentType newType)
	{
		AssignmentType type = this.types.get(position);
		type.setName(newType.getName());
		type.setWeight(newType.getWeight());
		this.notifyDataSetChanged();
	}
	
	public void addType(AssignmentType newType)
	{
		this.types.add(newType);
		this.notifyDataSetChanged();
	}
	
	public void deleteItem(int position)
	{
		this.types.remove(position);
		this.notifyDataSetChanged();
	}
	
	public int getTotalWeighting()
	{
		int retVal = 0;
		for(AssignmentType entry : this.types)
		{
			retVal += entry.getWeight() * 100;
		}
		return retVal;
	}
	
	public ArrayList<AssignmentType> getAllTypes()
	{
		return this.types;
	}
}
