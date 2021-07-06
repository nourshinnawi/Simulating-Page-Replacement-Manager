//First In First Out (FIFO) Algorithm

package project.pkg2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class FIFO {
    //Declare variables
    private int faults = 0;
    private int size = 0;
    private String referenceString = "";
    private final HashSet<Integer> set = new HashSet<>();
    private final ArrayList<Integer> pageFrames = new ArrayList<>(size);
    
    //Algorithm for First In First Out
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
        }catch (FileNotFoundException | NumberFormatException e) {}
        
        //Variable for previously inserted index
        int previousIndex = 0;
        
        //Iterate through each reference string
        for(int i = 0; i < referenceString.length(); i++)
        {
            int current = Character.getNumericValue(referenceString.charAt(i));
            //If the set has the current page
            if(set.contains(current)) 
            {
                System.out.print(current + " " + pageFrames.toString());
                for(int j = 0; j < size - pageFrames.size(); j++)
                {
                    System.out.print("[ ]");
                }
                //Mark the page faults
                System.out.println(" X");
            }
            else{
                //Increment page faults
                faults++;
                //If the size of the set is equal to page size
                if(set.size() == size)
                {
                    //Remove previously inserted index
                    set.remove(pageFrames.get(previousIndex));
                    //Add the current page
                    set.add(current);
                    //Set the current page in the list
                    pageFrames.set(previousIndex, current);
                    //If the previous inserted index is the last element
                    if(previousIndex == pageFrames.size() - 1)
                    {
                        //Set previous inserted index to 0
                        previousIndex = 0;
                    }
                    else{
                        //Increment previous inserted index
                        previousIndex++;
                    }
                }
                else{
                    //Add the current page to the set
                    set.add(current);
                    //Add the current page to the list
                    pageFrames.add(current);
                }
                //Output page frames
                System.out.print(current + " " + pageFrames.toString());
                for(int j = 0; j < size - pageFrames.size(); j++) 
                {
                    System.out.print("[ ]");
                }
                System.out.println();
            }
        }
        //Output total page faults
        System.out.println("Page faults: " + faults);
        return faults;
    }
}
