package fileVisitors.treeBuilder;

import java.util.ArrayList;
import fileVisitors.util.MyLogger;
import fileVisitors.visitor.VisitorI;

public class TreeBuilder {
	private Node root;

	public TreeBuilder(){
		MyLogger.writeMessage("Tree constructor called.", MyLogger.DebugLevel.CONSTRUCTOR);		
		root = null;
	}
	
	/**
	 *	Add a node to the tree
	 *	@param word - The word to add
	 */
	public synchronized void addWord(String word){
		if(root == null){
			root = new Node(word);
		}else{
			root.addWord(word);
		}
		
	}

	/**
	 *	Get the root of the tree 
	 *	@return - The root
	 */
	public Node getRoot(){
		return root;
	}

	/**
	 *	Accept method for visitor pattern
	 *	@param v - The visitor to accept
	 */
	public void accept(VisitorI v){
		v.visit(this);
	}

	@Override
	public String toString(){
		if(root == null)
			return "";
		return root.toString();
	}

}