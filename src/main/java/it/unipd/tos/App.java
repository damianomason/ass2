package it.unipd.tos;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
    public static String covered() {
        return "This code is covered";
    }
    public static String notCovered() {
        return "This code is not covered";
    }
}
