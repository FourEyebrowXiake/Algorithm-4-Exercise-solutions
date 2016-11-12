package c_1_3;

import java.io.PushbackInputStream;
import java.util.Iterator;

import javax.swing.Popup;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayStack<Item>  implements Iterable<Item>{

	private Item[] a=(Item[]) new Object[1];
	private int N=0;
	public boolean isEmpty(){return N==0;}
	public int size(){return N;}
	
	private void resize(int max){
		Item[] temp=(Item[]) new Object[max];
		for(int i=0;i<N;i++)
			temp[i]=a[i];
		a=temp;
	}

	public void push(Item item){
		if(N==a.length){resize(2*a.length);}
		a[N++]=item;
	}
	
	public Item pop(){
		Item item =a[--N];
		a[N]=null;
		if(N>0 && N==a.length/4)resize(a.length/2);
		return item;
	}
	
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<Item>{

		private int i=N;
		
		@Override
		public boolean hasNext() {
			return i>0;
		}

		@Override
		public Item next() {
			return a[--i];
		}
		
	}
	
	public static void main(String[] args) {
		 ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
	        while (!StdIn.isEmpty()) {
	            String item = StdIn.readString();
	            if (!item.equals("-")) stack.push(item);
	            else if (!stack.isEmpty()) StdOut.print(stack.pop() + " ");
	        }
	        StdOut.println("(" + stack.size() + " left on stack)");

	}
}
