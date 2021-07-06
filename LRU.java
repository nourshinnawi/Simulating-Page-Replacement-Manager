//Least Recently Used (LRU) Algorithm

package project.pkg2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class LRU {
    
    //Declare variables
    private int faults = 0;
    private int size = 0;
    private String referenceString = "";
    private final LinkedList<Integer> list = new LinkedList<>();
    private final HashSet<Integer> set = new HashSet<>();
    private final LinkedList<Integer> pageFrames = new LinkedList<>();
    
    //Algorithm for Least Recently Used
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
        } catch (FileNotFoundException | NumberFormatException e){}
        
        //Iterate through each reference string
        for(int i = 0; i < referenceString.length(); i++) {
            int current = Character.getNumericValue(referenceString.charAt(i));
            //If the current page is in the set
            if(set.contains(current)) {
                //Add the current page to the end of the list
                list.addLast(current);
                //Declare variable for the value of the current page
                Integer num = current;
                //Remove the value from the list
                list.remove(num);
                //Output page frames
                System.out.print(current + " " + pageFrames.toString());
                for(int j = 0; j < size - pageFrames.size(); j++)
                {
                    System.out.print("[ ]");
                }
                //Mark page faults
                System.out.println(" X");
            }
            else {
                //Increment page faults
                faults++;
                //If the size of the set is equal to the page size
                if(set.size() == size) {
                    //Get the first element in the list
                    //and remove it from the set
                    set.remove(list.getFirst());
                    //Add the current page to the set
                    set.add(current);
                    //Get index position of the first element
                    //in the list and get the current page element
                    //the set them into the list
                    pageFrames.set(pageFrames.indexOf(list.getFirst()), current);
                    //Add the current page to the end of the list
                    list.addLast(current);
                    //Remove the first element from the list
                    list.removeFirst();
                }
                else {
                    //Add the current page to the set and lists
                    set.add(current);
                    pageFrames.add(current);
                    list.add(current);
                }
                //Output the page frames
                System.out.print(current + " " + pageFrames.toString());
                for(int j = 0; j < size - pageFrames.size(); j++) 
                {
                    System.out.print("[ ]");
                }
                System.out.println("");
            }
        }
        //Output total page faults
        System.out.println("Page faults: " + faults);
        return faults;
    }
}
