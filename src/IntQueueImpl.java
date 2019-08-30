import java.io.PrintStream;
import java.util.*;

public class IntQueueImpl<T> implements IntQueue<T>{
	
	private Node<T> head, tail;
	int size;
	
	public IntQueueImpl(){
		head = null;
		tail = null;
		size = 0;
	}//end of constructor
	
	public boolean isEmpty(){
		return head == null;
	}//end of isEmpty
	
	public void put(T item){
		Node<T> t = tail;
		tail = new Node<T>(item);
		if(isEmpty())
			head = tail;
		else
			t.next = tail;
		size++;
	}//end of Put
	
	public T get() throws NoSuchElementException{
		if(size == 0)
			throw new NoSuchElementException("Size = 0");
		T v = head.getElement();
		Node<T> t = head.getNext();
		head = t;
		size--;
		return v;
	}//end of get
	
	
	public T peek() throws NoSuchElementException{
		if(size == 0)
			throw new NoSuchElementException("Size = 0");
		return head.getElement();
	}//end of peek
	
	public int size(){
		return size;
	}//end if size
	
	public void printQueue(PrintStream stream){
		Node node = head; // top of the stack

		 while(node != null){
		    stream.println(node.getElement());
		    stream.flush();
		    node = node.next;
		 }
	}//end of printStack
	
	
	

}//end of class
