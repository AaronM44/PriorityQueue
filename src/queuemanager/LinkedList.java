package queuemanager;

/**
 * Implementation of a Linked List
 */
public class LinkedList<T> {

    public Node firstNode;

    public LinkedList() {

        firstNode = null;
    }

    // check if the list is empty
    public boolean isEmpty() {

        return(firstNode == null);
    }

    // add an item to the list
    public <T> void add(T item) {

        Node newNode = new Node(item);

        newNode.next = firstNode;

        firstNode = newNode;
    }

    // remove an item from the list
    public void remove() throws QueueUnderflowException{

        if(isEmpty()) {

            throw new QueueUnderflowException();
        }
        else {

            firstNode = firstNode.next;
        }
    }

    @Override
    public String toString() {

        Node node = firstNode;
        String string = "";

        while (node != null) {

            string += (node.toString());

            if (node.next != null) {

                string += ", ";
            }

            node = node.next;
        }

        return string;
    }
}
