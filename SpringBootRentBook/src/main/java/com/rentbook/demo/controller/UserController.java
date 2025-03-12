package com.rentbook.demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentbook.demo.busines.abstracts.IUserService;
import com.rentbook.demo.core.modelMapper.IModelMapperService;
import com.rentbook.demo.dto.request.Book.BookUpdateRequest;
import com.rentbook.demo.dto.request.User.UserSaveRequest;
import com.rentbook.demo.dto.request.User.UserUpdateRequest;
import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private final IUserService iUserService;
    private final IModelMapperService modelMapper;

    public UserController(IUserService iUserService, IModelMapperService modelMapper) {
        this.iUserService = iUserService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserSaveRequest userSaveRequest) throws JsonProcessingException {
        User user = modelMapper.forRequest().map(userSaveRequest, User.class);
        User savedUser = iUserService.saveUser(user);

        return savedUser;
    }

    @GetMapping("/findAll")
    public List<User> getAllUsers(){
        return iUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return iUserService.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        iUserService.deleteUser(id);
        return "Kullanıcı başarıyla silindi";
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequest userUpdateRequest) throws JsonProcessingException {
        User existingUser = this.iUserService.getUserById(id); // Önce kitabı bul
        this.modelMapper.forRequest().map(userUpdateRequest, existingUser); // Güncelleme işlemi
        User updatedUser = this.iUserService.userUpdate(existingUser); // Güncellenmiş kitabı kaydet

        // JSON olarak düzgün dönüp dönmediğini görmek için
        System.out.println(new ObjectMapper().writeValueAsString(updatedUser));

        return updatedUser; // Güncellenmiş kitabı JSON olarak döndür
    }





}
