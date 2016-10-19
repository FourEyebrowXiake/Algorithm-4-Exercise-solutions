package z_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex_1_1_35 {
	public static final int SIDES=6;
	private static double[] dist=new double[2*SIDES+1];
	
    public static double[] initialization(){
    	for(int i=1;i<=SIDES;i++){
    		for(int j=1;j<=SIDES;j++){
    			dist[i+j]+=0.0;
    		}
    	}
    	
    	for(int k=2;k<=2*SIDES;k++){
    		dist[k]/=36.0;
    	}
    	return dist;
    }
    
    public static double[] start(int n){
    	for (int i = 0; i < n; i++)
            dist[throwDice()]++;
        
        for (int k = 2; k <= 2*SIDES; k++)
            dist[k] /= n;
        
        return dist;
    }
    
    public static int throwDice(){
    	return StdRandom.uniform(1,SIDES+1)+StdRandom.uniform(1,SIDES+1);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N=StdIn.readInt();
		
		double[] init=initialization();
		
		for (int i = 2; i <= 2*SIDES; i++)
            StdOut.printf("%7d", i);
        StdOut.println();
        
        for (int i = 2; i <= 2*SIDES; i++)
            StdOut.printf("%7.3f", init[i]);
        StdOut.println();

        double[] experim = start(N); 
        
        for (int i = 2; i <= 2*SIDES; i++)
            StdOut.printf("%7.3f", experim[i]);
        StdOut.println();
	}

}
