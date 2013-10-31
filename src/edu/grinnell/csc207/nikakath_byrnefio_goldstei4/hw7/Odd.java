package edu.grinnell.csc207.nikakath_byrnefio_goldstei4.hw7;

public class Odd implements Predicate<Integer> {

    @Override
    public boolean test(Integer val) {
         return (val.intValue() % 2) != 0;
    } // test
 } // class IsEven