package wordTree.threadMgmt;

import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;
import wordTree.store.Results;
import wordTree.wordNode.Tree;
import wordTree.wordNode.Node;
 
public class CreateWorkers {
	private Tree wordTree;
	private Thread[] populate_threads;
	private Thread[] delete_threads;
	private FileProcessor file;
	private Results res;

	/**
	 *	Constructor
	 *	@param in_file - An initialized FileProcessor object
	 *	@param in_res - An initialized Results object
	 */
	public CreateWorkers(FileProcessor in_file, Results in_res, Tree in_tree){
		MyLogger.writeMessage("CreateWorkers constructor called.", MyLogger.DebugLevel.CONSTRUCTOR);
		res = in_res;
		file = in_file;
		populate_threads = null; 
		delete_threads = null;	
		wordTree = in_tree;	
	}

	/**
	 *	Start the threads to populate the tree
	 *	@param num_thread - The number of threads to start
	 */
	public void startPopulateWorkers(int num_threads){
		try{
			populate_threads = new Thread[num_threads];
			for(int i = 0; i < num_threads; ++i){
				Runnable p = new PopulateThread(file, res, wordTree);
				populate_threads[i] = new Thread(p);
				populate_threads[i].start();
			}
			for(Thread t : populate_threads){
				t.join();
			}
		}catch(InterruptedException | NullPointerException e){
			e.printStackTrace();
			System.exit(1);
		}finally{

		}		
	}

	/**
	 *	Start the threads to delete words from the tree
	 *	@param num_thread - The number of threads to start
	 */
	public void startDeleteWorkers(int num_threads, String[] delete_words){
		try{
			delete_threads = new Thread[num_threads];
			for(int i = 0; i < num_threads; ++i){
				Runnable d = new DeleteThread(file, res, wordTree, delete_words[i]);
				delete_threads[i] = new Thread(d);
				delete_threads[i].start();
			}
			for(Thread t : delete_threads){
				t.join();
			}
		
			
		}catch(InterruptedException | NullPointerException | ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			System.exit(1);
		}finally{

		}
	}

	@Override
	public String toString(){
		return "";
	}
}
