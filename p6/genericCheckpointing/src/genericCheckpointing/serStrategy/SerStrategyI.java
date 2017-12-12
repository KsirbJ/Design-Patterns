package genericCheckpointing.serStrategy;

import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.FileProcessor;

public interface SerStrategyI {
	public void serializeObject(SerializableObject o, FileProcessor fp); 
	public SerializableObject deserializeObject(FileProcessor fp);
}