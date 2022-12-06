package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @RolesAllowed({"Manager", "Admin"})
    public ResponseEntity<ResponseWrapper> getUsers(){  // see custom output using ResponseWrapper
        List<UserDTO> userDTOList = userService.listAllUsers();
        return ResponseEntity.ok(new ResponseWrapper("Users are successfully retrieved", userDTOList, HttpStatus.OK));
    }

    @GetMapping("/{username}")
    @RolesAllowed("Admin")
    public ResponseEntity<ResponseWrapper> getUserByUserName(@PathVariable("username") String username){
        return ResponseEntity.ok(new ResponseWrapper("User is successfully retrieved", userService.findByUserName(username), HttpStatus.OK));
    }

    @PostMapping
    @RolesAllowed("Admin")
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO user){
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("User is successfully created", HttpStatus.CREATED));
    }

    @PutMapping
    @RolesAllowed("Admin")
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO user){
        userService.update(user);
        return ResponseEntity.ok(new ResponseWrapper("User is successfully updated", HttpStatus.OK));
    }

    @DeleteMapping("/{username}")
    @RolesAllowed("Admin")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable("username") String username){
        userService.delete(username);
        return ResponseEntity.ok(new ResponseWrapper("User is successfully deleted", HttpStatus.OK));
    }
}
