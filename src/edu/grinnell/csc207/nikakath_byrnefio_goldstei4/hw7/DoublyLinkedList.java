package edu.grinnell.csc207.nikakath_byrnefio_goldstei4.hw7;

import java.util.Iterator;

/**
 * Doubly linked lists.
 */
public class DoublyLinkedList<T> implements ListOf<T> {

	// FIELDS
	
	Node<T> front;

	Node<T> back;

	// CONSTRUCTORS
	/**
	 * Create a new linked list.
	 */
    public DoublyLinkedList() {
	this.front = null;
	this.back = null;
    } // DoublyLinkedList

	// ITERABLE METHODS
    @Override
    public Iterator<T> iterator() {
	return new DoublyLinkedListIterator(this.front);
    }

	// LIST OF METHODS
	/**
	 * Insert an element at the location of the cursor (between two elements).
	 * 
	 * @pre c must be associated with the list and in the list.
	 * 
	 * @throws Exception
	 *             If the precondition is not met.
	 * @throws Exception
	 *             If there is no memory to expand the list.
	 * 
	 * @post The previous element to the cursor remains the same val is inserted
	 *       immediately after the cursor The element that previously followed
	 *       the cursor follows val
	 */
    public void insert(T val, Cursor c) throws Exception {
	Node<T> in = new Node(val);
	DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
	// Insert into null list
	if (this.front == null) {
	    this.prepend(val);
	    curs.pos = this.front;
	} else if (curs.pos == this.front) { // Insert at front
	    this.prepend(val);
	    curs.pos = this.front;
	} else { // Insert into middle
	    in.prev = curs.pos.prev;
	    curs.pos.prev.next = in;
	    in.next = curs.pos;
	    curs.pos.prev = in;
	    curs.pos = in;
	}
    } // insert(T, Cursor)

	/**
	 * Add an element to the end of the list. (Creates a one-element list if 
	 * the list is empty.)
	 * 
	 * @throws Exception
	 *             If there is no memory to expand the list.
	 * 
	 * @post val is inserted immediately after the original back of the list The
	 *       back of the list is now val
	 */
    public void append(T val) throws Exception {
	if (this.back == null) {
	    this.back = new Node(val);
	    this.front = this.back;
	} else {
	    this.back.next = new Node(val);
	    this.back.next.prev = this.back;
	    this.back = this.back.next;
	}
    } // append(T)

	/**
	 * Add an element to the front of the list. (Creates a one-element list if
	 * the list is empty.)
	 * 
	 * @throws Exception
	 *             If there is no memory to expand the list.
	 * @post val is inserted immediately before the original front of the list
	 *       The front of the list is now val
	 */
    public void prepend(T val) throws Exception {
    	Node<T> pre = new Node(val);
    	pre.next = this.front;
    	
    	if (this.front != null) {
    		front.prev = pre;
    	}
    	this.front = pre;
    	if (this.back == null) {
    		this.back = pre;
    	}
    } // prepend(T)

	/**
	 * Delete the element immediately after the cursor.
	 * 
	 * @post The remaining elements retain their order.
	 * @post The cursor is at the position The successor of the element
	 *       immediately before the cursor is the successor of the now-deleted
	 *       element.
	 */
    public void delete(Cursor c) throws Exception {
    	DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
    	if (curs.pos != this.front) {
    		curs.pos.prev.next = curs.pos.next;
    	} else {
    		this.front = this.front.next;
    	}
    	if (curs.pos != this.back) {
    		curs.pos.next.prev = curs.pos.prev;
    	} else {
    		this.back = this.back.prev;
    	}
    	curs.pos = curs.pos.next;
    } // delete(Cursor)

    /**
     * Get an cursor right before the front of the list.
     * 
     * @throws Exception
     *             If the list is empty.
     */
    public Cursor front() throws Exception {
    	return new DoublyLinkedListCursor<T>(this.front);
    } // front()

	/**
	 * Advance to the next position between elements
	 * 
	 * @pre The list has a next element.
	 * @throws Exception
	 *             If there is no next element.
	 * @post The cursor has advanced one element
	 */
    public void advance(Cursor c) throws Exception {
    	DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
    	if (curs.pos != null) {
    		curs.pos = curs.pos.next;
    	}
    } // advance(Cursor)

	/**
	 * Retreat to the previous position between elements
	 * 
	 * @pre The list has a previous element.
	 * @throws Exception
	 *             If there is no previous element.
	 * @post The cursor has retreated one element
	 */
    public void retreat(Cursor c) throws Exception {
    	DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
    	if (curs.pos != null) {
    		curs.pos = curs.pos.prev;
    	}
    } // retreat(Cursor)

	/**
	 * Get the element immediately following this cursor.
	 * 
	 * @pre c is valid and associated with this list.
	 * @throws Exception
	 *             If the preconditions are not met.
	 */
    public T get(Cursor c) throws Exception {
    	DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
    	T value = curs.pos.val;
    	return value;
    } // get

	/**
	 * Get the element immediately preceding this cursor.
	 * 
	 * @pre c is valid and associated with this list.
	 * @throws Exception
	 *             If the preconditions are not met.
	 */
    public T getPrev(Cursor c) throws Exception {
    	DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
    	return curs.pos.prev.val;
    } // getPrev(Cursor)

	/**
	 * Determine if it's safe to advance to the next position.
	 * 
	 * @pre pos is valid and associated with the list.
	 */
    public boolean hasNext(Cursor c) {
    	DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
    	return curs.pos.next != null;
    } // hasNext

	/**
	 * Determine if it's safe to retreat to the previous position.
	 * 
	 * @pre pos is valid and associated with the list.
	 */
    public boolean hasPrev(Cursor c) {
    	DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
    	return curs.pos.prev != null;
    } // hasPrev

	/**
	 * Swap the elements at the positions the correspond to c1 and c2.
	 * 
	 * @pre Both c1 and c2 are valid and associated with this list. v1 =
	 *       get(c1), v2 = get(c2)
	 *      
	 * @post c1 and c2 are unchanged. v1 = get(c2), v2 = get(c1)
	 */
    public void swap(Cursor c1, Cursor c2) throws Exception {
    	DoublyLinkedListCursor<T> curs1 = (DoublyLinkedListCursor<T>) c1;
    	DoublyLinkedListCursor<T> curs2 = (DoublyLinkedListCursor<T>) c2;
    	Node<T> temp = curs1.pos;
    	if (this.front == curs1.pos) {
    		this.front = curs2.pos;
    	} else if (this.front == curs2.pos) {
    		this.front = curs1.pos;
    	} else if (this.back == curs1.pos) {
    		this.back = curs2.pos;
    	} else if (this.back == curs2.pos) {
    		this.back = curs1.pos;
    	}

    	if (curs1.pos.next == curs2.pos || curs2.pos.next == curs1.pos) {
    		curs1.pos.prev = curs2.pos;
    		curs1.pos.next = curs2.pos.next;
    		curs2.pos.prev = temp.prev;
    		curs2.pos.next = curs1.pos;
    		curs1.pos.next.prev = curs1.pos;
    		curs2.pos.prev.next = curs2.pos;
    	} else {

    		curs2.pos.prev.next = curs1.pos;
    		curs2.pos.next.prev = curs1.pos;

    		curs2.pos.prev = temp.prev;
    		curs2.pos.next = temp.next;
    		temp.prev.next = curs2.pos;
    		temp.next.prev = curs2.pos;
    	}
    } // swap(Cursor, Cursor)

    /**
     * Search for a value that fulfills a predicate, moving the cursor to the
     * first such value in the list.
     * 
     * @return true, if the value was found
     * @return false, if the value was not found
     * 
     * @pre c is valid and associated with the list.
	 * @pre pred is applicable to all the values in the list.
     * 
     * @post If the value is not found, the cursor has not moved.
     * @post If the value is found, get(c) is the first such value in the list
     */
    public boolean search(Cursor c, Predicate<T> pred) throws Exception {
    	DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
    	Node<T> temp = curs.pos;

    	curs.pos = this.front;
    	while (this.hasNext(curs)) {
    		if (pred.test(curs.pos.val)) {
    			return true;
    		}
    		curs.pos = curs.pos.next;
    	}
    	curs.pos = temp;
    	return false;
    } // search(Cursor, Predicate<T>)

	/**
	 * Selects the elements from the list that satisfy the given predicate.
	 * 
	 * @return vals, a new list containing all elements from the given
	 *         	list for which pred returns true
	 * 
	 * @pre pred must be applicable to all elements of the list
	 * 
	 * @post vals contains all elements for which pred returns true exactly 
	 *         once
	 */
    public ListOf<T> select(Predicate<T> pred) throws Exception {
    	DoublyLinkedList<T> vals = new DoublyLinkedList<T>();
    	DoublyLinkedListCursor<T> curs = new DoublyLinkedListCursor<T>(
    			this.front);
    	while (this.hasNext(curs)) {
    		if (pred.test(curs.pos.val)) {
    			vals.append(curs.pos.val);
    		}
    		this.advance(curs);
    	}
    	return vals;
    } // select(Predicate<T>)

	/**
	 * Grab a sublist.
	 * 
	 * @return vals, a sublist containing all elements between start and
	 *       	end including start but not end in the order they appear in the
	 *       	original list
	 * 
	 * @pre start and end are valid and associated with the list.
	 * @pre	start precedes end.
	 * 
	 * @throws Exception
	 *             If the cursors are invalid.
	 */
    public ListOf<T> subList(Cursor start, Cursor end) throws Exception {
    	DoublyLinkedListCursor<T> curs1 = (DoublyLinkedListCursor<T>) start;
    	DoublyLinkedListCursor<T> curs2 = (DoublyLinkedListCursor<T>) end;
    	DoublyLinkedList<T> vals = new DoublyLinkedList<T>();

    	while (!curs1.equals(curs2)) {
    		vals.append(curs1.pos.val);
    		this.advance(curs1);
    	}
    	return vals;
    } // subList(Cursor, Cursor)

	/**
	 * Determine if one iterator precedes another iterator.
	 * 
	 * @return true if c1 precedes c2, including if they point to the same
	 *          element, false otherwise
	 * 
	 * @pre c1 and c2 are valid and associated with the list.
	 * 
	 * @throws Exception
	 *             If the cursors are invalid.
	 *             
	 */
    public boolean precedes(Cursor c1, Cursor c2) throws Exception {
    	DoublyLinkedListCursor<T> curs1 = (DoublyLinkedListCursor<T>) c1;
    	DoublyLinkedListCursor<T> curs2 = (DoublyLinkedListCursor<T>) c2;
    	while (this.hasNext(curs1)) {
    		if (curs1.equals(curs2)) {
    			return true;
    		}
    	}
    	return false;
    } // precedes(Cursor, Cursor)
} // class DoublyLinkedList

/**
 * Nodes in the list.
 */
class Node<T> {
	T val;
	Node<T> next;
	Node<T> prev;

	/**
	 * Create a new node.
	 */
	public Node(T val) {
		this.val = val;
		this.next = null;
		this.prev = null;
	} // Node(T)
} // Node<T>

/**
 * Cursors in the list.
 */
class DoublyLinkedListCursor<T> implements Cursor {
	Node<T> pos;

	/**
	 * Create a new cursor that points to a node.
	 */
	public DoublyLinkedListCursor(Node<T> pos) {
		this.pos = pos;
	} // DoublyLinkedListCursor
} // DoublyLinkedListCursor<T>

/**
 * Iterators in the list.
 */
class DoublyLinkedListIterator<T> implements Iterator<T> {
	Node<T> pos;

	/**
	 * Create a new iterator that points to a node.
	 */
	public DoublyLinkedListIterator(Node<T> pos) {
		this.pos = pos;
	} // DoublyLinkedListIterator(Node<T>)

	/**
	 * Advance this iterator one position in the list.
	 */
	@Override
	public T next() {
		T temp = this.pos.next.val;
		this.pos = this.pos.next;
		return temp;
	} // next()

	/**
	 * Determine if the iterator can advance to the next position.
	 */
	@Override
	public boolean hasNext() {
		return this.pos.next != null;
	} // hasNext()

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	} // remove()
} // class DoublyLinkedListIterator
