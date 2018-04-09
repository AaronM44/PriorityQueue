package queuemanager;

public class HeapPriorityQueue<T> implements PriorityQueue<T>{

    private final Object[] storage;

    private final int capacity;

    private int tailIndex;

    public HeapPriorityQueue (int size) {

        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
    }

    @Override
    public T head() throws QueueUnderflowException{

        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            return ((PriorityItem<T>) storage[0]).getItem();
        }
    }

    @Override
    public void add(T item, int priority) throws QueueOverflowException{

        tailIndex += 1;
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex -= 1;
            throw new QueueOverflowException();
        } else {

            storage[tailIndex] = new PriorityItem<>(item, priority);

            siftUp();
        }
    }

    public void remove() throws QueueUnderflowException {

        if (isEmpty()) {

            throw new QueueUnderflowException();
        }
        else {

            for (int i = 0; i < tailIndex; i++) {
                storage[i] = storage[i + 1];
            }
            tailIndex = tailIndex - 1;

            siftDown();
        }
    }

    @Override
    public boolean isEmpty() {
        return tailIndex < 0;
    }

    private void siftUp() {

        int itemIndex = tailIndex;

        while (itemIndex > 0) {

            int parentIndex = (itemIndex - 1) / 2;
            Object item = storage[itemIndex];
            Object parent = storage[parentIndex];

            if (((PriorityItem<T>) item).getPriority() > ((PriorityItem<T>) parent).getPriority()) {

                swapValues(itemIndex, parentIndex);

                itemIndex = parentIndex;
            }
            else {

                break;
            }

        }
    }

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

    private void siftDown() {

        int itemIndex = 0;
        int leftNode = 2 * itemIndex + 1;

        while (leftNode < tailIndex) {

            int max = leftNode, rightNode = leftNode + 1;

            // there is a right child
            if (rightNode < tailIndex) {

                if (((PriorityItem<T>) storage[rightNode]).getPriority() > ((PriorityItem<T>) storage[leftNode]).getPriority()) {

                    max++;
                }
            }

            if (((PriorityItem<T>) storage[itemIndex]).getPriority() > ((PriorityItem<T>) storage[max]).getPriority()) {

                swapValues(itemIndex, max);

                itemIndex = max;

                leftNode = 2 * itemIndex + 1;
            }
            else {
                break;
            }
        }
    }

    public void swapValues(int indexOne, int indexTwo) {

        Object temp = storage[indexOne];
        storage[indexOne] = storage[indexTwo];
        storage[indexTwo] = temp;
    }

}
