package se.anas.demo.service;

import jdk.jfr.Percentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import se.anas.demo.domain.Address;
import se.anas.demo.domain.Person;
import se.anas.demo.repository.AddressRepo;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepo addressRepo;

    public List<Address> getAllAddresses() {
        return addressRepo.findAll();
    }

    public Address getAddress(Long id) {
        return addressRepo.findById(id).get();
    }


    public void addAddress(Address address) {
        addressRepo.save(address);
    }

    public String updateAddress(Long id, Address address) {
        Address savedAddress;
        try {
            savedAddress = addressRepo.findById(id).get();
            if (addressRepo.findAll().contains(savedAddress)) {
                savedAddress.setId(id);
                savedAddress.setStreet(address.getStreet());
                addressRepo.save(savedAddress);
                return String.format("%s is updated.", savedAddress.getStreet());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return String.format("address with id no:%d is not found.", id);
    }

    /*
    public String updateAddress(Long id, Address address){
        Address savedAddress = addressRepo.findById(id).get();
        if(addressRepo.findAll().contains(savedAddress)){
            savedAddress.setId(id);
            savedAddress.setStreet(address.getStreet());
            addressRepo.save(savedAddress);
            return String.format("%s is updated.",savedAddress.getStreet());
        }else{
            return String.format("address with id no:%d is not found.",id);

        }
    }

     */

    public String deleteAddress(Long id) {
        Address savedAddress = addressRepo.findById(id).get();

        if (addressRepo.findAll().contains(savedAddress)) {
            addressRepo.delete(savedAddress);
            return "object is deleted";
        } else
            return "object is not found";
    }
}
