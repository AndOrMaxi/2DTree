/**
 * @author Toumanidou Andromachi 3040185
 * 
 */

class TreeNode
{
	 protected TreeNode parent, left, right;	//gonios kombou, aristero kai deksi paidi
     protected Point point;		//shmeio (x,y) pou periexei o kombos
     
     TreeNode(Point point) 
     {
         if (point == null) throw new IllegalArgumentException();
         this.point = point;
     }
     
     protected void unlink() //afairesh kombou, den thn xrhsimopoiw kapou sta zhtoumena ths ergasias
     {
    	 point = null;
         parent = left = right = null;
     }
	
} 