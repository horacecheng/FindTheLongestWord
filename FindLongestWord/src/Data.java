/*
 * Author: Ho Yin Cheng Horace
 * Description:
 *It stores the original word and suffix. We will use this class when we add data to the queue
 * 
 */
public class Data 
{
	private String originalword;
	private String suffix;
	public Data(String originalword, String suffix)
	{
		this.originalword = originalword;
		this.suffix = suffix;
	}
	
	public String getOriginalWord()
	{
		return originalword;
	}
	
	public String getSuffix()
	{
		return suffix;
	}
	
}
