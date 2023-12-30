package com.deiz0n.junit.services;

import com.deiz0n.junit.domain.User;
import com.deiz0n.junit.domain.dto.UserDTO;
import com.deiz0n.junit.repositories.UserRepository;
import com.deiz0n.junit.services.exceptions.FieldExistingException;
import com.deiz0n.junit.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements GenericService {


    private UserRepository repository;

    private ModelMapper mapper;

    public UserService(UserRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User getResource(Long id) {
       var user = repository.findById(id);
       return user.
               orElseThrow(() -> new ResourceNotFoundException(
                       String.format("O recurso com id: %s não foi encontrado.",  id.toString())));
    }

    @Override
    public List<User> getResources() {
        List<User> users = repository.findAll();
        return users;
    }

    @Override
    public User createResource(UserDTO newUser) {
        validationData(newUser);
        var user = mapper.map(newUser, User.class);
        return repository.save(user);
    }

    @Override
    public User updateResource(UserDTO newUser) {
        validationData(newUser);
        var user = mapper.map(newUser, User.class);
        return repository.save(user);
    }

    @Override
    public void removeResource(Long id) {
        var user = getResource(id);
        repository.delete(user);
    }

    private void validationData(UserDTO user) {
        var obj = repository.findByEmail(user.getEmail());
        if (obj.isPresent() && !obj.get().getId().equals(user.getId())) {
            throw new FieldExistingException("Email já cadastrado");
        }
    }

}
