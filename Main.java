/**
    CSC-242 Project 2: Double Linked List
    Student: Weiquan Mai
    Date: October 7, 2025
    Description: Program utilizes a csv command to control operations of a double linked list.
    Commands:
        insert(index, value)- inserts the value at specified index
        remove(index)- removes specified index
        get(index)- returns value at specified index
        print(0 || 1)- print 0 returns the list, and print 1 returns the sorted list
 */
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main{
    public static void main(String[] args){
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        // Try with resources
        try(Scanner scanner = new Scanner(new File(".idea/commands.csv"))){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();

                // Try block for commands and arguments
                try{
                    // Parse line using comma as regex. words[0] will be the command, and words[1] will be value
                    String[] words = line.split(",");
                    int position = Integer.parseInt(words[1]);

                    // Switch cases to handle commands from csv file
                    switch(words[0]){
                        case "create":
                            System.out.println("create");
                            break;

                        // Calls insert method from DoubleLinkedList
                        case "insert":
                            int value = Integer.parseInt(words[2]);
                            if(linkedList.insert(position,value)){
                                System.out.println("insert: " + value);
                            }
                            else{
                                System.out.println("insert: failed" );
                            }
                            break;
                        // Calls remove method from DoubleLinkedList
                        case("remove"):
                            System.out.print("remove: ");
                            System.out.println(linkedList.remove(position));
                            break;
                        // Calls getEntry method from DoubleLinkedList
                        case("get"):
                            System.out.print("get: ");
                            System.out.println(linkedList.getEntry(position));
                            break;
                        case("print"):
                            // Prints after sorting if argument is 1
                            if(position == 1){
                                Comparator<Integer> compare = Comparator.naturalOrder();
                                linkedList.sort(compare);
                                System.out.println(linkedList);
                                break;
                            }
                            // Prints in natural order if argument is 0
                            else if (position == 0){
                                System.out.println(linkedList);
                                break;
                            }
                            else{
                                System.out.println("Invalid command");
                                break;
                            }
                        default:
                            System.out.println("Unknown Command");
                    }
                } catch(IndexOutOfBoundsException e){
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
        }
    }
}
