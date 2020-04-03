package eg.edu.alexu.csd.datastructure.stack;

import java.util.*;

/**
 *This class is used to run and test the Stack class.
 */
public class UIStack {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Stack stack = new Stack();
		boolean running = true;
		while(running) {
			int choice;
			mainMenuUI();
			try {
				choice = input.nextInt();
				if (choice>6 || choice<1) throw new InputMismatchException();
			} catch(InputMismatchException ex) {
				System.out.println("Invalid input. Please enter a valid choice.");
				continue;
			}
			switch(choice) {
			case 1:
				System.out.println("Enter the value to be pushed :");
				stack.push(input.next());
				break;
			case 2:
				try {
					String element =(String)stack.pop();
					System.out.println(element + " is poped successfully.");
				}catch(RuntimeException ex) {
					System.out.println("No elements to be poped. Stack is empty.");
				}
				break;
			case 3:
				try {
					String element =(String)stack.peek();
					System.out.println("The element is " +element);
				}catch(RuntimeException ex) {
					System.out.println("No elements to be peeked. Stack is empty.");
				}
				break;
			case 4:
				System.out.println("The size of the stack is " + stack.size());
				break;
			case 5:
				System.out.println(stack.isEmpty());
				break;
			case 6:
				System.exit(0);
			}
		}
	}
	/**
	 * A method to display the main menu options.
	 */
	private static void mainMenuUI() {
		System.out.println("\n\n			Main menu");
		System.out.println( "1: Push\n" +
							"2: Pop\n" + 
							"3: Peek\n" + 
							"4: Get size\n" + 
							"5: Check if empty\n"+
							"6: Exit\n"+
							"Choose one of the above operations :");
	}
}
