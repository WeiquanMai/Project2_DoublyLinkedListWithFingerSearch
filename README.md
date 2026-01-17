# Project2_DoublyLinkedListWithFingerSearch
October 8, 2025 CSC-242 Data Structures and Algorithms
In this project you will contemplate an interesting form of the list ADT called a Doubly
Linked. This is a data structure that is similar to a linked list but, maintains not only a
pointer to the next node in the list (the successor) but the previous node in the list (the
predecessor). Once you implement your double linked list, DoubleLinkedList, (see phase I)
you will enhance it to increase the ef@iciency of getNodeAt (see phase II).

Phase I: Double Linked List
In this phase of the project you will implement a doubly linked list as described above.
As a hint I would start from the Node.java and LinkedList.java @iles from class and modify the
files.

Phase II: Finger Searching
As we saw in class, searching through a linked list is a time-consuming process. To speed
up search we can use 5inger searching. In a @inger search, an extra reference called the
finger (finger attribute below) is maintained in such a way that it refers to the node
resulting from the previous successful search in the chain. To successfully implement
finger searching you will need to implement a Finger class and update the DoubleLinkedList
class.
