package com.globant.topic_5.Service.Interfaces;

import com.globant.topic_5.Persistence.Model.User;

public interface UserService {
    User saveNewUser(User user) throws Exception;

    User getUserById(Long idNumber) throws Exception;

    User getUserByUsername(String username) throws Exception;

    User editUser(User user) throws Exception;

    User deleteUser(User user) throws Exception;
}
