package edu.rosehulman.classtracker.datamanagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.util.Log;
import edu.rosehulman.classtracker.classview.DataSet;

public class SerializedDataReader implements IDataReader {
	
	private String filename = "ClassTrackerSaveFile";
	private Context context;
	
	private static final String LOG_ID = "CT_SDR";
	
	public SerializedDataReader()
	{
	}
	
	public DataSet readData() {
		try
		{
			InputStream input = this.context.openFileInput(filename);
			ObjectInputStream objReader = new ObjectInputStream(input);
			DataSet set = (DataSet) objReader.readObject();
			return set;
		}
		catch (FileNotFoundException e)
		{
			Log.d(LOG_ID, "Could not find file " + filename + ".  Read aborted.");
		} catch (StreamCorruptedException e) {
			Log.d(LOG_ID, "StreamCorrupted.  " + e.getMessage());
		} catch (IOException e) {
			Log.d(LOG_ID, "IOException.  " + e.getMessage());
		} catch (ClassNotFoundException e) {
			Log.d(LOG_ID, "Class not found in file.  " + e.getMessage());
		} catch (Exception e)
		{
			Log.d(LOG_ID, "Generic Exception: " + e.getMessage());
		}
		Log.d(LOG_ID, "Exception caught.  Returning blank DataSet.");
		return new DataSet();
	}

	public void setDetails(String details) {
		if(details != "")
			this.filename = details;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
