package com.example.prac01.user;

import com.example.prac01.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    @ElementCollection
    @Size(max = 5, message = "5 is maximum!")
    private List<String> clock = new ArrayList<>();

    public User(UserDTO userDTO){
        this.username = userDTO.getUsername();
        this.email = userDTO.getEmail();
        this.clock.addAll(userDTO.getClock());
    }
}
