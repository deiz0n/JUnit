package com.deiz0n.junit.controllers;

import com.deiz0n.junit.domain.User;
import com.deiz0n.junit.domain.dto.UserDTO;
import com.deiz0n.junit.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {

    public static final Integer ID = 1;
    public static final String NOME = "Eduardo";
    public static final String EMAIL = "eduardo@gmail.com";
    public static final String SENHA = "123";
    public static final Integer INDEX = 0;


    @InjectMocks
    private UserController controller;
    @Mock
    private ModelMapper mapper;
    @Mock
    private UserService service;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        starUser();
    }

    @Test
    void whenGetUserTheReturnUserDTO() {
        when(service.getResource(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = controller.getUser(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(SENHA, response.getBody().getSenha());
    }

    @Test
    void whenGetUsersThenReturnListOfUserDTO() {
        when(service.getResources()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = controller.getUsers();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(SENHA, response.getBody().get(INDEX).getSenha());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void starUser() {
        user = new User(
                ID,
                NOME,
                EMAIL,
                SENHA
        );
        userDTO = new UserDTO(
                ID,
                NOME,
                EMAIL,
                SENHA
        );
    }

}