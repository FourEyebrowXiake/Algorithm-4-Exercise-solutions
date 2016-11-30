package third_weak;
import java.util.Arrays;

import javax.xml.soap.Node;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private int lineNumber;
	private Node first;
	
	public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
	{
		if(points==null){
			throw new java.lang.NullPointerException();
		}
		
		
		int num=points.length;
		
		Point[] copy=new Point[num];
		
		 for (int i = 0; i < num; i++) {
			             if (points[i] == null) {
			                  throw new NullPointerException();
			              }
			  
			              for (int j = i + 1; j < num; j++) {
			                  if (points[i].compareTo(points[j]) == 0) {
			                      throw new IllegalArgumentException();
			                  }
			              }
			              copy[i] = points[i];
			         	}
		 
		 lineNumber=0;
		 Arrays.sort(copy);
		 for(int i=0;i<num;i++){
			 for(int j=i+1;j<num;j++){
				 for(int a=j+1;a<num;a++){
					 for(int b=a+1;b<num;b++){
						 double d1=copy[i].slopeTo(copy[j]);
						 double d2=copy[j].slopeTo(copy[a]);
						 double d3=copy[a].slopeTo(copy[b]);
						 
						 if((d1==d2)&&(d2==d3)){
							 if(first!=null){
								 Node newNode=new Node();
								 newNode.next=first.next;
								 newNode.lineSegment=new LineSegment(copy[i], copy[b]);
								 first.next=newNode;
							 }else {
								first=new Node();
								first.lineSegment=new LineSegment(copy[i], copy[b]);
							}
							 lineNumber++;
						 }
					 }
				 }
			}
		 }
		
	}
	
	public int numberOfSegments()        // the number of line segments
	{
		return lineNumber;
	}
	public LineSegment[] segments()                // the line segments
	{
		LineSegment[] lineSegments=new LineSegment[lineNumber];
		Node current=first;
		for(int i=0;i<lineNumber;i++){
			lineSegments[i]=first.lineSegment;
			first=first.next;
		}
		return lineSegments;
	}
	
	public static void main(String[] arga){
	     // read the n points from a file
	    In in = new In(arga[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        StdOut.println(p);
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();

	}
	
	private class Node{
		private LineSegment lineSegment;
		private Node next;
	}
	
}
