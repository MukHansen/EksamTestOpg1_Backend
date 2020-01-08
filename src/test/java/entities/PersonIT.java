package entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author martin
 */
public class PersonIT {
    private static Person p1;
    private static Person p2;
    private static Person p3;
    
    public PersonIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        p1 = new Person("a", "b", "c");
        p2 = new Person("d", "e", "f");
        p3 = new Person("g", "i", "j");
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getID method, of class Person.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        p1.setPersonID(1);
        int expResult = p1.getPersonID();
        int result = 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Person.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Person instance = p1;
        instance.setEmail("h@h.h");
        String expResult = "h@h.h";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Person.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "j@j.j";
        Person instance = p1;
        instance.setEmail(email);
        String expResult = "j@j.j";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFirstName method, of class Person.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Person instance = p1;
        instance.setFirstName("h");
        String expResult = "h";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class Person.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "h";
        Person instance = p1;
        instance.setFirstName(firstName);
        String expResult = "h";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLastName method, of class Person.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Person instance = p1;
        instance.setLastName("j");
        String expResult = "j";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastName method, of class Person.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "j";
        Person instance = p1;
        instance.setLastName(lastName);
        String expResult = "j";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }
    
}
