package wordTree.threadMgmt;

import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;
import wordTree.store.Results;
import wordTree.wordNode.Tree;

public class DeleteThread implements Runnable {

	private Tree tree;
	private String target;

	public DeleteThread(FileProcessor newFile, Results  res, Tree newTree, String newTarget){
		MyLogger.writeMessage("DeleteThread constructor called.", MyLogger.DebugLevel.CONSTRUCTOR);

		tree = newTree;
		target = newTarget;
	}

	public void run(){
		try {	
			MyLogger.writeMessage("DeleteThread run method called.", MyLogger.DebugLevel.RUN);
			tree.deleteWord(target);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
		}
	}

	@Override
	public String toString(){
		return target;
	}
}
