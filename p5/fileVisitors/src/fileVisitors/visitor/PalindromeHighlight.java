package fileVisitors.visitor;

import fileVisitors.treeBuilder.TreeBuilder;
import fileVisitors.treeBuilder.Node;
import fileVisitors.util.MyLogger;

public class PalindromeHighlight implements VisitorI {
	
	public PalindromeHighlight(){
		MyLogger.writeMessage("PalindromeHighlight constuctor called", MyLogger.DebugLevel.CONSTRUCTOR);
	}

	/**
	 *	Visit the tree and capitalize palindromes
	 *	@param tree - The tree to work on
	 */
	public void visit(TreeBuilder tree){
		MyLogger.writeMessage("PalindromeHighlight visiting tree", MyLogger.DebugLevel.VISIT);
		doMarkPalindrome(tree.getRoot());
	}

	/**
	 *	Helper method to check and mark each word in the tree from node n 
	 *	@param n - The node to start with
	 */
	private void doMarkPalindrome(Node n){
		if(n == null)
			return;

		String content = n.getWord();
		if(content.length() > 3){
			if(isPalindrome(content)){
				content = content.toUpperCase();
				n.setWord(content);
			}
		}
		doMarkPalindrome(n.getLeft());
		doMarkPalindrome(n.getRight());
	}

	/**
	 *	Check if a string is a palindrome
	 *	@param word - The string to check
	 *	@return {boolean} - True if the string is a palindrome, false otherwise and on error
	 */
	public boolean isPalindrome(String word){
		int mid = (int)Math.floor(word.length() / 2.0);
		int i = (word.length() % 2 == 0) ? 0 : 1;	// First letters to compare
		int j = i == 0 ? 1 : 0;
		try{
			while((mid - i - j) >= 0){
				if(word.charAt(mid - i - j) != word.charAt(mid + i))
					return false;
				i++;
			}
			return true;
		}catch(NullPointerException | IndexOutOfBoundsException e){
			e.printStackTrace();
			return false;
		}finally{
		}	
	}

	@Override
	public String toString(){
		return "Class PalindromeHighlight";
	}
}
