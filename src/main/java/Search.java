package Trains;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class Search {
	private Graph graph ;
	private HashMap < String, ArrayList<String> > avaiList ; // store each town node and it's edges
	private ArrayList<String> TownList ;
	private Vector < ArrayList<String> >  visited; 
	
	public Search (Graph g)
	{
		this.graph = g ;
		this.TownList  = g.getTownList();
	}

	public int getDistance (String[] t) throws Exception
	{
		String[] part = getEdges(t) ;
		int distance = 0 ;
		
		for (String twn :  part)
		{
			String s = twn.substring(0,1) ;
			String e = twn.substring(1,2) ;
			if (!graph.isAdjacentTown(s, e))
				throw new Exception();
			else
				distance = distance+ graph.getEdgeWeight(s, e) ;
		}
		return distance;
	}
	
	private String[] getEdges(String[] t) {
		int townnumber = t.length - 1 ;
		String[] edges =  new String[townnumber] ;
		for (int i = 0;  i < townnumber ; i++)
		{
			edges[i] = t[i] + t[i+1] ;
		}
		
		return edges;
	}
	public void getGraphAdjacentList()
	{
		ArrayList<Edge> edges ;
		ArrayList<String> endP ;
		if (avaiList !=null)
		{
			return ;
		}
		avaiList = new HashMap<String, ArrayList<String>>() ;
		
		for (String s : TownList)
		{
			endP = new ArrayList<String>() ;
			edges = graph.getEdgeList(s) ;
			for (Edge e : edges)
				{ endP.add( e.getEnd()) ; }
			avaiList.put(s, endP) ;
		}
	}
	public int getTrip (String s, String e, int limit)
	{
		return this.getTrip(s, e, limit, false) ;
	}
	public int getTrip (String s, String e, int limit, boolean maxReq) {
		int nTrips = 0 ;
		
		getGraphAdjacentList() ;
		
		int nStops ;
		int nTowns = 0 ;
		
		int[] trips = new int[10] ;
		
		Iterator<String> is = avaiList.get(s).iterator() ;
		while ( is.hasNext())
		{
			String next = is.next() ;
			nStops = 1 ;
			nStops += traverse (s,next) ;
			trips [nTowns ++] = nStops ;
		}
		
		for (int i = 0 ; i < trips.length ; i ++)
		{
			if (maxReq)
			{
				if (trips[i] == limit && trips[i] !=0)
					nTrips++ ;
			} else
			{
				if (trips[i] <=limit && trips[i] !=0)
					nTrips++ ;
			}
		}
		return nTrips ;
	}

	public int traverse(String s, String next) {
		int nStops = 0; 
		Iterator<String> it = avaiList.get(next).iterator() ;
		while (it.hasNext())
		{
			String n = it.next() ;
			nStops ++ ;
			if (n.equalsIgnoreCase(next))
				break ;
		}
		
		return nStops;
	}

	public int ShortestLength (String s, String e)
	{
		int Lenth = Integer.MAX_VALUE ;
		int townWeight =  0; 
		
		ArrayList <ArrayList<String>> trips = new ArrayList <ArrayList<String>> () ;
		
		getGraphAdjacentList();
		
		ArrayList<String> stops = new ArrayList<String> () ;
		
		String start = s; 
		int ncount = avaiList.get(s).size() ;
		if (ncount ==1)
		{
			stops.add(0, s) ;
			start =avaiList.get(s).get(0) ;
			
		}
		Iterator<String> it = avaiList.get(start).iterator() ;
		while (it.hasNext())
		{
			String next = it.next() ;
			
			stops.add(stops.size(),start) ;
			stops.addAll(stops.size(), getStops(next, e)) ;
			trips.add(new ArrayList<String>(stops)) ;
			stops.clear() ;
			if (ncount ==1)
			{
				stops.add(0,s) ;
			}
			for (ArrayList<String> list :trips)
			{
				try {
					townWeight = getDistance(list.toArray(new String[list.size()])) ;
				}catch(Exception e1)
				{
					townWeight = 0; 
				}
				if (townWeight < Lenth)
				{
					 Lenth = townWeight ;
				}
			}
		}
		
		
		return Lenth;
		
	}
	

public int getRoute(String s, String e, int lim) {
	int length = lim;
	int weight = 0;
	int Tcount = 0;
	
	ArrayList<ArrayList<String>> trips =
		new ArrayList<ArrayList<String>>();
			
	getGraphAdjacentList();

	ArrayList<String> stops = new ArrayList<String>();
	visited = new Vector<ArrayList<String>>();
	
	String n1 = s;
	
	int ncount = avaiList.get(s).size();
	if (ncount == 1) {
		// if start node only has one edge iterate
		// on next node to build the tree
		// TODO should check if the next node also only has 1 edge
		stops.add(0, s);
		addVisited(s, stops.size()-1);
		n1 = avaiList.get(s).get(0);
	}
	
	stops.add(stops.size(), n1);
	addVisited(n1, stops.size()-1);
	Iterator<String> is = avaiList.get(n1).iterator();
	while (is.hasNext()) {
		
		String ntown = is.next();
		
		stops.add(ntown);
		addVisited(ntown, stops.size()-1);
		traverseT(ntown, e, stops);
		
		try {
			
			weight = getDistance(stops.toArray(new String[stops.size()]));
		} catch (Exception e1) {
			// if there's an error don't count this trip
			weight = Integer.MAX_VALUE;
		}
		
		while (weight < length) {
			trips.add((new ArrayList<String>(stops)));
			Tcount++;
			if (ntown.equalsIgnoreCase(e)) {
				stops.remove(stops.size()-1);
			}

			traverseT(stops.get(stops.size()-1), e, stops);
			try {
				
				weight = getDistance(stops.toArray(new String[stops.size()]));
			} catch (Exception e2) {
				// if there's an error don't count this trip
				weight = Integer.MAX_VALUE;
			}
			
		}
		
		
		// get last selected trip and follow next division from
		// last node with more than one edge
		ArrayList<String> ltrip = trips.get(trips.size()-1);

		// get next node from last stops to determine where
		// to division next
		String lsnn = stops.get(ltrip.size());
		stops.clear();
		stops.addAll(ltrip);
		stops.add(lsnn);
		
		// if last route larger than the limit, backup 1 edge
		// and go down next division (if town only has 1 edge
		// continue to back up until next edge is found)
		// -- stops should have at least one edge
		if (stops.size() > 1) {
			boolean ndivision = false;
			while (!ndivision && stops.size()>1) {
				String tv = stops.get(stops.size()-1);		
				stops.remove(stops.size()-1);
				String v1 = stops.get(stops.size()-1);
				ArrayList<String> nodelist = avaiList.get(v1);
				while (nodelist.size() == 1 && stops.size() > 1) {
					tv = stops.get(stops.size()-1);;
					stops.remove(stops.size()-1);
					clearVisited(stops.size());
					v1 = stops.get(stops.size()-1);
					nodelist = avaiList.get(v1);
				}
				for (String elem : nodelist) {
					// check if all paths were visited for this node
					int i = nodelist.lastIndexOf(tv);
					if (i == nodelist.size()-1) {
						continue;
					}
					if (!isVisited(elem, stops.size())) {
						n1 = elem;
						stops.add(n1);
						addVisited(n1, stops.size()-1);
						is = avaiList.get(n1).iterator();
						ndivision = true;
						continue;
					}
				}
			}
		} else {
				
			stops.clear();
			
			if (ncount == 1) {
				// if start town has only one edge reset
				// 
				stops.add(0, s);
			}
			stops.add(stops.size(), n1);
		}
						
	}		

	return Tcount;
}

	 
	public void traverseT(String s, String e, ArrayList<String> stops ) 
	{
		getGraphAdjacentList();
		
		String lastnode = stops.get(stops.size() -1) ;
		if (!s.equalsIgnoreCase(lastnode))
		{
			stops.add(stops.size(),s) ;
			addVisited(s,stops.size()-1) ;
		}
		Iterator<String> it = avaiList.get(s).iterator() ;
		while(it.hasNext())
		{
			String next = it.next() ;
			if (! isVisited(next,stops.size()))
			{
				stops.add(next) ;
				addVisited(next,stops.size()-1) ;
			}
			else continue ;
			while (!next.equalsIgnoreCase(e))
			{
				next = avaiList.get(next).get(0) ;
				stops.add(next) ;
			}
			return ;
		}
		
	}

	public ArrayList<String> getStops(String s, String n) {
		ArrayList<String> stops = new ArrayList<String> () ;
		stops.add(s) ;
		

		Iterator it = avaiList.get(s).iterator() ;
		while (it.hasNext())
		{
			String next = (String) it.next() ;
			stops.add(next) ;
			
			while (!next.equalsIgnoreCase(n))
			{
				it = avaiList.get(next).iterator() ;
				while (it.hasNext())
				{
					next = (String) it.next() ;
					stops.add(next) ;
				}
			}
			if (next.equalsIgnoreCase(n))
				break;
		}
		return stops;
	}

	public void addVisited(String s, int i) {
		// TODO Auto-generated method stub
		try{
			visited.get(i).add(s) ;
		} catch (ArrayIndexOutOfBoundsException e)
		{
			visited.add(i, new ArrayList<String>()) ;
			visited.get(i).add(s) ;
		}
		
	}
	public boolean isVisited(String s, int i)
	{
		try
		{
			return visited.get(i).contains(s) ;
		} catch (ArrayIndexOutOfBoundsException e)
		{
		}
		return false ;
	}
	public void clearVisited (int i)
	{
		visited.remove(i) ;
	}

	public static void main (String[] args)
	{
		String[] edgs = {"AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"} ;
		Graph g =new Graph(new String[] {"A","B","C","D","E"} )  ;
		for (String e : edgs)
		{
			String s = e.substring(0,1) ;
			//System.out.println(" s" +s) ;
			String end = e.substring(1,2) ;
			//System.out.println(" end " +end) ;
			//System.out.println(" 2+3 " + e.substring(2,3)) ;
			int weight = Integer.parseInt(e.substring(2)) ;
			//System.out.println(" weight " + weight) ;
			g.addEdge(s, end, weight) ;
		}
		Search gs = new Search (g) ;
		try {
			System.out.println("Distance of ABC " +"is : " + 
					gs.getDistance( new String[]{"A","B","C"} ) ) ;
		} catch (Exception e)
		{
			System.out.println("No such route");
		}
		try {
			System.out.println("Distance of AD" + " is: " + 
					gs.getDistance( new String[]{"A","D"})) ;
		}catch (Exception e)
		{
			System.out.println("No such route") ;
		} 
		try {
			System.out.println("Distance of ADC " + "is :" + 
					gs.getDistance(new String[]{"A","D","C"})) ;
		}catch (Exception e)
		{
			System.out.println("No such route") ;
		}
		try{
			System.out.println("Distance of AEBCD " + "is :" + 
					gs.getDistance( new String[]{"A","E","B","C","D"})) ;
		} catch (Exception e)
		{
			System.out.println("No such route") ;
		}
		try {
			System.out.println("Distance of AED" + "is :" +
					gs.getDistance(new String[] {"A","E","D"})) ;
		} catch (Exception e)
		{
			System.out.println("AED: No such route"); 
		}
		System.out.println("No.of trips C-C <=3 towns :" +gs.getTrip("C", "C", 3)) ;
		System.out.println("No. of trips A to C =4 towns:" + gs.getTrip("A", "C", 4)) ;
		
		System.out.println("The shortest route A-C" + gs.ShortestLength("A", "C")) ;
		System.out.println("The shortest route B-B" + gs.ShortestLength("B", "B")) ;
		System.out.println("No. of routes C-C" + gs.getRoute("C", "C", 30));
	}
}