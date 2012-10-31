package edu.rosehulman.classtracker.fragments;

import edu.rosehulman.classtracker.R;
import edu.rosehulman.classtracker.activites.ClassEntry;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ClassEntryDialogs extends DialogFragment {
	
	public static final String KEY_DIALOG_TYPE = "DIALOG TYPE KEY";
	public static final String KEY_TYPES_ADD_UP_TO = "TYPES ADD UP TO KEY";
	public static final String KEY_TYPE_NAME = "TYPE NAME KEY";
	public static final String KEY_TYPE_WEIGHTING = "TYPE WEIGHTING KEY";
	public static final String KEY_TYPE_INDEX = "TYPE INDEX KEY";
	
	public static final int DIALOG_ID_DOES_NOT_ADD_UP = 0;
	public static final int DIALOG_ID_ENTER_NEW_TYPE = 1;
	public static final int DIALOG_ID_EDIT_NEW_TYPE = 2;
	public static final int DIALOG_ID_NO_TYPES = 3;
	public static final int DIALOG_ID_NO_NAME = 4;
	
	public static ClassEntryDialogs getFragment(Bundle arguments)
	{
		ClassEntryDialogs frag = new ClassEntryDialogs();
		frag.setArguments(arguments);
		return frag;
	}
	
	public ClassEntryDialogs()
	{
		
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		Bundle args = this.getArguments();
		int type = args.getInt(KEY_DIALOG_TYPE);
		switch(type)
		{
		case DIALOG_ID_DOES_NOT_ADD_UP:
			return this.createDoesNotAddUpDialog();
		case DIALOG_ID_ENTER_NEW_TYPE:
			return this.createEnterNewTypeDialog();
		case DIALOG_ID_EDIT_NEW_TYPE:
			return this.createEditNewTypeDialog();
		case DIALOG_ID_NO_NAME:
			return this.createNewNoNameDialog();
		case DIALOG_ID_NO_TYPES:
			return this.createNewNoTypesDialog();
		}
		return new Dialog(getActivity().getApplicationContext());
	}

	private Dialog createNewNoTypesDialog() {
		return new AlertDialog.Builder(getActivity())
			.setMessage(R.string.classEntry_dialogs_assignmentTypeEntry_noTypesMessage)
			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.create();
	}

	private Dialog createNewNoNameDialog() {
		return new AlertDialog.Builder(getActivity())
		.setMessage(R.string.classEntry_dialogs_assignmentTypeEntry_noNameMessage)
		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		})
		.create();
	}

	private Dialog createEditNewTypeDialog() {
		Bundle args = this.getArguments();
		final int typeIndex = args.getInt(KEY_TYPE_INDEX);
		
		AlertDialog.Builder build = createBaseTypeDialog();
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_assignment_type_entry, null);
		build.setView(dialogView);
		
		final EditText nameEntry = (EditText)dialogView.findViewById(
				R.id.classEntry_dialogs_assignmentTypeEntry_nameEditText);
		final EditText weightingEntry = (EditText)dialogView.findViewById(
				R.id.classEntry_dialogs_assignmentTypeEntry_weightingEditText);
		
		nameEntry.setText(args.getString(ClassEntryDialogs.KEY_TYPE_NAME));
		weightingEntry.setText(Integer.toString(args.getInt(ClassEntryDialogs.KEY_TYPE_WEIGHTING)));
		
		/* Set the dialog arguments, finally */
		build.setTitle(args.getString(KEY_TYPE_NAME))
			.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					/* Handle response differently if the weighting does not parse
					 * out to an acceptable int (0-100, actually an int)
					 */
					EditText weightingEntry = (EditText) ((AlertDialog) dialog).findViewById(
							R.id.classEntry_dialogs_assignmentTypeEntry_weightingEditText);
					EditText nameEntry = (EditText) ((AlertDialog) dialog).findViewById(
							R.id.classEntry_dialogs_assignmentTypeEntry_nameEditText);
					if(checkString(weightingEntry.getText().toString()))	
						((ClassEntry)getActivity()).editType(typeIndex, 
							nameEntry.getText().toString(),
							Integer.parseInt(weightingEntry.getText().toString()));
					else
					{
						Bundle childDialogArguments = new Bundle();
						childDialogArguments.putInt(ChildDialogs.KEY_DIALOG_ID, 
								ChildDialogs.DIALOG_ID_CLASS_ENTRY_WEIGHTING_ERROR);
						ChildDialogs frag = ChildDialogs.getFragment(childDialogArguments);
						frag.show(getFragmentManager(), "child");
					}
				}
			})
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.setNeutralButton(R.string.delete, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					((ClassEntry)getActivity()).deleteType(typeIndex);
					dialog.dismiss();
				}
			});
		
		return build.create();
	}

	private Dialog createEnterNewTypeDialog() {
		AlertDialog.Builder build = createBaseTypeDialog();
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_assignment_type_entry, null);
		build.setView(dialogView);
		Bundle args = getArguments();
		
		return build.setTitle(args.getString(KEY_TYPE_NAME))
			.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					EditText weightingEntry = (EditText) ((AlertDialog) dialog).findViewById(
							R.id.classEntry_dialogs_assignmentTypeEntry_weightingEditText);
					EditText nameEntry = (EditText) ((AlertDialog) dialog).findViewById(
							R.id.classEntry_dialogs_assignmentTypeEntry_nameEditText);
							
					if(checkString(weightingEntry.getText().toString()))
						((ClassEntry)getActivity()).addType(nameEntry.getText().toString(),
								Integer.parseInt(weightingEntry.getText().toString()));
					else
					{
						Bundle childDialogArguments = new Bundle();
						childDialogArguments.putInt(ChildDialogs.KEY_DIALOG_ID, 
								ChildDialogs.DIALOG_ID_CLASS_ENTRY_WEIGHTING_ERROR);
						ChildDialogs frag = ChildDialogs.getFragment(childDialogArguments);
						frag.show(getFragmentManager(), "child");	
					}
				}
			})
			
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.create();
	}
	
	private AlertDialog.Builder createBaseTypeDialog()
	{

		return new AlertDialog.Builder(getActivity());
	}
	
	private Dialog createDoesNotAddUpDialog() {
		Bundle args = this.getArguments();
		int addsUpTo = args.getInt(KEY_TYPES_ADD_UP_TO); 
		return new AlertDialog.Builder(getActivity())
			.setTitle(R.string.classEntry_dialogs_doesNotAddUp_title)
			.setMessage(String.format(getString(R.string.classEntry_dialogs_doesNotAddUp_message), addsUpTo))
			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
					arg0.dismiss();
				}
			})
			.create();
	}
	
	private boolean checkString(String check)
	{
		try
		{
			int sizeCheck = Integer.parseInt(check);
			if(sizeCheck > 100 || sizeCheck < 0)
				return false;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		return true;
	}
}
