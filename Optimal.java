//Optimal Algorithm

package project.pkg2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Optimal {
    //Declare variables
    private int faults = 0;
    private int size = 0;
    private String referenceString = "";
    private final HashMap<Integer, Integer> map = new HashMap<>();
    private final ArrayList<Integer> pageFrames = new ArrayList<>();
    private final HashSet<Integer> set = new HashSet<>();

    //Algorithm for Optimal
    public int algorithm(File file)
    { 
        try{
            //Read in from file
            try (Scanner input = new Scanner(file)) {
                while(input.hasNextLine())
                {
                    input.nextLine();
                    size = Integer.parseInt(input.nextLine());
                    input.nextLine();
                    referenceString = input.nextLine();
                }
            } 
        }catch(FileNotFoundException | NumberFormatException e){}

        //Iterate through each reference string
        for(int i = 0; i < referenceString.length(); i++) 
        {
            int current = Character.getNumericValue(referenceString.charAt(i));
            //If the current page is in the set
            if(set.contains(current))
            {
                //Output page frames
                System.out.print(current + " " + pageFrames.toString());
                for(int j = 0; j < size - pageFrames.size(); j++)
                {
                    System.out.print("[ ]");
                }
                System.out.println("");
            }
            else{
                //Increment page faults
                faults++;
                //If the size of the set is equal to the page size
                if(set.size() == size)
                {
                    //Declare variable to replace page
                    int oldPage = optimalDistance(i,referenceString);
                    //Remove the page to replace from the set
                    set.remove(oldPage);
                    //Add the current page to the set
                    set.add(current);
                    //Get index of the old page and declare variable
                    int oldIndex = map.get(oldPage);
                    //Remove the page to replace
                    map.remove(oldPage);
                    //Set the list with the old index and current page
                    pageFrames.set(oldIndex, current);
                    //Set the current page to associate with the old index
                    map.put(current, oldIndex);
                }
                else{
                    //Add the current page to the list, set, and map
                    pageFrames.add(current);
                    set.add(current);
                    map.put(current,  pageFrames.indexOf(current));
                }
                //Output the page frames
                System.out.print(current + " " + pageFrames.toString());
                for(int j = 0; j < size - pageFrames.size(); j++) 
                {
                    System.out.print("[ ]");
                }
                //Mark page faults
                System.out.println(" X");
            }
        }
        //Output total page faults
        System.out.println("Page faults: " + faults);
        return faults;
    }
    
    //Calculates the optimal distance 
    private int optimalDistance(int index,String referenceString)
    {
        int frameValue;
        int max = Integer.MIN_VALUE;
        int oldPage = -1;
        boolean found;
        
        for(int i = 0; i < pageFrames.size(); i++) 
        {
            //Set the found page equal to false
            found = false;
            //Get index of page frames for frame value
            frameValue = pageFrames.get(i);
            for(int j = index + 1; j < referenceString.length(); j++)
            {
                //If the current page frame value is equal to the index
                if(frameValue == Character.getNumericValue(referenceString.charAt(j))) 
                {
                    //Set found page equal to false
                    found = true;
                    //Declare variable for distance
                    int distance = j - index;
                    //If the ditance is greater than the max distance
                    if (distance > max)
                    {
                        //Set the max distance equal to distance
                        max = distance;
                        //Set the old page equal to the current page frame value
                        oldPage = frameValue;
                    }
                    j = 1000;
                }
            }
            //If page is not found return the current page frame value
            if(!found)
            {
                return frameValue;
            }
        }
        return oldPage;
    }
}
