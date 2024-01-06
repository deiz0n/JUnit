package com.deiz0n.junit.controllers.exceptions;

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
        ResponseEntity<?> response = controller
                .entityNotFound(
                        new ResourceNotFoundException("User não encontrado"), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandartError.class, response.getBody().getClass());
    }

    @Test
    void fieldExisting() {
    }
}