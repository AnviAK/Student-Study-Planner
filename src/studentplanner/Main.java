package studentplanner;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome to Student Study Planner!");
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("What is your name?: ");
		String name = input.nextLine();
		
		System.out.println("Hello, " + name + "!");
		
		System.out.println("=============================");
		System.out.println("   STUDENT STUDY PLANNER");
		System.out.println("=============================");
		
		ArrayList<Assignment> assignments = new ArrayList<>();
		//loads what's previously saved
		loadAssignments(assignments);
		
		int num = 0;
		while(num != 5) {
			System.out.println("1. Add assignment");
			System.out.println("2. View assignments");
			System.out.println("3. Mark assignment completed");
			System.out.println("4. Delete assignment");
			System.out.println("5. Exit");
			
			System.out.print("Choose an option: ");
			
			try {
				num = input.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Please enter a number.");
				input.nextLine();
				continue;
			}
			if(num == 1) {
				
				System.out.println("You chose to add an assignment!");
				input.nextLine();
				
				System.out.print("Assignment name: ");
				String assignmentName = input.nextLine();
			
				System.out.print("Course: ");
				String courseName = input.nextLine();
			
				System.out.print("Due Date: ");
				String dateDue = input.nextLine();
			
				//this creates an object
				Assignment assignment = new Assignment(
					assignmentName,
					courseName,
					dateDue
				);
				//stores it in array
				assignments.add(assignment);
				
				System.out.println(assignment.getName());
				System.out.println(assignment.getCourse());
				System.out.println(assignment.getDueDate());
			}else if(num == 2) {
				System.out.println("You chose to view assignments!");
				
				if(assignments.size() == 0) {
					System.out.println("You have no assignments yet.");
				}
				for(int i = 0; i < assignments.size(); i++) {
					
					Assignment assignment = assignments.get(i);
					
					System.out.println("Assignment: " + assignment.getName());
					System.out.println("Course: " + assignment.getCourse());
					System.out.println("Due Date: " + assignment.getDueDate());
					System.out.println("Completed: " + assignment.isCompleted());
					System.out.println("--------------------------------");
				}
			}else if(num == 3){
				input.nextLine();
				
				System.out.print("Which assignment do you want to complete? ");
				try {
					int assignmentNum = input.nextInt();
				
					Assignment assignment = assignments.get(assignmentNum - 1);
					assignment.markCompleted();
				
					System.out.println("Assignment marked as completed!");
				}catch(InputMismatchException e) {
					System.out.println("Please enter a number.");
					input.nextLine();
				}catch (IndexOutOfBoundsException e) {
					System.out.println("That assignment does not exist.");
				}
			}else if(num == 4) {
				input.nextLine();
				
				System.out.print("Which assignment do you want to delete? ");
				try{				
					int assignmentNum = input.nextInt();
				
					assignments.remove(assignmentNum - 1);
				
					System.out.println("Assignment deleted!");
					
				}catch(InputMismatchException e) {
					System.out.println("Please enter a number.");
					input.nextLine();
				}catch (IndexOutOfBoundsException e) {
					System.out.println("That assignment does not exist.");
				}
			}else if(num == 5) {
				saveAssignments(assignments);
				System.out.println("Goodbye!");
			}else {
				System.out.println("Error! Wrong input.");
			}
		}
		
	}
	
	public static void saveAssignments(ArrayList<Assignment> assignments) {
		try {
			PrintWriter writer = new PrintWriter("assignments.txt");
			
			for(Assignment assignment : assignments) {
				writer.println(
					assignment.getName() + "|"+
					assignment.getCourse() + "|" +
					assignment.getDueDate() + "|" +
					assignment.isCompleted()
				);
			}
			writer.close();
			
		}catch(FileNotFoundException e) {
			System.out.println("Could no save assignments.");
		}
	}
	
	public static void loadAssignments(ArrayList<Assignment> assignments) {
		try {
			Scanner fileInput = new Scanner(new File("assignments.txt"));
			
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				//stored in  array values
				String[] data = line.split("\\|");
				
				String name = data[0];
				String course = data[1];
				String dueDate = data[2];
				//parseBoolean store boolean as a text
				boolean completed = Boolean.parseBoolean(data[3]);
				
				Assignment assignment = new Assignment(name, course, dueDate, completed);
				assignments.add(assignment);
			}
		}catch(FileNotFoundException e) {
			System.out.println("No saved assignments found.");
		}
	}

}
