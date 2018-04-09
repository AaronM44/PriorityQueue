package queuemanager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeapPriorityQueueTest {

    public PriorityQueue<Person> instance = new HeapPriorityQueue<>(8);

    @Before
    public void setUp() throws Exception {
    }

    // test adding a single item to an empty queue
    @Test
    public void testAdd() {

        System.out.println(("test adding a single item to an empty queue"));

        Person person = new Person("person");

        // add items
        try {

            instance.add(person, 5);
        }
        catch (QueueOverflowException e){

            System.out.println("Add operation failed: " + e);
        }

        String expResult = "[(person, 5)]";
        String result = instance.toString();

        assertEquals(expResult, result);
    }

    // test adding 8 items to a queue
    @Test
    public void testAdd2() {

        System.out.println(("test adding 8 items to a queue"));

        Person person = new Person("person");

        // add items
        try {
            for (int i = 1; i <= 8; i++) {
                instance.add(person, i);
            }
        }
        catch (QueueOverflowException e){

            System.out.println("Add operation failed: " + e);
        }

        String expResult = "[(person, 8), (person, 7), (person, 6), (person, 4), (person, 3), (person, 2), (person, 5), (person, 1)]";
        String result = instance.toString();

        assertEquals(expResult, result);
    }

    // test adding an item to a full queue
    @Test
    public void testAddFull() {

        System.out.println(("test adding an item to a full queue"));

        Person person = new Person("person");

        String result = "";

        // add items
        try {
            for (int i = 1; i <= 9; i++) {
                instance.add(person, i);
            }
        }
        catch (QueueOverflowException e){

            result = "Add operation failed: " + e;
        }

        String expResult = "Add operation failed: queuemanager.QueueOverflowException: Queue is full";

        assertEquals(expResult, result);
    }

    // test removing an item from the queue
    @Test
    public void testRemove() {

        System.out.println(("test removing an item from the queue"));

        Person person = new Person("person");

        // add items
        try {
            for (int i = 1; i <= 8; i++) {
                instance.add(person, i);
            }
        }
        catch (QueueOverflowException e){

            System.out.println("Add operation failed: " + e);
        }

        // remove item
        try {
            instance.remove();
        }
        catch (QueueUnderflowException e){

            System.out.println("Remove operation failed: " + e);
        }

        String expResult = "[(person, 7), (person, 4), (person, 6), (person, 1), (person, 3), (person, 2), (person, 5)]";
        String result = instance.toString();

        assertEquals(expResult, result);
    }

    // test removing an item from an empty queue
    @Test
    public void testRemoveEmpty() {

        System.out.println(("test removing an item from an empty queue"));

        String result = "";

        // remove item
        try {
            instance.remove();
        }
        catch (QueueUnderflowException e){

            result = "Remove operation failed: " + e;
        }

        String expResult = "Remove operation failed: queuemanager.QueueUnderflowException: Queue is empty";

        assertEquals(expResult, result);
    }

    // test head method with items in the queue
    @Test
    public void testHead() {

        System.out.println(("test head method with items in the queue"));

        String result = "";

        // add items
        try {

            for (int i = 1; i < 5; i++) {

                Person person = new Person("person" + i);
                instance.add(person, i);
            }
        }
        catch (QueueOverflowException e){

            System.out.println("Add operation failed: " + e);
        }

        try {

            result = instance.head().toString();
        }
        catch (QueueUnderflowException e){

            System.out.println("Queue is empty: " + e);
        }

        String expResult = "person4";

        assertEquals(expResult, result);
    }

    // test head method on an empty queue
    @Test
    public void testHeadEmpty() {

        System.out.println(("test head method on an empty queue"));

        String result = "";

        try {

            instance.head();
        }
        catch (QueueUnderflowException e){

            result = "Queue is empty: " + e;
        }

        String expResult = "Queue is empty: queuemanager.QueueUnderflowException: Queue is empty";

        assertEquals(expResult, result);
    }

    // tests if the queue is empty
    @Test
    public void testEmpty() {

        System.out.println("test queue is empty");

        boolean expResult = true;
        boolean result = instance.isEmpty();
        
        assertEquals(expResult, result);
    }
}