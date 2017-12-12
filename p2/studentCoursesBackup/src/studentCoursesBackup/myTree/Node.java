package studentCoursesBackup.myTree;

import java.util.ArrayList;
import studentCoursesBackup.util.Results;

public class Node implements ObserverI, SubjectI, Cloneable {

	private ArrayList<String> courses;
	private ArrayList<ObserverI> observers; // This node's observers
	private int b_number;
	private Node left;
	private Node right;

	/**
	 *	Default constructor
	 */
	public Node(){
		b_number = -1;
		courses = new ArrayList<String>();
		left = null;
		right = null;
		observers = null;
	}

	/**
	 *	Value Constructor
	 *	@param b_number_in - The BNumber for the node
	 *	@param course - A course to add to the node
	 */
	public Node(int b_number_in, String course){
		b_number = b_number_in;
		courses = new ArrayList<String>();
		courses.add(course);
		left = null;
		right = null;
		observers = null;
	}

	/**
	 *	Copy constructor. Makes a deep copy of the node, ignoring the left and right children
	 *	@param in_node - The node to copy
	 */
	public Node(Node in_node){
		if(in_node != null){
			b_number = in_node.getBNumber();
			setCourses(in_node.getCourses());
			left = null;
			right = null;
			setObservers(in_node.getObservers());
		}else{
			b_number = -1;
			courses = new ArrayList<String>();
			left = null;
			right = null;
			observers = null;
		}
	}

	/**
	 *	Set the node's bnumber
	 *	@param bn - The BNumber
	 */
	public void setBNumber(int bn){
		b_number = bn;
	}

	/**
	 *	Add a course to the node's list
	 *	@param course_name - The course to add
	 */
	public void addCourse(String course_name){
		if(!findCourse(course_name)){
			if(courses == null){
				courses = new ArrayList<String>();
			}
			courses.add(course_name);
			//notifyObservers("insert", course_name);
		}else{
			//System.err.println("Ignoring duplicate course " + course_name + " for student " + b_number);
		}
	}

	/**
	 *	Remove a course from the list. Fails silently when course is not found
	 *	@param course_name - The course to remove
	 */
	public void removeCourse(String course_name){
		if(courses == null)
			return;

		try{
			for(int i = 0; i < courses.size(); ++i){
				if(courses.get(i).equals(course_name)){
					courses.remove(i);
					notifyObservers("delete", course_name);
					return;
				}
			}
		}catch(NullPointerException e){
			System.err.println("Error while deleting course");
			e.printStackTrace();
		}finally{

		}
	}

	/**
	 *	Find a course in the list
	 *	@param course_name - The name of the course to find
	 *	@return Whether the course was found or not
	 */
	public boolean findCourse(String course_name){

		if(courses == null)
			return false;


		try{

			for(int i = 0; i < courses.size(); ++i){
				if(courses.get(i).equals(course_name)){
					return true;
				}
			}
			return false;
		}catch(NullPointerException e){
			System.err.println("Error while looking for course");
			e.printStackTrace();
			return false;
		}finally{

		}
	}

	/**
	 *	Setter method for the courses list
	 *	@param in_courses - The array list of courses
	 */
	public void setCourses(ArrayList<String> in_courses){
		if(in_courses != null)
			courses = new ArrayList<String>(in_courses);
		else 
			courses = null;
	}

	/** 
	 *	Get the list of courses for this node
	 *	@return The list of courses
	 */
	public ArrayList<String> getCourses(){
		return courses;
	}

	/**
	 *	Get the BNumber for this node
	 *	@return the Node's bnumber
	 */
	public int getBNumber(){
		return b_number;
	}

	/**
	 *	Get the node's left child
	 *	@return The left child node
	 */
	public Node getLeft(){
		return left;
	}

	/**
	 *	Set the left child node
	 *	@param in_left - The node to copy
	 */
	public void setLeft(Node in_left){
		left = new Node(in_left);
	}

	/**
	 *	Get the node's right child
	 *	@return The right child node
	 */
	public Node getRight(){
		return right;
	}

	/**
	 *	Set the right child node
	 *	@param in_right - The node to copy
	 */
	public void setRight(Node in_right){
		right = new Node(in_right);
	}

	/**
	 *	Clone this node
	 *	@return A new node that's a clone of this one
	 */
	@Override
	public Node clone(){
		Node n = new Node(this);
		return n;
	}

	/**
	 *	toString. 
	 *	@return The node's content
	 */
	@Override
	public String toString(){
		try{
			String ret_string = String.format("%04d", b_number) + ":";
			for(String course : courses)
				ret_string = ret_string + course + "," ;
			return ret_string;
		}catch(NullPointerException e){
			System.err.println("Error while prining node's values");
			e.printStackTrace();
			return "";
		}finally{

		}
		

	}

	/********************-------- TREE OPERATIONS --------*********************/

	/**
	 *	Insert a node into the tree
	 *	@param n - The node to insert
	 */
	public void insertNode(Node n){

		if(b_number == -1){
			b_number = n.getBNumber();
			courses = n.getCourses();
			observers = n.getObservers();

		}else if(b_number > n.getBNumber()){
			if(left == null)
				left = n;
			else
				left.insertNode(n);
		}else if(b_number < n.getBNumber()){
			if(right == null)
				right = n;
			else
				right.insertNode(n);
		}else if(b_number == n.getBNumber()){
			if(n.getCourses() != null){
				for(String course : n.getCourses())
					addCourse(course);
			}
		}
	}

	/**
	 * Find a node in the tree, given the bNumber
	 *	@param bn - The BNumber to find
	 *	@return The node if it is found, null otherwise
	 */
	public Node findNode(int bn){

		if(b_number == -1)
			return null;

		if(b_number == bn){
			return this;
		}else if(bn > b_number){
			if(right != null)
				return right.findNode(bn);
		}else if(bn < b_number){
			if(left != null)
				return left.findNode(bn);
		}
		return null;
	}

	/**
	 *	Delete A given course from a node
	 *	@param bn - The bnumber of the node to delete the course from
	 *	@param course - The course to delete
	 */
	public void deleteNodeCourse(int bn, String course){
		try{
			if(b_number == bn){
				removeCourse(course);
			}else{
				if(bn > b_number && right != null)
					right.deleteNodeCourse(bn, course);
				else if(bn < b_number && left != null)
					left.deleteNodeCourse(bn, course);
			} 
			
		}catch(NullPointerException e){
			System.err.println("Error while trying to delete course " + course + " for node " + bn);
			e.printStackTrace();
		}finally{

		}
	}


	/**
	 *	Get the values of all the nodes in the tree
	 *	@return the toString() value of each node on a new line
	 */
	public String printNodes(){
		try{
			if(b_number != -1){
				return (left != null ? left.printNodes() + '\n' : "")  + toString() + 
					(right != null ? '\n' + right.printNodes() : "");
			}else{
				return "";
			}
		}catch(NullPointerException e){
			System.err.println("Exception while printing nodes");
			e.printStackTrace();
		}finally{
			
		}	
		return "";
	}

	public void printNodes(Results r){
		try{
			if(b_number != -1){
				r.storeNewResult((left != null ? left.printNodes() + '\n' : "")  + toString() + 
					(right != null ? '\n' + right.printNodes() : ""));
			}
		}catch(NullPointerException e){
			System.err.println("Exception while printing nodes");
			e.printStackTrace();
		}finally{
			
		}	
	}


	/********----------- Subject/Observer methods -----------**********/

	/**
	 *	Register a new observer
	 *	@param o - The observer to register
	 */
	public void registerObserver(ObserverI o){
		if(observers == null)
			observers = new ArrayList<ObserverI>();
		observers.add(o);
	}

	/**
	 * 	Notify observers of a change
	 *	@param update_type - The type of update (delete or insert)
	 *	@param update - The update to apply
	 */
	public void notifyObservers(String update_type, String update){
		if(observers == null)
			return;
		try{
			for(ObserverI o : observers){
				o.update(update_type, update);
			}
		}catch(NullPointerException e){
			System.err.println("Couldn't notify observers");
			e.printStackTrace();
		}finally{

		}
	}

	/**
	 *	Remove the specified observer
	 *	@param o - The observer
	 */
	public void removeObserver(ObserverI o){
		if(observers == null)
			return;
		observers.remove(o);
	}

	/**
	*	Get the list of the node's observers
	*
	*	@return The observers
	*/
	public ArrayList<ObserverI> getObservers(){
		return observers;
	}

	/**
	 *	Set the observers arraylist
	 *	@param obs_in - The arraylist to copy
	 */
	public void setObservers(ArrayList<ObserverI> obs_in){
		if(obs_in != null)
			observers = new ArrayList<ObserverI>(obs_in);
		else 
			observers = null;
	}

	/**
	 *	For the observer, update when subject changes
	 *	@param update_type - The type of update (delete or insert)
	 *	@param update - The update to apply
	 */
	public void update(String update_type, String update){
		if(update_type.equals("delete"))
			removeCourse(update);
		// else if(update_type == "insert")
		// 	addCourse(update);
	}
}