package edu.rosehulman.classtracker.activites;

import java.util.ArrayList;

import edu.rosehulman.classtracker.R;
import edu.rosehulman.classtracker.adapters.AssignmentTypeAdapter;
import edu.rosehulman.classtracker.adapters.ClassAdapter;
import edu.rosehulman.classtracker.classview.AssignmentType;
import edu.rosehulman.classtracker.classview.ClassView;
import edu.rosehulman.classtracker.classview.Class;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class AssignmentEntry extends Activity implements OnClickListener {

	public static final String ENTER_EDIT_KEY = "ENTER_EDIT_KEY";
	
	public static final String ENTER_EDIT_ID_ENTER = "ENTER";
	public static final String ENTER_EDIT_ID_EDIT = "EDIT";
	
	private ListView mClassListView;
	private ListView mAssignmentTypeListView;
	private Button mDoneButton;
	private ClassView mClassView;
	
	private boolean calledAsEdit;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_entry);
       
        String enterEdit = this.getIntent().getStringExtra(ENTER_EDIT_KEY);
        if(enterEdit == ENTER_EDIT_ID_EDIT)
        {
        	calledAsEdit = true;
        }
        else
        {
        	calledAsEdit = false;
        }
        
        this.mClassView = ClassView.getInstance(getApplicationContext());
        this.mClassListView = (ListView) findViewById(R.id.addEditAssignments_classSelectList);
        this.mAssignmentTypeListView = (ListView) findViewById(R.id.addEditAssignments_typeSelectList);
        this.mDoneButton = (Button) findViewById(R.id.addEditAssignments_doneSelectingButton);
        
        ClassAdapter classAdapter = new ClassAdapter(mClassView.getClasses()); 
        this.mClassListView.setAdapter(classAdapter);
        
        this.mAssignmentTypeListView.setAdapter(new AssignmentTypeAdapter());
        
        this.mClassListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> list, View text, int position,
					long id) {
				text.setSelected(true);
				Class selected = (Class) ((ClassAdapter)list.getAdapter()).getItem(position);
				((AssignmentTypeAdapter)mAssignmentTypeListView.getAdapter()).replaceData(
						new ArrayList<AssignmentType>(selected.getAssigmentTypes()));
				mClassView.setCurrentClass(selected);
				mDoneButton.setEnabled(false);
			}
		});
        
        this.mAssignmentTypeListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> list, View text, int position,
					long id) {
				text.setSelected(true);
				AssignmentType selected = (AssignmentType) ((AssignmentTypeAdapter)list.getAdapter()).getItem(position);
				mClassView.setCurrentAssignmentType(selected);
				mDoneButton.setEnabled(true);
			}
		});
        
        this.mDoneButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_assignment_entry, menu);
        return true;
    }

	public void onClick(View v) {
		if(this.calledAsEdit)
		{
			callEditActivity();
		}
		else
		{
			createNewAssignmentDialog();
		}
	}

	private void createNewAssignmentDialog() {
		
	}

	private void callEditActivity() {
		
	}
}
