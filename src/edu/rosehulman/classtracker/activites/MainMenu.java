package edu.rosehulman.classtracker.activites;

import edu.rosehulman.classtracker.R;
import edu.rosehulman.classtracker.classview.ClassView;
import edu.rosehulman.classtracker.fragments.MainMenuDialogs;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity {

	private ClassView mClassView;
	
	private Button mViewClassesButton;
	private Button mEnterAssignmentButton;
	private Button mCreateClassButton;
	private Button mResetDataButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        this.mViewClassesButton = (Button)findViewById(R.id.mainMenu_viewGradesButton);
        this.mEnterAssignmentButton = (Button)findViewById(R.id.mainMenu_editAssignmentsButton);
        this.mCreateClassButton = (Button)findViewById(R.id.mainMenu_editClassesButton);
        this.mResetDataButton = (Button)findViewById(R.id.mainMenu_resetDataButton);
        
        this.mClassView = ClassView.getInstance(getApplicationContext());
        
        this.mResetDataButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Bundle args = new Bundle();
				args.putInt(MainMenuDialogs.DIALOG_TYPE_KEY, MainMenuDialogs.DIALOG_ID_RESET_WARNING);
				MainMenuDialogs frag = MainMenuDialogs.getFragment(args);
				frag.show(getFragmentManager(), "resetDialog");
			}
        	
        });
        
        this.mEnterAssignmentButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Bundle args = new Bundle();
				args.putInt(MainMenuDialogs.DIALOG_TYPE_KEY, MainMenuDialogs.DIALOG_ID_EDIT_ENTER_ASSIGNMENT_CHOOSE);
				MainMenuDialogs frag = MainMenuDialogs.getFragment(args);
				frag.show(getFragmentManager(), "enterEditDialog");
			}
		});
        
        this.mCreateClassButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent createClassIntent = new Intent(getApplicationContext(), ClassEntry.class);
				startActivity(createClassIntent);
			}
        });
        
        this.mViewClassesButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent viewIntent = new Intent(getApplicationContext(), ViewClasses.class);
				startActivity(viewIntent);
			}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

	public void resetDataPositive() {
		this.mClassView.resetData();
	}

	public void editAssignmentClicked() {
		Intent entryIntent = new Intent(getApplicationContext(), AssignmentEntry.class);
		entryIntent.putExtra(AssignmentEntry.ENTER_EDIT_KEY, AssignmentEntry.ENTER_EDIT_ID_EDIT);
		startActivity(entryIntent);
	}

	public void enterAssignmentClicked() {
		Intent entryIntent = new Intent(getApplicationContext(), AssignmentEntry.class);
		entryIntent.putExtra(AssignmentEntry.ENTER_EDIT_KEY, AssignmentEntry.ENTER_EDIT_ID_ENTER);
		startActivity(entryIntent);
	}
}
