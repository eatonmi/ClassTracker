package edu.rosehulman.classtracker.fragments;

import edu.rosehulman.classtracker.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class ChildDialogs extends DialogFragment {
	
	public static final String KEY_DIALOG_ID = "DIALOG ID KEY";
	
	public static final int DIALOG_ID_CLASS_ENTRY_WEIGHTING_ERROR = 0;
	
	public static ChildDialogs getFragment(Bundle args)
	{
		ChildDialogs retVal = new ChildDialogs();
		retVal.setArguments(args);
		return retVal;
	}
	
	public ChildDialogs()
	{
		
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		Bundle args = this.getArguments();
		int type = args.getInt(KEY_DIALOG_ID);
		switch(type)
		{
		case DIALOG_ID_CLASS_ENTRY_WEIGHTING_ERROR:
			return this.createEntryWeightingErrorDialog();
		}
		return new Dialog(getActivity().getApplicationContext());
	}

	private Dialog createEntryWeightingErrorDialog() {
		return new AlertDialog.Builder(getActivity())
			.setTitle(R.string.childDialogs_TypeEntryErrorTitle)
			.setMessage(R.string.childDialogs_TypeEntryErrorMessage)
			.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
			})
			.create();
	}
	
}
