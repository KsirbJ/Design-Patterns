package wordTree.driver;

import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;
import wordTree.store.Results;
import wordTree.threadMgmt.CreateWorkers;
import wordTree.wordNode.Tree;
import wordTree.wordNode.Node;
import wordTree.wordNode.TreeData;

public class Driver {

	public static void main(String[] args) {
		int debug_value = 0;
		int num_threads = 0;
		String[] delete_words = new String[1];
		// Process input
		try{
			if(args.length != 5){
				throw new IllegalArgumentException("Usage: java -jar <path/to/jar> <input_file> <output_file> " +
				 "<num_threads> <delete_words> <debug_value>");
			}else{
				num_threads = Integer.parseInt(args[2]);
				if(num_threads < 1 || num_threads > 3)
					throw new IllegalArgumentException("NUM_THREADS must be an int between 1 and 3 (inclusive)");
				debug_value = Integer.parseInt(args[4]);
				if(debug_value < 0 || debug_value > 4)
					throw new IllegalArgumentException("DEBUG_VALUE must be an int between 0 and 4 (inclusive)");	
			}
			delete_words = args[3].split(" ");
			if(delete_words.length != num_threads){

				throw new IllegalArgumentException("Number of delete words must be equal to the number of threads");
			}
		}catch(IllegalArgumentException | NullPointerException e){
			e.printStackTrace();
			System.exit(1);
		}

		MyLogger.setDebugValue(debug_value); 
		FileProcessor inf = new FileProcessor(args[0], 'r');
		Results res = new Results(args[1]);
 
		// Start processing data
		Tree word_tree = new Tree();
		CreateWorkers thread_ctrl = new CreateWorkers(inf, res, word_tree);
		thread_ctrl.startPopulateWorkers(num_threads);
		thread_ctrl.startDeleteWorkers(num_threads, delete_words);

		
 		TreeData wt_data = new TreeData();
 		res.storeNewResult("The total number of words: " + wt_data.getNumWords(word_tree));
 		res.storeNewResult("The total number of characters: " + wt_data.getNumCharacters(word_tree));
 		res.storeNewResult("The total number of distinct words: " + wt_data.getNumDistinctWords(word_tree));

		//write results to file
		res.storeToFile();
		res.cleanUp();
		inf.closeFiles();
	}
}
