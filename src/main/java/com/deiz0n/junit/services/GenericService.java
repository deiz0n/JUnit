package com.deiz0n.junit.services;

import com.deiz0n.junit.domain.User;
import com.deiz0n.junit.domain.dto.UserDTO;

import java.util.List;

public interface GenericService {

    User getResource(Integer id);
    List<User> getResources();
    User createResource(UserDTO newUser);
    User updateResource(UserDTO newUser);
    void removeResource(Integer id);

}
