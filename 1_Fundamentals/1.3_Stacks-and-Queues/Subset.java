package c_1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

	public static void main(String[] args) {
		RandomizedQueue<String> queue=new RandomizedQueue<String>();
		int num=Integer.parseInt(args[0]);
		
		while(!StdIn.isEmpty()){
			String item=StdIn.readString();
			queue.enqueue(item);
		}
		
		for (String string : queue) {
			if(num==0){
				break;
			}
			
			StdOut.println(string);
			num--;
		}
	}

}
