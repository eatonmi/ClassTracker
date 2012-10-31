package edu.rosehulman.classtracker.datamanagement;

import android.content.Context;
import android.util.Log;
import edu.rosehulman.classtracker.classview.DataSet;

public class DataManager {
	//public final static int READ_FROM_SQLITE = 0;
	//public final static int READ_FROM_XML = 1;
	public final static int READ_FROM_SERIALIZED = 2;
	
	//public final static int WRITE_TO_SQLITE = 0;
	//public final static int WRITE_TO_XML = 1;
	public final static int WRITE_TO_SERIALIZED = 2;
	
	public final static String LOG_KEY = "CT_DM";
	
	private IDataReader reader;
	private IDataWriter writer;
	private int readMode;
	private int writeMode;
	private Context context;
	
	public DataManager(Context context)
	{
		this.readMode = READ_FROM_SERIALIZED;
		this.writeMode = WRITE_TO_SERIALIZED;
		this.context = context;
		try
		{
			this.createReader("", context);
			this.createWriter("", context);
		}
		catch(Exception e)
		{
			Log.d(LOG_KEY, "read/write create exception: " + e.getMessage());
		}
	}
	
	public DataManager(Context context, int readMode, int writeMode)
	{
		this.readMode = readMode;
		this.writeMode = writeMode;
		try
		{
			this.createReader("", context);
			this.createWriter("", context);
		} 
		catch (Exception e)
		{
			Log.d(LOG_KEY, "Read/Write create exception: " + e.getMessage());
		}
		this.context = context;
	}
	
	public DataSet retreiveData()
	{
		return reader.readData();
	}
	
	public void writeData(DataSet data)
	{
		this.writer.writeData(data);
	}
	
	public void setReadMode(int mode, String details) throws Exception
	{
		this.readMode = mode;
		this.createReader(details, this.context);
	}
	
	public void setWriteMode(int mode, String details) throws Exception
	{
		this.writeMode = mode;
		this.createWriter(details, this.context);
	}
	
	private void createWriter(String details, Context context) throws Exception {
		switch(this.writeMode)
		{
		case WRITE_TO_SERIALIZED:
			this.writer = new SerializedDataWriter();
			break;
		default:
			throw new Exception("Write mode not recognized.  Use statically defined value");
		}
		this.writer.setDetails(details);
		this.writer.setContext(context);
	}

	private void createReader(String details, Context context) throws Exception {
		switch(this.readMode)
		{
		case READ_FROM_SERIALIZED:
			this.reader = new SerializedDataReader();
			break;
		default:
			throw new Exception("Read mode not recognized.  Use statically defined value");
		}
		
		this.reader.setDetails(details);
		this.reader.setContext(context);
	}
}
