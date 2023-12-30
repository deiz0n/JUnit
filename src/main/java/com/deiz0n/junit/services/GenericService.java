package com.deiz0n.junit.services;

import com.deiz0n.junit.domain.User;

import java.util.List;

public interface GenericService {

    User getResource(Long id);
    List<User> getResources();
    User createResource(User user);
    User updateResource(User user);
    void removeResource(Long id);

}
