package edu.rosehulman.classtracker.fragments;

import edu.rosehulman.classtracker.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AssignmentEntryDialogs extends DialogFragment {

	public static final int DIALOG_ID_SELECT_CLASS_AND_TYPE = 0;
	
	public static final String KEY_DIALOG_TYPE = "DIALOG TYPE KEY";
	
	public static AssignmentEntryDialogs getFragment(Bundle arguments)
	{
		AssignmentEntryDialogs frag = new AssignmentEntryDialogs();
		frag.setArguments(arguments);
		return frag;
	}
	
	public AssignmentEntryDialogs()
	{
		
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		Bundle args = this.getArguments();
		int type = args.getInt(KEY_DIALOG_TYPE);
		
		switch(type)
		{
		case DIALOG_ID_SELECT_CLASS_AND_TYPE:
			return this.createSelectClassAndTypeDialog();
		}
		
		return new Dialog(getActivity().getApplicationContext());
	}

	private Dialog createSelectClassAndTypeDialog() {
		return new AlertDialog.Builder(getActivity())
			.setMessage(R.string.addEditAssignments_dialogs_needTypeDialog_text)
			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.create();
	}
}
