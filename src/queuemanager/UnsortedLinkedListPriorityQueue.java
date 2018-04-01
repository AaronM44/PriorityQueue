package queuemanager;

public class UnsortedLinkedListPriorityQueue<T> extends LinkedList implements PriorityQueue<T> {

    @Override
    public void add(T item, int priority) {

        PriorityItem<T> newItem = new PriorityItem(item, priority);

        this.add(newItem);
    }

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

    @Override
    public void remove() throws QueueUnderflowException {

        Node previousNode = firstNode;
        Node currentNode = firstNode.next;
        T head = head();

        if (isEmpty()) {

            throw new QueueUnderflowException();
        }
        else {

            // head is found in the first node
            if (((PriorityItem<T>) firstNode.getItem()).getItem() == head) {

                firstNode = currentNode;
            }
            else {
                // head is somewhere in the middle of the list
                // traverse list until we find the head
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
