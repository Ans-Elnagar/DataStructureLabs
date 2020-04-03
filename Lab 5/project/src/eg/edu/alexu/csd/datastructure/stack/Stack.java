package eg.edu.alexu.csd.datastructure.stack;
/**
 * This class implements IStack interface
 * to create and manipulate stacks.
 */
public class Stack implements IStack {
	/**
	 * This class creates the nodes that is used in the 
	 * stack.
	 */
	private static class Node {
		private Object element;
		private Node next;
		/**
		 * The class constructor that creates a node 
		 * and assign its data fields.
		 * @param value
		 * assign the node element to this value.
		 * @param n
		 * assign the the Node next reference 
		 * variable to Node n reference variable value.
		 */
		Node(Object value,Node n) {
			setElement(value);
			setNext(n);
		}
		/**
		 * Getter of the node reference variable.
		 * @return
		 * The value of the next node reference variable.
		 */
		Node getNext() {
			return next;
		}
		/**
		 * Setter of the node next reference variable.
		 * @param n
		 * Set the node next reference variable to this value.
		 */
		void setNext(Node n) {
			next = n;
		}
		/**
		 * Getter if the node element.
		 * @return
		 * The node element value.
		 */
		Object getElement() {
			return element;
		}
		/**
		 * Setter of the node element.
		 * @param value
		 * assign the node element to this value.
		 */
		void setElement(Object value) {
			element = value;
		}
    }
	private Node head;
	/**
	 * The Stack constructor to create empty stack;
	 */
	public Stack() {
		head = null;
	}
	/**
	* Removes the element at the top of stack and returns that element.
	*
	* @return top of stack element, or through exception if empty
	*/
	public Object pop() {
		if(head == null) throw new RuntimeException("Stack is empty.");
		Object temp = head.getElement();
		head = head.getNext();
		return temp;
	}
	/**
	* Get the element at the top of stack without removing it from stack.
	*
	* @return top of stack element, or through exception if empty
	*/
	public Object peek() {
		if(head == null) throw new RuntimeException("Stack is empty.");
		return head.getElement();
	}
	/**
	* Pushes an item onto the top of this stack.
	*
	* @param object
	* to insert
	*/
	public void push(Object element) {
		Node n = new Node(element,head);
		head = n;
	}
	/**
	* Tests if this stack is empty
	*
	* @return true if stack empty
	*/
	public boolean isEmpty() {
		return head == null;
	}
	/**
	* Returns the number of elements in the stack.
	*
	* @return number of elements in the stack
	*/
	public int size() {
		int size = 0;
		Node temp = head;
		while(temp != null) {
			size++;
			temp = temp.getNext();
		}
		return size;
	}
}
