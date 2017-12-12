package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject; 

public interface StoreI extends StoreRestoreI {
	void writeObj(SerializableObject aRecord, int authID, String wireFormat);
}