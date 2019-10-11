/**
 * @author Toumanidou Andromachi 3040185
 * 
 */
public class test //gia dokimastikes ekteleseis prin thn syggrafh ths main() sthn TwoDTree
{
	
	public static void main(String args[])
	{
		Point a = new Point(10,4);
		Point c = new Point(3,50);
		Rectangle b = new Rectangle (0,0,50,100); 
		System.out.println(b.toString());
		System.out.println(a.toString());
		System.out.println(c.toString());
		System.out.println(b.contains(a));
		System.out.println(b.contains(c));
		System.out.println(b.squareDistanceTo(c));
		System.out.println(b.distanceTo(c));
		System.out.println(c.squareDistanceTo(a));
		System.out.println(c.distanceTo(a));
		
		
		
		XComparator xCmp = new XComparator();
		YComparator yCmp = new YComparator();
		TwoDTree tree = new TwoDTree(xCmp,yCmp);
		Point p = new Point(20,40);
		tree.insert(p);
		p = new Point(90,20);
		tree.insert(p);
		p = new Point(40,90);
		tree.insert(p);
		p = new Point(30,90);
		tree.insert(p);
		p = new Point(50,60);
		tree.insert(p);
		p = new Point(10,20);
		tree.insert(p);
		p = new Point(50,10);
		tree.insert(p);
		System.out.println(tree.size());
		System.out.println(tree.root.right.point.toString());
		System.out.println(tree.search(a));
		System.out.println(tree.search(p));
		System.out.println(tree.search(c));
		System.out.println(tree.search(p));
		tree.printLevels();
		System.out.println();
		System.out.println(tree.nearestNeighbor(new Point(95,80)).toString());
		System.out.println();
		Queue<Point> q = tree.rangeSearch(new Rectangle (0,0,50,100));
		System.out.println(q.size());
		System.out.println(q.get().toString());
		System.out.println(q.get().toString());
		System.out.println(q.get().toString());
		
	}


}
