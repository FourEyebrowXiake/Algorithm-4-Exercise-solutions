package fifth;

import java.util.Stack;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	
	private PointNode root;
	private int pointCount;
	
	public KdTree(){
		root=null;
		pointCount=0;
	}
	
	public void checkNull(Object obj){
		if(obj==null){
			throw new NullPointerException();
		}
	}
	
	 public boolean isEmpty()                      // is the set empty? 
	 {
		 return root==null;
	 }
	 public int size()                         // number of points in the set 
	 {
		 return pointCount;
	 }
	 public  void insert(Point2D p)              // add the point to the set (if it is not already in the set)
	 {
		 checkNull(p);
		 if(isEmpty()){
			 root=new PointNode(null,p,false);
			 pointCount++;
		 }else {
			PointNode current=root;
			while(true){
				if(current.value.compareTo(p)==0){
					return;
				}
				if(current.isAtLeftSideOfNode(p)){
					if(current.left==null){
						current.left=new PointNode(current,p,true);
						pointCount++;
						break;
					}else {
						current=current.left;
					}
				}else {
					if(current.right==null){
						current.right=new PointNode(current,p,false);
						pointCount++;
						break;
					}else {
						current=current.right;
					}
				}
			}
		}
		 
	 }
	 
	 public boolean contains(Point2D p)            // does the set contain point p?
	 {
		 checkNull(p);
		 
		 PointNode current=root;
		 
		 while(current!=null){
			 if(current.value.compareTo(p)==0){
				 return true;
			 }else if (current.isAtLeftSideOfNode(p)) {
				current=current.left;
			}else {
				current=current.right;
			}
		 }
		 
		 return false;
	 }
	 public              void draw()                         // draw all points to standard draw 
	 {
		 if(root!=null){
			 root.draw(null);
		 }
	 }
	 
	 public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
	 {
		 checkNull(rect);
		 Stack<Point2D> stack=new Stack<>();
		 if(root!=null){
			 addToStack(stack,root,rect);
		 }
		 return stack;
	 }
	 
	 private void addToStack(Stack<Point2D> stack,PointNode current,RectHV rectHV){
		 if(!current.rectHV.intersects(rectHV)){
			 return;
		 }
		 if ((current.value.x() >= rectHV.xmin()) &&
				                  (current.value.x() <= rectHV.xmax()) &&
				                  (current.value.y() >= rectHV.ymin()) &&
				                  (current.value.y() <= rectHV.ymax())) {
				              stack.push(current.value);
				          }
		 
		 if(current.left!=null){
			 addToStack(stack, current.left, rectHV);
		 }
		 
		 if(current.right!=null){
			 addToStack(stack, current.right, rectHV);
		 }
	 }
	 
	 public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
	 {
		 checkNull(p);
		  
		          Point2D point = null;
		          if (root != null)
		            point = searchNode(p, root, point);
		  
		          return point;
	 }

	 private Point2D searchNode(Point2D find,PointNode current,Point2D nearset){
		 if(nearset==null){
			 nearset=current.value;
		 }
		 
		 double distance=nearset.distanceSquaredTo(find);
		 double newDistache=current.value.distanceSquaredTo(find);
		 
		 if((distance>=newDistache)||(distance>=current.rectHV.distanceSquaredTo(find))){
			 if(distance>newDistache){
				 nearset=current.value;
			 }
			 
			 if((current.isY&&(find.y()>current.value.y()))||
					 (!current.isY&&(find.x()<current.value.x()))){
				 if(current.left!=null){
					 nearset=searchNode(find, current.left, nearset);
				 }
				 if(current.right!=null){
					 nearset=searchNode(find, current.right, nearset);
				 }
			 }else {
				if(current.left!=null){
					nearset=searchNode(find, current.left, nearset);
				}
				if(current.right!=null){
					nearset=searchNode(find, current.right, nearset);
				}
			}
		 }
		 
		 return nearset;
	 }


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static class PointNode{
		private PointNode left;
		private PointNode right;
		private RectHV rectHV;
		private boolean isY;
		private Point2D value;
		
		public PointNode(){
			
		}
		
		public PointNode(PointNode parent, Point2D val,boolean isleftUp){
			left=null;
			right=null;
			value=val;
			if(parent==null){
				isY=false;
				rectHV=new RectHV(0, 0, 1, 1);
			}else {
				isY=!parent.isY;
				if(isY){
					if(isleftUp){
					rectHV=new RectHV(parent.rectHV.xmin(), parent.rectHV.ymin(), parent.value.x(), parent.rectHV.ymax());
					}
					else {
					rectHV=new RectHV(parent.value.x(), parent.rectHV.ymin(), parent.rectHV.xmax(), parent.rectHV.ymax());
					}
				}
				else {
					if(isleftUp){
						rectHV=new RectHV(parent.rectHV.xmin(), parent.value.y(), parent.rectHV.xmax(), parent.rectHV.ymax());
					}
					else {
						rectHV=new RectHV(parent.rectHV.xmin(), parent.rectHV.ymin(), parent.rectHV.xmax(), parent.value.y());
					}
				}
			}
		}
		
		public void draw(PointNode p){
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(0.01);
			value.draw();
			StdDraw.setPenRadius();
			
			if(p==null){
				StdDraw.setPenColor(StdDraw.RED);
				new Point2D(value.x(), 0).drawTo(new Point2D(value.x(), 1));
			}else {
				if(p.isY){
					StdDraw.setPenColor(StdDraw.RED);
					new Point2D(value.x(),rectHV.ymin()).drawTo(new Point2D(value.x(),rectHV.ymax()));
				}else {
					StdDraw.setPenColor(StdDraw.BLACK);
					new Point2D(rectHV.xmin(), value.y()).drawTo(new Point2D(rectHV.xmax(), value.y()));
				}
			}
			
			StdDraw.pause(100);
			
			if (left != null) {
				                 left.draw(this);
				             }
				 
			if (right != null) {
				                 right.draw(this);
				             }

		}
		
		public boolean isAtLeftSideOfNode(Point2D p) {
			             if (isY) {
			                 return p.y() > value.y();
			             } else {
			                 return p.x() < value.x();
			             }
			         }

		
	}

}
