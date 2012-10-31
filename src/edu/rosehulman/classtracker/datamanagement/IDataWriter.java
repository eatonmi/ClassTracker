package edu.rosehulman.classtracker.datamanagement;
import android.content.Context;
import edu.rosehulman.classtracker.classview.DataSet;

public interface IDataWriter {
	public void writeData(DataSet data);
	public void setDetails(String details) throws Exception;
	public void setContext(Context context);
}
