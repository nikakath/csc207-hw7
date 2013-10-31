package edu.grinnell.csc207.nikakath_byrnefio_goldstei4.hw7;

import java.io.PrintWriter;

/**
 * A simple set of experiments to make sure that our amazing DoublyLinkedList 
 * class works fine.
 */
public class Experiment {

    /**
     * Print a list of objects.
     * @throws Exception 
     */
    public static <T> void printList(PrintWriter pen, DoublyLinkedList<T> list) throws Exception {
	pen.print("[" + list.front.val + " "); //don't know why it doesn't print front
	for (T val : list) {		// on its own. Our error? Sam's? 
	    pen.print(val);
	    pen.print(" ");	    
	} // for
	pen.println("]");
	pen.flush();
    } // printList(PrintWriter, LinkedList<Object>)

    public static void main(String[] args) throws Exception {
	// Set up output
	PrintWriter pen = new PrintWriter(System.out, true);

	// Create some lists
	DoublyLinkedList<String> strings = new DoublyLinkedList<String>();
	DoublyLinkedList<Integer> numbers = new DoublyLinkedList<Integer>();

	// Prepend a few elements
	numbers.prepend(42);
	numbers.prepend(77);
	numbers.prepend(11);
	printList(pen, numbers);


	// Append a few elements
	numbers.append(1);
	numbers.append(2);
	numbers.append(3);
	printList(pen, numbers);

	// Delete the first element
	pen.println("Deleting first");
	Cursor c = numbers.front();
	numbers.delete(c);
	pen.println(numbers.get(c));
	printList(pen, numbers);

	// Delete the third element
	pen.println("Deleting third");
	Cursor d = numbers.front();
	numbers.advance(d);
	numbers.advance(d);
	numbers.delete(d);
	printList(pen, numbers);
	
	// Insert and get some elements
	Cursor f = strings.front();
	strings.insert("world", f);
	printList(pen, strings);
	pen.print(strings.get(f));
	strings.advance(f);
	strings.insert("hello", f);
	printList(pen, strings);
	pen.print(strings.get(f));
	strings.retreat(f);
	pen.print(strings.get(f));
	printList(pen, strings);
	
	// And we're done
	pen.close();
    } // main(String[])
} // class Experiment

