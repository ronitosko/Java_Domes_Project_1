/**
 * ListNode represents a signe-link list node
 * Each node contains an Object reference to data and a reference to the next node in the list.
*/
class Node<T>
{
	// package access members; List can access these directly
	protected T element;
	protected Node<T> next;

	/**
	 * Constructor. It initializes data and sets next node to null
	 * @param element a reference to node's data
	*/
	Node( T element )
	{
		this(element, null);

	} // end ListNode one-argument constructor

	/**
	 * constructor creates ListNode with passed data and next node
	 * @param element the reference to node's data
	 * @param node the next node in the list
	*/
	Node( T element, Node<T> node )
	{
		this.element = element;
		this.next = node;

	} // end ListNode two-argument constructor

	/**
	 * Returns this node's data
	 * @return the reference to node's data
	*/
	T getElement()
	{
		// return Object in this node
		return element;

	} // end method getObject

	/**
	 * Get reference to next node
	 * @return the next node
	*/
	Node<T> getNext()
	{
		// get next node
		return next;

	} // end method getNext
} // end class ListNode
