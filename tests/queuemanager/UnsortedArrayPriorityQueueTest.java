package queuemanager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class UnsortedArrayPriorityQueueTest {

    public PriorityQueue<Person> instance;

    @Before
    public abstract void setUp() throws Exception;

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

}