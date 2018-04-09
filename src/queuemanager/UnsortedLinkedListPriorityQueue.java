package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using an unsorted linked list.
 *
 * Data is stored in the order it comes into the queue
 *
 * @param <T> The type of things being stored.
 */
public class UnsortedLinkedListPriorityQueue<T> extends LinkedList implements PriorityQueue<T> {

    /**
     * Adds an item to the queue
     *
     * @param item
     * @param priority
     */
    @Override
    public void add(T item, int priority) {

        PriorityItem<T> newItem = new PriorityItem(item, priority);

        this.add(newItem);
    }

    /**
     * Return the highest priority item in the queue
     *
     * Requires searching through the list
     *
     * @return
     * @throws QueueUnderflowException
     */
    @Override
    public T head() throws QueueUnderflowException {

        Node currentNode = firstNode;
        PriorityItem<T> item = null;
        int highestPriority = -1;

        if (isEmpty()) {

            throw new QueueUnderflowException();
        }
        else {
            // loop through all nodes
            while (currentNode != null) {

                // compare the current nodes priority to the highest recorded
                if (((PriorityItem<T>) currentNode.getItem()).getPriority() > highestPriority) {

                    item = new PriorityItem(((PriorityItem<T>) currentNode.getItem()).getItem(), ((PriorityItem<T>) currentNode.getItem()).getPriority());
                    highestPriority = ((PriorityItem<T>) currentNode.getItem()).getPriority();
                }
                else {

                    currentNode = currentNode.next;
                }
            }
        }

        return item.getItem();
    }

    /**
     * Removes the highest priority item in the queue
     *
     * @throws QueueUnderflowException
     */
    @Override
    public void remove() throws QueueUnderflowException {

        Node previousNode = firstNode;
        Node currentNode = null;
        T head = head();

        if (isEmpty()) {

            throw new QueueUnderflowException();
        }
        else {

            // set current node to second node in the list
            // during testing if this was set earlier it resulted in a null pointer exception
            currentNode = firstNode.next;

            // head is found in the first node
            if (((PriorityItem<T>) firstNode.getItem()).getItem() == head) {

                firstNode = currentNode;
            }
            else {
                // traverse list until the head is found
                while (((PriorityItem<T>) currentNode.getItem()).getItem() != head) {

                    previousNode = previousNode.next;
                    currentNode = currentNode.next;
                }
                // head is the last node in the list
                if (currentNode.next == null) {

                    previousNode.next = null;
                }
                // head is found somewhere in the middle of the list
                else {

                    previousNode.next = currentNode.next;
                }
            }
        }
    }
}
