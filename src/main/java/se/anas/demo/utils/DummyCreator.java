package se.anas.demo.utils;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.anas.demo.domain.Address;
import se.anas.demo.domain.Person;
import se.anas.demo.repository.AddressRepo;
import se.anas.demo.repository.PersonRepo;

//@Component
public class DummyCreator implements CommandLineRunner {

    private final Faker faker;
    private final PersonRepo personRepo;
    private final AddressRepo addressRepo;

    public DummyCreator(Faker faker, PersonRepo personRepo, AddressRepo addressRepo) {
        this.faker = faker;
        this.personRepo = personRepo;
        this.addressRepo = addressRepo;
    }

    /*
    this to create 6 person objects and 6 address objects and save them in db , so when we run the application
    we have already them in db so we don't need to post them and waste time.
     */
    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i <= 5; i++) {
            Address address = new Address(null, faker.address().streetName());
            Address saved = addressRepo.save(address);
            Person person = new Person(null, faker.name().fullName(), saved);
            personRepo.save(person);
        }
    }
}
