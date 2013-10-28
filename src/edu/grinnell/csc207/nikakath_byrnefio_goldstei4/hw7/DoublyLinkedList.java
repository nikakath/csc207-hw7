package edu.grinnell.csc207.nikakath_byrnefio_goldstei4.hw7;

import java.util.Iterator;

/**
 * Doubly linked lists.
 */
public class DoublyLinkedList<T> implements ListOf<T> {

	// FIELDS

	Node<T> front;

	Node<T> back;

	Node<T> dummy;

	// CONSTRUCTORS
	/**
	 * Create a new linked list.
	 */
	public DoublyLinkedList() {
		this.front = null;
		this.back = null;
		this.dummy = null;
	} // DoublyLinkedList

	// ITERABLE METHODS
	@Override
	public Iterator<T> iterator() {
		return new DoublyLinkedListIterator<T>(this);
	} // iterator()

	// LISTOF METHODS
	/**
	 * @pre The list must not be empty c must be associated with the list
	 * @post val is inserted into the list before the original value denoted by
	 *       c If the new node is at the front or back of the list, front and/or
	 *       back is updated c now points to the new node
	 */
	public void insert(T val, Cursor c) throws Exception {
		Node<T> in = new Node(val);
		DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
		if (curs.pos == this.front) {
			this.front = in;
		}
		if (curs.pos == this.back) {
			this.back = in;
		}
		in.prev = curs.pos.prev;
		in.next = curs.pos;
		curs.pos = in;
	} // insert(T, Cursor)

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

	public void prepend(T val) throws Exception {
		Node<T> pre = new Node(val);
		pre.next = this.front;
		this.front = pre;
		if (this.back == null) {
			this.back = this.front;
		}
	} // prepend(T)

	public void delete(Cursor c) throws Exception {
		DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
		curs.pos.prev.next = curs.pos.next;
		curs.pos.next.prev = curs.pos.prev;
		curs.pos = curs.pos.next;
	} // delete(Cursor)

	public Cursor front() throws Exception {
		return new DoublyLinkedListCursor<T>(this.front);
	} // front()

	public void advance(Cursor c) throws Exception {
		DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
		if (curs.pos != null) {
			curs.pos = curs.pos.next;
		}
	} // advance(Cursor)

	public void retreat(Cursor c) throws Exception {
		DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
		if (curs.pos != null) {
			curs.pos = curs.pos.prev;
		}
	} // retreat(Cursor)

	public T get(Cursor c) throws Exception {
		DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
		return curs.pos.val;
	} // get

	public T getPrev(Cursor c) throws Exception {
		DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
		return curs.pos.prev.val;
	} // getPrev(Cursor)

	public boolean hasNext(Cursor c) {
		DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
		return curs.pos.next != null;
	} // hasNext

	public boolean hasPrev(Cursor c) {
		DoublyLinkedListCursor<T> curs = (DoublyLinkedListCursor<T>) c;
		return curs.pos.prev != null;
	} // hasPrev

	public void swap(Cursor c1, Cursor c2) throws Exception {
		DoublyLinkedListCursor<T> curs1 = (DoublyLinkedListCursor<T>) c1;
		DoublyLinkedListCursor<T> curs2 = (DoublyLinkedListCursor<T>) c2;
		Node<T> temp = curs1.pos;
		curs1.pos.prev = curs2.pos.prev;
		curs1.pos.next = curs2.pos.next;
		curs2.pos.prev = temp.prev;
		curs2.pos.next = temp.next;
	} // swap(Cursor, Cursor)

	public boolean search(Cursor c, Predicate<T> pred) throws Exception {
		throw new UnsupportedOperationException("STUB");
	} // search(Cursor, Predicate<T>)

	public ListOf<T> select(Predicate<T> pred) throws Exception {
		throw new UnsupportedOperationException("STUB");
	} // select(Predicate<T>)

	public ListOf<T> subList(Cursor start, Cursor end) throws Exception {
		throw new UnsupportedOperationException("STUB");
	} // subList(Cursor, Cursor)

	public boolean precedes(Cursor c1, Cursor c2) throws Exception {
		throw new UnsupportedOperationException("STUB");
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
