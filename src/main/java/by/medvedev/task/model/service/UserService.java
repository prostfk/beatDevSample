package by.medvedev.task.model.service;

import by.medvedev.task.model.entity.User;
import by.medvedev.task.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        for (User user : repository.findAll()) {
            users.add(user);
        }
        return users;
    }

    public User findById(long id){
        return repository.findById(id).get();
    }

}
