package fileVisitors.treeBuilder;
import fileVisitors.util.MyLogger;

public class Node {
	private String word;
	private Node left_child = null;
	private Node right_child = null;

	public Node(String new_word){
		MyLogger.writeMessage("Node constructor called.", MyLogger.DebugLevel.CONSTRUCTOR);
		word = new_word;
	}

	public Node(){
		MyLogger.writeMessage("Node default constructor called.", MyLogger.DebugLevel.CONSTRUCTOR);
		word = null;
	}

	/**
	 *	get word
	 *	@return word
	 */
	public String getWord(){
		return word;
	}

	/**
	 *	Set the word
	 *	@param w - The new word
	 */
	public void setWord(String w){
		word = w;
	}

	/**
	 *	get left child
	 *	@return left_child
	 */
	public Node getLeft(){
		return left_child;
	}

	/**
	 *	get right child
	 *	@return right_child
	 */
	public Node getRight(){
		return right_child;
	}

	/**
	 *	Add a node
	 *	@param target - The value for the node
	 */
	public void addWord(String target){
		if(word == null){
			word = target;
			MyLogger.writeMessage(target + " added.", MyLogger.DebugLevel.WORD_ADDED);
			return; 
		}

		if(target.equals(word)){
			return;
		} else if (target.toLowerCase().compareTo(word.toLowerCase()) < 0){
			if(left_child == null){
				left_child = new Node(target);
				MyLogger.writeMessage(target + " added.", MyLogger.DebugLevel.WORD_ADDED);
			} else {
				left_child.addWord(target);
			}
		} else {
			if(right_child == null){
				right_child = new Node(target);
				MyLogger.writeMessage(target + " added.", MyLogger.DebugLevel.WORD_ADDED);
			} else {
				right_child.addWord(target);
			}
		}
	}

	@Override
	public String toString(){
		return word;
	}
}
