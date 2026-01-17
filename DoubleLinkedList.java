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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DoubleLinkedList <T> implements ListInterface<T>{

    private DoubleNode<T> head;
    private int itemCount;
    private Finger<T>[] finger;

    // No-args constructor
    @SuppressWarnings("unchecked")
    public DoubleLinkedList(){
        head = null;
        itemCount = 0;
        this.finger = new Finger[2];
    }

    /**
     * Tests if the list is empty
     * @return true if the list is empty; otherwise, false
     */
    @Override
    public boolean isEmpty() {
        return itemCount == 0;
    }

    /**
     * Returns length of the list
     * @return positive integer representing length of the list
     */
    @Override
    public int getLength() {
        return itemCount;
    }

    /**
     * Inserts the entry into position if position is valid
     * @param position a number between 1 and (length of list + 1) inclusive
     * @param entry the item to insert
     * @return true if successful
     * @throws IndexOutOfBoundsException if position is unknown
     */
    @Override
    public boolean insert(int position, T entry){
        validateInsert(position);
        DoubleNode<T> newNode = new DoubleNode<>(entry);

        // If inserting at head
        if(position == 1){
            newNode.setNext(head);
            newNode.setPrev(null);
            if(head != null){
                head.setPrev(newNode);
            }
            head = newNode;
        }
        // Else if inserting at end
        else if(position == itemCount + 1){
            DoubleNode<T> previous = getNodeAt(position - 1);
            previous.setNext(newNode);
            newNode.setPrev(previous);
            newNode.setNext(null);
        }
        // Else inserting in middle
        else{
            DoubleNode<T> previous = getNodeAt(position - 1);
            DoubleNode<T> current = getNodeAt(position);
            newNode.setNext(current);
            newNode.setPrev(previous);
            current.setPrev(newNode);
            previous.setNext(newNode);
        }
        itemCount++;
        updateFinger();
        return true;
    }

    /**
     * Removes position from list if position is valid
     * @param position a number between 1 and list length inclusive.
     * @return removed item
     * @throws IndexOutOfBoundsException if position is unknown
     */
    @Override
    public T remove(int position) {
        validatePosition(position);

        // If list only has 1 item
        if(itemCount == 1){
            DoubleNode<T> temp = getNodeAt(position);
            head = null;
            itemCount = 0;
            updateFinger();
            return temp.getItem();
        }
        // Else removing from top
        else if(position == 1 && itemCount > 1){
            DoubleNode<T> temp = head;
            DoubleNode<T> next = temp.getNext();
            next.setPrev(null);
            head = next;
            temp.setNext(null);
            itemCount--;
            updateFinger();
            return temp.getItem();
        }
        // Else if removing last item
        else if(position == itemCount){
            DoubleNode<T> previous = getNodeAt(position - 1);
            DoubleNode<T> temp = previous.getNext();
            previous.setNext(null);
            itemCount--;
            updateFinger();
            return temp.getItem();
        }
        // Else removing in the middle
        else{
            DoubleNode<T> temp = getNodeAt(position);
            DoubleNode<T> previous = temp.getPrev();
            DoubleNode<T> next = temp.getNext();
            previous.setNext(next);
            next.setPrev(previous);
            itemCount--;
            updateFinger();
            return temp.getItem();
        }
    }

    /**
     * Removes all items from the list
     */
    @Override
    public void clear() {
        head = null;
        itemCount = 0;
    }

    /**
     * Returns the item at entry, provided the position is valid
     * @param position a number between 1 and list length inclusive.
     * @return item found at position
     * @throws IndexOutOfBoundsException if position is unknown
     */
    @Override
    public T getEntry(int position){

        // Call validatePosition to check if position is valid
        validatePosition(position);

        if(head == null)
            throw new IndexOutOfBoundsException();
        return getNodeAt(position).getItem();
    }

    /**
     * Replaces item at position with entry. The replaced item is returned.
     * @param position a number between 1 and list length inclusive
     * @param entry item to insert
     * @return replaced item
     * @throws IndexOutOfBoundsException if position is unknown
     */
    @Override
    public T replace(int position, T entry){
        // Call validatePosition to check if position is valid
        validatePosition(position);

        DoubleNode<T> node = getNodeAt(position);
        T item = node.getItem();
        node.setItem(entry);
        return item;
    }

    /**
     * Returns index of key if key is found
     * @param key value to search for
     * @return index where key is found or -1 if not found
     */
    public int getIndexOf(T key){
        if(head == null){
            return -1;
        }

        DoubleNode<T> walker = head;
        int position = 0;
        while (walker != null){
            if(walker.getItem().equals(key)){
                return position;
            }
            position++;
        }
        return -1;
    }

    /**
     * Returns the array form of the list data
     * @return an array of list data ordered the same as the list
     */
    @Override
    public Object[] toArray() {
        if(isEmpty()){
            return null;
        }

        Object[] array = new Object[itemCount];
        DoubleNode<T> walker = head;

        for(int i = 0; i < itemCount; i++){
            array[i] = walker.getItem();
            walker = walker.getNext();
        }
        return array;
    }

    /**
     * Returns the node at a certain position
     * @param position a number between 1 and list length inclusive
     * @return node found at position
     * @throws IndexOutOfBoundsException if position is unknown
     */
    private DoubleNode<T> getNodeAt(int position){
        // Call validatePosition to check if position is valid
        validatePosition(position);

        Finger<T> closest = getClosest(position);
         int closestPosition = closest.getIndex();
         DoubleNode<T> walker = closest.getNode();

        if(position >= closestPosition){
            int numTraversal = position - closestPosition;
            for(int i = 0; i < numTraversal; i++){
                walker = walker.getNext();
            }
        }
        else{
            int numTraversal = closestPosition - position;
            for(int i = 0; i < numTraversal; i++){
                walker = walker.getPrev();
            }
        }

        return walker;
    }

    /**
     * Returns string form of DoubleLinkedList in [position] value format.
     * Returns "Empty List" if list is empty
     * @return DoubleLinkedList in string format
     */
    @Override
    public String toString(){
        if(isEmpty()){
            return "Empty List";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 1; i <= itemCount; i++){
            stringBuilder.append("[").append(i).append("]" ).append(getEntry(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Updates fingers to point at equidistant locations in the list
     */
    private void updateFinger(){
        // Clear fingers
        for(int i = 0; i < finger.length; i++){
            finger[i] = null;
        }
        // If list is empty, we can't create fingers
        if (isEmpty()){
            return;
        }

        // Create fingers based on formula that finger should be Math.log10(itemCount)
        int numFinger = (int)(Math.ceil(Math.log10(getLength())));

        // If numFinger <= 0, then no fingers were created and we can return
        if(numFinger <= 0){
            return;
        }

        // Calculate equidistant location on list
        int equidistant = (getLength() / (numFinger + 1));

        // iterate through from i to numFinger and set fingers at equidistant points on the list
        for(int i = 0; i < numFinger; i++){
            int fingerPosition = equidistant * (i + 1);
            DoubleNode<T> temp = getNodeAt(fingerPosition);
            finger[i] = new Finger<>(fingerPosition,temp);
        }
    }

    /**
     * Returns closest finger to the given index
     * @param idx a number between 1 and list length inclusive
     * @return finger closest to idx
     * @throws IndexOutOfBoundsException if position is unknown
     */
    private Finger<T> getClosest(int idx){
        validatePosition(idx);

        // Set initial closest and min distance as head
        Finger<T>closest = new Finger<>(1, head);
        int minDistance = Math.abs(closest.getIndex() - idx);

        // iterate through finger array and calculate distance from current finger
        for(int i = 0; i < finger.length; i++){
            Finger<T> current = finger[i];

            if(current != null){
                int currentDistance = Math.abs(current.getIndex() - idx);

                // If current distance is smaller than min distance, set current distance as min distance
                if(currentDistance < minDistance){
                    minDistance = currentDistance;
                    closest = current;
                }
            }
        }

        return closest;
    }

    /**
     * Checks to see if position is valid
     * @param position a number between 1 and list length inclusive
     * @throws IndexOutOfBoundsException if position is unknown
     */
    private void validatePosition(int position) throws IndexOutOfBoundsException{
        if(position < 1 || position > itemCount){
            throw new IndexOutOfBoundsException("Position " + position + " is out of bounds");
        }
    }

    /**
     * Checks to see if position is valid for insertion
     * @param position a number between 1 and (list length + 1) inclusive
     * @throws IndexOutOfBoundsException if position is unknown
     */
    private void validateInsert(int position) throws IndexOutOfBoundsException{
        if(position < 1 || position > itemCount + 1){
            throw new IndexOutOfBoundsException("Cannot insert position " + position + " is out of bounds");
        }
    }

    /**
     * Sorts the array in natural order
     * @param comparator
     */
    public void sort(Comparator<T> comparator){
        if(isEmpty()){
            return;
        }

        // Convert linked list into array
        Object[] items = toArray();

        // Create an arrayList, and add items from array into array list
        ArrayList<T> temp = new ArrayList<>();
        for(int i = 0; i < items.length; i++){
            temp.add((T) items[i]);
        }

        // Clear previous unsorted list
        clear();

        // Call collections to sort array list using comparator
        Collections.sort(temp, comparator);

        // Insert sorted array list into linked list
        for(int i = 0; i < temp.size(); i++){
            T item = temp.get(i);
            insert(getLength() + 1, item);
        }
    }
}
