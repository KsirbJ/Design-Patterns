package studentCoursesBackup.driver;


import studentCoursesBackup.myTree.SubjectI;
import studentCoursesBackup.myTree.ObserverI;
import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.FileProcessor;

public class Driver {
	public Driver(){

	}

	public static void main(String[] args) {
		
		if(args.length < 5){
			System.err.println("Program usage: ant -buildfile src/build.xml run -Darg0=FIRST -Darg1=SECOND -Darg2=THIRD -Darg3=FOURTH -Darg4=FIFTH");
			System.exit(1);
		}

		Results orig_res = new Results(args[2]);
		Results backup_1_res = new Results(args[3]);
		Results backup_2_res = new Results(args[4]);

		SubjectI node_orig = null;
		ObserverI backup_Node_1 = null;
		ObserverI backup_Node_2 = null;

		// Read in input
		FileProcessor inf = new FileProcessor(args[0], 'r');
		

		// Parse input file
		Boolean gotLine = true;
		while(gotLine){
			String line = inf.readLine();
			if(line == null){
				break;
			}
			try{
				String[] data = line.split(":");
				if(data.length < 2 || !data[1].matches("[A-K]")){
					System.err.println("Ignoring invalid input " + line);
				}else{
					int i = Integer.parseInt(data[0]);
					if(node_orig == null){
						node_orig = new Node(i, data[1]);
						backup_Node_1 = ((Node)node_orig).clone();
						backup_Node_2 = ((Node)node_orig).clone();
						node_orig.registerObserver(backup_Node_1);
						node_orig.registerObserver(backup_Node_2);
					}else{
						SubjectI n = new Node(i, data[1]);
						ObserverI bn1 = ((Node)n).clone();
						ObserverI bn2 = ((Node)n).clone();
						n.registerObserver(bn1);
						n.registerObserver(bn2);
						((Node)node_orig).insertNode((Node)n);
						((Node)backup_Node_1).insertNode((Node)bn1);
						((Node)backup_Node_2).insertNode((Node)bn2);
					}
				}
				
			}catch (NumberFormatException e){
				System.err.println("Ignoring invalid value: " + (line.length() > 0 ? line : "empty line"));
			}finally{

			}
			
		}
		inf.closeFiles(); // close input file

		// Read delete file
		FileProcessor df = new FileProcessor(args[1], 'r');
		while (gotLine) {
			String line = df.readLine();
			if(line == null){
				break;
			}
			try{
				String[] data = line.split(":");
				if(data.length < 2 || !data[1].matches("[A-K]")){
					System.err.println("Ignoring invalid input " + line);
				}else{
					int i = Integer.parseInt(data[0]);
					if(node_orig != null){
						((Node)node_orig).deleteNodeCourse(i, data[1]);
					}
				}
				
			}catch (NumberFormatException e){
				System.err.println("Ignoring invalid value: " + (line.length() > 0 ? line : "empty line"));
			}finally{

			}
		}
		df.closeFiles();

		if(node_orig != null){
			((Node)node_orig).printNodes(orig_res);
			//System.out.println("------------------------------");
			((Node)backup_Node_1).printNodes(backup_1_res);
			//System.out.println("------------------------------");
			((Node)backup_Node_2).printNodes(backup_2_res);
			orig_res.storeToFile();
			backup_1_res.storeToFile();
			backup_2_res.storeToFile();
			orig_res.cleanUp();
			backup_1_res.cleanUp();
			backup_2_res.cleanUp();
		}
		
	}
	
}

