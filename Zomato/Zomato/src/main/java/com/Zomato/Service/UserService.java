package com.Zomato.Service;

import com.Zomato.Entity.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUser();
    public User getByIdUser(Long id);
    public User update(Long id,User userDetails);
    public void delete(Long id);
}
