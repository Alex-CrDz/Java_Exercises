package com.globant.topic_5.Controller;

import com.globant.topic_5.Persistence.Model.User;
import com.globant.topic_5.Service.Interfaces.UserService;
import com.globant.topic_5.Util.UserBuilder;
import com.globant.topic_5.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserBuilder userBuilder;

    @GetMapping("/find/{username}")
    ResponseEntity getUser(@PathVariable String username) {
        User user = null;
        try {
            user = userService.getUserByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/new")
    ResponseEntity newUser(@RequestBody UserDto userDto) {
        try {
            User user = userBuilder
                    .setIdNumber(userDto.getIdNumber())
                    .setUsername(userDto.getUsername())
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setAddress(userDto.getAddress())
                    .setZipCodeCity(userDto.getZipCodeCity())
                    .setState(userDto.getState())
                    .setCountry(userDto.getCountry())
                    .setRole(userDto.getRole())
                    .build();
            userService.saveNewUser(user);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User Created", HttpStatus.OK);
    }

    @PutMapping("/edit")
    ResponseEntity editUser(@RequestBody UserDto userDto) {
        try {
            User user = userBuilder
                    .setIdNumber(userDto.getIdNumber())
                    .setUsername(userDto.getUsername())
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setAddress(userDto.getAddress())
                    .setZipCodeCity(userDto.getZipCodeCity())
                    .setState(userDto.getState())
                    .setCountry(userDto.getCountry())
                    .setRole(userDto.getRole())
                    .build();
            userService.editUser(user);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idNumber}")
    ResponseEntity deleteUser(@PathVariable(name = "idNumber") Long idNumber){
        try {
            User user = userService.getUserById(idNumber);
            userService.deleteUser(user);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User deleted", HttpStatus.OK);
    }
}
