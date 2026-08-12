package studentplanner;

public class Assignment {
	private String name;
	private String course;
	private String dueDate;
	private boolean completed;

	//contructor
	public Assignment(String name, String course, String dueDate) {
		this.name = name;
		this.course = course;
		this.dueDate = dueDate;
		this.completed = false;
	}
	//this constructor is for an assignment loaded from file
	public Assignment(String name, String course, String dueDate, boolean completed) {
		this.name = name;
		this.course = course;
		this.dueDate = dueDate;
		this.completed = completed;
	}
	//getter
	public String getName() {
		return name;
	}
	//getter
	public String getCourse() {
		return course;
	}
	//getter
	public String getDueDate() {
		return dueDate;
	}
	
	//getter
	public boolean isCompleted() {
		return completed;
	}
	
	public void markCompleted() {
		completed = true;
	}
	
	

	
}
