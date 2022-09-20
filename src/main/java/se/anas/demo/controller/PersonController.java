package se.anas.demo.controller;

import org.springframework.web.bind.annotation.*;
import se.anas.demo.domain.Address;
import se.anas.demo.domain.Person;
import se.anas.demo.repository.AddressRepo;
import se.anas.demo.repository.PersonRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonRepo personRepo;
    private final AddressRepo addressRepo;

    /*
    this way to put them in constructor is the same (without constructor) , but they sa is better:

    @Autowired
    private final PersonRepo personRepo;
     */
    public PersonController(PersonRepo personRepo, AddressRepo addressRepo) {
        this.personRepo = personRepo;
        this.addressRepo = addressRepo;
    }

    // to get all persons as list
    @GetMapping("/people")
    public List<Person> getAll() {
        return personRepo.findAll();
    }

    // to get a person by it's id in url request (http://localhost:8080/api/people/4)
    // we put the value in postman not direct to url
    @GetMapping("/people/{id}")
    public Person getById(@PathVariable Long id) {
        return personRepo.findById(id).get();
    }

    // to get a person by it's direct in url request  (http://localhost:8080/api/param?id=7)
    @GetMapping("/param")
    public Person getByIdParam(@RequestParam Long id) {
        return personRepo.findById(id).get();
    }


    // to get a person by header ( we put the value of object's p_key in postman BUT it doest appear in url )
    // I think it's better
    @GetMapping("/header")
    public Person getByIdHeaders(@RequestHeader Long id) {
        return personRepo.findById(id).get();
    }

    /*
    we get the person object by send a request with (object ) and this sent object contain specific needed attributes
    and we get this sent object by create new class
     */
    @GetMapping("/body")
    public Person getByIdBody(@RequestBody BodyData bodyData) {
        return personRepo.findById(bodyData.getId()).get();  // from new class which give us bodyData object
    }

    /*
    when we add a new person to the list, so the program check if the address of this added person already
    exist in address talbe or not , if not so the new address add to the address table and then the new person
    will be added to the person table
    note: without these code if we insert a new address ( not exist in address table ) we will get error
     */
    @PostMapping("/body")
    public Person savePerson(@RequestBody Person person) {
        Address address = person.getAddress();
        if (!addressRepo.existsById(address.getId())) {
            Address saved = addressRepo.save(address);
            person.setAddress(saved);
        }
        return personRepo.save(person);
    }

    /*
     update it means we change the state of object, so when we add a address to a person so we use
     PutMapping not (PostMapping)
     here first we get the person by it's header and then check the added address if it's new or
     already exist in address table, if t's new so we add it to the address table and then add it
     to the person, or add it
     */
    @PutMapping("/put")
    public String updatePerson(@RequestHeader Long id, @RequestBody Address address) {
        Address saved;
        try {
            Person person = personRepo.findById(id).get();   // return a needed person to add him a new address
            if (!addressRepo.existsById(address.getId())) {
                 saved = addressRepo.save(address);
                 person.setAddress(saved);
            }
                 person.setAddress(address);
                 personRepo.save(person);
                 return "person is added";
        } catch (Exception e) {
            e.printStackTrace();
        }
           return "person is not added";
    }
}

/*
 this class by which we get the sent object in request
 */
class BodyData {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

