package com.globant.topic_5.Service;

import com.globant.topic_5.Persistence.Model.User;
import com.globant.topic_5.Persistence.UserRepository;
import com.globant.topic_5.Service.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public User saveNewUser(User user) throws Exception {
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new Exception("Username already exist");
        }
        if (userRepo.existsByIdNumber(user.getIdNumber())){
            throw new Exception("Identification number already exist");
        }
        return userRepo.save(user);
    }

    @Override
    public User getUserById(Long idNumber) throws Exception {
        AtomicReference<User> user = new AtomicReference<User>();
        userRepo.findById(idNumber).ifPresent(dbUser -> user.set(dbUser));
        if (user.get() != null) {
            return user.get();
        }
        throw new Exception("Find User: Username not found");
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        AtomicReference<User> user = new AtomicReference<User>();
        userRepo.findByUsername(username).ifPresent(dbUser -> user.set(dbUser));
        if (user.get() != null) {
            return user.get();
        }
        throw new Exception("Find User: Username not found");
    }

    @Override
    public User editUser(User user) throws Exception {
        if (userRepo.existsById(user.getIdNumber())) {
            AtomicReference<User> dbUser = new AtomicReference<User>();
            userRepo.findById(user.getIdNumber()).ifPresent(getUser -> dbUser.set(getUser));
            dbUser.set(User.MapUser(user, dbUser.get()));
            try {
                return userRepo.save(dbUser.get());
            } catch (Exception e) {
                throw new Exception("User not updated: " + e.getMessage());
            }
        }
        throw new Exception("Edit User: User not found");
    }

    @Override
    public User deleteUser(User user) throws Exception {
        if (userRepo.existsById(user.getIdNumber())) {
            User dbUser = userRepo.findById(user.getIdNumber()).get();
            try {
                userRepo.delete(user);
            } catch (Exception e) {
                throw new Exception("User not deleted: " + e.getMessage());
            }
            return dbUser;
        }
        throw new Exception("Delete User: User not found or already deleted");
    }
}
