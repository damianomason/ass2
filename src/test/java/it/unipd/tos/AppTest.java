package it.unipd.tos;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigourous Test :-)
     */
    @Test
    public void testApp()
    {
        assertEquals( "This code is covered", App.covered());
    }
}
