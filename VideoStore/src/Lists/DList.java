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
public class DList implements Lists {
	private DNode header;
	private DNode trailer;
	private int size;
	
	public DList() {
		header = new DNode(null, null, null);
		trailer = new DNode(null, header, null);
		header.setNext(trailer);
	}

	public int size() {
		return size;
	}

	public DNode getHeader() {
		return header;
	}
	
	public DNode getTrailer() {
		return trailer;
	}
	
	public void setHeader(DNode header) {
		this.header = header;
	}
	
	public void setTrailer(DNode trailer) {
		this.trailer = trailer;
		
	}
	
	public void print() {
		var list = "";
		if (header.getNext() == trailer) throw new NoSuchElementException();
		var current = header.getNext();
		while (current != trailer) {
			list += current.getElement() + "\n";
			current = current.getNext();
		}
		System.out.println(list);
	}

	public boolean contains(Object element) {
		return indexOf(element) != -1;
	}

	public int indexOf(Object element) {
		int index = 0;
		var current = header.getNext();
		while (current != trailer) {
			if (Objects.equals(current.getElement(), element)) {
				return index;
			} else {
				current = current.getNext();
				index++;
			}
		}
		return -1;
	}

	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index + " out of bounds for range 0 - " + size);
		}
		var current = header.getNext();
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getElement();
	}
	public void add(Object o){
		DNode d = new DNode(o, null, null);
		if (header.getNext() == null){
			addFirst(d);
		}
		else{
			addLast(d);
		}
	}

	public void addFirst(DNode n){
        var temp = header.getNext();
        header.setNext(n);
        n.setPrev(header);
        n.setNext(temp);
        temp.setPrev(n);
        size++;
    }

	public void addLast(DNode n) {
		var temp = trailer.getPrev();
		temp.setNext(n);
		n.setPrev(temp);
		n.setNext(trailer);
		trailer.setPrev(n);
		size++;
	}
	
	public Object remove(Object n) {
		if (header.getNext() == trailer) {
			throw new NoSuchElementException();
		}
		var current = header.getNext();
		var next = current.getNext();
		while (current != trailer) {
			if (Objects.equals(current.getElement(), n)) {  // Check if content is the same
                current.getPrev().setNext(next);
				next.setPrev(current.getPrev());
                current.setPrev(null);
				current.setNext(null);
				size--;
				return current.getElement();
            }
		    else {
				current = current.getNext();
				next = next.getNext();
				size--;
		    }
		}
		throw new NoSuchElementException();
	}
	
	public void reverse() {
		DNode previous = header;
		DNode current = header.getNext();
		DNode next = header.getNext().getNext();
		while (current != trailer) {
			current.setNext(previous);
			current.setPrev(next);
			previous = current;
			current = next;
			next = current.getNext();
		}
		header.getNext().setNext(trailer);
		trailer.getPrev().setPrev(header);
		trailer.setPrev(header.getNext());
		header.setNext(previous);
		return;
	}
	
	public void join(DList b) {
		var current = b.getHeader().getNext();
		while (current != b.trailer) {
			addLast(new DNode(current.getElement(),null, null));
			current = current.getNext();
		}
	}
	
	public void swap(int i, int j) {
		var iNode = header; var jNode = header;
		for (int x = 0; x < i; x++) {
			iNode = iNode.getNext();
		}
		for (int x = 0; x < j; x++) {
			jNode = jNode.getNext();
		}
		if (iNode == header || jNode == trailer) {
			throw new IllegalArgumentException();
		}
		else {
			var iTemp = iNode.getElement(); var jTemp = jNode.getElement();
			iNode.setElement(jTemp); jNode.setElement(iTemp);
		}
		
	}

}

