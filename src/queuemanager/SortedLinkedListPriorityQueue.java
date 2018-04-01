package queuemanager;

public class SortedLinkedListPriorityQueue<T> extends LinkedList implements PriorityQueue<T> {

    @Override
    public T head() throws QueueUnderflowException {

        if (isEmpty()) {

            throw new QueueUnderflowException();

        } else {

            return ((PriorityItem<T>) firstNode.getItem()).getItem();
        }
    }

    // add item by priority
    @Override
    public void add(T item, int priority) {

        Node currentNode = firstNode;
        PriorityItem newItem = new PriorityItem(item, priority);
        Node newNode = new Node(newItem);

        if (!isEmpty()) {

            // check if new item is higher or the same as the first item
            // if true, add new item to the front of the queue
            if (priority >= ((PriorityItem<T>) currentNode.getItem()).getPriority()) {

                firstNode = newNode;
                newNode.next = currentNode;
                size++;
            }

            // iterate through list as long as the priority of the new item is less than
            // that of the current item
            while (priority < ((PriorityItem<T>) currentNode.getItem()).getPriority()) {

                // if we reach the end of the queue add the new item to the end
                if (currentNode.next == null) {

                    currentNode.next = newNode;
                    size++;
                }
                // if the new item is higher or equal in priority to the next item
                // add new item at current position and shuffle the next one along
                else if (priority >= ((PriorityItem<T>) currentNode.next.getItem()).getPriority()) {

                    newNode.next = currentNode.next;
                    currentNode.next = newNode;
                    size++;
                }

                currentNode = currentNode.next;
            }
        }
        // if the list is empty add new item as the first node
        else {

            firstNode = newNode;
            size++;
        }
    }
}
