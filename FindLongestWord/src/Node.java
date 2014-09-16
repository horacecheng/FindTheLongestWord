import java.util.ArrayList;
/*
 * Author: Ho Yin Cheng Horace
 * Description:
 * This class is the structure of a node
 * 
 */

public class Node 
{
	private char c;
	private ArrayList<Node> children;
	private Node parent;
	
	
	public Node()	//for initialize the root
	{	
		parent = null;
		children = new ArrayList<Node>();
	}
	
	public Node(char c, Node parent)
	{
		this.parent = parent;
		this.c = c;
		children = new ArrayList<Node>();
		
	}

	public Node getParent()
	{
		return parent;
	}
	
	public Character getChar()
	{
		return c;
	}
	
	public void addChild(Node node)
	{
		children.add(node);
	}
	
	public Node search(char c, int startindex)
	{
		Node node;
		for(int i =startindex ; i<children.size() ; i++)	//the maximum of node should be 26+1 = 27
		{													//since we have 26 alphabets + "#"
			node = children.get(i);
			if(node.c =='#' || node.c == c)
			{
				//System.out.println("search found: " +c );
				return node;
			}
				
		}
		return null;
	}
	
	public int sizOfChildren()
	{
		return children.size();
	}
	
	public Node getNode(int index)
	{
		return children.get(index);
	}
	


}
