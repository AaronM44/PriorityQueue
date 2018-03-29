package queuemanager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    LinkedList instance = new LinkedList();

    @Before
    public void setUp() throws Exception {
    }


    // test adding 1 person to the list
    @Test
    public void testAdd() {

        System.out.println("testing add method for 1 item");

        Person person = new Person("person");
        PriorityItem item = new PriorityItem(person, 5);

        instance.add(item);

        String result = instance.toString();
        String expResult = "(person, 5)";

        assertEquals(expResult, result);
    }

    // test removing 1 item from a list of 2
    @Test
    public void testRemove() {

        System.out.println("testing remove method for 1 item");

        // add 2 people to the list
        for (int i = 0; i < 2; i++) {

            Person person = new Person("person");
            PriorityItem item = new PriorityItem(person, 5);

            instance.add(item);
        }

        instance.remove();

        String result = instance.toString();
        String expResult = "(person, 5)";

        assertEquals(expResult, result);
    }

    @Test
    public void testEmpty() {

        System.out.println("testing isEmpty with an empty list");

        boolean result = instance.isEmpty();

        boolean expResult = true;

        assertEquals(expResult, result);
    }

}