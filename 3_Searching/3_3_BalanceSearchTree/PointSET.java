package fifth;

import java.util.Stack;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SET;

public class PointSET {
	
	private SET<Point2D> pointSet;
	
	// construct an empty set of points 
	 public PointSET(){
		pointSet=new SET<Point2D>(); 
	 }
	 
	 private void checkNull(Object obj){
		 if(obj==null){
			 throw new NullPointerException();
		 }
	 }
	 // is the set empty? 
	 public boolean isEmpty(){
		 return pointSet.isEmpty();
	 }
	 
	// number of points in the set
	 public int size(){
		 return pointSet.size();
	 }
	 
	// add the point to the set (if it is not already in the set)
	 public void insert(Point2D p){
		 checkNull(p);
		 pointSet.add(p);
	 }
	 
	// does the set contain point p? 
	 public boolean contains(Point2D p){
		 checkNull(p);
		 return pointSet.contains(p);
	 }
	 
	// draw all points to standard draw 
	public void draw(){
		for (Point2D p : pointSet) {
			p.draw();
		}
	} 
	
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rectHV){
		checkNull(rectHV);
		
		Stack<Point2D> stack=new Stack<Point2D>();
		for (Point2D point2d : pointSet) {
			if((point2d.x()>=rectHV.xmin())&&(point2d.x()<=rectHV.xmax())&&
					(point2d.y()>=rectHV.ymin())&&(point2d.y()<=rectHV.ymax())){
				stack.push(point2d);
			}
		}
		
		return stack;
	}
	
	//a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p){
		checkNull(p);
		Point2D point=null;
		for (Point2D point2d : pointSet) {
			if((point==null)||(p.distanceSquaredTo(point)>p.distanceSquaredTo(point2d))){
				point=point2d;
			}
		}
		return point;
	}
	
	public static void main(String[] args){
	}
}



















