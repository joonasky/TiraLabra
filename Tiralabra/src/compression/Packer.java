/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compression;

/**
 * Pakkaa sille annetun merkkijonon ja tekee siitä binääriluvun.
 * @author joonaskylliainen
 */

 // import java.util.HashMap;
import datastructures.Node;
import datastructures.Paketti;
import datastructures.Tree;
import filehandling.MyWriter;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Packer {
    
    
    /**
     * Pakkaajan päämetodi, joka pakkaa sille annetun tiedoston.
     * @param word syötetty merkkijono
     * @return pakkaus
     */
    public Paketti pack( String word ) {
        
        int[] list = count(word);
        PriorityQueue<Node> que = makeQueue(list);
        Tree tree = makeTree(que);
        String pakkaus = compress(word, tree);
        Paketti paketti = new Paketti(tree,pakkaus);
        MyWriter writer = new MyWriter();
        System.out.println("Anna tiedostolle nimi: ");
        Scanner in = new Scanner(System.in);
        writer.write(paketti, in.nextLine());
       
        return paketti;
        
    }
    

    /**
     * laskee eri merkkien määrän tekstissä ja luo listan, joka sisältää Node olioita.
     * Olioilla on merkki ja esiintymistaajuus.
     * @param s käyttäjän antama syöte
     * @return dynaaminen lista joissa jokaisen merkki esiintymistaajuiksineen.
     */
    private static int[] count(String s) {
        
        char[] input = s.toCharArray();
        int[] freq = new int[256];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }
        return freq;
    }
    /**
     * tekee keon listasta, jossa on solmut esiintymistaajuuksineen.
     * @param list
     * @return keko
     */
    private static PriorityQueue<Node> makeQueue(int[] list) {
        PriorityQueue<Node> que = new PriorityQueue<Node>(256);
        for (char i = 0; i < 256;i++) {
            if (list[i] > 0) {
                que.add(new Node(i, list[i]));
            }           
        }

        return que;
    }
    /**
     * Tekee sille annetusta keosta Huffmann-puun
     * @param que keko
     * @return puu
     */
    private static Tree makeTree(PriorityQueue<Node> que) {
        while (que.size() > 1) {
            Node a = que.poll();
            Node b = que.poll();
            que.add(makeNewNode(a,b));
        }    
        Node root = que.poll();
        Tree tree = new Tree(root);
        return tree;
    }
    
    /**
     * tekee uuden solmun, eli Node-olion kekoa varten.
     * Solmun taajuus on lapsien taajuuksien summa
     * Käytetään silloin kun keosta tehdään puuta.
     * @param left solmun vasen lapsi
     * @param right solmun oikea lapsi
     * @return uusi solmu
     */
    private static Node makeNewNode(Node left, Node right) {
        char c = '*';
        Node nod = new Node(c, left.getFrequency() + right.getFrequency());
        nod.setLeft(left);
        nod.setRight(right);
        return nod;
    }
    
    /**
     * tekee puusta ja annetusta merkkijonosta pakatun version.
     * @param s merkkijono
     * @param tree Huffmann-puu
     * @return pakkaus
     */
    private static String compress(String s, Tree tree) {
        String pakkaus = "";
        char[] ca = s.toCharArray();
        String[] list = tree.makeDirectory();       
        for (int i = 0; i < s.length(); i++) {
            pakkaus += list[ca[i]];    
        }    
        return pakkaus;
    }

    
}
