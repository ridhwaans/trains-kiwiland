package Trains;

public class Edge {
	public int weighting ;
	public String start ;
	public String end ;
	
	public Edge ( int w , String s , String e)
	{
		this.weighting = w ;
		this.start = s ;
		this.end = e ;
	}
	public String getStart ()
	{
		return start ;
	}

	public void setStart (String s)
	{
		this.start = s ;
	}
	public String getEnd ()
	{
		return end ;
	}
	public void setEnd (String e)
	{
		this.end = e ;
	}
	public int getWeight ()
	{
		return weighting ;
	}
	public void setWeight (int w)
	{
		this.weighting = w ;
	}
	
}