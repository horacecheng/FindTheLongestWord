import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;



public class Main 
{
	ArrayList<String> stringlist = new ArrayList<String>();
	
	public static void main(String[] args) 
	{
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("src/wordsforproblem.txt"));
			Trie trie = new Trie();
			
			String line;
			int count = 0;
			while((line = br.readLine()) != null)
			{
				trie.addWords(line);
				count++;
			}
			br.close();
			
			ArrayList<String> solution = trie.findTheLongestWord();
			
			System.out.println("The total inserted words : " + count);
			System.out.println("The first longest word: " +solution.get(0) + "    " + solution.get(0).length() + "letters");
			System.out.println("The second longest word: " +solution.get(1) + "    " + solution.get(1).length() + "letters");
			System.out.println("The first shortest word: "+ solution.get(solution.size()-1) + "   " +
						solution.get(solution.size()-1).length() + " letters");
			System.out.println("The second shortest word: "+ solution.get(solution.size()-2) +"   "+
					solution.get(solution.size()-2).length() + " letters");
			
			
			System.out.println("The total longest words count: " + solution.size());
			//System.out.println("The children size is " + trie.root.sizOfChildren());
			


		}catch(Exception e)
		{
			System.out.println("Cannot find file");
		}
		/*
		Trie trie = new Trie();
		trie.addWords("cat");
		trie.addWords("cats");
		trie.addWords("catsdogcats");
		trie.addWords("catxdogcatsrat");
		trie.addWords("dog");
		trie.addWords("dogcatsdog");
		trie.addWords("hippopotamuses");
		trie.addWords("rat");
		trie.addWords("ratcatdogcat");
		
		ArrayList<String> solution = trie.findTheLongestWord();
		System.out.println("The first longest word: " +solution.get(0) + "    " + solution.get(0).length() + "letters");
		System.out.println("The second longest word: " +solution.get(1) + "    " + solution.get(1).length() + "letters");
		System.out.println("The Total count: " + solution.size());
		
	*/	
		
		
		
	}
}
