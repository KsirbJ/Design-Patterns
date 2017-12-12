package myArrayList.util;

import java.util.ArrayList;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	private ArrayList<String> results; // Using ArrayList 'cuz dynamic
	private FileProcessor fileIO;	// Used to write to file

	public Results(){
		results = new ArrayList<String>();
		fileIO = null;
	}

	/**
	 *	Value constructor
	 *	
	 *	@param outFile - A file to write to
	 */
	public Results(String outFile){
		fileIO = new FileProcessor(outFile, 'w');
		results = new ArrayList<String>();
	}

	/** 
	 *	Store a new string in the arraylist
	 *
	 *	@param result - The string to store 
	 */
	public void storeNewResult(String result){
		results.add(result);
	}

	/**
	 *	Print all the values in the results to stdout
	 */
	public void print(){
		System.out.println("\n\nResults:");
		System.out.println("----------------------------------------");
		for(int i = 0; i < results.size(); ++i){
			System.out.println(results.get(i));
		}
		System.out.println("----------------------------------------");
	}

	/**
	*	Method to print string to stdout
	*	
	*	@param s - The string to print
	*/
	public void writeToStdout(String s){
		System.out.println(s);
	}


	/**
	*	Method to print a string to a file
	*
	*	@param s - The string to print
	*/
	public void writeToFile(String s){
		if(fileIO != null){
			fileIO.writeLine(s);
		}else{
			System.err.println("Cannot store results file. No file open");
		}
	}

	/**
	*	Store the results arraylist to file 
	*/
	public void storeToFile(){
		for(String line : results){
			writeToFile(line);
		}
	}

	/**
	 *	Get the results arraylist
	 *
	 *	@return - The results ArrayList
	 */
	public ArrayList<String> getResults(){
		return results;
	}

	/**
	 * Close the files before exiting
	 */
	public void cleanUp(){
		fileIO.closeFiles();
	}

	/**
	 * 	overrides toString() 
	 *
	 *	@return All the values in the arraylist
	 */
	@Override
	public String toString(){
		String content = "[";
		for(String result : results){
			content = content + ", " + result;
		}
		content += "]";
		return content;
	}


}