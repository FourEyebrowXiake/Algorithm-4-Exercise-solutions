package c_1_3;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int N;
	private class Node{
		Item item;
		Node before;
		Node next;
	}
	
	public Deque(){
		N=0;
		first=null;
		last=null;
	}
	
	public boolean isEmpty() {
		return first==null;
	}
	
	public int size(){
		return N;
	}
	
	public void addFirst(Item item){
		if(item==null){
			throw new NullPointerException();
		}
		
		Node oldfirst=first;
		first=new Node();
		first.item=item;
		first.next=oldfirst;
		
		if(oldfirst==null) {
			last=first;
		}else {
			
			oldfirst.before=first;
		}
		
		N++;
	}
	
	public void addLast(Item item){
		if(item==null){
			throw new NullPointerException();
		}
		
		Node oldlast=last;
		last=new Node();
		last.item=item;
		last.next=null;
		last.before=oldlast;
		
		if(oldlast==null) {
			first=last;
		}
		else {
			oldlast.next=last;
		}
		
		N++;
	}
	
	public Item removeFirst(){
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		Item item1=first.item;
		first=first.next;
		if(first==null){
			last=null;
		}else {
		first.before=null;
		}
		N--;
		return item1;
	}
	
	public Item removeLast(){
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		Item item1=last.item;
		last=last.before;
		
		if(last==null){
			first=null;
		}else {	
		last.next=null;
		}
		N--;
		return item1;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item>{
		private Node current=first;

		@Override
		public boolean hasNext() {
			return current!=null;
		}

		@Override
		public Item next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			
			Item  item=current.item;
			current=current.next;
			
			return item;
		}
		
		@Override
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) {
		
		
		Deque<String> deque=new Deque<>();
		deque.addFirst("am");
		deque.addFirst("i");
		deque.addLast("lin");
		deque.addLast("jin");
		for (String string : deque) {
			System.out.println(string);
		}
		
		deque.removeFirst();
		deque.removeLast();
		
		for (String string : deque) {
			System.out.println(string);
		}
     
	}
}
