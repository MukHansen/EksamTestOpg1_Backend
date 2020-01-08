package facades;

import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PersonHobbyOutDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * @author martin
 */
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private EntityManager em;

    private Person p1, p2;
    private Hobby h1, h2, h3;
    private Address a1, a2;
    private CityInfo c1, c2;
    private Phone phone1, phone2, phone3;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = PersonFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();
            c1 = new CityInfo(2100, "KBH Ø");
            c2 = new CityInfo(2300, "KBH S");
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            h1 = new Hobby("Cykling", "Cykling på hold");
            h2 = new Hobby("Film", "Gyserfilm");
            h3 = new Hobby("Film", "Dramafilm");
            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            a1 = new Address("Testgade", "dejligt sted", c1);
            a2 = new Address("Testvej", "fint sted", c2);
            em.persist(a1);
            em.persist(a2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        em = emf.createEntityManager();
        try {
            p1 = new Person("email", "Gurli", "Mogensen", a1);
            p2 = new Person("mail", "Gunnar", "Hjorth", a2);

            em.getTransaction().begin();
            p1.addHobby(h1);
            p1.addHobby(h2);
            p2.addHobby(h1);
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        em = emf.createEntityManager();
        try {
            phone1 = new Phone("1234", "hjemmetelefon", p1);
            phone2 = new Phone("5678", "mobil", p1);
            phone3 = new Phone("4321", "arbejdstelefon", p2);
            em.getTransaction().begin();
            em.persist(phone1);
            em.persist(phone2);
            em.persist(phone3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }
     /**
     * Test of createPerson method, of class PersonFacade.
     */
    @Test
    public void testCreatePerson() {
        PersonDTO pDTO = facade.createPerson("email", "firstName", "lastName", "street", 0);
        assertNotNull(pDTO.getPersonID());
    }

    /**
     * Test of getPerson method, of class PersonFacade.
     */
    @Test
    public void testGetPerson() {
        System.out.println("getPerson");
        PersonDTO pDTO = new PersonDTO(p1.getEmail(), p1.getFirstName(), p1.getLastName(), p1.getAddress().getStreet(), p1.getAddress().getCityInfo().getZipCode());
        pDTO.setPersonID(p1.getPersonID());
        PersonDTO instance = facade.getPerson(p1.getPersonID());
        assertEquals(p1.getPersonID(), pDTO.getPersonID());
    }

    /**
     * Test of getPerson method, of class PersonFacade.
     */
    @Test
    public void testGetPersonByPhoneNumber() {
        System.out.println("getPersonByPhoneNumber");
        PersonHobbyOutDTO instance = facade.getPersonByPhoneNumber("1234");
        // we should be comparing ID's but there's no ID in the PersonOutDTO, so not bothering this time
        assertEquals(instance.getFirstName(), p1.getFirstName());
    }

    /**
     * Test of getAllPersonsWithHobby method, of class PersonFacade.
     */
//    @Test
    public void testGetAllPersonsWithHobby() {
        System.out.println("getAllPersons");
        PersonFacade instance = null;
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPersonsWithZipCode method, of class PersonFacade.
     */
    @Test
    public void testGetAllPersonsWithZipCode() {
        System.out.println("getZipCodes");
        List<Person> persons = facade.getAllPersonsWithZipCode(2300);
        assertEquals(persons.size(), 1);
    }

    /**
     * Test of getCountPeopleWithHobby method, of class PersonFacade.
     */
//    @Test
    public void testGetCountPeopleWithHobby() {
        System.out.println("getCountPeopleWithHobby");
        PersonFacade instance = null;
        int expResult = 0;
        int result = instance.getCountPeopleWithHobby();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllZipCodes method, of class PersonFacade.
     */
//    @Test
    public void testGetAllZipCodes() {
        System.out.println("getAllZipCodes");
        PersonFacade instance = null;
        instance.getAllZipCodes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFacadeExample method, of class PersonFacade.
     */
//    @Test
    public void testGetFacadeExample() {
        System.out.println("getFacadeExample");
        EntityManagerFactory _emf = null;
        PersonFacade expResult = null;
        PersonFacade result = PersonFacade.getFacadeExample(_emf);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    @Test
    public void testEditPCI(){
        System.out.println("Testing editPCI");
        PersonDTO pDTO = new PersonDTO("email@email.dk", "Johnny", "Johnson", "Johnstreet", 0);
        pDTO.setPersonID(p1.getPersonID());
        
        facade.editPCI(pDTO);
        PersonDTO p1DTO = facade.getPerson(p1.getPersonID());
        
        assertEquals(p1DTO.getPersonID() , pDTO.getPersonID());
        assertEquals(p1DTO.getFirstName() , pDTO.getFirstName());
        assertEquals(p1DTO.getLastName() , pDTO.getLastName());
        assertEquals(p1DTO.getEmail() , pDTO.getEmail());
        assertNotEquals(p1DTO.getStreet(), pDTO.getStreet());
        assertNotEquals(p1DTO.getZipcode(), pDTO.getZipcode());
    }
    
   @Test
   public void testGetAllPersons(){
       List<PersonDTO> allpersons = facade.getAllPersons();
       assertEquals(allpersons.size(), 2);
       assertThat(allpersons.get(0).getFirstName(), either(containsString("Gurli")).or(containsString("Gunnar")));
   }
   
   @Test
   public void testAddHobby(){
       PersonHobbyOutDTO pDTO = facade.addHobby(new HobbyDTO("TestHobby", "This is a test hobby"), p1.getPersonID());
       assertEquals(pDTO.getHobbies().size(), 3);
       assertEquals(pDTO.getHobbies().get(2).getName(), "TestHobby");
   }
}
