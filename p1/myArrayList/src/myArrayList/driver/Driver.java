package myArrayList.driver;

/**
 *	This class contains the main method to run MyArrayList.java
 *
 *	@author Jake Brisk
 */
import myArrayList.MyArrayList;
import myArrayList.util.FileProcessor;
import myArrayList.util.Results;
import myArrayList.test.MyArrayListTest;

public class Driver 
{

	public static void main(String[] args) 
	{

		if(args.length < 2 || args.length > 2){
			System.err.println("Program requires 2 arguments");
			System.err.println("You can run: 'ant -buildfile src/build.xml run -Darg0=<input-file> -Darg1=<output-file>'");
			System.err.println("Or run: 'java -jar path/to/myArrayList.jar <input-file> <output-file>'");
			System.exit(1);
		}

		// Create test instance
		MyArrayList arr = new MyArrayList();

		// open input file
		FileProcessor infile = new FileProcessor(args[0], 'r');

		// Parse input file
		Boolean gotLine = true;
		while(gotLine){
			String line = infile.readLine();
			if(line == null){
				break;
			}
			try{
				int i = Integer.parseInt(line);
				arr.insertSorted(i);
			}catch (NumberFormatException e){
				System.err.println("Ignoring invalid value: " + (line.length() > 0 ? line : "empty line"));
			}finally{

			}
			
		}
		infile.closeFiles(); // close input file

		// Create a new Results instance and give it the output file to work with
		Results myResults = new Results(args[1]);

		// Run first required test
		int sum = arr.sum();
		myResults.storeNewResult("The sum of all the values in the array list is: " + sum);

		// Run many additional tests on the ArrayList
		MyArrayListTest alTester = new MyArrayListTest();
		alTester.testMe(arr, myResults);

		// Write test results to file
		myResults.storeToFile();
		myResults.cleanUp();



		
	}
	
}
