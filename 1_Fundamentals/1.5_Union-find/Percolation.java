package c_1_3;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int N;
	private WeightedQuickUnionUF quf;
	private boolean[][] grids;
	private WeightedQuickUnionUF qufBackWash;
	
	public Percolation(int n){
		
		if(n<=0){
			throw new IllegalArgumentException();
		}
		
		N=n;
		grids=new boolean[n][n];
		
		quf=new WeightedQuickUnionUF(n*n+2);
		qufBackWash=new WeightedQuickUnionUF(n*n+2);
	}
	
	public void open(int row,int col){
		judge(row, col);
		grids[row-1][col-1]=true;
		
		if(row==1){
			qufBackWash.union(0, (row-1)*N+col+1);
			quf.union(0, (row-1)*N+col+1);
		}
		
		if(row==N){
			quf.union(1, (row-1)*N+col+1);
		}
		
		//如果四周有开，就相连；
		if(col>1){
			if(isOpen(row, col-1)){
			quf.union((row-1)*N+col+1, (row-1)*N+col-1+1);
			qufBackWash.union((row-1)*N+col+1, (row-1)*N+col-1+1);
			}
		}
		
		if(col<N){
			if(isOpen(row, col+1)){
			quf.union((row-1)*N+col+1, (row-1)*N+col+1+1);
			qufBackWash.union((row-1)*N+col+1, (row-1)*N+col+1+1);
			}
		}
		if(row>1){
			if(isOpen(row-1, col)){
			quf.union((row-1)*N+col+1,(row-2)*N+col+1);
			qufBackWash.union((row-1)*N+col+1,(row-2)*N+col+1);
			}
		}
		if(row<N){
			if(isOpen(row+1, col)){
			quf.union((row-1)*N+col+1,(row)*N+col+1);
			qufBackWash.union((row-1)*N+col+1,(row)*N+col+1);
		 	}
		}
	}
	
	public boolean isOpen(int row,int col){
		judge(row, col);
		return grids[row-1][col-1];
	}
	
	public boolean isFull(int row,int col){
		judge(row, col);
		if(isOpen(row, col)){
		return qufBackWash.connected((row-1)*N+col+1, 0);
		}
		return false;
	}
	
	public boolean percolates(){
		return quf.connected(0, 1);
	}
	
	//判断输入是否合法
	private void judge(int row,int col){
	 if((row>N)||(col>N)||(row<=0)||(col<=0)){
		 throw new IndexOutOfBoundsException();
	 }
	}
	

	public static void main(String[] args) {
	/*
		int N=10000;
       Percolation percolation=new Percolation(N);
       int count=0;
       while(!percolation.percolates()){
    	   int a=StdRandom.uniform(N)+1;
    	   int b=StdRandom.uniform(N)+1;
    	   percolation.open(a, b);
       }
       
       for(int i=0;i<N*N;i++){
    		   if(percolation.isOpen(i/N+1,i%N+1)) count++;
       }
		System.out.println(count);
		*/
	}

}
