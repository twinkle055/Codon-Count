
/**
 * Write a description of DNAAndCodon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class DNAAndCodon {
    
    private HashMap<String, Integer> map;
    
    public DNAAndCodon(){
        map = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        map.clear();
        int l = dna.length() - 3;
        for(int i=start; i<l; i+=3)
        {
            String s = dna.substring(i, i+3);
            if(map.containsKey(s))
                map.put(s, map.get(s)+1);
            else
                map.put(s, 1);
        }
    }
    
    public String getMostCommonCodon(){
        int max = 0;
        String mcc = null;
        for(String s : map.keySet())
        {
            if(map.get(s) > max)
            {
                max = map.get(s);
                mcc = s;
            }
        }
        return mcc;
    }
    
    public void printCodonCounts(int start, int end){
        for(String s : map.keySet())
        {
            if(map.get(s) >= start && map.get(s) <= end)
            {
                System.out.println(s + "\t" + map.get(s));
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase();
        for(int i=0; i<3; i++)
        {
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " results in " + map.size() + " unique codons");
            String mcc = getMostCommonCodon();
            System.out.println("and most common codon is " + mcc + " with count " + map.get(mcc));
            System.out.println("Counts of codons between 1 and 5 inclusive are: ");
            printCodonCounts(7, 10);
        }
    }
}