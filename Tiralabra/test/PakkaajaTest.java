/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import pakkaaja.Pakkaaja;
import pakkaaja.Node;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joonaskylliainen
 */
public class PakkaajaTest {
    
    String word1;
    String word2;
    String word3;
    
    public PakkaajaTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        word1 = "poks";
        word2 = "j'aime aller sur le bord de l'eau les jeudis ou les jours impairs";
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
//    @Test
//    public void countEasy() {
//        ArrayList<Node> list = count(word1);
//    }
    
}