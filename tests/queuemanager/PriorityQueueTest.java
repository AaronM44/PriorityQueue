package queuemanager;

import org.junit.Before;

import static org.junit.Assert.*;

public class PriorityQueueTest extends UnsortedArrayPriorityQueueTest{

    @Before
    @Override
    public void setUp() {

        instance = new UnsortedArrayPriorityQueue<>(8);
    }
}