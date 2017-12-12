package fileVisitors.visitor;

import fileVisitors.treeBuilder.TreeBuilder;
import fileVisitors.treeBuilder.Node;
import fileVisitors.util.FileProcessor;
import fileVisitors.util.MyLogger;

public class PrintTree implements VisitorI {

    private FileProcessor file;

    public PrintTree(FileProcessor new_file){ 
    	MyLogger.writeMessage("PrintTree constuctor called", MyLogger.DebugLevel.CONSTRUCTOR);
        file = new_file;
    }

    /**
     *	Visit the tree and print it
     *	@param tree - The tree to print
     */
    public void visit(TreeBuilder tree){
    	MyLogger.writeMessage("PrintTree visiting tree", MyLogger.DebugLevel.VISIT);
        doPrintTree(tree.getRoot());
    }

    /**
     *	Helper method to recursively print the tree
     *	@param n - The current node
     */
    public void doPrintTree(Node n){
        if(n == null)
            return;

        doPrintTree(n.getLeft());
        file.writeLine(n.getWord());
        doPrintTree(n.getRight());
    }

    @Override
	public String toString(){
		return "Class PrintTree";
	}
}
