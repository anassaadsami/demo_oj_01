package se.anas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.anas.demo.domain.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
