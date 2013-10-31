package edu.grinnell.csc207.nikakath_byrnefio_goldstei4.hw7;

import java.io.PrintWriter;

/**
 * A simple set of experiments to make sure that our amazing DoublyLinkedList
 * class works fine.
 */
public class Experiment {

    /**
     * Print a list of objects.
     * 
     * @throws Exception
     */
    public static <T> void printList(PrintWriter pen, DoublyLinkedList<T> list)
	    throws Exception {
	pen.print("[" + list.front.val + " "); // don't know why it doesn't
					       // print front
	for (T val : list) { // on its own. Our error? Sam's?
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

	// Insert into null list
	strings.insert("world", f);
	printList(pen, strings);
	pen.println(strings.get(f));

	// Insert at front of list
	strings.insert("hello", f);
	// Expected output:
	// "[hello world ]/hello/world/hello/hello/[hello world ]"
	printList(pen, strings);
	pen.println(strings.get(f));
	strings.advance(f);
	pen.println(strings.get(f));
	if (strings.hasPrev(f)) {
	    pen.println(strings.getPrev(f));
	} else {
	    pen.println("hasPrev false negative");
	}
	strings.retreat(f);
	if (strings.hasPrev(f)) {
	    pen.println("hasPrev false positive");
	}
	pen.println(strings.get(f));
	pen.flush();
	printList(pen, strings);

	// Insert mid-list
	strings.advance(f);
	strings.insert("foo", f);
	printList(pen, strings);
	pen.println(strings.get(f));
	strings.advance(f);
	strings.insert("bar", f);
	printList(pen, strings);
	pen.println(strings.get(f));

	// Testing Swap
	/*
	 * pen.println(); pen.println("Testing swap(c1, c2):");
	 * pen.print("  Current list: "); printList(pen, strings); Cursor s1 =
	 * strings.front(); Cursor s2 = strings.front();
	 * 
	 * // Swap first two:
	 * 
	 * 
	 * Thread.sleep(1000); System.out.println("ONE"); Thread.sleep(1000);
	 * 
	 * strings.advance(s2); strings.swap(s1, s2);
	 * pen.print("  [foo hello bar world ] = "); printList(pen, strings);
	 * pen.println("    s1 = " + strings.get(s1) + "; s2 = " +
	 * strings.get(s2));
	 * 
	 * Thread.sleep(1000); System.out.println("TWO"); Thread.sleep(1000);
	 * 
	 * strings.advance(s1);
	 * 
	 * strings.advance(s2); strings.advance(s2); pen.println("    s1 = " +
	 * strings.get(s1) + "; s2 = " + strings.get(s2)); strings.swap(s1, s2);
	 * pen.print("  [foo bar hello world ] = "); printList(pen, strings);
	 * pen.print("    s1 = " + strings.get(s1) + "; s2 = " +
	 * strings.get(s2));
	 */

	// Testing Search
	pen.println();
	pen.println("Testing search(c, pred):");
	pen.print("  Current list: ");
	printList(pen, numbers);
	Cursor g = numbers.front();
	Even even = new Even();
	Seven seven = new Seven();
	
	System.out.println("  cursor @ " + numbers.get(g));
	System.out.println("  " + numbers.search(g, even));
	System.out.println("  cursor @ " + numbers.get(g));

	pen.print("  Current list: ");
	printList(pen, numbers);
	numbers.advance(g);
	System.out.println("  cursor @ " + numbers.get(g));
	System.out.println("  " + numbers.search(g, even));
	System.out.println("  cursor @ " + numbers.get(g));

	pen.print("  Current list: ");
	printList(pen, numbers);
	System.out.println("  cursor @ " + numbers.get(g));
	System.out.println("  " + numbers.search(g, seven));
	System.out.println("  cursor @ " + numbers.get(g));
	
	System.out.println("  cursor @ " + numbers.get(g));
	System.out.println("  " + numbers.search(g, even));
	System.out.println("  cursor @ " + numbers.get(g));

	//Testing select
	Odd odd = new Odd();
	pen.println("Testing select(pred):");
	pen.print("  Current list: ");
	
	System.out.print("even numbers:  ");
	DoublyLinkedList<T> sevens =  (DoublyLinkedList) numbers.select(even);
	PrintList(pen, sevens);
	
	System.out.println("odd numbers:  " + numbers.select(odd));

	System.out.println("sevens:  " + numbers.select(seven));

	// And we're done
	pen.close();
    } // main(String[])
} // class Experiment

