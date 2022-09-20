package se.anas.demo.controller;

import org.springframework.web.bind.annotation.*;
import se.anas.demo.domain.Address;
import se.anas.demo.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/getAllAddresses")
    public List<Address> getAllAddresses(){
       return addressService.getAllAddresses();
    }
    @GetMapping("/getAddress/header")
    public Address getAddressByHeader(@RequestHeader Long id){
       return addressService.getAddress(id);
    }

    @GetMapping("/getAddress/param/")
    public Address getAddressByParam(@RequestParam Long id){
       return addressService.getAddress(id);
    }

    @GetMapping("/getAddress/{id}")
    public Address getAddressByPath(@PathVariable Long id){
       return addressService.getAddress(id);
    }

    /*
    the id is auto generated so we don't need to set it in postman when we add a new address
    and if we set the id , so the program will set it again any way
     */
   @PostMapping("/addAddress")
    public void addAddress(@RequestBody Address address){
        addressService.addAddress(address);
   }
   @PutMapping("/updateAddress/{id}")
    public void updateAddress(@PathVariable Long id, @RequestBody Address address){
        addressService.updateAddress(id, address);
   }

   @DeleteMapping("/deleteAddress/param/")
        public void deleteAddress(@RequestParam Long id) {
       addressService.deleteAddress(id);
   }

}
