/**
 *	This class is used to test MyArrayList.java
 */

package myArrayList.test;
import myArrayList.util.Results;
import myArrayList.MyArrayList;

public class MyArrayListTest {

	public MyArrayListTest(){

	}

	public void testMe(MyArrayList myArrayList, Results results){
		myArrayList.clear();
		results.storeNewResult(testSmallInsert(myArrayList));
		results.storeNewResult(testBigInsert(myArrayList));
		results.storeNewResult(testPrint(myArrayList));
		results.storeNewResult(testSmallRemove(myArrayList));
		results.storeNewResult(testMultipleRemove(myArrayList));
		results.storeNewResult(testSum(myArrayList));
		results.storeNewResult(testIndexOf(myArrayList));
		results.storeNewResult(testSize(myArrayList));
		results.storeNewResult(testResize(myArrayList));
		results.storeNewResult(testWrongArg(myArrayList));
	}

	/**
	 *	Test a small insert operation on the arraylist
	 *
	 *	@param al - The arraylist to test
	 *	@return Whether the test passed or failed
	 */
	public String testSmallInsert(MyArrayList al){
		al.insertSorted(2);
		al.insertSorted(4);
		al.insertSorted(5);
		if(al.indexOf(2) != -1 && al.indexOf(4) != -1 && al.indexOf(5) != -1){
			return "Test testSmallInsert passed.";
		}
		return "Test smallInsertTest failed. Couldn't find inserted values.";
	}

	/**
	 *	Test a small print operation on the arraylist
	 *
	 *	@param al - The arraylist to test
	 *	@return Whether the test passed or failed
	 */
	public String testPrint(MyArrayList al){
		al.clear();
		al.insertSorted(5);
		al.insertSorted(2);
		al.insertSorted(1);
		al.insertSorted(-10);	// Try invalid value
		String res = al.toString();
		if(res.equals("[1, 2, 5, ]"))
			return "Test testPrint passed.";
		return "Test testPrint failed. Output does not match expected format.";
	}

	/**
	 *	Test a large insert operation on the arraylist
	 *
	 *	@param al - The arraylist to test
	 *	@return Whether the test passed or failed
	 */
	public String testBigInsert(MyArrayList al){
		al.clear();
		for(int i = 60; i > 0; --i){
			al.insertSorted(i);
		}
		if(al.size() != 60)
			return "Test testBigInsert failed. ArrayList was not resized correctly.";

		for(int i = 1; i < 61; ++i){
			if(al.indexOf(i) != i-1)
				return "Test testBigInsert failed. The valued were not in the correct order.";
		}
		return "Test testBigInsert passed.";
	}

	/**
	 *	Test a small remove operation on the arraylist
	 *
	 *	@param al - The arraylist to test
	 *	@return Whether the test passed or failed
	 */
	public String testSmallRemove(MyArrayList al){
		al.clear();
		al.insertSorted(1);
		al.insertSorted(2);
		al.insertSorted(3);
		al.removeValue(2);
		if(al.toString().equals("[1, 3, ]"))
			return "Test testSmallRemove passed.";
		return "Test testSmallRemove failed. ArrayList values do not match expected values.";
	}

	/**
	 *	Test a multiple remove operation on the arraylist
	 *
	 *	@param al - The arraylist to test
	 *	@return Whether the test passed or failed
	 */
	public String testMultipleRemove(MyArrayList al){
		al.clear();
		al.insertSorted(1);
		al.insertSorted(20);
		al.insertSorted(5);
		int i = 0;
		while(i < 8){
			al.insertSorted(8);
			i++;
		}
		al.insertSorted(4);
		al.insertSorted(10);
		al.insertSorted(12);
		al.removeValue(8);
		if(al.toString().equals("[1, 4, 5, 10, 12, 20, ]"))
			return "Test testMultipleRemove passed.";
		return "Test testMultipleRemove failed. ArrayList values are not as expected.";
	}


	/**
	 *	Test a small sum operation on the arraylist
	 *
	 *	@param al - The arraylist to test
	 *	@return Whether the test passed or failed
	 */
	public String testSum(MyArrayList al){
		al.clear();
		al.insertSorted(1);
		al.insertSorted(20);
		al.insertSorted(5);
		if(al.sum() == 26)
			return "Test testSum passed.";
		return "Test testSume failed. Value not as expected.";
	}


	/**
	 *	Test the indexOf method of the arraylist
	 *
	 *	@param al - The arraylist to test
	 *	@return Whether the test passed or failed
	 */
	public String testIndexOf(MyArrayList al){
		al.clear();
		al.insertSorted(1);
		al.insertSorted(35);
		al.insertSorted(3);
		if(al.indexOf(3) == 1){
			return "Test testIndexOf passed.";
		}
		return "Test testIndexOf failed. Index is not as expected.";
	}

	/**
	 *	Test the size method of the arraylist
	 *
	 *	@param al - The arraylist to test
	 *	@return Whether the test passed or failed
	 */
	public String testSize(MyArrayList al){
		al.clear();
		if(al.size() == 0){
			al.insertSorted(1);
			al.insertSorted(2);
			if(al.size() == 2){
				al.removeValue(1);
				if(al.size() == 1)
					return "Test testSize passed.";
			}
		}
		return "Test testSize failed. Size is not as expected.";
	}

	/**
	 *	Test whether the arraylist resizes correctly
	 *
	 *	@param al - The arraylist to test
	 *	@return Whether the test passed or failed
	 */
	public String testResize(MyArrayList al){
		al.clear();
		if(al.getMaxSize() == 50){
			for(int i = 0; i < 53; ++i){
				al.insertSorted(i);
			}
			if(al.getMaxSize() == 75)
				return "Test testResize passed.";
		}
		return "Test testResize failed. Size not as expected.";
	}


	/**
	 *	Test whether the arraylist throws exceptions properly
	 *
	 *	@param al - The arraylist to test
	 *	@return Whether the test passed or failed
	 */
	public String testWrongArg(MyArrayList al){
		try {
			MyArrayList test = new MyArrayList(-30);
		}catch(IllegalArgumentException e){
			return "Test testWrongArg passed.";
		}
		return "Test testWrongArg failed. MyArrayList did not throw exception.";
	}






}