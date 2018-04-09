package queuemanager;

/**
 * Implementation of the PriorityQueue ADT using an unsorted array.
 *
 * Data is stored in the order it comes into the queue
 *
 * @param <T> The type of things being stored.
 */
public class UnsortedArrayPriorityQueue<T> implements PriorityQueue<T> {

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
    public UnsortedArrayPriorityQueue(int size) {
        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
    }

    /**
     * Return the highest priority item
     *
     * @return
     * @throws QueueUnderflowException
     */
    @Override
    public T head() throws QueueUnderflowException {

        int max = 0;
        int index = 0;

        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            // loop through all items in queue
            for (int i = 0; i < tailIndex + 1; i++) {
                // if the priority of the current item is higher than that stored
                // record the position of the current item
                if (((PriorityItem<T>) storage[i]).getPriority() > max) {

                    max = ((PriorityItem<T>) storage[i]).getPriority();
                    index = i;
                }
            }

            return ((PriorityItem<T>) storage[index]).getItem();
        }
    }

    /**
     * Adds an item to the queue
     *
     * @param item
     * @param priority
     * @throws QueueOverflowException
     */
    @Override
    public void add(T item, int priority) throws QueueOverflowException {

        tailIndex +=1;

        if (tailIndex >= capacity) {

            tailIndex -= 1;
            throw new QueueOverflowException();
        } else {

            storage[tailIndex] = new PriorityItem<>(item, priority);
        }
    }

    /**
     * Remove the highest priority ite from the queue
     *
     * @throws QueueUnderflowException
     */
    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {

            int max = 0;
            int index = 0;

            // loop // through all items in the queue
            for (int i = 0; i < tailIndex + 1; i++) {

                // if the priority of the current item is higher than that stored
                // record the position of the current item
                if (((PriorityItem<T>) storage[i]).getPriority() > max) {

                    max = ((PriorityItem<T>) storage[i]).getPriority();
                    index = i;
                }
            }
            // start at the position of the highest priority item and loop through those after it
            // replacing each value with that of the next item
            for (int i = index; i < tailIndex + 1; i++) {

                storage[i] = storage[i + 1];
            }

            // decrement tail index
            tailIndex--;
        }
    }

    /**
     * Check if the queue is empty
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return tailIndex < 0;
    }

    /**
     * Method for printing the queue
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
}
