package fileVisitors.driver;

import fileVisitors.treeBuilder.TreeBuilder;
import fileVisitors.visitor.PalindromeHighlight;
import fileVisitors.visitor.PopulateVisitor;
import fileVisitors.visitor.PrintTree;
import fileVisitors.visitor.PrimeLength;
import fileVisitors.visitor.VisitorI;
import fileVisitors.util.MyLogger;
import fileVisitors.util.FileProcessor;

public class Driver {

	public static void main(String[] args) {
		
		try{
			if(args.length != 3){
				throw new IllegalArgumentException("Expected 3 arguments, got " + args.length);
			}else{
				int debug_lvl = Integer.parseInt(args[2]);
				if(debug_lvl < 0 || debug_lvl > 4)
					throw new IllegalArgumentException("Debug level must be between 0 and 4");
				MyLogger.setDebugValue(debug_lvl);
			}
		}catch(IllegalArgumentException e){
			e.printStackTrace();
			System.exit(1);
		}finally{
		}

		TreeBuilder word_tree = new TreeBuilder();
		VisitorI fill_tree = new PopulateVisitor(args[0]);
		word_tree.accept(fill_tree);
		VisitorI mark_palindromes = new PalindromeHighlight();
		word_tree.accept(mark_palindromes);
		VisitorI mark_primes = new PrimeLength();
		word_tree.accept(mark_primes);
		FileProcessor of = new FileProcessor(args[1], 'w');
		VisitorI printer = new PrintTree(of);
		word_tree.accept(printer);
		of.closeFiles();
	}
	
}
