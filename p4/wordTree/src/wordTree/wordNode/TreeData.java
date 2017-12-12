package wordTree.wordNode;


public class TreeData {

	public TreeData(){

	}

	public int getNumWords(Tree tree){
		Node root = tree.getRoot();
		return getNumWords(root);
	}

	/**
	 *	Get the number of words in the tree
	 *	@param node - The node to start with
	 *	@return The number of words in the tree
	 */
	public int getNumWords(Node node){
		if(node == null)
			return 0;

		return getNumWords(node.getLeft())
				 + node.getCount() 
				 + getNumWords(node.getRight());
	}

	public int getNumCharacters(Tree tree){
		Node root = tree.getRoot();
		return getNumCharacters(root);
	}

	/**
	 *	Get the number of characters in the tree 
	 *	@param node - The node to start with
	 *	@return - The number of characters in the tree
	 */
	public int getNumCharacters(Node node){
		if(node == null || node.getWord() == null)
			return 0;

		return getNumCharacters(node.getLeft()) 
				+ node.getWord().length() * node.getCount()
				+ getNumCharacters(node.getRight());
	}

	public int getNumDistinctWords(Tree tree){
		Node root = tree.getRoot();
		return getNumDistinctWords(root);
	}

	/**
	 *	Get the number of distinct words in the tree
	 *	@param node - The node to start with
	 *	@return - The number of distinct words
	 */
	public int getNumDistinctWords(Node node){
		if(node == null)
			return 0;

		return getNumDistinctWords(node.getLeft()) 
				+ (node.getCount() > 0 ? 1 : 0)
				+ getNumDistinctWords(node.getRight());
	}

	@Override
	public String toString(){
		return "TreeData";
	}
}
