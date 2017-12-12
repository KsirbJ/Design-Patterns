package myArrayList;
/**
*	This program implements a simple ArrayList.
*	It can store integers between 0 and 10000, and always keeps the list sorted
*	It supports insert (sorted), remove, indexOf, size, and sum.
*	
*	@author Jake Brisk
*	@version 1.0.0
*/
public class MyArrayList {
	
	private int[] arrayList;	// The array
	private int size;			// The current size of the array (num of elmts in it)
	private int maxSize;		// The max size the array supports now

	/**
	*	Default constructor. Calls the value constructor with 50 as the initial size
	*/
	public MyArrayList(){
		this(50);
	}

	/**
	*	Value constructor 
	*
	*	@param size - The initial size of the arraylist
	*/
	public MyArrayList(int inSize){
		if(inSize < 1){
			throw new IllegalArgumentException("Invalid list size specified. Size must be positive");
			//System.err.println("Invalid list size specified");
			//System.exit(1);
		}
		inSize = inSize > 50 ? inSize : 50; // Min size is 50
		arrayList = new int[inSize];
		maxSize = inSize;
		size = 0;
	}	


	/**
	*	Insert a number into the arraylist while keeping it sorted
	*
	*	@param newValue - The value to insert
	*/
	public void insertSorted(int newValue){
		if(newValue < 0 || newValue > 10000){
			System.err.println("Values must be between 0 and 10000");
			return;
		}
		int index = findPosition(newValue);	// Find where to insert the new value

		// If the index is out of bounds resize the arraylist
		if(index >= maxSize || size >= maxSize)
			arrayList = resizeAndCopy();
		
		try{
			// Move everything over 
			for(int i = size; i > index; --i){
				arrayList[i] = arrayList[i-1];
			}

			// insert and update size
			arrayList[index] = newValue;
			size++;
		}catch(IndexOutOfBoundsException e){
			System.err.println("Failed to insert value");
			e.printStackTrace();
		}finally{

		}		

	}

	/**
	*	Remove all instances of a value from the arraylist
	*	This method will also resize it. Method does nothing if the value isn't found
	*
	*	@param value - The value to remove
	*/
	public void removeValue(int value){
		if(value < 0)
			return;
		int steps = 0; // Increments for the number of values we remove
		for(int i = 0; i < size; ++i){
			arrayList[i-steps] = arrayList[i];
			if(arrayList[i] == value)
				steps++;
		}
		size -= steps;
	}

	/**
	*	Get the first index of a value in the arraylist
	*
	*	@param value - The value to find
	*	@return - The value, or -1 if it's not found
	*/
	public int indexOf(int value){
		if(value < 0)
			return -1;
		for(int i = 0; i < size; ++i){
			if(arrayList[i] == value)
				return i;
		}
		return -1;
	}

	/**
	*	Get the current size of the arraylist
	*
	*	@return The size of the arraylist
	*/
	public int size(){
		return size;
	}

	/**
	*	Get the max size of the arraylist
	*
	*	@return The max size of the arraylist
	*/
	public int getMaxSize(){
		return maxSize;
	}

	/**
	*	Get the sum of all the values in the arraylist
	*
	*	@return The sum of the values in the arraylist
	*/
	public int sum(){
		int sum = 0;
		for(int i : arrayList)
			sum += i;
		return sum;
	}

	/**
	*	Get the formatted values of the arraylist
	*
	*	@return Formatted values in the arraylist
	*/
	@Override
	public String toString(){
		String ret = "[";
		int i = 0;
		while(i < size){
			ret = ret + Integer.toString(arrayList[i]) + ", " ;
			i++;
		}
		ret += "]";
		return ret;
	}

	/**
	 *	Clear the arrayList. Also resets size to 50
	 */
	public void clear(){
		arrayList = new int[50];
		size = 0;
		maxSize = 50;
	}


	/******--------------  Helper methods -----------------*********/

	/**
	*	Resize the array. Creates a new array that's 1.5x the size
	*	and copies the elements from the arrayList over
	*
	*	@return The resized array
	*/
	private int[] resizeAndCopy(){
		int[] arr = new int[(int)(size*1.5)];
		for(int i = 0; i < size; ++i){
			arr[i] = arrayList[i];
		}
		maxSize = (int)(size*1.5);
		return arr;
	}

	/**
	*	Find the position where an element belongs in the arraylist
	*
	*	@param value - The value to look for
	*	@return The position where the element belongs
	*/
	private int findPosition(int value){
		int i = 0;
		while(i < size && arrayList[i] < value ){
			i++;
		}
		return i;
	}

}