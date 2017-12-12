package airportSecurityState.util;
/**
 *	This program helps with file processing. It can be used in read or write mode.
 *	In read mode you can get the file contents line by line, in write mode you can write
 *	to the file a line at a time.
 *
 *	@author Jake Brisk
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class FileProcessor  {

	private BufferedReader reader;
	private BufferedWriter writer;
	private String file;
	private char mode;

	/**
	 *	Default constructor. Sets everything to null
	 */
	FileProcessor(){
		MyLogger.writeMessage("FileProcessor default constructor called", 
			MyLogger.DebugLevel.CONSTRUCTOR);

		reader = null;
		file = null;
		writer = null;
	}

	/**
	*	Val constructor 
	*	
	*	@param file - A file to work with
	*	@param inMode - Read 'r' or write 'w'
	*/
	public FileProcessor(String inFile, char inMode) {
		MyLogger.writeMessage("FileProcessor constructor called", 
			MyLogger.DebugLevel.CONSTRUCTOR);
		if(inMode == 'r'){
			mode = 'r';
		}else{
			mode = 'w';
		}

		file = inFile;
		try{
			// Open files 

			if(mode == 'r'){
				reader = new BufferedReader(new FileReader(file));
				writer = null;
			}else{
				File wFile = new File(file);
				if(!wFile.exists()){
					wFile.createNewFile();
				}

				writer = new BufferedWriter(new FileWriter(wFile));
				reader = null;
			}
			
		}catch(IOException e){
			System.err.println("Couldn't open file. program quitting");
			e.printStackTrace();
			System.exit(1);
		}finally{

		}
	}


	/**
	*	Write a line to a file (only if used val constructor)
	*
	*	@param line - The line to write
	*/
	public void writeLine(String line) {
		if(writer == null){
			System.err.println("No files open to write");
			return;
		}
		try{
			writer.write(line);
			writer.newLine();
		}catch(IOException e){
			System.err.println("Couldn't write to file, please try again");
			e.printStackTrace();
		}finally {

		}
	}

	/**
	*	Read a line from the current file (only used with val constructor)
	*
	*	@return The line it read
	*/
	public String readLine(){
		if(reader == null){
			System.err.println("No files open to read");
			return null;
		}
		try{
			String line = reader.readLine();
			return line;
		}catch(IOException e){
			System.err.println("Couldn't read from file, please try again");
			e.printStackTrace();
		}finally{

		}
		return null;
	}

	/**
	*	Open a file to read 
	*	
	*	@param filename - The file to read
	*/
	public void openReadFile(String filename){
		file = filename;
		try{
			reader = new BufferedReader(new FileReader(file));
		}catch(IOException e){
			System.err.println("Couldn't open file. Please try again");
			reader = null;
		}finally{

		}
		
	}

	/**
	*	Open a file to write
	*	
	*	@param filename - The file to write to
	*/
	public void openWriteFile(String filename){
		file = filename;
		try{
			File wFile = new File(file);
			if(!wFile.exists()){
				wFile.createNewFile();
			}

			writer = new BufferedWriter(new FileWriter(wFile));
		}catch(IOException e){
			System.err.println("Couldn't open file. Please try again");
			writer = null;
		}finally{

		}
	}

	/**
	 *	Close the open files from the reader and writer
	 */
	public void closeFiles() {
		try{
			if(writer != null)
				writer.close();
			if(reader != null)
				reader.close();
		}catch(IOException e){
			System.err.println("Couldn't close file, please try again");
			e.printStackTrace();
		}
	}
	
}