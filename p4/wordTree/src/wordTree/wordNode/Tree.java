package wordTree.wordNode;

import wordTree.wordNode.Node;
import wordTree.util.MyLogger;

import java.util.ArrayList;

public class Tree {
	private Node root;

	public Tree(){
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
	 *	Delete a node from the tree
	 *	@param word - The word to delete
	 */
	public synchronized void deleteWord(String word){
		if(root == null)
			return;
		root.deleteWord(word);
	}

	/**
	 *	Get the root of the tree
	 *	@return - The root
	 */
	public Node getRoot(){
		return root;
	}


	@Override
	public String toString(){
		if(root == null)
			return "";
		return root.toString();
	}
}
