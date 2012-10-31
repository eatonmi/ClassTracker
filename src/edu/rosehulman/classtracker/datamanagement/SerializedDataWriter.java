package edu.rosehulman.classtracker.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.Log;
import edu.rosehulman.classtracker.classview.DataSet;

public class SerializedDataWriter implements IDataWriter {

	private String filename = "ClassTrackerSaveFile";
	private Context context;
	
	private final static String LOG_ID = "DM_SDW";
	
	public void writeData(DataSet data) {
		try {
			FileOutputStream output = context.openFileOutput(filename, Context.MODE_PRIVATE);
			ObjectOutputStream objOutput = new ObjectOutputStream(output);
			objOutput.writeObject(data);
		} catch (FileNotFoundException e) {
			Log.d(LOG_ID, "File not found.  " + e.getMessage());
		} catch (IOException e) {
			Log.d(LOG_ID, "IOException.  " + e.getMessage());
		} catch (Exception e)
		{
			Log.d(LOG_ID, "Generic Exception: " + e.getMessage());
		}
		
	}

	public void setDetails(String details) {
		if(details != "")
			this.filename = details;
	}

	public void setContext(Context context) {
		this.context = context;
	}

}
