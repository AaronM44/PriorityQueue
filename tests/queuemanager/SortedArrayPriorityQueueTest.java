package queuemanager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortedArrayPriorityQueueTest {

    public PriorityQueue<Person> instance = new SortedArrayPriorityQueue<>(8);

    @Before
    public void setUp() throws Exception {


    }

    // tests if the queue is empty
    @Test
    public void testEmpty() {

        System.out.println("test queue is empty");

        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    // tests adding one person to the queue
    @Test
    public void testAddPerson() {

        System.out.println("test adding a person");

        Person person = new Person("person");
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

    // tests adding to the queue when its full
    @Test
    public void testAddPersonFull() {

        System.out.println("test adding a person when queue is full");

        Person person = new Person("person");

        String result = "";

        for (int i = 0; i < 10; i++) {
            try {
                instance.add(person, 5);
            } catch (QueueOverflowException e) {
                result = ("Add operation failed: " + e);
            }
        }

        String expResult = "Add operation failed: queuemanager.QueueOverflowException: Queue is full";

        assertEquals(expResult, result);
    }

    // tests the head function with 2 items in queue
    @Test
    public void testHead() {

        System.out.println("test head function");

        Person person1 = new Person("person1");
        Person person2 = new Person("person2");

        String result = "";

        try {
            instance.add(person1, 2);
            instance.add(person2, 5);
        }
        catch (QueueOverflowException e){
            System.out.println("Add operation failed: " + e);
        }

        try {
            result = instance.head().toString();
        }
        catch (QueueUnderflowException e) {
            System.out.println("Queue is empty");
        }

        String expResult = "person2";

        assertEquals(expResult, result);
    }

    // tests the head function when queue is empty
    @Test
    public void testHeadEmpty() {

        System.out.println("test head function when queue is empty");

        String result = "";

        try {
            instance.head();
        }
        catch (QueueUnderflowException e) {
            result = ("Queue is empty");
        }

        String expResult = "Queue is empty";

        assertEquals(expResult, result);
    }

    // tests the remove function with 2 items in the queue
    @Test
    public void testRemove() {

        System.out.println("test remove function with 2 items in queue");

        Person person1 = new Person("person1");
        Person person2 = new Person("person2");

        String result = "";

        try {
            instance.add(person1, 5);
            instance.add(person2, 3);
        }
        catch (QueueOverflowException e){
            System.out.println("Add operation failed: " + e);
        }

        try {
            instance.remove();
        }
        catch (QueueUnderflowException e) {
            System.out.println("Remove operation failed: " + e);
        }

        result = instance.toString();
        String expResult = "[(person2, 3)]";

        assertEquals(expResult, result);
    }

    // tests the remove function with 2 items in the queue
    @Test
    public void testRemoveEmpty() {

        System.out.println("test remove function with 2 items in queue");

        String result = "";

        try {
            instance.remove();
        }
        catch (QueueUnderflowException e) {
            result = ("Remove operation failed: " + e);
        }

        String expResult = "Remove operation failed: queuemanager.QueueUnderflowException: Queue is empty";

        assertEquals(expResult, result);
    }

}