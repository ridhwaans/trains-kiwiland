package Trains;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
	private ArrayList<String> TownList ;
	private AdjacencyMatrix Matrix ;
	
	public Graph (String[] t)
	{
		TownList = new ArrayList<String> (Arrays.asList(t)) ; 
		this.Matrix = new AdjacencyMatrix(t) ;
	}
	public void addEdge (String s , String e , int weight)
	{
		Matrix.setAdjacent (s, e, weight) ;
	}
	public Edge getEdge (String s, String e )
	{
		if (Matrix.isAdjacentMatrix(s, e))
			return new Edge ( Matrix.getAdjacentEdgeWeight(s, e), s,e ) ;
		else
			return null ;
	}
	
	public int getEdgeWeight (String s , String e)
	{
		return Matrix.getAdjacentEdgeWeight(s, e) ;
	}
	
	public ArrayList<String> getTownList ()
	{
		return TownList ;
	}
	
	public boolean isAdjacentTown (String a , String b)
	{
		return Matrix.isAdjacentMatrix(a, b) ;
	}
	public ArrayList<Edge> getEdgeList()
	{
		ArrayList<Edge> edges = new ArrayList<Edge> () ;
		for (String a : TownList)
		{
			for (String b : TownList)
			{
				if (Matrix.isAdjacentMatrix(a, b)) ;
					edges.add( getEdge(a,b)) ; 
			}
		}
		
		return edges;
	}
	public ArrayList<Edge> getEdgeList(String a) // given a list
	{
		ArrayList<Edge> edges = new ArrayList<Edge> () ;
		for (String b : TownList)
		{
			if ( Matrix.isAdjacentMatrix(a, b))
				edges.add( getEdge(a,b)) ;
		}
		return edges;
		
	}
	
	/*
	 * https://en.wikipedia.org/wiki/Adjacency_matrix*/
	class AdjacencyMatrix {
		private int rows ;
		private int cols ; 
		private int value[][] ;
		
		public AdjacencyMatrix (String[] t)
		{
			this.rows = t.length ;
			this.cols = t.length ;
			value = new int[rows][cols] ;
		}
		public void setAdjacent (String a , String b , int weight)
		{
			
			value[TownList.indexOf(a)][TownList.indexOf(b)] = weight ; 
		}
		public int getAdjacentEdgeWeight (String a, String b)
		{
			return value[TownList.indexOf(a)][TownList.indexOf(b)] ;
		}
		public boolean isAdjacentMatrix (String a, String b)
		{
			 if (value[TownList.indexOf(a)][TownList.indexOf(b)] > 0)
				return true;
			else
				return false;	
		}
		

	}
	             
}