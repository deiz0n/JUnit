package com.deiz0n.junit.services;

import com.deiz0n.junit.domain.User;
import com.deiz0n.junit.domain.dto.UserDTO;

import java.util.List;

public interface GenericService {

    User getResource(Long id);
    List<User> getResources();
    User createResource(UserDTO newUser);
    User updateResource(User user);
    void removeResource(Long id);

}
