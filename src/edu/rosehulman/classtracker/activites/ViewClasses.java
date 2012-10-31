package edu.rosehulman.classtracker.activites;

import edu.rosehulman.classtracker.R;
import edu.rosehulman.classtracker.R.layout;
import edu.rosehulman.classtracker.R.menu;
import edu.rosehulman.classtracker.classview.ClassView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;

public class ViewClasses extends Activity {
	
	private Button mDoneButton;
	private ListView mClassList;
	private ListView mTypeList;
	private ClassView mClassView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classes);
        
        this.mClassView = ClassView.getInstance(getApplicationContext());
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_classes, menu);
        return true;
    }
}
