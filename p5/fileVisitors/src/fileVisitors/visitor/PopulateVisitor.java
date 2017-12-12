package fileVisitors.visitor;
import fileVisitors.treeBuilder.TreeBuilder;
import fileVisitors.util.MyLogger;
import fileVisitors.util.FileProcessor;
import java.util.regex.PatternSyntaxException;

public class PopulateVisitor implements VisitorI {
	private String file;

	public PopulateVisitor(String in_file){
		MyLogger.writeMessage("PopulateVisitor constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		file = in_file;
	} 

	/**
	 *	Visit the tree and poppulate it
	 *	@param tree - The tree to populate
	 */
	public void visit(TreeBuilder tree){
		MyLogger.writeMessage("PopulateVisitor visiting tree", MyLogger.DebugLevel.VISIT);
		try{ 
			FileProcessor inf = new FileProcessor(file, 'r');
			String line;

			while((line = inf.readLine()) != null){
				if(line.trim().isEmpty()){
					line = inf.readLine();
					continue;
				}
				String[] words = line.split(" +");

				for (String word : words){
					if(!word.trim().isEmpty())
						tree.addWord(word);	
				}
			}
			inf.closeFiles();
		} catch (PatternSyntaxException | NullPointerException e){
			e.printStackTrace();
		} finally {

		}
		
	}	
}
