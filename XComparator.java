import java.util.Comparator;
/**
 * @author Toumanidou Andromachi 3040185
 * 
 */
final class XComparator implements Comparator<Point> 
{
	@Override
	public int compare(Point p1, Point p2) 
	{		
		 if (p1.x() < p2.x()) 
			 return -1;
		 else if (p1.x() > p2.x()) 
			 return 1;
		 else
			 return 0;
		
	}	
}