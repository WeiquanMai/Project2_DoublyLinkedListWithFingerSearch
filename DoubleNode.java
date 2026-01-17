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

public class DoubleNode <T>{
    private T item;
    private DoubleNode<T> next;
    private DoubleNode<T> prev;

    // No-args constructor
    public DoubleNode(){

    }

    // Overloaded Constructor with one parameter (item)
    public DoubleNode(T item){
        this(item, null,null);
    }

    // Overloaded Constructor with three parameters (item, previous node, and next node)
    public DoubleNode(T item, DoubleNode<T> prev, DoubleNode<T> next){
        this.item = item;
        this.prev = prev;
        this.next = next;
    }

    // Mutators and accessors
    public void setItem(T item){
        this.item = item;
    }
    public void setNext(DoubleNode<T> next){
        this.next = next;
    }
    public void setPrev(DoubleNode<T> prev){
        this.prev = prev;
    }
    public T getItem(){
        return item;
    }
    public DoubleNode<T> getNext(){
        return next;
    }
    public DoubleNode<T> getPrev(){
        return prev;
    }
}
