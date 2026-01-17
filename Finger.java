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

public class Finger <T>{
    private DoubleNode<T> node;
    private int idx;

    // Overloaded Constructor with two parameters (index and node)
    public Finger(int idx, DoubleNode<T> node){
        this.idx = idx;
        this.node = node;
    }

    // Mutators and Accessors
    public int getIndex(){
        return idx;
    }
    public void setIndex(int idx){
        this.idx = idx;
    }
    public DoubleNode<T> getNode(){
        return node;
    }
    public void setNode(DoubleNode<T> node){
        this.node = node;
    }

}
