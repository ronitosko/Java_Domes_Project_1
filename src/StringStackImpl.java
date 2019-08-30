import java.io.PrintStream;
import java.util.NoSuchElementException;



public class StringStackImpl<T> implements StringStack<T>{
	
	private Node<T> head;
	private int size;
	
	
	
	//constructor of StringStackImpl
	public StringStackImpl(){
		size = 0;
		head = null;
	}//end of constructor
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public void push(T item){
		head = new Node<T>(item, head);
		size++;
	}//end of push
	
	public T pop() throws NoSuchElementException{
		if(size == 0)
			throw new NoSuchElementException("Size = 0");
		
		
		T v = head.getElement();
		Node<T> t = head.getNext();
		head = t;
		size--;
		return v;
		
	}//end of pop
	
	public T peek() throws NoSuchElementException{
		if(size == 0)
			throw new NoSuchElementException("Size = 0");
		
		return head.getElement();
	}//end of peek
	
	public int size(){
		return size;
	}//end of size
	
	
	
	public void printStack(PrintStream stream){
		Node node = head; // top of the stack

		 while(node != null){
		    stream.println(node.getElement());
		    stream.flush();
		    node = node.next;
		 }
	}//end of printStack

}//end of class
