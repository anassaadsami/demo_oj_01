package se.anas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.anas.demo.domain.Person;

public interface PersonRepo extends JpaRepository<Person, Long> {

}
