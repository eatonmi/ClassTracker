package edu.rosehulman.classtracker.activites;

import edu.rosehulman.classtracker.R;
import edu.rosehulman.classtracker.R.layout;
import edu.rosehulman.classtracker.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AssignmentSelect extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_select);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_assignment_select, menu);
        return true;
    }
}
