package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject {
	private int myInt;
	private int myOtherInt;
	private long myLong;
	private long myOtherLong;
	private String myString;
	private boolean myBool;

	public MyAllTypesFirst(){

	}

	public MyAllTypesFirst(int my_int, int my_other_int, long my_long, 
		long my_other_long, String my_string, boolean my_bool){
		myInt = my_int;
		myOtherInt = my_other_int;
		myLong = my_long;
		myOtherLong = my_other_long;
		myString = my_string;
		myBool = my_bool;
	}

	public void setMyInt(int val){
		myInt = val;
	}

	public void setMyOtherInt(int val){
		myOtherInt = val;
	}

	public void setMyLong(long val){
		myLong = val;
	}

	public void setMyOtherLong(long val){
		myOtherLong = val;
	}

	public void setMyString(String val){
		myString = val;
	}

	public void setMyBool(boolean val){
		myBool = val;
	}

	public int getMyInt(){
		return myInt;
	}

	public int getMyOtherInt(){
		return myOtherInt;
	}

	public long getMyLong(){
		return myLong;
	}

	public long getMyOtherLong(){
		return myOtherLong;
	}

	public String getMyString(){
		return myString;
	}

	public boolean getMyBool(){
		return myBool;
	}

	@Override
	public String toString(){
		return "-----MyAllTypesFirst-----"
			+ "\n\tmyInt: " + myInt
			+ "\n\tmyOtherInt: " + myOtherInt
			+ "\n\tmyLong: " + myLong
			+ "\n\tmyOtherLong: " + myOtherLong
			+ "\n\tmyString: " + myString
			+ "\n\tmyBool: " + myBool
			+ "\n-------------------------";
	}

	@Override
	public int hashCode(){
		return myInt + myOtherInt + Long.hashCode(myLong) 
				+ Long.hashCode(myOtherLong) + myString.hashCode()
				+ Boolean.hashCode(myBool);
	}

	@Override 
	public boolean equals(Object second){
		if(second instanceof MyAllTypesFirst){
			if(second == this)
				return true;

			MyAllTypesFirst o = ((MyAllTypesFirst)second);
			if(myString.equals(o.getMyString()) 
				&& myInt == o.getMyInt()
				&& myOtherInt == o.getMyOtherInt()
				&& myLong == o.getMyLong()
				&& myOtherLong == o.getMyOtherLong()
				&& myBool == o.getMyBool()){
				return true;
			}
		}
		
		return false;
	}

}