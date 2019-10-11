import java.util.Comparator;
import java.util.Scanner;
import java.io.*;
/**
 * @author Toumanidou Andromachi 3040185
 * 
 */
public class TwoDTree 
{	
	protected TreeNode root;	//h riza tou dentroy dyadikhs anazhthshs
    protected int size;		//N shmeia sto disdiastato xwro
    protected Comparator<Point> xCmp, yCmp; //2 comparators, enas gia thn x kai enas gia thn y diastash
	
	
	public TwoDTree()
	{
		this(new XComparator(), new YComparator());
	}
	
	public TwoDTree(Comparator<Point> xCmp, Comparator<Point> yCmp) 
	{
        this.size = 0;
        this.root=null;
        this.xCmp = xCmp;
        this.yCmp = yCmp;
	}
	
	public boolean isEmpty()
	{
		return root == null;
	}
	
	public int size()
	{
		return size;
	}	
	
	public TreeNode insertR (TreeNode root, Point p, int level)//anadromikh eisagwgh se fyllo
	{
		if (p == null) throw new IllegalArgumentException();
		
		if (root == null)//pros8hkh kombou sth riza an to dentro einai adeio
		{
			TreeNode node = new TreeNode(p);
			++size;
			return node;
		}
		
		int result;
		if (level%2==0)//elegxos an eimai se artio h peritto epipedou tou dentrou wste na ginei sygkrish twn x h twn y syntetagmenwn antistoixa
			result = xCmp.compare(p, root.point);
		else
			result = yCmp.compare(p, root.point);
		 
		if (result == 0)	  //an einai ises, sygkrinoume kai tis y h tis x syntetagmenes antistoixa
			if (((level%2==0)&& (yCmp.compare(p, root.point)==0))||((level%2==1)&& (xCmp.compare(p, root.point)==0)))
				return root;//to shmeio yparxei hdh, opote epistrefw
						
		    
		if (result<0)//analoga me to apotelesma ths sygkrishs
		    root.left = insertR(root.left, p, ++level);//kalw thn insertR() gia to aristero ypodentro
		else
		    root.right = insertR(root.right, p, ++level);//kalw thn insertR() gia to deksi ypodentro
		return root;
		 
	}
	
	public void insert(Point p)
	{
		 root = insertR(root, p, 0);//kalw thn anadromikh apo th riza tou dentrou pou einai sto epipedo 0
	}
	
	public boolean searchR(TreeNode root, Point p, int level)//oi sygkriseis ginontai antistoixa me thn insertR
	{
		if (p == null) throw new IllegalArgumentException();
		  if (root == null)
	            return false;
	        int result;
	        if (level%2==0)
				 result = xCmp.compare(p, root.point);
			 else
				 result = yCmp.compare(p, root.point);
	        if (result == 0)
	        {
	        	if (((level%2==0)&& (yCmp.compare(p, root.point)==0))||((level%2==1)&& (xCmp.compare(p, root.point)==0)))
					return true;
				else      		
					return false;
	        }
	        if (result<0)
	            return searchR(root.left, p, ++level);
	        else
	            return searchR(root.right, p, ++level);
	}
	
	public boolean search(Point p)
	{
		return searchR(root, p, 0);//kalw thn anadromikh apo th riza tou dentrou
	}
		
	public Point nearestNeighbor(TreeNode root, Point p, Point n, Rectangle r,int level)//anadromikh
	{
		if (p == null) throw new IllegalArgumentException();
		
		Point nearest=n;
		Rectangle NodeRegion1, NodeRegion2;
		if (level%2==0)//elegxos an eimai se artio h peritto epipedo gia to an 8a xwristei h perioxh orizontiws h ka8etws
		{
			NodeRegion1 = new Rectangle(r.xmin(),r.ymin(), root.point.x(), r.ymax());
			NodeRegion2 = new Rectangle(root.point.x(),r.ymin(), r.xmax() , r.ymax());
		}
		else
		{
			NodeRegion1 = new Rectangle(r.xmin(),r.ymin(),r.xmax(), root.point.y());
			NodeRegion2 = new Rectangle(r.xmin(),root.point.y(), r.xmax() , r.ymax());
		}
//		System.out.println(NodeRegion1.toString());		//dokimastikes ektypwseis
//		System.out.println(NodeRegion2.toString());
		if (nearest.squareDistanceTo(p)>root.point.squareDistanceTo(p))
			nearest =root.point;//an h apostash apo th riza toy ypodentrou einai mikroterh apo thn apostash apo to mexri tote kontinotero,tote ginetai h riza nearest
		if (NodeRegion1!=null)
			if ((nearest.squareDistanceTo(p) >= NodeRegion1.squareDistanceTo(p))&& root.left!=null)//elegxw an 8a psaksw sto aristero ypodentro h oxi
				nearest = nearestNeighbor(root.left, p,nearest,NodeRegion1,level+1);
		if (NodeRegion2!=null)
			if ((nearest.squareDistanceTo(p) >= NodeRegion2.squareDistanceTo(p))&& root.right!=null)//elegxw an 8a psaksw sto deksi ypodentro h oxi
				nearest = nearestNeighbor(root.right, p,nearest,NodeRegion2,level+1);
		return nearest;
				
	}
	
	public Point nearestNeighbor(Point p)
	{
		if (this.isEmpty()) return null;
		return nearestNeighbor(root, p,root.point,new Rectangle(0,0,100,100),0);//kalei thn anadromikh ksekinwntas apo th riza, me perioxh olo ton xwro [0,100]x[0,100] kai bazontas ws arxiko kontinotero geitona th riza 
		
		
	}
	
	public Queue<Point> rangeSearch(TreeNode root, Rectangle rect, Queue<Point> queue,Rectangle r,int level)//anadromikh
	{
		if (rect == null) throw new IllegalArgumentException();
		Queue<Point> points = queue;
		
		Rectangle NodeRegion1, NodeRegion2;
		if (level%2==0)//elegxos an eimai se artio h peritto epipedo gia to an 8a xwristei h perioxh orizontiws h ka8etws
		{
			NodeRegion1 = new Rectangle(r.xmin(),r.ymin(), root.point.x(), r.ymax());
			NodeRegion2 = new Rectangle(root.point.x(),r.ymin(), r.xmax() , r.ymax());
		}
		else
		{
			NodeRegion1 = new Rectangle(r.xmin(),r.ymin(),r.xmax(), root.point.y());
			NodeRegion2 = new Rectangle(r.xmin(),root.point.y(), r.xmax() , r.ymax());
		}
//		System.out.println(NodeRegion1.toString());
//		System.out.println(NodeRegion2.toString());
		
		if (rect.contains(root.point))//an h riza perilmbanetai mesa sto query rectangle, mpainei mesa sthn oura
			points.put(root.point);
		if (NodeRegion1 != null)
			if (rect.intersects(NodeRegion1) && root.left!=null)//elegxos gia to an 8a psaksoume sto aristero ypodentro h oxi
				points = rangeSearch(root.left, rect, points, NodeRegion1,level+1);
		
		if (NodeRegion2 != null)
			if (rect.intersects(NodeRegion2) && root.right!=null)//elegxos an 8a psaksoume sto deksi ypodentro h oxi
				points = rangeSearch(root.right, rect, points, NodeRegion2,level+1);
		
		return points;//epistrefei thn oura me ta ews tote shmeia
	}
	
	public Queue<Point> rangeSearch(Rectangle rect)
	{
		return rangeSearch(root, rect, new Queue<Point>(), new Rectangle(0,0,100,100), 0);//kalei thn anadromikh ksekinwntas apo th riza tou dentrou kai exontaw olo to xwro [0,100]x[0,100] kai pairnontas mia oura pou 8a pernaei se ka8e anadromh kai 8a epistrafei sto telos gemath me ta points
	}
	
	public void printLevels(TreeNode root)//kanei level order diasxish tou dentrou h toy ypodentrou me th xrhsh mias Queue<TreeNode> kai typwnei to shmeio ka8e kombou
	{//thn eftiaksa gia dokimastikes ektypwseis, opws proteinetai sthn ekfwnhsh
		Queue<TreeNode> levelOrder=new Queue<TreeNode>("levelOrder");
		TreeNode temp = root;
		levelOrder.put(temp);//bazw th riza sthn oura
		while (!levelOrder.isEmpty())//oso h oura exei stoixeia
		{
			TreeNode t= levelOrder.get();//bgazw ena stoxeio apo thn oura
			System.out.print(t.point.toString() + " ");//to typwnw
			if (t.left!=null) levelOrder.put(t.left);//an exei paidia ta bazw sthn oura
			if (t.right!=null) levelOrder.put(t.right);
		}
	}
	
	public void printLevels()
	{
		printLevels(root);//kalei thn printLevels gia olo to dentro
	}
	
	public static void main(String args[])
	{
		XComparator xCmp = new XComparator();
		YComparator yCmp = new YComparator();
		TwoDTree tree = new TwoDTree(xCmp,yCmp);//dhmiourgw ena TwoDTree
		String path = "C:\\points.txt";//to path tou arxeiou me ta shmeia tou dentrou
		String l;
		File f = null;
		BufferedReader reader = null;
		
		try 
		{
			f = new File(path);
		}
		catch (NullPointerException e) 
		{
			System.err.println("File not found.");
		}
		
		try 
		{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));//anoigma tou arxeiou
			
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Error opening file.");
		}
		try
		{
			String[] number =reader.readLine().trim().split(" ");//diabasma prwths grammhs kai diaxwrismos ths ws pros ton keno xarakthra, pou 8eloume na perilambanei to plh8os twn shmeiwn
			if (number.length==1)//elegxos an pragmati h prwth grammh periexei mono ena stoixeio
			{
				for(int i=0;i<Integer.parseInt(number[0]);i++)//diabazw toses grammes oses to plh8os pou eixe h prwth grammh
				{
					l=reader.readLine().trim();
					String[] parts=l.split(" ");
					if (parts.length ==2)//elegxw an einai dyo ta merh pou proekypsan apo to diaxwrismo ths grammhs
					{
						Point p = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
						if (tree.search(p))//elegxw an yparxei hdh to shmeio pou diabasthke apo to arxeio
						{
							System.out.println(p.toString() + "already exists");
							break;
						}
						else
							tree.insert(p);//an den yparxei hdh sto dentro, kanw eisagwgh
						
						//tree.printLevels();	//dokimastikh ektypwsh toy mexri twra dentrou
						//System.out.println();
					}
					else
						System.out.println("Every line must contain: \"x y\"");
					
				}
			}
			else
				System.out.println("First line must contains the number of the points");
		}
		catch (IOException e)
		{
			System.out.println(" Error: Sudden end . ");
	    }
		
	    try
		{
	        reader.close();//kleisimo arxeiou
	    }
		catch (IOException e)
		{
	        System.err.println("Error closing file.");
	    }
		String line = "---------------------------------------";
		String prompt = " > ";
        Scanner in = new Scanner(System.in);
        boolean done = false;
        String answer;
        while (!done)//atermwn broxos, emfanish menou mexri na dwsei o xrhsths 0
        {
        	System.out.printf("%n%s%n%20s%n%s%n", line, "2D trees", line);
            System.out.println(" 1. Query rectangle");
            System.out.println(" 2. Query point");            
            System.out.println(" 0. Exit");
            System.out.println(line);
            System.out.print(prompt);
            answer = in.nextLine();
            
            if(answer.equals("1"))
            {
            	System.out.println("Query rectangle");
            	System.out.println(line);
				System.out.println("Give \"xmin xmax\"");
				System.out.print(prompt);
				
				String[] temp1 = in.nextLine().trim().split(" ");	
				if (temp1.length==2)
				{
					System.out.println("Give \"ymin ymax\"");
					System.out.print(prompt);
					String[] temp2 = in.nextLine().trim().split(" ");
					
					if(temp2.length==2)
					{
						Rectangle qr= new Rectangle(Integer.parseInt(temp1[0]), Integer.parseInt(temp2[0]), Integer.parseInt(temp1[1]), Integer.parseInt(temp2[1]));
						System.out.println(line);
						System.out.println("Given rectangle: \n" + qr.toString());
						Queue<Point> tempq=tree.rangeSearch(qr);
						System.out.println("Range search results are:");
						while (!tempq.isEmpty())
							System.out.print(tempq.get().toString() + " ");
						System.out.println();
					}
					else	
					{
						System.out.println("Wrong numbern of arguments!Expected \"ymin ymax\"");
					}
				}
				else	
				{
					System.out.println("Wrong numbern of arguments!Expected \"xmin xmax\"");
				}
            }
            else if(answer.equals("2"))
            {
            	System.out.println("Query point");
            	System.out.println(line);
				System.out.println("Give \"x y\"");
				System.out.print(prompt);
				String[] temp = in.nextLine().trim().split(" ");	
				if (temp.length==2)
				{
					Point qp = new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
					
					System.out.println("Nearest neighbor to "+ qp.toString() +" is: " + tree.nearestNeighbor(qp).toString());
				}
				else
				{
					System.out.println("You must enter x and y dimension");
				}
				
            }
            else if(answer.equals("0"))
            {
            	done = true;            	
            }
            else
            	System.out.println("Choose 1, 2 or 0.\n");
                        
        }
        in.close();
	}

}
