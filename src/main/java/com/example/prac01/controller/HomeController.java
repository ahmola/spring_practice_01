package com.example.prac01.controller;

import com.example.prac01.dto.UserDTO;
import com.example.prac01.service.UserService;
import com.example.prac01.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class HomeController {

    private final UserService userService;

    @GetMapping("/home")
    public ResponseEntity<String> getHome(){
        return new ResponseEntity<>("home", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id")Long id) throws Exception {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) throws Exception {
        User user = new User();
        try{
            user = userService.login(userDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error : " + e.getMessage());
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/addAlarm/{id}")
    public ResponseEntity<String> addAlarm(@PathVariable(name = "id")Long id, @RequestBody String alarm){
        try {
            userService.addAlarm(id, alarm);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error : " + e.getMessage());
        }
        return new ResponseEntity<>(alarm, HttpStatus.OK);
    }

    @PutMapping("/deleteAlarm/{id}/{index}")
    public ResponseEntity<String> deleteAlarm(
            @PathVariable(name = "id")Long id,
            @PathVariable(name = "index")Integer index){
        try{
            userService.deleteAlarm(id, index);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error : " + e.getMessage());
        }
        return new ResponseEntity<>(index+" of " + id + "'s alarm has successfully deleted", HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/user")
    public ResponseEntity<User> editUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.editUser(userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
}
