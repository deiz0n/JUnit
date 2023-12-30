package com.deiz0n.junit.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter @Setter
@AllArgsConstructor
public class StandartError {

    private Instant timestamp;
    private HttpStatus status;
    private String message;
    private String path;

}
