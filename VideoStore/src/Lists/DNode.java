/**
 * 
 */
package Lists;

/**
 * @author shmueljacobsen
 *
 */
public class DNode {
	private Object element;
	private DNode prev;
	private DNode next;
	
	public DNode(Object element, DNode prev, DNode next) {
		this.element = element;
		this.prev = prev;
		this.next = next;
	}
	
	public Object getElement() {
		return element;
	}

	public DNode getPrev() {
		return prev;
	}

	public DNode getNext() {
		return next;
	}
	
	public void setElement(Object element) {
		this.element = element;
	}
	
	public void setPrev(DNode prev) {
		this.prev = prev;
	}

	public void setNext(DNode next) {
		this.next = next;
	}

}
