package edu.grinnell.csc207.nikakath_byrnefio_goldstei4.hw7;

/**
 * A predicate.  That is, a procedure that can be applied to values and
 * that returns true or false.
 */
public interface Predicate<T> {
	/**
	 * Test to see if val meets the predicate.
	 */
	public boolean test(T val);	
} // interface Predicate<T>
