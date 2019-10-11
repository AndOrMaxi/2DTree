/**
 * @author Toumanidou Andromachi 3040185
 * 
 */
public class Point 
{
	
	private int x, y;//syntetagmenes tou shmeiou
	
	public Point(int x, int y)
	{
		if(x<0 || x>100 || y<0 || y>100) throw new IllegalArgumentException();//periorizomaste sto xwro [0,100]x[0,100]
		
		this.x=x;
		this.y=y;
	}

	public int x()
	{
		return this.x;
	}
	
	public int y()
	{
		return this.y;
	}
	
	public void setX(int x)
	{
		if(x<0 || x>100) throw new IllegalArgumentException();
		
		this.x=x;
	}
	
	public void setY(int y)
	{
		if(y<0 || y>100) throw new IllegalArgumentException();
		
		this.y=y;
	}
	
	public double distanceTo(Point z)
	{
		return Math.sqrt(squareDistanceTo(z));//ypologizei thn apostash ws th riza tou tetragwnou thw apostashs apo to shmeio z
	}
	
	public int squareDistanceTo(Point z)
	{
		return ((x-z.x)*(x-z.x) + (y-z.y)*(y-z.y));//epistrefei to tetragwno thw apostashs apo to shmeio z
	}
	
	public String toString()
	{
		return "(" + this.x + ", " + this.y + ")";//epistrefei (x, y)
	}
}
