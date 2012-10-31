package edu.rosehulman.classtracker.fragments;

import edu.rosehulman.classtracker.R;
import edu.rosehulman.classtracker.activites.MainMenu;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class MainMenuDialogs extends DialogFragment {
	
	public static final String DIALOG_TYPE_KEY = "DIALOG TYPE KEY";
	
	public static final int DIALOG_ID_RESET_WARNING = 0;
	public static final int DIALOG_ID_EDIT_ENTER_ASSIGNMENT_CHOOSE = 1;
	
	public static MainMenuDialogs getFragment(Bundle arguments)
	{
		MainMenuDialogs retVal = new MainMenuDialogs();
		retVal.setArguments(arguments);
		return retVal;
	}
	
	public MainMenuDialogs()
	{
		
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		int type = this.getArguments().getInt(DIALOG_TYPE_KEY);
		switch(type)
		{
		case DIALOG_ID_RESET_WARNING:
			return this.createResetWarning();
		case DIALOG_ID_EDIT_ENTER_ASSIGNMENT_CHOOSE:
			return this.createEditEnterAssignmentDialog();
		}
		return new Dialog(getActivity().getApplicationContext());
	}

	private Dialog createEditEnterAssignmentDialog() {
		return new AlertDialog.Builder(getActivity())
			.setPositiveButton(R.string.mainMenu_dialogs_enterEdit_newButtonText,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							((MainMenu)getActivity()).editAssignmentClicked();
						}
					})
			.setNegativeButton(R.string.mainMenu_dialogs_enterEdit_editButtonText,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							((MainMenu)getActivity()).enterAssignmentClicked();
						}
					})
			.create();
	}

	private Dialog createResetWarning() {
		return new AlertDialog.Builder(getActivity())
			.setPositiveButton(R.string.yes,
					new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							((MainMenu)getActivity()).resetDataPositive();
						}
					})
			.setNegativeButton(R.string.no,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				})
			.setMessage(R.string.mainMenu_dialogs_resetWarning_message)
			.create();
	}
}
