package net.viralpatel.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Estudent" , code = HttpStatus.INTERNAL_SERVER_ERROR)  // 404
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super("Error nano : " + message);
    }
}
