package rest;

import dto.AddressOutDTO;
import dto.HobbyDTO;
import dto.PersonOutDTO;
import dto.CityInfoOutDTO;
import dto.PersonDTO;
import dto.PersonHobbyOutDTO;
import dto.PhoneOutDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import errorhandling.ExceptionDTO;
import utils.EMF_Creator;
import facades.PersonFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

@OpenAPIDefinition(
        info = @Info(
                title = "Hobby API",
                version = "0.1",
                description = "API to get info about hobbies.",
                contact = @Contact(name = "Gũnther Steiner", email = "info@simonskodebiks.dk")
        ),
        tags = {
            @Tag(name = "hobby", description = "API related to Hobby Info")

        },
        servers = {
            @Server(
                    description = "For Local host testing",
                    url = "http://localhost:8080/course-assignment-2"
            ),
            @Server(
                    description = "Server API",
                    url = "https://simonskodebiks.com/course-assignment-2"
            )

        }
)

@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/ca2",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE = PersonFacade.getFacadeExample(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Person info by ID",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))),
                @ApiResponse(responseCode = "200", description = "The Requested Person"),
                @ApiResponse(responseCode = "404", description = "Person not found")})

    public PersonDTO getPersonInfo(@PathParam("id") int personID) {
        if (FACADE.getPerson(personID) == null) {
            throw new WebApplicationException("Person not found", 404);
        } else {
            PersonDTO pDTO = FACADE.getPerson(personID);
            return pDTO;
        }
    }

//    Get information about a person (address, hobbies etc) given a phone number
    @GET
    @Path("phone/{phoneNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Person info by PhoneNumber",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonHobbyOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "The Requested Person"),
                @ApiResponse(responseCode = "404", description = "Person not found")})

    public PersonHobbyOutDTO getPersonInfoByPhoneNumber(@PathParam("phoneNumber") String phoneNumber) {
        List<Hobby> hob = new ArrayList();
        hob.add(new Hobby("Cykling", "Cykling på hold"));
        hob.add(new Hobby("Film", "Gyserfilm"));
        if (phoneNumber != null && phoneNumber.equals("1111")) {
            // for test
            return new PersonHobbyOutDTO("info@simonskodebiks.dk", "Gũnther", "Steiner", "Street addInfogade2, 1234 KBH", hob);
        } else {
            PersonHobbyOutDTO pOut = FACADE.getPersonByPhoneNumber(phoneNumber);
            return pOut;
        }
    }

//    Get all persons with a given hobby
    @GET
    @Path("hobby/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all Persons with given hobby",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "The Requested Person"),
                @ApiResponse(responseCode = "404", description = "Persons not found")})

    public List<PersonOutDTO> getAllPersonsInfoByHobby() {
        List<PersonOutDTO> p = new ArrayList();
        p.add(new PersonOutDTO(new Person("info@simonskodebiks.dk", "Gũnther", "Steiner", new Address("Street", "addInfo", new CityInfo(123, "KBH")))));
        p.add(new PersonOutDTO(new Person("info@simonskodebiks.dk", "Gũnther", "Steiner", new Address("Street", "addInfo", new CityInfo(123, "KBH")))));
        return p;
    }

//    Get all persons living in a given city (i.e. 2800 Lyngby)
    @GET
    @Path("zipcode/{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Person info by zipcode",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "The Requested Persons"),
                @ApiResponse(responseCode = "404", description = "Person not found")})
    public List<PersonOutDTO> getAllPersonsInfoByCity(@PathParam("zipCode") String zipCode) {
        if (zipCode != null && zipCode.equals("1234")) {
            // for test
            List<PersonOutDTO> persons = new ArrayList<>();
            PersonOutDTO p = new PersonOutDTO(new Person("info@simonskodebiks.dk", "Gũnther", "Steiner", new Address("Street", "addInfo", new CityInfo(123, "KBH"))));
            HobbyDTO h1 = new HobbyDTO(new Hobby("fodbold", "hver tirsdag"));
            HobbyDTO h2 = new HobbyDTO(new Hobby("fodbold", "hver onsdag"));
            ArrayList<HobbyDTO> hobbies = new ArrayList<>();
            hobbies.add(h1);
            hobbies.add(h2);
            p.setHobbies(hobbies);
            persons.add(p);
            return persons;
        } else {
            // here should be something real :-)
            List<PersonOutDTO> persons = new ArrayList<>();
            PersonOutDTO p = new PersonOutDTO(new Person("info@simonskodebiks.dk", "Gũnther", "Steiner", new Address("Street", "addInfo", new CityInfo(123, "KBH"))));
            HobbyDTO h1 = new HobbyDTO(new Hobby("fodbold", "hver tirsdag"));
            HobbyDTO h2 = new HobbyDTO(new Hobby("fodbold", "hver onsdag"));
            ArrayList<HobbyDTO> hobbies = new ArrayList<>();
            hobbies.add(h1);
            hobbies.add(h2);
            p.setHobbies(hobbies);
            persons.add(p);
            return persons;
        }
    }

//    Get the count of people with a given hobby
    @GET
    @Path("count/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get the count of people with a given hobby",
            tags = {"hobby"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = HobbyDTO.class))),
                @ApiResponse(responseCode = "200", description = "The count of a given hobby"),
                @ApiResponse(responseCode = "404", description = "Hobby not found")})
    public int getCountPersonByHobby(@PathParam("hobby") String hobby) {
        List<HobbyDTO> hobbies = new ArrayList();
        hobbies.add(new HobbyDTO(new Hobby("Tennis", "Green ball")));
        return hobbies.size();
    }

//    Get a list of all zip codes in Denmark
    @GET
    @Path("zipcode/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all zipcodes",
            tags = {"zipcode"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CityInfoOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "The Requested Persons"),
                @ApiResponse(responseCode = "404", description = "Person not found")})
    public List<CityInfoOutDTO> getAllZipCodes() {
        return FACADE.getAllZipCodes();
    }

    //    Get all persons with a given hobby(i.e. golf)
    @GET
    @Path("hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Person info by hobby",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "The Requested Persons"),
                @ApiResponse(responseCode = "404", description = "Person not found")})
    public List<PersonOutDTO> getPersonsInfoByHobby(@PathParam("hobby") String hobby) {
        if (hobby != null && hobby.equals("golf")) {
            // for test
            List<PersonOutDTO> persons = new ArrayList<>();
            persons.add(new PersonOutDTO(new Person("info@simonskodebiks.dk", "Gũnther", "Steiner", new Address("Street", "addInfo", new CityInfo(123, "KBH")))));
            return persons;
        } else {
            // here should be something real :-)
            List<PersonOutDTO> persons = new ArrayList<>();
            persons.add(new PersonOutDTO(new Person("info@simonskodebiks.dk", "Gũnther", "Steiner", new Address("Street", "addInfo", new CityInfo(123, "KBH")))));
            return persons;
        }
    }

    //@PUT
    public void editPersonAddress() {
    }

    @PUT
    @Path("edit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit existing person", tags = {"person"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The edited person"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public PersonDTO editPersonCoreInformation(PersonDTO person) {
        if (person.getFirstName() == null || person.getLastName() == null || person.getEmail() == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }

        return FACADE.editPCI(person);
//        //dummy data
//        PersonOutDTO p = new PersonOutDTO(new Person("info@simonskodebiks.dk", "Gũnther", "Steiner", new Address("Street", "addInfo", new CityInfo(123, "KBH"))));
//        HobbyDTO h1 = new HobbyDTO(new Hobby("fodbold", "hver tirsdag"));
//        HobbyDTO h2 = new HobbyDTO(new Hobby("fodbold", "hver onsdag"));
//        ArrayList<HobbyOutDTO> hobbies = new ArrayList<>();
//        hobbies.add(h1);
//        hobbies.add(h2);
//        p.setHobbies(hobbies);
//        return p;
    }

    //@POST
    @POST
    @Path("hobby/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new hobby to a person", tags = {"hobby"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Person with added hobby"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public PersonOutDTO addHobby(PersonOutDTO person) {
        if (person.getFirstName() == null || person.getLastName() == null || person.getEmail() == null || person.getAddress() == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //dummy data
        PersonOutDTO p = new PersonOutDTO(new Person("info@simonskodebiks.dk", "Gũnther", "Steiner", new Address("Street", "addInfo", new CityInfo(123, "KBH"))));
        HobbyDTO hobby = new HobbyDTO(new Hobby("golf", "totalt handicapped"));
        ArrayList<HobbyDTO> hobbies = new ArrayList<>();
        hobbies.add(hobby);
        person.setHobbies(hobbies);
        return person;
    }

    //@DELETE
    public void deleteHobby() {
    }

    //@POST
    @POST
    @Path("phone/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new phone to a person", tags = {"phone"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Person with added phone"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public PersonOutDTO addPhone(PersonOutDTO person) {
        if (person.getFirstName() == null || person.getLastName() == null || person.getEmail() == null || person.getAddress() == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //dummy data
        PersonOutDTO p = new PersonOutDTO(new Person("info@simonskodebiks.dk", "Gũnther", "Steiner", new Address("Street", "addInfo", new CityInfo(123, "KBH"))));
        PhoneOutDTO newPhone = new PhoneOutDTO();
        newPhone.setDescription("work phone");
        newPhone.setPhoneNumber("223344");
        person.getPhones().add(newPhone);
        return person;
    }

    //@DELETE
    public void deletePhone() {
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create new Person", tags = {"person"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The Newly created Person"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public PersonDTO createPerson(PersonDTO person) {
        if (person.getFirstName() == null || person.getLastName() == null || person.getEmail() == null || person.getStreet() == null || person.getZipcode() == 0) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        return FACADE.createPerson(person.getEmail(), person.getFirstName(), person.getLastName(), person.getStreet(), person.getZipcode());
    }

    //@DELETE
    public void deletePerson() {
    }

    //    fill db with data
    @GET
    @Path("fill")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Fill data into database",
            tags = {"default"},
            responses = {
                //                @ApiResponse(
                //                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "Database is filled")
            })
    public String getFilling() {
        return FACADE.fillUp();
    }

    //    empty db
    @GET
    @Path("empty")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Empty database",
            tags = {"default"},
            responses = {
                //                @ApiResponse(
                //                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "Data has been deleted")
            })
    public String getDeleting() {
        return FACADE.emptyDB();
    }
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all Persons info",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonOutDTO.class))),
                @ApiResponse(responseCode = "200", description = "The Requested Persons"),
                @ApiResponse(responseCode = "404", description = "Persons not found")})

    public List<PersonDTO> getAllPersons() {
        return FACADE.getAllPersons();
    }

}
