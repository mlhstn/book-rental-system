package com.rentbook.demo.busines.concretes;

import com.rentbook.demo.busines.abstracts.IUserService;
import com.rentbook.demo.core.config.Msg;
import com.rentbook.demo.core.config.exception.NotFoundException;
import com.rentbook.demo.core.config.exception.RecordAlreadyExistException;
import com.rentbook.demo.dao.UserRepository;
import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserManager implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {

        User existingUser = userRepository.findByName(user.getName())
;
        if (Objects.nonNull(existingUser)){

            throw new RecordAlreadyExistException(existingUser.getId());
        }

        user.setId(null);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("Kullanıcı bulunamadı"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User userUpdate(User user) {
        this.get(user.getId());
        return this.userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
}
