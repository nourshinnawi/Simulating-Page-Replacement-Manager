//Main

package project.pkg2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Project2 {
       
    public static void main(String[] args) {
        
        //Create arraylists for test averages of each algorithm
        ArrayList<Integer> FIFO = new ArrayList<>();
        ArrayList<Integer> LRU = new ArrayList<>();
        ArrayList<Integer> Optimal = new ArrayList<>();
        //Variable for total amount of tests
        final float testCases = 50;
        
        //For each test
        for (int i = 0; i < testCases; i++)
        {
            String referenceString = "";
            //For reference strings of length 30
            for(int j = 0; j < 30; j++) 
            {
                //Generate random string
                int random = (int)(Math.random()*8);
                referenceString += Integer.toString(random);
            }
            //For NumberOfPageFrame (3, 4, 5 and 6) page frames
            for(int k = 3; k < 7; k++) 
            {
                //Create ReferenceString.txt file for each 
                //page frame size and write to it
                try(Writer writer = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream
                                ("ReferenceString" + k + ".txt"), "utf-8"))){
                    writer.write("NumberOfPageFrame Value:\n" + 
                                   k + "\nReference String:\n");
                    writer.write(referenceString);
                }catch(Exception e){}
            }
            //Runs the algorithms
            run(FIFO, LRU, Optimal);
        }
        //Prints the results
        print(FIFO, LRU, Optimal, testCases);
    }
    //Runs the algorithms
    private static void run(ArrayList<Integer> FIFO, 
            ArrayList<Integer> LRU, ArrayList<Integer> Optimal)
    {
        for(int i = 3; i < 7; i++)
        {
            //Create objects to be used
            FIFO firstInFirstOut = new FIFO();
            LRU leastRecentlyUsed = new LRU();
            Optimal optimal = new Optimal();
            
            //Create file for each page frame size
            File file = new File("ReferenceString" + i + ".txt");
            
            //For First In First Out Algorithm
            System.out.println("---------------------------------------------");
            System.out.println("First In First Out Algorithm"
                    + " Page Frame Size " + (i));
            //Call algorithm for page faults
            int fifoFaults = firstInFirstOut.algorithm(file);
            //Add page faults to list
            FIFO.add(fifoFaults);
            System.out.println();
            
            //For Least Recently Used Algorithm
            System.out.println("---------------------------------------------");
            System.out.println("Least Recently Used Algorithm"
                    + " Page Frame Size " + (i));
            //Call algorithm for page faults
            int lruFaults = leastRecentlyUsed.algorithm(file);
            //Add page faults to list
            LRU.add(lruFaults);
            System.out.println();
            
            //For Optimal Algorithm
            System.out.println("---------------------------------------------");
            System.out.println("Optimal Algorithm Page Frame Size " + (i));
            //Call algorithm for page faults
            int optimalFaults = optimal.algorithm(file);
            //Add page faults to list
            Optimal.add(optimalFaults);
            System.out.println();
        }
    }
    //Prints the output of each algorithm
    private static void print(ArrayList<Integer> FIFO, 
            ArrayList<Integer> LRU, ArrayList<Integer> Optimal, float testCases)
    {
        System.out.println("-------------------------------------------------");
        System.out.println();

        //For FIFO algorithm
        //Declare variables for page frame size for FIFO
        int fifoFrame_3 = 0;
        int fifoFrame_4 = 0;
        int fifoFrame_5 = 0;
        int fifoFrame_6 = 0;
        
        //For FIFO tests
        for(int i = 0; i < FIFO.size(); i+=4)
        {
            fifoFrame_3 += FIFO.get(i);
            fifoFrame_4 += FIFO.get(i+1);
            fifoFrame_5 += FIFO.get(i+2);
            fifoFrame_6 += FIFO.get(i+3);
        }
        //Output page fault average for each page frame size
        System.out.println("FIFO page fault average for frame size 3: "
                + fifoFrame_3 / testCases);
        System.out.println("FIFO page fault average for frame size 4: "
                + fifoFrame_4 / testCases);
        System.out.println("FIFO page fault average for frame size 5: " 
                + fifoFrame_5 / testCases);
        System.out.println("FIFO page fault average for frame size 6: "
                + fifoFrame_6 / testCases);
        System.out.println();
        
        //For LRU algorithm
        //Declare variables for page frame size for LRU
        int lruFrame_3 = 0;
        int lruFrame_4 = 0;
        int lruFrame_5 = 0;
        int lruFrame_6 = 0;
        
        //For LRU tests
        for(int i = 0; i < LRU.size(); i+=4)
        {
            lruFrame_3 += LRU.get(i);
            lruFrame_4 += LRU.get(i+1);
            lruFrame_5 += LRU.get(i+2);
            lruFrame_6 += LRU.get(i+3);
        }
        //Output page fault average for each page frame size
        System.out.println("LRU page fault average for frame size 3: "
                + lruFrame_3 / testCases);
        System.out.println("LRU page fault average for frame size 4: "
                + lruFrame_4 / testCases);
        System.out.println("LRU page fault average for frame size 5: " 
                + lruFrame_5 / testCases);
        System.out.println("LRU page fault average for frame size 6: "
                + lruFrame_6 / testCases);
        System.out.println();
        
        //For Optimal algorithm
        //Declare variables for page frame size for Optimal
        int optimalFrame_3 = 0;
        int optimalFrame_4 = 0;
        int optimalFrame_5 = 0;
        int optimalFrame_6 = 0;
        
        //For Optimal tests
        for(int i = 0; i < Optimal.size(); i+=4) 
        {
            optimalFrame_3 += Optimal.get(i);
            optimalFrame_4 += Optimal.get(i+1);
            optimalFrame_5 += Optimal.get(i+2);
            optimalFrame_6 += Optimal.get(i+3);
        }
        //Output page fault average for each page frame size
        System.out.println("Optimal page fault average for frame size 3: "
                + optimalFrame_3 / testCases);
        System.out.println("Optimal page fault average for frame size 4: "
                + optimalFrame_4 / testCases);
        System.out.println("Optimal page fault average for frame size 5: " 
                + optimalFrame_5 / testCases);
        System.out.println("Optimal page fault average for frame size 6: " 
                + optimalFrame_6 / testCases);
        System.out.println();
    }
}
