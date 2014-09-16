import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * Author: Ho Yin Cheng Horace
 * Description:
 *This class is a Trie that contains all the words from a file
 * 
 */
public class Trie 
{
	private Node root;
	private Queue<Data> queue;//contain original word with the suffix that we will check after inserting 
						//all the words in the queue are potential solution
	
	private Set<String> longestwords; //prevent duplicate solution
	
	//constructor
	public Trie()
	{
		root = new Node();
		queue = (Queue<Data>) new LinkedList<Data>();
		longestwords = new HashSet<String>();
	}
	
	//add words funtion
	public void addWords(String string)
	{
		char[] chararray = string.toCharArray();
		Node tmpnode = root;
		int index = 0;
		for(int i = 0; i<chararray.length ; i++)
		{

			Node tmpnode2 = tmpnode.search(chararray[i], index);
			if(tmpnode2 != null && tmpnode2.getChar() == chararray[i])
			{
				tmpnode = tmpnode2;
				index = 0;	
				continue;
			}
			else if(tmpnode2 != null && tmpnode2.getChar() == '#') //'#' node is always the first child of the node
			{

				queue.add(new Data(string, string.substring(i, string.length())));
				tmpnode = tmpnode2.getParent();
				index =1;
				i--;
				continue;
				
			}
			//if we surely cannot find any matched char node, then keep adding child without wasting time on searching
			addFaster(chararray, i, tmpnode);
			break;		
		}
		

	}

	/*improve the speed of adding without wasting time on searching
	 *  since we have confirmed that there is no matched char node
	*/
	private void addFaster(char[] chararray, int index, Node parent)
	{
		
		for(int i = index ; i<chararray.length ; i++)
		{
			
			if(chararray[i] == ' ')
				break;
			Node child = new Node(chararray[i], parent);
			parent.addChild(child);
			parent = child;
			
			
		}
		//after finish the loop, we use the '#' to indicate end of string
		parent.addChild(new Node('#', parent));
		
	}
	
	
	/*We will not do any adding node here. If one of the char does not match with the char in the trie, 
    *then the original word surely is not constructed by other words from the file.
    *We can exclude it from the solution
	*/
	public ArrayList<String> findTheLongestWord()	
	{

		if(!queue.isEmpty())
		{
			while(!queue.isEmpty())
			{
				Data data = queue.poll();
				
				String originalword = data.getOriginalWord();
				String suffix= data.getSuffix();
				
				if(longestwords.contains(originalword))//Since we had already found the solution, discard it
				{
					continue;
				}
				char[] chararray = suffix.toCharArray();
				Node parent = root;
				boolean save = true;
				int index = 0;
				
				for(int i = 0; i<chararray.length ; i++)
				{
					Node tmpnode = parent.search(chararray[i],index);
					if(tmpnode != null &&tmpnode.getChar() == '#')
					{
						queue.add(new Data(originalword, new String(chararray, i, chararray.length-i)));
						//System.out.println("adding here: " + originalword+ "  " + new String(chararray, i, chararray.length-i));
						parent = tmpnode.getParent();
						index =1;
						i--;
						continue;

					}
					else if(tmpnode != null &&tmpnode.getChar() == chararray[i])
					{
						parent = tmpnode;
						if(i == chararray.length-1)	// we are at the end of the original word.
						{							//At this point, every char is matched.
							if(tmpnode.search('#', 0)!= null)//check the child to see whether it matches end of string
							{
								save = true;
								break;
							}
							else
							{
								save = false;
								break;
							}
							
						}
						index =0;
						continue;
					}
					else	//null case (no matched char is found), then kick out
					{
						save = false;
						break;
					}

				}
				if(save)
				{
					longestwords.add(originalword);
				}
				
			}

		}
		else
		{
			System.out.println("the queue is empty");
		}
		
		//do the sorting
		ArrayList<String> list = new ArrayList<String>(longestwords);
		Collections.sort(list, new Comparator<String>(){

			@Override
			public int compare(String s1, String s2) 
			{
				return s2.length()-s1.length();
			}
		});
		
		return list;
		
	}
	
	public int sizeOfQueue()
	{
		return queue.size();
	}

}
