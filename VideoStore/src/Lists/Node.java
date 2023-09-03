/**
 * 
 */
package Lists;

/**
 * @author shmueljacobsen
 *
 */
public class Node {
	private Object element;
	private Node next;

	public Node(Object element, Node next) {
		this.element = element;
		this.next = null;
	}

	public Object getElement() {
		return element;
	}
	
	public Node getNext() {
		return next;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	
	public void setNext(Node next) {
		this.next = next;
	}
}
