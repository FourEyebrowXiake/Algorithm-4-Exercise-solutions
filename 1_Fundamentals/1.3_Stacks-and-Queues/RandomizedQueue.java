package c_1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
 
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
     private Item[] a; // array of items
     private int n; // number of elements on stack
 
     public RandomizedQueue() // construct an empty randomized queue
      {
         a = (Item[]) new Object[2];
         n = 0;
     }
 
     public boolean isEmpty() // is the queue empty?
      {
         return n == 0;
      }
     
     public int size() // return the number of items on the queue
      {
         return n;
     }
 
     // resize the underlying array holding the elements
     private void resize(int capacity) {
         assert capacity >= n;
 
         Item[] temp = (Item[]) new Object[capacity];
 
         for (int i = 0; i < n; i++) {
             temp[i] = a[i];
         }
 
         a = temp;
     }

     public void enqueue(Item item) // add the item
      {
         if (item == null) {
             throw new NullPointerException();
         }
 
         if (n == a.length) {
             resize(2 * a.length); // double size of array if necessary
         }
 
         a[n++] = item; // add item
     }
 
     public Item dequeue() // remove and return a random item
      {
         if (isEmpty()) {
             throw new NoSuchElementException();
         }
 
         int targetid = StdRandom.uniform(0, n);
         Item item = a[targetid];
         a[targetid] = a[n - 1];
         a[n - 1] = null; // to avoid loitering
         n--;
 
         // shrink size of array if necessary
         if ((n > 0) && (n == (a.length / 4))) {
             resize(a.length / 2);
         }
 
         return item;
     }
 
     public Item sample() // return (but do not remove) a random item
      {
         if (isEmpty()) {
             throw new NoSuchElementException();
         }
 
         int targetid = StdRandom.uniform(0, n);
 
         return a[targetid];
     }
 
     public Iterator<Item> iterator() // return an independent iterator over items in random order
      {
         return new RandomizedQueueIterator();
     }
 
 
     private class RandomizedQueueIterator implements Iterator<Item> {
         private int i;
         private Item[] temp;
 
         public RandomizedQueueIterator() {
             i = n - 1;
             temp = (Item[]) new Object[a.length];
             for (int j = 0; j < n; j++) {
                 temp[j] = a[j];
             }
         }
 
         public boolean hasNext() {
             return i >= 0;
         }
 
         public void remove() {
             throw new UnsupportedOperationException();
         }
 
         public Item next() {
             if (!hasNext()) {
                 throw new NoSuchElementException();
             }
 
             int targetid = StdRandom.uniform(0, i + 1);
             Item item = temp[targetid];
             temp[targetid] = temp[i];
             temp[i] = item;
             i--;
             return item;
         }
     }
     
     public static void main(String[] args) // unit testing
     {
    	 
    }
 }
