/**
 * @author Toumanidou Andromachi 3040185
 * 
 */
public class Rectangle 
{
	private int xmin, ymin, xmax, ymax;

	public Rectangle(int xmin, int ymin, int xmax, int ymax)
	{
		if(xmin>xmax || ymin>ymax || xmin<0 || xmin>100|| xmax<0 || xmax>100 || ymin<0 || ymin>100 || ymin<0 || ymax>100)
			throw new IllegalArgumentException();//periorizomaste sto xwro [0,100]x[0,100] kai elegxoyme an min<=max gia ka8e diastash
		this.xmin=xmin;
		this.ymin=ymin;
		this.xmax=xmax;
		this.ymax=ymax;
	}
	
	public int xmin()
	{
		return this.xmin;
	}
	
	public int ymin()
	{
		return this.ymin;
	}
	
	public int xmax()
	{
		return this.xmax;
	}
	
	public int ymax()
	{
		return this.ymax;
	}
		
	public boolean contains(Point p)
	{
		return (p.x()>=xmin && p.x()<=xmax && p.y()>=ymin && p.y()<=ymax);//epistrefei an periexetai to p sto sto parallhlogrammo
	}
	
	public boolean intersects(Rectangle that)//elegxos an exoun estv kai ena koino shmeio to trexon parallhlogrammo me to parallhlogrammo that 
	{
		return (((this.xmin <= that.xmin) && (that.xmin <= this.xmax) ||	
				(this.xmin <= that.xmax) && (that.xmax <= this.xmax) ||
				(that.xmin <= this.xmin) && (this.xmin <= that.xmax) ||
				(that.xmin <= this.xmax) && (this.xmax <= that.xmax)
				) &&
				((this.ymin <= that.ymin) && (that.ymin <= this.ymax) ||
				(this.ymin <= that.ymax) && (that.ymax <= this.ymax) ||
				(that.ymin <= this.ymin) && (this.ymin <= that.ymax) ||
				(that.ymin <= this.ymax) && (this.ymax <= that.ymax))
				);//elegxos an "mplekontai" ta parallhlogramma me kapoion tropo sthn x kai sthn y diastash
	}
	
	public double distanceTo(Point p)
	{
		return Math.sqrt(squareDistanceTo(p));//ypologizetai ws h riza toy tetragwnou ths apostashs apo to shmeio p
	}
	
	public int squareDistanceTo(Point p)
	{
		Point temp = new Point(p.x(),p.y());//shmeio panw sto parallhlogrammo apo to opoio 8a ypologistei to tetragwno ths apostashs apo to shmeio p ws h elaxisth apostash tou p apo to parallhlogrammo
											//arxika pairnei tis syntetagmenes tou shmeiou
		if (this.contains(p))//elegxos an periexetai to p sto parallhlogrammo
		{
//			int min = p.x()-xmin;			aytes tis grammes kwdika tis eixa grapsei prin bgei h anakoinwsh me tis dieykriniseis
//			if (min > xmax-p.x())
//				min = xmax-p.x();
//			if (min > ymax-p.y())
//				min = ymax-p.y();
//			if (min > p.y()-ymin)
//				min = p.y()-ymin;
//			return min*min;
			return 0;
		}
		else 
		{//diakrinw peiptwseis pou na mhn periexetai to shmeio mesa sto parallhlogrammo
			if (p.x()>=xmin && p.x()<=xmax)	//periptwsh h x syntetagmenh toy shmeiou na einai anamesa sto xmin kai sto xmax	
			{//tote h syntomoterh apostash apo to parallhlogrammo einai h ka8eth apostash
				if (p.y()>ymax)//dinw sto proswrino shmeio ymax h ymin ws y syntetagmenh analogws me to an einai panw h katw apo to parallhlogrammo to p shmeio
					temp.setY(ymax);
				else
					temp.setY(ymin);
			}
			else if ( p.y()>=ymin && p.y()<=ymax)//periptwsh h y syntetagmenh tou shmeiou na einai anamesa sto ymin kai sto ymax
			{
				if(p.x()>xmax)//dinw sto proswrino shmeio xmin h xmax ws x syntetagmenh analogws me to an einai deksia h aristera apo to parallhlogrammo to p shmeio
					temp.setX(xmax);
				else
					temp.setX(xmin);
			}
			else
			{//an to p den einai panw h katw h deksia h aristera apo to parallhlogrammo, tote h apostash ypologizetai apo th mia apo tis 4 gwnies tou parallhlogrammou, analogws poia einai pio kontinh
				if(p.x()>xmax && p.y()<ymin)
				{
					temp.setX(xmax);//pio kontinh h gwnia (xmax, ymin)
					temp.setY(ymin);
				}
				else if(p.x()>xmax && p.y()>ymax)
				{
					temp.setX(xmax);//pio kontinh h gwnia (xmax, ymax)
					temp.setY(ymax);
				}
				else if(p.x()<xmin && p.y()<ymin)
				{
					temp.setX(xmin);//pio kontinh h gwnia (xmin, ymin)
					temp.setY(ymin);
				}
				else
				{
					temp.setX(xmin);//pio kontinh h gwnia (xmin, ymax)
					temp.setY(ymax);
				}
			}
			
			return temp.squareDistanceTo(p);
		}		
	}
	
	public String toString()
	{
		return "[" + xmin + ", " + xmax + "] x [" + ymin + ", " + ymax + "]";//epistrefei [xmin, xmax]x[ymin,ymax]
	}

}
