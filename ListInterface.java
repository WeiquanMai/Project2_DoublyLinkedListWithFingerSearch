/******************************************
 * @Author      : Ali Azhari
 * @Date        : Created On : Feb 25 2025
 * @File        : MyLinkedList.java
 * @Description : TODO
 *******************************************/

public interface ListInterface<T>
{
    /**
     * Tests if the list is empty.
     * @return true if the list is empty; otherwise, false.
     */
    public boolean isEmpty();

    /**
     * Returns the length of the list.
     * @return a positive integer representing the length of the list.
     */
    public int getLength();

    /**
     * Inserts element entry at position position.
     * @param position a number between 1 and list length inclusive.
     * @param entry the element to insert at {@code position} in the list.
     * @return true if successful; otherwise, false.
     */
    public boolean insert(int position, T entry);

    /**
     * Removes the element at position position.
     * @param position a number between 1 and list length inclusive.
     * @return T if successful; otherwise, null is returned.
     */
    public T remove(int position);

    /**
     * Removes all items from the list.
     */
    public void clear();

    /**
     * Gets the item at entry position, provided the position is valid.
     * @param position a number between 1 and list length inclusive.
     * @return the item found at {@code position}.
     * @throws IndexOutOfBoundsException if the position is unknown
     */
    public T getEntry(int position) throws IndexOutOfBoundsException;

    /**
     * Replaces the item at position position with entry entry. The
     * replaced entry is returned.
     * @param position a number between 1 and list length inclusive.
     * @param entry to repelace the entry at {@code position} with.
     * @return the item that was replaced.
     * @throws IndexOutOfBoundsException if the position is unknown
     */
    public T replace(int position, T entry) throws IndexOutOfBoundsException;

    /**
     * Returns the array form of the list data.
     * @return an array of list data ordered the same as the list.
     */
    public Object[] toArray();
}
