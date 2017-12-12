package fileVisitors.visitor;

import fileVisitors.treeBuilder.TreeBuilder;
import fileVisitors.treeBuilder.Node;
import fileVisitors.util.MyLogger;


public class PrimeLength implements VisitorI{

    public PrimeLength(){
    	MyLogger.writeMessage("PrimeLength constuctor called", MyLogger.DebugLevel.CONSTRUCTOR);
    }
 
    /** 
     *  Visit the tree and mark the prime nodes
     *  @param tree - The tree to visit
     */
    public void visit(TreeBuilder tree){
    	MyLogger.writeMessage("PrimeLength visiting tree", MyLogger.DebugLevel.VISIT);
        doMarkPrime(tree.getRoot());
    }

    /**
     *  Helper method to recursively mark prime nodes
     *  @param n - The current node
     */
    private void doMarkPrime(Node n){
        if(n == null)
            return;

        String content = n.getWord();
        if (isPrime(content.length())){
            content = content + "-PRIME";
            MyLogger.writeMessage("Prime word found", MyLogger.DebugLevel.PRIME);
            n.setWord(content);
        }

        doMarkPrime(n.getLeft());
        doMarkPrime(n.getRight());
    }

    /**
     *  Check if a number is prime
     *  @param n - The number to check
     */
    private boolean isPrime(int n){
        if (n == 1){
	    return false;
	}
	if (n == 2){
            return true;
        }
        for (int p = 2; p <= (n/2); p++) {
            if (n % p == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        return "Class PrimeLength";
    }
}
