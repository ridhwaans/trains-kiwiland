package Trains;

import static org.junit.Assert.*;
import org.junit.*;

public class TestCase {
    
    private Search search ;
    private Graph  graph ;
    public static String[] edges = {"AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"} ;
    public static String[] town = {"A","B","C","D","E"} ; 
    
    @Before
    // Will be performed before each test.
    public void testSetup()
    {
        System.out.println("\nSetup for test complete.");
    }

    @After
    // Will be performed after each test.
    public void testComplete()
    {
        System.out.println("Test complete.");
    }
    
    @Test
    public void TestUnit1() throws Exception
    {
        graph = new Graph (town) ;
        for (String e : edges)
        {
            String s = e.substring(0,1) ;
            String end = e.substring(1,2) ;
            int weight = Integer.parseInt(e.substring(2,3)) ;
            graph.addEdge(s, end, weight) ;
        }
        search = new Search(graph) ;
        
        try {
            
            int expected = 9 ;
            int actual  ;   
            System.out.println ("TestUnit1: The distance of the route A-B-C, " +
                    "EXPECTED result is:" + expected);
            actual  = search.getDistance( new String[]{"A","B","C"} ) ;
            
            assertEquals(expected, actual) ;
            
            System.out.println( "\nGot ACTUAL distance of the route A-B-C, " +  actual+
            "\nTestUnit1: completed successfully.");
            
        } catch (AssertionError e)
        {
            //System.out.println("No such route");
            e.printStackTrace() ;
        }
    }
    @Test
    public void TestUnit2() throws Exception
    {
        graph = new Graph (town) ;
        for (String e : edges)
        {
            String s = e.substring(0,1) ;
            String end = e.substring(1,2) ;
            int weight = Integer.parseInt(e.substring(2,3)) ;
            graph.addEdge(s, end, weight) ;
        }
        search = new Search(graph) ;
        
        try {
            
            int expected = 5 ;
            int actual  ;   
            System.out.println ("TestUnit2: The distance of the route A-D," +
                    " EXPECTED result is:"+expected);
            actual  = search.getDistance( new String[]{"A","D"} ) ;
            
            assertEquals(expected, actual) ;
            
            System.out.println( "\nGot ACTUAL distance of the route A-D, " +  actual +
            "\nTestUnit2: completed successfully.");
            
        } catch (AssertionError e)
        {
            //System.out.println("No such route");
            e.printStackTrace() ;
        }
    }
    
    @Test
    public void TestUnit3() throws Exception
    {
        graph = new Graph (town) ;
        for (String e : edges)
        {
            String s = e.substring(0,1) ;
            String end = e.substring(1,2) ;
            int weight = Integer.parseInt(e.substring(2,3)) ;
            graph.addEdge(s, end, weight) ;
        }
        search = new Search(graph) ;
        
        try {
            
            int expected = 13 ;
            int actual  ;   
            System.out.println ("TestUnit3: The distance of the route A-D-C, " +
                    "EXPECTED result is:" + expected);
            actual  = search.getDistance( new String[]{"A","D","C"} ) ;
            
            assertEquals(expected, actual) ;
            
            System.out.println( "\nGot ACTUAL distance of the route A-D-C, " +  actual +
            "\nTestUnit3: completed successfully.");
            
        } catch (AssertionError e)
        {
            //System.out.println("No such route");
            e.printStackTrace() ;
        }
    }

    @Test
    public void TestUnit4() throws Exception
    {
        graph = new Graph (town) ;
        for (String e : edges)
        {
            String s = e.substring(0,1) ;
            String end = e.substring(1,2) ;
            int weight = Integer.parseInt(e.substring(2,3)) ;
            graph.addEdge(s, end, weight) ;
        }
        search = new Search(graph) ;
        
        try {
            
            int expected = 22;
            int actual  ;   
            System.out.println ("TestUnit4: The distance of the route A-E-B-C-D, " +
                    "EXPECTED result is:" + expected);
            actual  = search.getDistance( new String[]{"A","E","B","C","D"} ) ;
            
            assertEquals(expected, actual) ;
            
            System.out.println( "\nGot ACTUAL distance of the route A-E-B-C-D, " +  actual +
            "\nTestUnit4: completed successfully.");
            
        } catch (AssertionError e)
        {
            //System.out.println("No such route");
            e.printStackTrace() ;
        }
    }
    @Test
    public void TestUnit5() throws Exception
    {
        graph = new Graph (town) ;
        for (String e : edges)
        {
            String s = e.substring(0,1) ;
            String end = e.substring(1,2) ;
            int weight = Integer.parseInt(e.substring(2,3)) ;
            graph.addEdge(s, end, weight) ;
        }
        search = new Search(graph) ;
        
        try {
            
            int expected = 0;
            int actual  ;   
            System.out.println ("TestUnit5: The distance of the route A-E-D, " +
                    "EXPECTED result is:" + expected);
            actual  = search.getDistance( new String[]{"A","E","D"} ) ;
            
            assertEquals(expected, actual) ;
            
            //System.out.println();
            System.out.println( "\nGot ACTUAL distance of the route A-E-D, " + actual +
            "\nTestUnit5: completed successfully.");
            
        } catch (Exception e)
        {
            System.out.println("No such route");
            //e.printStackTrace() ;
        }
        
    }
    
    @Test
    public void TestUnit6() throws Exception
    {
        graph = new Graph (town) ;
        for (String e : edges)
        {
            String s = e.substring(0,1) ;
            String end = e.substring(1,2) ;
            int weight = Integer.parseInt(e.substring(2,3)) ;
            graph.addEdge(s, end, weight) ;
        }
        search = new Search(graph) ;
        
        try {
            
            int expected = 2;
            int actual  ;   
            System.out.println ("TestUnit6: The number of trips starting at C and ending at " +
                    "C with a maximum of 3 stops, " +
                    "EXPECTED result is:" + expected);
            actual  = search.getTrip("C", "C", 3) ;
            
            assertEquals(expected, actual) ;
            
            System.out.println( "\nGot ACTUAL number of trips C-C is " + actual +
            "\nTestUnit6: completed successfully.");
            
        } catch (AssertionError e)
        {
            //System.out.println("No such route");
            e.printStackTrace() ;
        }
    }
    
    @Test
    public void TestUnit7() throws Exception
    {
        graph = new Graph (town) ;
        for (String e : edges)
        {
            String s = e.substring(0,1) ;
            String end = e.substring(1,2) ;
            int weight = Integer.parseInt(e.substring(2,3)) ;
            graph.addEdge(s, end, weight) ;
        }
        search = new Search(graph) ;
        
        try {
            
            int expected = 3;
            int actual  ;   
            System.out.println ("TestUnit7: The number of trips starting at A and ending at " +
                    "C with exactly 4 stops, " +
                    "EXPECTED result is:" + expected);
            actual  = search.getTrip("A", "C", 4) ;
            
            assertEquals(expected, actual) ;
            
            System.out.println( "\nGot ACTUAL number of trips A-C is " + actual +
            "\nTestUnit7: completed successfully.");
            
        } catch (AssertionError e)
        {
            //System.out.println("No such route");
            e.printStackTrace() ;
        }
    }
    
    @Test
    public void TestUnit8() throws Exception
    {
        graph = new Graph (town) ;
        for (String e : edges)
        {
            String s = e.substring(0,1) ;
            String end = e.substring(1,2) ;
            int weight = Integer.parseInt(e.substring(2,3)) ;
            graph.addEdge(s, end, weight) ;
        }
        search = new Search(graph) ;
        
        try {
            
            int expected = 9;
            int actual  ;   
            System.out.println ("TestUnit8: The length of the shortest route " +
                    "(in terms of distance to travel) from A to C " +
                    "EXPECTED result is:" + expected);
            actual  = search.ShortestLength("A", "C") ;
            
            assertEquals(expected, actual) ;
            
            System.out.println( "\nGot ACTUAL Length of shortest route A to C " + actual +
            "\nTestUnit8: completed successfully.");
            
        } catch (AssertionError e)
        {
            //System.out.println("No such route");
            e.printStackTrace() ;
        }
    }
    
    @Test
    public void TestUnit9() throws Exception
    {
        graph = new Graph (town) ;
        for (String e : edges)
        {
            String s = e.substring(0,1) ;
            String end = e.substring(1,2) ;
            int weight = Integer.parseInt(e.substring(2,3)) ;
            graph.addEdge(s, end, weight) ;
        }
        search = new Search(graph) ;
        
        try {
            
            int expected = 9;
            int actual  ;   
            System.out.println ("TestUnit9: The length of the shortest route" +
                    " (in terms of distance to travel) from B to B" +
                    " EXPECTED result is:" + expected);
            actual  = search.ShortestLength("B", "B") ;
            
            assertEquals(expected, actual) ;
            
            System.out.println( "\nGot ACTUAL Length of shortest route B to B " + actual +
            "\nTestUnit9: completed successfully.");
            
        } catch (AssertionError e)
        {
            //System.out.println("No such route");
            e.printStackTrace() ;
        }
    }
    
    @Test
    public void TestUnit10() throws Exception
    {
        graph = new Graph (town) ;
        for (String e : edges)
        {
            String s = e.substring(0,1) ;
            String end = e.substring(1,2) ;
            int weight = Integer.parseInt(e.substring(2,3)) ;
            graph.addEdge(s, end, weight) ;
        }
        search = new Search(graph) ;
        
        try {
            
            int expected = 7;
            int actual  ;   
            System.out.println ("TestUnit10: The number of different routes from C to C with " +
                    "a distance of less than 30 " +
                    "EXPECTED result is:" + expected);
            actual  = search.getRoute("C", "C", 30) ;
            
            assertEquals(expected, actual) ;
            
            System.out.println( "\nGot ACTUAL  number of different routes from C to C <30 is " + actual +
            "\nTestUnit10: completed successfully.");
            
        } catch (AssertionError e)
        {
            //System.out.println("No such route");
            e.printStackTrace() ;
        }
    }
    public static void main(String[] args) 
    {
        org.junit.runner.JUnitCore.main("TestCase") ;   
    }

}