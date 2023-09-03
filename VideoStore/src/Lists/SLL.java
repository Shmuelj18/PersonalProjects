/**
 * 
 */
package Lists;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author shmueljacobsen
 *
 */
public class SLL implements Lists {
	private Node head;
	private int size;
	
	public SLL() {
		head = null;
	}
	
	public Node getHead() {
		return head;
	}
	
	public void setHead(Node head) {
		this.head = head;
	}

	public int size(){
		return size;
	}
	
	public void print() {
		var list = "";
		var current = head;
		if (head == null) throw new NoSuchElementException();
		while (current != null) {
			list += current.getElement() + "\n";
			current = current.getNext();
		}
		System.out.println(list);
	}

	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index + " out of bounds for range 0 - " + size);
		}
		var current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getElement();
	}
	
	public void add(Object o){
		Node n = new Node(o, null);
        if(head == null){
            head = n;
        } else {
            var current = head;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(n);
        }   
        n.setNext(null);
        size++;
    }
	
	public void remove(){
        if(head == null){
        	throw new NoSuchElementException();
        } else if (head.getNext() == null){
            head = null;
        } else {
            var current = head;
            while(current.getNext().getNext() != null){
                current = current.getNext();
            }
            current.setNext(null);
        }   
        size--;
    }
	
	public void reverse() {
		Node previous = null;
		Node current = head;
		Node next = head;
		while (current != null) {
			next = current.getNext();
			current.setNext(previous);
			previous = current;
			current = next;
		}
		head = previous;
		return;
	}
	
	public int indexOf(Object element) {
		int index = 0;
		var current = head;
		while (current != null) {
			if (Objects.equals(current.getElement(), element)) {
				return index;
			} else {
				current = current.getNext();
				index++;
			}
		}
		return -1;
	}
	
	public boolean contains(Object element) {
		return indexOf(element) != -1;
	}
	
	public Object middle() {
		var temp = head;
		//If empty, return null
		if (head == null) {
			return null;
		//If count is odd, return object at node ((count+1)/2) 
		} else if (size % 2 == 1) {
			int middleLength = ((size +1)/2);
			while (middleLength != 1) {
				temp = temp.getNext();
				System.out.println(temp.getElement());
				middleLength--;
			}
		//If count is even, return object at node (count/2) 
		} else if (size % 2 == 0) {
			int middleLength = (size /2);
			while (middleLength != 1) {
				temp = temp.getNext();
				System.out.println(temp.getElement());
				middleLength--;
			}
		}
		return temp.getElement(); 
	}
	public Object remove(Object object){
		if (!contains(object)) {
			throw new NoSuchElementException();
		} else {
			Object removedElement;
			var current = head;
			size--;
			if (current.getElement().equals(object)) {  // Element to remove is at head
				removedElement = head.getElement();
				head = current.getNext();
			} else {  // Element to remove is somewhere else in the list
				while (!current.getNext().getElement().equals(object)) {
					current = current.getNext();
				}
				removedElement = current.getNext().getElement();
				current.setNext(current.getNext().getNext());
			}
			return removedElement;
		}
	}
}
