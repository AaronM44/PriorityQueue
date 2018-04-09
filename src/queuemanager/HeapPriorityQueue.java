package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using a max heap for storage.
 *
 * Highest priority item should always be the root node
 * Child node priority should always be less than or equal to their parent node
 *
 * @param <T> The type of things being stored.
 */
public class HeapPriorityQueue<T> implements PriorityQueue<T>{

    /**
     * Where the data is actually stored.
     */
    private final Object[] storage;

    /**
     * The size of the storage array.
     */
    private final int capacity;

    /**
     * The index of the last item stored.
     *
     * This is equal to the item count minus one.
     */
    private int tailIndex;

    /**
     * Create a new empty queue of the given size.
     *
     * @param size
     */
    public HeapPriorityQueue (int size) {

        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
    }

    /**
     * Method for finding the highest priority item in the queue
     *
     * @return
     * @throws QueueUnderflowException
     */
    @Override
    public T head() throws QueueUnderflowException{

        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            return ((PriorityItem<T>) storage[0]).getItem();
        }
    }

    /**
     * Method for adding an item to the queue
     *
     * @param item
     * @param priority
     * @throws QueueOverflowException
     */
    @Override
    public void add(T item, int priority) throws QueueOverflowException{

        tailIndex += 1;
        
        if (tailIndex >= capacity) {
            tailIndex -= 1;
            throw new QueueOverflowException();
        } else {

            // add the item to the end of the queue, then use the shift up method
            // to sort the item into its correct position
            storage[tailIndex] = new PriorityItem<>(item, priority);

            this.shiftUp();
        }
    }

    /**
     * Method for removing the highest priority item from the queue
     *
     * @throws QueueUnderflowException
     */
    @Override
    public void remove() throws QueueUnderflowException {

        if (isEmpty()) {

            throw new QueueUnderflowException();
        }
        else {

            // move the last item in the array to the root node position
            storage[0] = storage[tailIndex];

            tailIndex--;

            shiftDown();
        }
    }

    /**
     * Method to check if the queue is empty
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return tailIndex < 0;
    }

    /**
     * Method used for printing out the queue
     *
     * @return
     */
    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i <= tailIndex; i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result = result + storage[i];
        }
        result = result + "]";
        return result;
    }

    /**
     * Moves the item at the end of the queue up through the tree until it is in the correct position
     *
     */
    private void shiftUp() {

        int itemIndex = tailIndex;

        // loop until reaching the root
        while (itemIndex > 0) {

            // find the parent node
            int parentIndex = (itemIndex - 1) / 2;

            Object item = storage[itemIndex];
            Object parent = storage[parentIndex];

            // if the node we are currently looking at is greater than its parent, swap them
            if (((PriorityItem<T>) item).getPriority() > ((PriorityItem<T>) parent).getPriority()) {

                swapValues(itemIndex, parentIndex);

                // move up a level
                itemIndex = parentIndex;
            }
            else {

                break;
            }

        }
    }

    /**
     * Method for sorting a node down from the root into its correct position
     *
     */
    private void shiftDown() {

        int itemIndex = 0;
        // first child is always at 2 * n + 1
        int leftNode = 2 * itemIndex + 1;

        while (leftNode < tailIndex) {

            int max = leftNode;
            // second child at 2n + 2 or next to the left node
            int rightNode = leftNode + 1;

            // check if the parent node has a second child node
            if (rightNode < tailIndex) {

                // compare right and left nodes, if right node is greater switch max to it's value
                if (((PriorityItem<T>) storage[rightNode]).getPriority() > ((PriorityItem<T>) storage[leftNode]).getPriority()) {

                    max++;
                }
            }

            // check if the item we are at is less than what we have for the max, if so swap the nodes
            if (((PriorityItem<T>) storage[itemIndex]).getPriority() < ((PriorityItem<T>) storage[max]).getPriority()) {

                swapValues(itemIndex, max);

                // reset for next pass
                itemIndex = max;
                leftNode = 2 * itemIndex + 1;
            }
            else {
                break;
            }
        }
    }

    /**
     * Method for swapping the data at two different indexes
     *
     * @param indexOne
     * @param indexTwo
     */
    private void swapValues(int indexOne, int indexTwo) {

        Object temp = storage[indexOne];
        storage[indexOne] = storage[indexTwo];
        storage[indexTwo] = temp;
    }

}
