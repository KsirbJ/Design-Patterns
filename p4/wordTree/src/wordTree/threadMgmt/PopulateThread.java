package wordTree.threadMgmt;

import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;
import wordTree.store.Results;
import wordTree.wordNode.Tree;

import java.util.regex.PatternSyntaxException;

public class PopulateThread implements Runnable {
	Tree tree;
	FileProcessor myFile;

	public PopulateThread(FileProcessor file, Results  res, Tree tree_in){
		MyLogger.writeMessage("PopulateThread constructor called.", MyLogger.DebugLevel.CONSTRUCTOR);
		tree = tree_in;
	    myFile = file;
	}

	public void run(){ 
		try {
			MyLogger.writeMessage("PopulateThread run method called.", MyLogger.DebugLevel.RUN);
			String line = myFile.readLine();
			while(line != null){
				if(line.trim().isEmpty()){
					line = myFile.readLine();
					continue;
				}
				String[] words = line.split(" +");

				for (String word : words){
					tree.addWord(word);	
				}
				line = myFile.readLine();
			}
		} catch (PatternSyntaxException | NullPointerException e){
			//no sleep, wait, or join called, so can't catch InterruptedException
			e.printStackTrace();
		} finally {
		}
	}

	@Override
	public String toString(){
		return "";
	}
}
