package wordTree.wordNode;
import wordTree.util.MyLogger;

public class Node {
	private int count = 0;
	private String word;
	private Node left_child = null;
	private Node right_child = null;
	
	public Node(String new_word, int new_count){
		MyLogger.writeMessage("Node constructor called.", MyLogger.DebugLevel.CONSTRUCTOR);
		word = new_word;
		count = new_count;
	}

	public Node(String new_word){
		word = new_word;
		count = 1;
	}

	public Node(){
		word = null;
		count = 0;
	}

	/**
	 * get count
	 * @return count
	 */
	public int getCount(){
	       return count;
	}
	/**
	 * set count
	 */
	public void setCount(int new_count){
		count = new_count;
	}
	/**
	 * get word
	 * @return word
	 */
	public String getWord(){
		return word;
	}
	/**
	 * get left child
	 * @return left_child
	 */
	public Node getLeft(){
		return left_child;
	}
	/**
	 * get right child
	 * @return right_child
	 */
	public Node getRight(){
		return right_child;
	}

	/**
	 *	Delete a node
	 *	@param target - The node to delete
	 */
	public void deleteWord(String target){
		if(target.equals(word)){
			if (count > 0){
				MyLogger.writeMessage(target + " deleted.", MyLogger.DebugLevel.WORD_DELETED);
				count--;
			}
		} else if (target.compareTo(word) < 0){
			if (left_child != null){
				left_child.deleteWord(target);
			}
		} else {
			if (right_child != null){
				right_child.deleteWord(target);
			}
		}
	}

	/**
	 *	Add a node
	 *	@param target - The value for the node
	 */
	public void addWord(String target){
		if(target.equals(word)){
			MyLogger.writeMessage(target + " added.", MyLogger.DebugLevel.WORD_ADDED);
			count++;
		} else if (target.compareTo(word) < 0){
			if(left_child == null){
				left_child = new Node(target,1);
				MyLogger.writeMessage(target + " added.", MyLogger.DebugLevel.WORD_ADDED);
			} else {
				left_child.addWord(target);
			}
		} else {
			if(right_child == null){
				right_child = new Node(target,1);
				MyLogger.writeMessage(target + " added.", MyLogger.DebugLevel.WORD_ADDED);
			} else {
				right_child.addWord(target);
			}
		}
	}
	
	@Override
	public boolean equals(Object o){
		//TODO
		return false;
	}

	@Override
	public String toString(){
		return count + " " + word;
	}
}
