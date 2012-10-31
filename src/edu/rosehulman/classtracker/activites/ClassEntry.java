package edu.rosehulman.classtracker.activites;

import java.util.ArrayList;

import edu.rosehulman.classtracker.R;
import edu.rosehulman.classtracker.adapters.AssignmentTypeAdapter;
import edu.rosehulman.classtracker.classview.AssignmentType;
import edu.rosehulman.classtracker.classview.ClassView;
import edu.rosehulman.classtracker.classview.Class;
import edu.rosehulman.classtracker.fragments.ClassEntryDialogs;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ClassEntry extends Activity {

	public static final String LOG_TAG = "CT_CE";
	
	private Button mDoneButton;
	private Button mAddButton;
	private ListView mTypeList;
	
	private ClassView mClassView;
	private EditText mNameBox;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_entry);
        
        this.mClassView = ClassView.getInstance(getApplicationContext());
        this.mDoneButton = (Button)findViewById(R.id.classEntry_completeButton);
        this.mAddButton = (Button)findViewById(R.id.classEntry_addTypeButton);
        this.mTypeList = (ListView)findViewById(R.id.classEntry_typeList);
        this.mNameBox = (EditText)findViewById(R.id.classEntry_NameEditText);
        
        this.mAddButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Bundle arguments = new Bundle();
				arguments.putInt(ClassEntryDialogs.KEY_DIALOG_TYPE, 
						ClassEntryDialogs.DIALOG_ID_ENTER_NEW_TYPE);
				arguments.putInt(ClassEntryDialogs.KEY_TYPE_NAME, 
						R.string.classEntry_dialogs_addNewTitle);
				ClassEntryDialogs frag = ClassEntryDialogs.getFragment(arguments);
				frag.show(getFragmentManager(), "newType");
			}
		});
        
        this.mDoneButton.setOnClickListener(new OnClickListener()
        {
			public void onClick(View arg0) {
				int total = getTotalWeighting();
				int typeCount = ((AssignmentTypeAdapter) mTypeList.getAdapter()).getCount();
				if(mNameBox.getText().toString() == "")
				{
					Bundle arguments = new Bundle();
					arguments.putInt(ClassEntryDialogs.KEY_DIALOG_TYPE, ClassEntryDialogs.DIALOG_ID_NO_NAME);
					ClassEntryDialogs frag = ClassEntryDialogs.getFragment(arguments);
					frag.show(getFragmentManager(), "noName");
				}
				else if(typeCount == 0)
				{
					Bundle arguments = new Bundle();
					arguments.putInt(ClassEntryDialogs.KEY_DIALOG_TYPE, ClassEntryDialogs.DIALOG_ID_NO_TYPES);
					ClassEntryDialogs frag = ClassEntryDialogs.getFragment(arguments);
					frag.show(getFragmentManager(), "noTypes");
				}
				else if(total != 100)
				{
					Bundle arguments = new Bundle();
					arguments.putInt(ClassEntryDialogs.KEY_DIALOG_TYPE,
							ClassEntryDialogs.DIALOG_ID_DOES_NOT_ADD_UP);
					ClassEntryDialogs frag = ClassEntryDialogs.getFragment(arguments);
					frag.show(getFragmentManager(), "doesNotAddUp");
				}
				else
				{
					Class created = new Class(mNameBox.getText().toString(),
							((AssignmentTypeAdapter)mTypeList.getAdapter()).getAllTypes());
					mClassView.addClass(created);
					finish();
				}
			}
        });

        this.mTypeList.setOnItemClickListener(new OnItemClickListener()
        {
			public void onItemClick(AdapterView<?> adapterView, View itemView, int position,
					long id) {
				AssignmentType type = (AssignmentType) ((AssignmentTypeAdapter)mTypeList.getAdapter()).getItem(position);
				Bundle arguments = new Bundle();
				
				arguments.putInt(ClassEntryDialogs.KEY_DIALOG_TYPE, ClassEntryDialogs.DIALOG_ID_EDIT_NEW_TYPE);
				arguments.putString(ClassEntryDialogs.KEY_TYPE_NAME, type.getName());
				arguments.putInt(ClassEntryDialogs.KEY_TYPE_WEIGHTING, (int)(type.getWeight() * 100));
				arguments.putInt(ClassEntryDialogs.KEY_TYPE_INDEX, position);
				arguments.putString(ClassEntryDialogs.KEY_TYPE_NAME, type.getName());
				
				Log.d(LOG_TAG, "Type Name set to " + type.getName());
				
				ClassEntryDialogs frag = ClassEntryDialogs.getFragment(arguments);
				frag.show(getFragmentManager(), "typeEdit");
			}
        });
        
        this.mTypeList.setAdapter(new AssignmentTypeAdapter(new ArrayList<AssignmentType>()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_class_entry, menu);
        return true;
    }

	public void editType(int typeIndex, String name, int weighting) {
		AssignmentTypeAdapter adapter = (AssignmentTypeAdapter)this.mTypeList.getAdapter();
		AssignmentType toEdit = new AssignmentType(((double)weighting)/100, name);
		adapter.replaceItem(typeIndex, toEdit);
	}

	public void addType(String name, int weighting) {
		AssignmentTypeAdapter adapter = (AssignmentTypeAdapter)this.mTypeList.getAdapter();
		AssignmentType toAdd = new AssignmentType(((double)weighting)/100, name);
		adapter.addType(toAdd);
	}
	
	public void deleteType(int position)
	{
		AssignmentTypeAdapter adapter = (AssignmentTypeAdapter)this.mTypeList.getAdapter();
		adapter.deleteItem(position);
	}
	
	private int getTotalWeighting() {
		return ((AssignmentTypeAdapter)this.mTypeList.getAdapter()).getTotalWeighting();
	}
}
