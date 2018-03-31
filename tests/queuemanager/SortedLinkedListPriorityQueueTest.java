package queuemanager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortedLinkedListPriorityQueueTest {

    public PriorityQueue<Person> instance = new SortedLinkedListPriorityQueue<>();

    @Before
    public void setUp() throws Exception {
    }

    // test the is empty method when queue is empty
    @Test
    public void testEmpty1() {

        System.out.println("test is empty function");

        boolean expResult = true;
        boolean result = instance.isEmpty();

        assertEquals(expResult, result);
    }

    // test adding 1 item to an empty queue
    @Test
    public void testAdd1() {

        System.out.println("test adding 1 item to an empty queue");

        Person person = new Person("person");

        try {
            instance.add(person, 5);
        }
        catch (QueueOverflowException e) {
            System.out.println(e);
        }

        String result = instance.toString();
        String expResult = "(person, 5)";

        assertEquals(expResult, result);
    }

    // testing adding a lower priority item to a queue of 1 item
    @Test
    public void testAdd2() {

        System.out.println("testing adding a lower priority item to a queue of 1 item");

        Person person1 = new Person("person1");
        Person person2 = new Person("person2");

        try {
            instance.add(person1, 5);
            instance.add(person2, 3);
        }
        catch (QueueOverflowException e) {
            System.out.println(e);
        }

        String result = instance.toString();
        String expResult = "(person1, 5), (person2, 3)";

        assertEquals(expResult, result);
    }

    // testing adding a higher priority item to a queue of 1 item
    @Test
    public void testAdd3() {

        System.out.println("testing adding a higher priority item to a queue of 1 item");

        Person person1 = new Person("person1");
        Person person2 = new Person("person2");

        try {
            instance.add(person1, 3);
            instance.add(person2, 5);
        }
        catch (QueueOverflowException e) {
            System.out.println(e);
        }

        String result = instance.toString();
        String expResult = "(person2, 5), (person1, 3)";

        assertEquals(expResult, result);
    }

    // testing adding an item with a priority between that of the 2 in the queue
    @Test
    public void testAdd4() {

        System.out.println("testing adding an item with a priority between that of the 2 in the queue");

        Person person1 = new Person("person1");
        Person person2 = new Person("person2");
        Person person3 = new Person("person3");

        try {
            instance.add(person1, 3);
            instance.add(person2, 5);
            instance.add(person3, 4);
        }
        catch (QueueOverflowException e) {
            System.out.println(e);
        }

        String result = instance.toString();
        String expResult = "(person2, 5), (person3, 4), (person1, 3)";

        assertEquals(expResult, result);
    }

    // test removing an item from a queue of 1
    @Test
    public void testRemove() {

        System.out.println("test removing an item from a queue of 1");

        Person person = new Person("person");

        // adding
        try {
            instance.add(person, 5);
        }
        catch (QueueOverflowException e) {
            System.out.println("Cannot add to queue: " + e);
        }

        // removing
        try {
            instance.remove();
        }
        catch (QueueUnderflowException e) {
            System.out.println("Queue is empty: " + e);
        }

        String result = instance.toString();
        String expResult = "";

        assertEquals(expResult, result);
    }

    // test removing an item from a queue of 1
    @Test
    public void testRemoveEmpty() {

        System.out.println("test removing an item when the queue is empty");

        String result = "";

        try {
            instance.remove();
        }
        catch (QueueUnderflowException e) {
            result = "Queue is empty: " + e;
        }

        String expResult = "Queue is empty: queuemanager.QueueUnderflowException: Queue is empty";

        assertEquals(expResult, result);
    }

    @Test
    public void testHead() {

        System.out.println("test head function on a queue of 1 item");

        Person person = new Person("person");
        String result = "";

        try {
            instance.add(person, 5);
        }
        catch (QueueOverflowException e) {
            System.out.println("Cannot add to queue: " + e);
        }

        try {
            result = instance.head().toString();
        }
        catch (QueueUnderflowException e) {
            System.out.println("Queue is empty: " + e);
        }

        String expResult = "person";

        assertEquals(expResult, result);
    }

    @Test
    public void testHeadEmpty() {

        System.out.println("test head when queue is empty");

        String result = "";

        try {

            instance.head().toString();
        }
        catch (QueueUnderflowException e) {

            result = "Queue is empty: " + e;
        }

        String expResult = "Queue is empty: queuemanager.QueueUnderflowException: Queue is empty";

        assertEquals(expResult, result);
    }
}