package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.FileProcessor;
import java.lang.reflect.InvocationTargetException;
import genericCheckpointing.serStrategy.SerStrategyI;
import genericCheckpointing.serStrategy.XMLSerialization;

public class StoreRestoreHandler implements InvocationHandler {
	private FileProcessor read_file = null, write_file = null;
	
	public StoreRestoreHandler(){ }

	/** 
	 *	Set the file from where to read the objects
	 *	@param filename - The file name
	 */
	public void setReadFile(String filename){
		read_file = new FileProcessor(filename, 'r');
	}

	/**
	 *	Set the file where to write the objects
	 *	@param filename - The file name
	 */
	public void setWriteFile(String filename){
		write_file = new FileProcessor(filename, 'w');
	}

	/**
	 *	Invoke a handler method on an object
	 *	@param proxy - The proxy
	 *	@param m - The method to call
	 *	@param args - The arguments to the method
	 *	@return - Whatever m returns
	 */
	public Object invoke(Object proxy, Method m, Object[] args) 
			throws IllegalAccessException, 
				IllegalArgumentException,
                InvocationTargetException { 

        String mname = m.getName();
        String format = (String)args[args.length-1];
        // read or write object
        if(mname.equals("readObj")){
        	if(read_file == null){
				System.err.println("Read file hasn't been initialized. Cannot serialize object");
				return null;
			}
        	switch(format.toLowerCase()){
				case "xml":
					SerStrategyI serializer = new XMLSerialization(); 
					return serializer.deserializeObject(read_file);
				default:
					return null;
			}
        }else if(mname.equals("writeObj")){
        	if(write_file == null){
				System.err.println("Write file hasn't been initialized. Cannot serialize object");
				return null;
			}
			switch(format.toLowerCase()){
				case "xml":
					SerStrategyI serializer = new XMLSerialization();  
					SerializableObject o = (SerializableObject)args[0];
					serializer.serializeObject(o, write_file);
					break;
				default:
					break;
			}
        }else{
        	System.err.println("storeRestoreHandler called with wrong args. Ignoring");
        	return null;
        }
        return null;
	}

	public void doneWriting(){
		if(write_file != null)
			write_file.closeFiles();
	}

	public void doneReading(){
		if(read_file != null)
			read_file.closeFiles();
	}
}