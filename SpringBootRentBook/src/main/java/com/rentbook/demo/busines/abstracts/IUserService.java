package com.rentbook.demo.busines.abstracts;

import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IUserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    User userUpdate(User user);
    User get(Long id);
}
