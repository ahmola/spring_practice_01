package com.example.prac01.service;

import com.example.prac01.dto.UserDTO;
import com.example.prac01.repository.UserRepository;
import com.example.prac01.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) throws Exception{
        if(userRepository.findById(id).isEmpty())
            throw new RuntimeException("There is no such User!");
        else
            return userRepository.findById(id).get();
    }

    public User createUser(UserDTO userDTO) {
        User user = new User(userDTO);
        return userRepository.save(user);
    }


    public User editUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).get();
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setClock(userDTO.getClock());
        return userRepository.save(user);
    }

    public Boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    public User login(UserDTO userDTO) throws Exception{
        if(!userRepository.existsByEmail(userDTO.getEmail()))
            throw new RuntimeException("There is no such user with : " + userDTO.getEmail());
        log.info("User Login success : " + userDTO.getEmail());
        return userRepository.findByEmail(userDTO.getEmail());
    }

    public void addAlarm(Long id, String alarm) throws Exception{
        if(userRepository.findById(id).isEmpty())
            throw new RuntimeException("There is no such User");
        User user = userRepository.findById(id).get();
        user.getClock().add(alarm);
        userRepository.save(user);
    }

    public void deleteAlarm(Long id, Integer index) throws Exception{
        if(userRepository.findById(id).isEmpty())
            throw new RuntimeException("There is no such User");
        if(index > 5)
            throw new RuntimeException("Index cannot be over 5");
        User user = userRepository.findById(id).get();
        user.getClock().remove(index-1);
        userRepository.save(user);
    }
}
