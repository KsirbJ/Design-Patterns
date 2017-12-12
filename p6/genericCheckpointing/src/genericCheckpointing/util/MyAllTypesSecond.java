package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject {
	private double myDoubleT;
	private double myOtherDoubleT;
	private float myFloatT;
	private short myShortT;
	private short myOtherShortT;
	private char myCharT;

	public MyAllTypesSecond(){

	}
	
	public MyAllTypesSecond(double my_double, double my_other_double, float my_float, 
		short my_short, short my_other_short, char my_char){
		myDoubleT = my_double;
		myOtherDoubleT = my_other_double;
		myFloatT = my_float;
		myShortT = my_short;
		myOtherShortT = my_other_short;
		myCharT = my_char;
	}

	public void setMyDoubleT(double val){
		myDoubleT = val;
	}

	public void setMyOtherDoubleT(double val){
		myOtherDoubleT = val;
	}

	public void setMyFloatT(float val){
		myFloatT = val;
	}

	public void setMyShortT(short val){
		myShortT = val;
	}

	public void setMyOtherShortT(short val){
		myOtherShortT = val;
	}

	public void setMyCharT(char val){
		myCharT = val;
	}

	public double getMyDoubleT(){
		return myDoubleT;
	}

	public double getMyOtherDoubleT(){
		return myOtherDoubleT;
	}

	public float getMyFloatT(){
		return myFloatT;
	}

	public short getMyShortT(){
		return myShortT;
	}

	public short getMyOtherShortT(){
		return myOtherShortT;
	}

	public char getMyCharT(){
		return myCharT;
	}

	@Override
	public String toString(){
		return "-----MyAllTypesSecond-----"
			+ "\n\tmyDoubleT: " + myDoubleT
			+ "\n\tmyOtherDoubleT: " + myOtherDoubleT
			+ "\n\tmyFloatT: " + myFloatT
			+ "\n\tmyShortT :" + myShortT
			+ "\n\tmyOtherShortT: " + myOtherShortT
			+ "\n\tmyCharT: " + myCharT
			+ "\n--------------------------";
	}

	@Override
	public int hashCode(){
		return Double.hashCode(myDoubleT) + Double.hashCode(myOtherDoubleT)
				 + Float.hashCode(myFloatT) + myShortT + myOtherShortT 
				 + Character.hashCode(myCharT);
	}

	@Override 
	public boolean equals(Object second){
		if(second instanceof MyAllTypesSecond){
			if(second == this)
				return true;

			MyAllTypesSecond o = ((MyAllTypesSecond)second);
			if(myDoubleT == o.getMyDoubleT()
				&& myOtherDoubleT == o.getMyOtherDoubleT()
				&& myFloatT== o.getMyFloatT()
				&& myShortT == o.getMyShortT()
				&& myOtherShortT == o.getMyOtherShortT()
				&& myCharT == o.getMyCharT()){
				return true;
			}
		}
		
		return false;
	}

}