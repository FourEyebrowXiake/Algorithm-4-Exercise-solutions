package c_1_3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Josephus {
	
	private static int N,m;
	
	public Josephus(int N,int m) {
		this.m=m;
		this.N=N;
	}
	
	public static Queue<Integer> whoDead(){
		Queue<Integer> queue=new Queue<Integer>();
		
		for (int i = 0; i < N; i++)
            queue.enqueue(i);

		 while (!queue.isEmpty()) {
	            for (int i = 0; i < m-1; i++)
	                queue.enqueue(queue.dequeue());
	            StdOut.print(queue.dequeue() + " ");
	        } 
	        StdOut.println();
		
		return null;
		
	}

	public static void main(String[] args) {
	Josephus josephus=new Josephus(7, 2);
	System.out.println(whoDead());

	}

}
