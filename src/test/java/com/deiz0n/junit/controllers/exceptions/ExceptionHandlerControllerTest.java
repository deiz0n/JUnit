package com.deiz0n.junit.controllers.exceptions;

import com.deiz0n.junit.services.exceptions.FieldExistingException;
import com.deiz0n.junit.services.exceptions.ResourceNotFoundException;
import com.deiz0n.junit.services.exceptions.StandartError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExceptionHandlerControllerTest {

    @InjectMocks
    private ExceptionHandlerController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenEntityNotFoundThenReturnNotFound() {
        ResponseEntity<StandartError> response = controller
                .entityNotFound(
                        new ResourceNotFoundException("User não encontrado"), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandartError.class, response.getBody().getClass());
        assertEquals("User não encontrado", response.getBody().getMessage());
        assertEquals(404, response.getBody().getStatus().value());
    }

    @Test
    void whenFieldExistingThenReturnBadRequest() {
        ResponseEntity<StandartError> response = controller
                .fieldExisting(new FieldExistingException("Email já cadastrado")
                        , new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandartError.class, response.getBody().getClass());
        assertEquals("Email já cadastrado", response.getBody().getMessage());
        assertEquals(400, response.getBody().getStatus().value());
    }
}