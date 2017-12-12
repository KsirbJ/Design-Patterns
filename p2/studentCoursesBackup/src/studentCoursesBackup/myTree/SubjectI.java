package studentCoursesBackup.myTree;

public interface SubjectI {

	public void notifyObservers(String update_type, String update);

	public void registerObserver(ObserverI o);

	public void removeObserver(ObserverI o);
}