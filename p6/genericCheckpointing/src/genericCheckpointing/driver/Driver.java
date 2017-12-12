
package genericCheckpointing.driver;

import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.RestoreI;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.MyAllTypesFirst;
import java.util.ArrayList;
import java.lang.reflect.InvocationHandler;
import genericCheckpointing.util.FileProcessor; 

public class Driver {
    
    public static void main(String[] args) {
	
		int NUM_OF_OBJECTS = 0;
		String MODE = null;
		String FILE = null;
	    try{
	    	if(args.length != 3)
	    		throw new IllegalArgumentException("Expected 3 arguments. <MODE> <NUM_OF_OBJECTS> <FILE>");
	    	MODE = args[0];
	    	if(!MODE.equals("serdeser") && !MODE.equals("deser"))
	    		throw new IllegalArgumentException("MODE should be 'deser' or 'serdeser'");
	    	NUM_OF_OBJECTS = Integer.parseInt(args[1]);
	    	FILE = args[2];
	    }catch(IllegalArgumentException e){
	    	e.printStackTrace();
	    	System.exit(1);
	    }
    	
		ProxyCreator pc = new ProxyCreator();
		InvocationHandler handler = new StoreRestoreHandler(); 
		// create a proxy
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
									 new Class[] {
									     StoreI.class, RestoreI.class
									 }, 
									 handler
								 ); 
		

		MyAllTypesFirst myFirst;
		MyAllTypesSecond  mySecond;
		ArrayList<SerializableObject> stored_objects = new ArrayList<SerializableObject>(); 

		if(MODE.equals("serdeser")){
			try{
				((StoreRestoreHandler)handler).setWriteFile("output.txt");

				for (int i=0; i<NUM_OF_OBJECTS; i++) { 
				   myFirst = new MyAllTypesFirst(i*2, i*4, i*6, i*8, "this is a string "+i, true);
				   mySecond = new MyAllTypesSecond(i*2.0, i*4.0, i*6, ((short)i), ((short)i), 'a'); 

				   stored_objects.add(myFirst);
				   stored_objects.add(mySecond);

				   ((StoreI) cpointRef).writeObj((SerializableObject)myFirst, 0, "XML");  
				   ((StoreI) cpointRef).writeObj((SerializableObject)mySecond, 0, "XML"); 
				} 
				((StoreRestoreHandler)handler).doneWriting();
			}catch(IllegalArgumentException e){
				e.printStackTrace();
				System.exit(1);
			}
			
			SerializableObject myRecordRet;
			ArrayList<SerializableObject> returned_objects = new ArrayList<SerializableObject>(); 
			((StoreRestoreHandler)handler).setReadFile(FILE); 
			for (int j=0; j<2*NUM_OF_OBJECTS; j++) {

			    myRecordRet = ((RestoreI) cpointRef).readObj("XML");
			    returned_objects.add(myRecordRet); 
			} 
			((StoreRestoreHandler)handler).doneReading();

			int num_mismatch = 0;
			for(int i = 0; i < 2*NUM_OF_OBJECTS; ++i){		
				if(!returned_objects.get(i).equals(stored_objects.get(i))){
					num_mismatch++;
				}
			}
			System.out.println(num_mismatch + " mismatched object(s)");
		}else if(MODE.equals("deser")){

			SerializableObject myRecordRet;
			((StoreRestoreHandler)handler).setReadFile(FILE); 
			for (int j=0; j<NUM_OF_OBJECTS; j++) {

			    myRecordRet = ((RestoreI) cpointRef).readObj("XML");
			    System.out.println(myRecordRet);
			} 
			((StoreRestoreHandler)handler).doneReading();
		}
		
	
    
    }
}
