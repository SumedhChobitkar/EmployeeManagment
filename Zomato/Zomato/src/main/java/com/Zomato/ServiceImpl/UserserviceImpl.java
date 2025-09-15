package com.Zomato.ServiceImpl;

import com.Zomato.Entity.User;
import com.Zomato.Exceptions.UserNotfoundException;
import com.Zomato.Repository.UserRepository;
import com.Zomato.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserserviceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getByIdUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotfoundException("User Not found with id"));
    }

    @Override
    public User update(Long id, User userDetails) {
        User Update=getByIdUser(id);
        Update.setName(userDetails.getName());
        Update.setMobileN(userDetails.getMobileN());
        Update.setEmail(userDetails.getEmail());
        return userRepository.save(Update);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);

    }
}
